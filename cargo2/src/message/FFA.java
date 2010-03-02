/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package message;

import java.util.ArrayList;
import java.util.List;

import message.util.StringSplitter;
import message.util.SubstringUtil;
import util.BeanUtil;
import util.DBClient;

import bean.Awb;
import bean.Participant;
import bean.awb.AwbDimension;
import bean.awb.AwbFlt;
import bean.awb.AwbShc;
import bean.awb.AwbUld;

/**
 *
 * @author Charliemagne Mark
 */
public class FFA extends AbstractMessageProcessor {
    Awb awb = new Awb();
    List<AwbShc> lstShc = new ArrayList<AwbShc>();
    List<AwbFlt> lstFlt = new ArrayList<AwbFlt>();
    List<AwbUld> lstUld = new ArrayList<AwbUld>();
    List<AwbDimension> lstDim = new ArrayList<AwbDimension>();
    Participant agent, consignee, shipper; 
    
    public FFA(String message) {
        super(message);
    }

    public static void main(String[] args) {
        String message = 
                "FFA/4" +
                "\n020-12345675FRAJFK/T20K800" +
                "\nLH404/02JUN/FRAJFK/KK" +
                "\nSSR/SPECIAL SERVICE INFORMATION" +
                "\nOSI/OTHER SERVICE INFORMATION" +
                "\nREF/FRAGDLH" +
                "\nSRI/LH8520/YNZ01JUN1800/03JUN0700";
        new FFA(message).processMessage();
    }
    
    @Override
    public void processMessage() {
        String mtype = lineSplitter.getNextLine();
        if (mtype.startsWith("FFA")) {
            putReceivedMessage();
            //process the file
            processConsignmentDetail(awb, lstShc, lineSplitter.getNextLine());
            processFlightDetail(awb, lstFlt, "FFA");
            processSSR(awb, lineSplitter.getLine("SSR"));
            processOSI(awb, lineSplitter.getLine("OSI"));
            processBookingReference(awb);
            processSRI(awb, lineSplitter.getLine("SRI"));  
            
            awb.save();
            for (AwbShc t : lstShc) {
                t.awbSeq = awb.seq;
                t.save();
            }
            for (AwbFlt t : lstFlt) {
                t.awbSeq = awb.seq;
                t.save();
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
}
