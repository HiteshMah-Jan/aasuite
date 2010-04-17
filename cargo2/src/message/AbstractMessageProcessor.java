/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import message.util.LineSplitter;
import message.util.SplitterUtil;
import message.util.StringSplitter;
import message.util.SubstringUtil;
import springbean.AAAConfig;
import util.BeanUtil;
import util.DBClient;
import util.DataUtil;
import util.DateUtil;
import bean.Awb;
import bean.CargoMessageInbox;
import bean.Participant;
import bean.awb.AwbDimension;
import bean.awb.AwbFlt;
import bean.awb.AwbParticipant;
import bean.awb.AwbShc;
import bean.awb.AwbUld;

/**
 *
 * @author Charliemagne Mark
 */
public abstract class AbstractMessageProcessor {
    protected LineSplitter lineSplitter;
    protected String[] clean = {"\r", "/"};
    protected CargoMessageInbox inbox;
    
    public AbstractMessageProcessor(String message) {
        AAAConfig.getServerInstance();
        lineSplitter = new LineSplitter(message, clean);
    }
    
    public abstract void processMessage();  

    protected void putReceivedMessage() {
        LineSplitter l = new LineSplitter(lineSplitter.toString(), clean);
        String mtype = l.getNextLine();
        inbox = new CargoMessageInbox();
        inbox.messageType = mtype.substring(0,3);
        inbox.messageContent = l.toString();
        inbox.processDate = new Date();
        inbox.transmitCount = l.toString().length();
        inbox.save();
    }
    
    protected void returnException(String ex, String toAddress) {
        inbox.exceptionText = ex;
        inbox.save();

        //this will create and return FNA message
        StringBuffer sb = new StringBuffer();
        sb.append("FNA/1\n").append("ACK/").append(ex).append("\n").append(lineSplitter.toString());
        sendMessage(toAddress, sb.toString());
    }
    
    protected void acknowledgeMessage(String ex, String toAddress) {
        //this will return FMA
        inbox.acknowledge = true;
        inbox.save();
        StringBuffer sb = new StringBuffer();
        sb.append("FMA/1\n").append("ACK/").append(ex).append("\n").append(lineSplitter.toString());
        sendMessage(toAddress, sb.toString());
    }
    
    protected void sendMessage(String toAddress, String message) {
        //send message
    }
    protected Date getDate(String str) {
        return DateUtil.readDateNoYear(str);
    }
    
    protected final String clean(String str) {
        return SplitterUtil.clean(str, clean);
    }

    protected void processBookingReference(Awb awb) {
        String ref = lineSplitter.getLine("REF");
        StringSplitter ss = lineSplitter.getStringSplitter(ref);
        ss.getNextSplitNonEmpty();
        awb.reqOfficeAddress = ss.getNextSplitNonEmpty();
        awb.bookRef = ss.getNextSplitNonEmpty();
        for (int i = 0; i < 3; i++) {
            String s = ss.getNextSplitNonEmpty();
            if (s!=null && !s.isEmpty()) {
                awb.reqParticipant += s;
            }
        }
    }
    
    protected void processConsignmentDetail(Awb awb, List<AwbShc> lstShc, String consignment) {
        StringSplitter ss = lineSplitter.getStringSplitter(consignment);

        SubstringUtil tawb = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
        awb.prefix = tawb.getNextSubstring(3);
        tawb.getNextSubstring(1);
        awb.serial = tawb.getNextSubstring(8);
        Awb b = (Awb) DBClient.getFirstRecord("SELECT a FROM Awb a WHERE a.prefix='"+awb.prefix+"' AND a.serial='"+awb.serial+"'");
        if (b!=null) {
        	BeanUtil.copyBean(b, awb);
        }
        awb.origin = tawb.getNextSubstring(3);
        awb.destination = tawb.getNextSubstring(3);

        SubstringUtil tqty = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
        awb.descCode = tqty.getNextSubstring(1); 
        awb.pieces = tqty.getNextInt();
        awb.kgLb = tqty.getNextSubstring(1);
        awb.weight = tqty.getNextDouble();
        //check if volume detail, density group, total consignment
        for (int i = 0; i < 3; i++) {
            String nonNumber = tqty.getNextNonNumeric();
            if ("DG".equals(nonNumber)) {
                awb.densityGroup = tqty.getNextInt();
            }
            else if (nonNumber.length()==1) {
                awb.totConsignmentPieces = tqty.getNextInt();
            }
            else {
                awb.volumeCode = nonNumber;
                awb.volume = tqty.getNextDouble();
            }
        }        
        awb.natureOfGoods = ss.getNextSplitNonEmpty();
        for (int i = 0; i < 9 && lstShc!=null; i++) {
            String shc = ss.getNextSplitNonEmpty();
            if (shc!=null && !shc.isEmpty()) {
                AwbShc s = new AwbShc();
                s.shcCode = shc;  
                lstShc.add(s); 
            }
            if (i==0) awb.shc1 = shc;
            if (i==1) awb.shc2 = shc;
        }
    }

    protected void processFlightDetail(Awb awb, List<AwbFlt> lstFlt, String message) {
        for (int i = 2; i < 7; i++) {
            String flight = lineSplitter.getLine(i);
            StringSplitter ss = lineSplitter.getStringSplitter(flight);
            //note, for this to be flight detail it must not have / at the 3rd char
            if (flight!=null && flight.length()>15 && flight.charAt(3)!='/') {
                //process flight detail
                AwbFlt bk = new AwbFlt();
                SubstringUtil tflt = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
                bk.carrier = tflt.getNextNonNumeric();
                bk.flightNumber = tflt.getNextInt()+"";
                
                String tdate = ss.getNextSplitNonEmpty();
                bk.flightDate = getDate(tdate);
                
                SubstringUtil tairports = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
                bk.origin = tairports.getNextSubstring(3);
                bk.destination = tairports.getNextSubstring(3);
                
                String talloc = ss.getNextSplitNonEmpty();
                bk.status = talloc;
                
                String tallotmentId = ss.getNextSplitNonEmpty();
                bk.allotmentIdentification = tallotmentId;
                bk.status = message;
                lstFlt.add(bk);
            }
            else {
                break;
            }
        }
    }

    protected void processOSI(Awb awb, String osi) {
        StringSplitter ss = lineSplitter.getStringSplitter(osi);
        ss.getNextSplitNonEmpty();  //contains OSI
        awb.otherServiceInformation = ss.getNextSplitNonEmpty() + "\n" + ss.getNextSplitNonEmpty();
    }

    protected void processProductInfo(Awb awb) {
        String pid = lineSplitter.getLine("PID");
        StringSplitter ss = lineSplitter.getStringSplitter(pid);
        ss.getNextSplitNonEmpty();
        awb.serviceCode = ss.getNextSplitNonEmpty();
        awb.rateClassCode = ss.getNextSplitNonEmpty();
        awb.uldRateClassType = ss.getNextSplitNonEmpty();
    }

    protected void processSRI(Awb awb, String sri) {
        StringSplitter ss = lineSplitter.getStringSplitter(sri);
        ss.getNextSplitNonEmpty();  //contains SSR
        awb.shipmentReferenceInformation = ss.getNextSplitNonEmpty() + "\n" + ss.getNextSplitNonEmpty() + "\n" + ss.getNextSplitNonEmpty();
    }

    protected void processSSR(Awb awb, String ssr) {
        StringSplitter ss = lineSplitter.getStringSplitter(ssr);
        ss.getNextSplitNonEmpty();  //contains SSR
        awb.specialServiceRequest = ss.getNextSplitNonEmpty() + "\n" + ss.getNextSplitNonEmpty();
    }

    protected List<AwbUld> processULD(Awb awb, String uld) {
    	if (uld==null || uld.trim().isEmpty()) {
    		return null;
    	}
        List<AwbUld> lstUld = new ArrayList<AwbUld>();
        StringSplitter ss = lineSplitter.getStringSplitter(uld);
        ss.getNextSplitNonEmpty();  //contains ULD
        String uldCount = ss.getNextSplitNonEmpty();
        awb.uldCount = DataUtil.getIntValue(uldCount);
        for (int i = 0; i < 3; i++) {
        	try {
                AwbUld auld = new AwbUld();
                String tuldId = ss.getNextSplitNonEmpty();
                if (tuldId==null || tuldId.trim().isEmpty()) break;
                
                String tuldNum = SplitterUtil.getSplit(tuldId, "-", 0);
                String tuldInd = SplitterUtil.getSplit(tuldId, "-", 1);
                auld.uld = tuldNum;
                auld.uldIndicator = tuldInd;
                
                SubstringUtil tuldContent = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
                auld.kgLb = tuldContent.getNextNonNumeric();
                auld.weight = tuldContent.getNextDouble();
                lstUld.add(auld);
        	}
        	catch (Exception e) {
        		e.printStackTrace();
        	}
        }
        return lstUld;
    }
    protected List<AwbDimension> processDimension(Awb awb, String dim) {
        List<AwbDimension> lstDim = new ArrayList<AwbDimension>();
        StringSplitter ss = lineSplitter.getStringSplitter(dim);
        ss.getNextSplitNonEmpty();  //contains DIM
        for (int i = 0; i < 20; i++) {
            AwbDimension odim = new AwbDimension(); 
            SubstringUtil tweight = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            odim.kgLb = tweight.getNextNonNumeric();
            odim.weight = tweight.getNextDouble();
            if (odim.kgLb==null || odim.kgLb.isEmpty() || odim.weight==0) continue;

            SubstringUtil tdimdet = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            tdimdet.getNextNonNumeric();
            odim.length = tdimdet.getNextInt();
            tdimdet.getNextNonNumeric();
            odim.width = tdimdet.getNextInt();
            tdimdet.getNextNonNumeric();
            odim.height = tdimdet.getNextInt();
            
            SubstringUtil tpieces = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            odim.pieces = tpieces.getNextInt();
            lstDim.add(odim);
        }
        return lstDim;
    }

    protected Participant processConsignee(Awb awb, String cne) {
    	if (cne==null || cne.trim().length()<4) {
    		return null;
    	}
        String acctDetail = SplitterUtil.getLineFrom(cne, 0);
        String name = clean(SplitterUtil.getLineFrom(cne, 1));
        String street = SplitterUtil.getLineFrom(cne, 2);
        String codeLoc = SplitterUtil.getLineFrom(cne, 3);
        return procConsignee(awb, acctDetail, name, street, codeLoc);
    }

    protected Participant processHAWBConsignee(Awb awb, String cne) {
        String acctDetail = null;
        String name = clean(SplitterUtil.getLineFrom(cne, 0));
        String street = SplitterUtil.getLineFrom(cne, 1);
        String codeLoc = SplitterUtil.getLineFrom(cne, 2);
        return procConsignee(awb, acctDetail, name, street, codeLoc);
    }

    private Participant procConsignee(Awb awb, String acctDet, String name, String tstreet, String code) {
        StringSplitter street = lineSplitter.getStringSplitter(tstreet);
        StringSplitter codeLoc = lineSplitter.getStringSplitter(code);

        if (acctDet!=null && !acctDet.trim().isEmpty()) {
            StringSplitter acctDetail = lineSplitter.getStringSplitter(acctDet);
            acctDetail.getNextSplitNonEmpty();
            awb.consignee = acctDetail.getNextSplitNonEmpty();
            if (awb.consignee==null || awb.consignee.trim().isEmpty()) return null;
        }
        
        //this needs to create the shipper if does not exist in the system
        Participant consignee = (Participant) DBClient.getFirstRecord("SELECT a FROM Participant a WHERE a.code='"+awb.consignee+"'");
        if (consignee==null) {
            consignee = new Participant();
            consignee.code = awb.consignee;
            consignee.participantType = "S";

            consignee.name = name;
            consignee.address = street.getNextSplitNonEmpty();
            consignee.place = street.getNextSplitNonEmpty();
            consignee.state = street.getNextSplitNonEmpty();
            consignee.country = codeLoc.getNextSplitNonEmpty();
            consignee.zipCode = codeLoc.getNextSplitNonEmpty();
            consignee.contactPerson = codeLoc.getNextSplitNonEmpty();
            consignee.contactNumber = codeLoc.getNextSplitNonEmpty();
            consignee.save();
        }
        else {
            consignee.name = name;
            consignee.address = street.getNextSplitNonEmpty();
            consignee.place = street.getNextSplitNonEmpty();
            consignee.state = street.getNextSplitNonEmpty();
            consignee.country = codeLoc.getNextSplitNonEmpty();
            consignee.zipCode = codeLoc.getNextSplitNonEmpty();
            consignee.contactPerson = codeLoc.getNextSplitNonEmpty();
            consignee.contactNumber = codeLoc.getNextSplitNonEmpty();
        }
        awb.contactPerson = consignee.contactPerson;
        awb.contactPhone = consignee.contactNumber;
        return consignee;
    }
    
    protected Participant processShipper(Awb awb, String shp) {
    	if (shp==null || shp.trim().length()<4) {
    		return null;
    	}
        String acctDetail = SplitterUtil.getLineFrom(shp, 0);
        String name = clean(SplitterUtil.getLineFrom(shp, 1));
        String street = SplitterUtil.getLineFrom(shp, 2);
        String codeLoc = SplitterUtil.getLineFrom(shp, 3);
        return procShipper(awb, acctDetail, name, street, codeLoc);
    }

    protected Participant processHAWBShipper(Awb awb, String shp) {
        String acctDetail = null;
        String name = clean(SplitterUtil.getLineFrom(shp, 0));
        String street = SplitterUtil.getLineFrom(shp, 1);
        String codeLoc = SplitterUtil.getLineFrom(shp, 2);
        return procShipper(awb, acctDetail, name, street, codeLoc);
    }

    private Participant procShipper(Awb awb, String acctDet, String name, String tstreet, String code) {
        StringSplitter street = lineSplitter.getStringSplitter(tstreet);
        StringSplitter codeLoc = lineSplitter.getStringSplitter(code);

        if (acctDet!=null && !acctDet.trim().isEmpty()) {
            StringSplitter acctDetail = lineSplitter.getStringSplitter(acctDet);
            acctDetail.getNextSplitNonEmpty();
            awb.shipper = acctDetail.getNextSplitNonEmpty();
            if (awb.shipper==null || awb.shipper.trim().isEmpty()) return null;
        }
        
        //this needs to create the shipper if does not exist in the system
        Participant shipper = (Participant) DBClient.getFirstRecord("SELECT a FROM Participant a WHERE a.code='"+awb.shipper+"'");
        if (shipper==null) {
            shipper = new Participant();
            shipper.code = awb.shipper;
            shipper.participantType = "S";

            shipper.name = name;
            shipper.address = street.getNextSplitNonEmpty();
            shipper.place = street.getNextSplitNonEmpty();
            shipper.state = street.getNextSplitNonEmpty();
            shipper.country = codeLoc.getNextSplitNonEmpty();
            shipper.zipCode = codeLoc.getNextSplitNonEmpty();
            shipper.contactPerson = codeLoc.getNextSplitNonEmpty();
            shipper.contactNumber = codeLoc.getNextSplitNonEmpty();
            shipper.save();
        }
        else {
            shipper.name = name;
            shipper.address = street.getNextSplitNonEmpty();
            shipper.place = street.getNextSplitNonEmpty();
            shipper.state = street.getNextSplitNonEmpty();
            shipper.country = codeLoc.getNextSplitNonEmpty();
            shipper.zipCode = codeLoc.getNextSplitNonEmpty();
            shipper.contactPerson = codeLoc.getNextSplitNonEmpty();
            shipper.contactNumber = codeLoc.getNextSplitNonEmpty();
        }
        awb.contactPerson = shipper.contactPerson;
        awb.contactPhone = shipper.contactNumber;
        return shipper;
    }

    protected void processCVD(Awb awb, String str) {
        StringSplitter ss = lineSplitter.getStringSplitter(str);
        ss.getNextSplitNonEmpty();
//        awb.currencyCode = ss.getNextSplit();
//        awb.originChargeCode = ss.getNextSplit();
//        awb.declaredValueCarriage = ss.getNextSplitDouble();
//        awb.declaredValueCustoms = ss.getNextSplitDouble();
//        awb.amountInsurance = ss.getNextSplitDouble();
    }
    
    protected Participant processAgent(Awb awb, String cus) {
        StringSplitter ss = lineSplitter.getStringSplitter(cus);
        ss.getNextSplitNonEmpty();
        awb.agent = ss.getNextSplit();
        if (awb.agent==null || awb.agent.trim().isEmpty()) {
            awb.agent = ss.getNextSplit();
        }
        
        //this needs to create the shipper if does not exist in the system
        Participant agent = (Participant) DBClient.getFirstRecord("SELECT a FROM Participant a WHERE a.code='"+awb.agent+"'");
        if (agent==null) {
            agent = new Participant();
            agent.code = awb.agent;
            agent.participantType = "A";

            agent.name = clean(SplitterUtil.getLineFrom(cus, 1));
            agent.place = clean(SplitterUtil.getLineFrom(cus, 2));
            agent.save();
        }
        else {
            agent.name = clean(SplitterUtil.getLineFrom(cus, 1));
            agent.place = clean(SplitterUtil.getLineFrom(cus, 2));
        }
        return agent;
    }

    protected void processAlsoNotify(Awb awb, String shp) {
        String name = clean(SplitterUtil.getLineFrom(shp, 1));
        String tstreet = SplitterUtil.getLineFrom(shp, 2);
        String code = SplitterUtil.getLineFrom(shp, 3);
        
        StringSplitter street = lineSplitter.getStringSplitter(tstreet);
        StringSplitter codeLoc = lineSplitter.getStringSplitter(code);

        //this needs to create the shipper if does not exist in the system
        AwbParticipant shipper = new AwbParticipant();
        shipper.awbSeq = awb.seq;
//        shipper.name = name;
//        shipper.streetAddress = street.getNextSplitNonEmpty();
//        shipper.place = street.getNextSplitNonEmpty();
//        shipper.state = street.getNextSplitNonEmpty();
//        shipper.country = codeLoc.getNextSplitNonEmpty();
//        shipper.postCode = codeLoc.getNextSplitNonEmpty();
//        shipper.contactId = codeLoc.getNextSplitNonEmpty();
//        shipper.contactPhone = codeLoc.getNextSplitNonEmpty();
        shipper.save();
    }
    
    protected void processSCI(Awb awb, String str) {
        StringSplitter ss = lineSplitter.getStringSplitter(str);
        ss.getNextSplitNonEmpty();  //contains OSI
        awb.remarks = ss.getNextSplitNonEmpty() + "\n" + ss.getNextSplitNonEmpty();
    }

}
