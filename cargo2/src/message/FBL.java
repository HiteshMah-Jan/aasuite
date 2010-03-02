/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import bean.Awb;
import bean.Flight;
import bean.awb.AwbFlt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import message.util.StringSplitter;
import message.util.SubstringUtil;
import util.BeanUtil;
import util.DBClient;
import util.DateUtil;

/**
 *
 * @author Charliemagne Mark
 */
public class FBL extends AbstractMessageProcessor {

    Flight flt = new Flight();
    Map<String, List> mapOffload = new HashMap<String, List>();

    public static void main(String[] args) {
        String message =
                "FBL/3" +
                "\n1/KL775/09FEB/ZRH/HB-IHA" +
                "\nLIS/NIL" +
                "\nCCS" +
                "\n074-69123473ZRHCCS/T12K950DG9/BANKNOTES/VAL" +
                "\n074-77184387ZRHMUN/P1K4DG4T12/SPARES" +
                "\n/EMERY" +
                "\n125-77184464ROMCCS/T123K12444MC94.4/CHEM LIQUID/RFL" +
                "\n//SR917/07FEB/AMS/L" +
                "\nCUR" +
                "\n074-88241152ZRHCUR/T1K830MC1.2/MACHINERY/HEAMAG" +
                "\nOSI/CFM THAT SPECIAL TRUCK MUST BE USED TO/MOVE THIS SHIPMENT" +
                "\nLIM" +
                "\n074-22221112ZRHLIM/T12K144DG4/MEDICINES/PER" +
                "\nSSR/KEEP COOL STORE AT MINUS 2 DEGREES" +
                "\nLAST";
        new FBL(message).processMessage();
    }

    public FBL(String message) {
        super(message);
    }

    @Override
    public void processMessage() {
        String mtype = lineSplitter.getNextLine();
        if (mtype.startsWith("FBL")) {
            processFlightDetail(flt, lineSplitter.getNextLine());
            String offload = "";
            AwbFlt aflt = null;
            while (lineSplitter.hasNextLine()) {
                String str = lineSplitter.getNextLine();
                if (str==null || str.isEmpty()) {
                    continue;
                }
                else if (str.endsWith("/NIL") || str.trim().length()==3) {
                    offload = str.substring(0, 3);
                    if (flt.destination==null || flt.destination.equals("--") || flt.destination.isEmpty()) {
                    	flt.destination = offload;
                    	flt.save();
                    }
                }
                else if ("LAST".equals(str) || "CONT".equals(str)) {
                    continue;
                }
                else {
                    if (str.charAt(3)=='-') {
                        aflt = processBookedCargo(flt, offload, str);
                    }
                    else if (str.startsWith("DIM")) {
                        processDimension(aflt.extractAwb(), str);
                    }
                    else if (str.startsWith("ULD")) {
                        processULD(aflt.extractAwb(), str);
                    }
                    else if (str.startsWith("SSR")) {
                        processSSR(aflt.extractAwb(), str);
                    }
                    else if (str.startsWith("OSI")) {
                        processOSI(aflt.extractAwb(), str);
                    }
                    else {
                        processOriginInfo(aflt, str);
                    }
                }
            }
            System.out.println("END FBL");
        }
    }

    private void processFlightDetail(Flight bk, String flight) {
        StringSplitter ss = lineSplitter.getStringSplitter(flight);
        ss.getNextSplitNonEmpty();
        SubstringUtil tflt = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
        bk.carrier = tflt.getNextNonNumeric();
        bk.flightNumber = tflt.getNextInt() + "";
        bk.flightDate = ss.getNextSplitDate();
        bk.origin = ss.getNextSplitNonEmpty();
        bk.aircraftType = ss.getNextSplitNonEmpty();
        //check if flight exist
        Flight f = (Flight) DBClient.getFirstRecord("SELECT a FROM Flight a WHERE a.carrier='"+bk.carrier+"' AND a.flightNumber='"+bk.flightNumber+"' AND a.flightDate='"+DateUtil.formatDateToSql(bk.flightDate)+"' AND a.origin='"+bk.origin+"'");
        if (f==null) {
            bk.destination = "--";
            bk.save();
        }
        else {
            bk.seq = f.seq;
        }
    }

    private AwbFlt processBookedCargo(Flight flt, String offload, String str) {
        List lstOffload = null;
        if (mapOffload.get(offload)==null) {
            lstOffload = new ArrayList();
            mapOffload.put(offload, lstOffload);
        }
        else {
            lstOffload = mapOffload.get(offload);
        }
        AwbFlt f = new AwbFlt();
        f.flightSeq = flt.seq;
        f.carrier = flt.carrier;
        f.flightNumber = flt.flightNumber;
        f.flightDate = flt.flightDate;
        f.arrivalDate = flt.arrivalDate;
        if (f.arrivalDate==null) {
            f.arrivalDate = f.flightDate;
        }
        f.origin = flt.origin;
        f.status = "FBL";
        //get the AWB
        processConsignmentDetail(f, str);
        
        f.save();
        lstOffload.add(f);
        return f;
    }

    protected void processConsignmentDetail(AwbFlt awbFlt, String consignment) {
        Awb awb = new Awb();
        StringSplitter ss = lineSplitter.getStringSplitter(consignment);

        SubstringUtil tawb = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
        awb.prefix = tawb.getNextSubstring(3);
        tawb.getNextSubstring(1);
        awb.serial = tawb.getNextSubstring(8);
        Awb b = (Awb) DBClient.getFirstRecord("SELECT a FROM Awb a WHERE a.prefix='"+awb.prefix+"' AND a.serial='"+awb.serial+"'");
        if (b!=null) {
        	BeanUtil.copyBean(b, awb);
            awbFlt.awbSeq = b.seq;
        }
        String air1 = tawb.getNextSubstring(3);
        String air2 = tawb.getNextSubstring(3);
        if (air2==null || air2.isEmpty()) {
            awb.destination = air1;
        }
        else {
            awb.origin = air1;
            awb.destination = air2;
        }

        SubstringUtil tqty = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
        String descCode = tqty.getNextSubstring(1); 
        int pieces = tqty.getNextInt();
        String kgLb = tqty.getNextSubstring(1);
        double weight = tqty.getNextDouble();
        int densityGroup = 0;
        int totConsignmentPieces = 0;
        String volumeCode = "";
        double volume = 0;
        //check if volume detail, density group, total consignment
        for (int i = 0; i < 3; i++) {
            String nonNumber = tqty.getNextNonNumeric();
            if ("DG".equals(nonNumber)) {
                densityGroup = tqty.getNextInt();
            }
            else if (nonNumber.length()==1) {
                totConsignmentPieces = tqty.getNextInt();
            }
            else {
                volumeCode = nonNumber;
                volume = tqty.getNextDouble();
            }
        }        
        String natureOfGoods = ss.getNextSplitNonEmpty();
        SubstringUtil shc = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
        awbFlt.shc1 = shc.getNextSubstring(3);
        awbFlt.shc2 = shc.getNextSubstring(3); 
        awbFlt.weight = weight;
        awbFlt.volume = volume;
        awbFlt.pieces = pieces;

        if (awb.origin==null || awb.origin.isEmpty()) awb.origin = awbFlt.origin; 
        if (awb.descCode==null || awb.descCode.isEmpty()) awb.descCode = descCode; 
        if (awb.pieces==0) awb.pieces = pieces; 
        if (awb.kgLb==null || awb.kgLb.isEmpty()) awb.kgLb = kgLb; 
        if (awb.weight==0) awb.weight = weight; 
        if (awb.densityGroup==0) awb.densityGroup = densityGroup; 
        if (awb.totConsignmentPieces==0) awb.totConsignmentPieces = totConsignmentPieces; 
        if (awb.volumeCode==null || awb.volumeCode.isEmpty()) awb.volumeCode = volumeCode; 
        if (awb.volume==0) awb.volume = volume; 
        if (awb.natureOfGoods==null || awb.natureOfGoods.isEmpty()) awb.natureOfGoods = natureOfGoods; 

        awb.save();
    }
    
    private void processOriginInfo(AwbFlt aflt, String str) {
        //not used for now
        util.Log.warning("METHOD FBL.processOriginInfo() is not in used for now.");
    }
}
