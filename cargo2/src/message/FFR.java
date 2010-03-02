/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package message;

import java.util.ArrayList;
import java.util.List;

import message.util.StringSplitter;
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
public class FFR extends AbstractMessageProcessor {
    Awb awb = new Awb();
    List<AwbShc> lstShc = new ArrayList<AwbShc>();
    List<AwbFlt> lstFlt = new ArrayList<AwbFlt>();
    List<AwbUld> lstUld = new ArrayList<AwbUld>();
    List<AwbDimension> lstDim = new ArrayList<AwbDimension>();
    Participant agent, consignee, shipper; 
    
    public FFR(String message) {
        super(message);
    }

    @Override
    public void processMessage() {
        String mtype = lineSplitter.getNextLine();
        if (mtype.startsWith("FFR")) {
            putReceivedMessage();
            //process the file
            processConsignmentDetail(awb, lstShc, lineSplitter.getNextLine());
            processFlightDetail(awb, lstFlt, "FFR");
            processULD(awb, lineSplitter.getLine("ULD"));
            processSSR(awb, lineSplitter.getLine("SSR"));
            processOSI(awb, lineSplitter.getLine("OSI"));
            processBookingReference(awb);
            processDimension(awb, lineSplitter.getLine("DIM"));
            processProductInfo();
            shipper = processShipper(awb, lineSplitter.getLine("SHP"));
            consignee = processConsignee(awb, lineSplitter.getLine("CNE"));
            processSRI(awb, lineSplitter.getLine("SRI"));  
            processAgent(awb, lineSplitter.getLine("CUS"));
            
            awb.save();
            for (AwbShc t : lstShc) {
                t.awbSeq = awb.seq;
                t.save();
            }
            for (AwbDimension t : lstDim) {
                t.awbSeq = awb.seq;
                t.save();
            }
            for (AwbFlt t : lstFlt) {
                t.awbSeq = awb.seq;
                t.save();
            }
            for (AwbUld t : lstUld) {
                t.awbSeq = awb.seq;
                t.save();
            }
        }
    }

    public static void main(String[] args) {
        String str = 
                "FFR/6" +
                "\n125-12345675FRAPHL/T10K900DG9/BOOKS" +
                "\n/VAL" +
                "\nBA173/19MAR/LHRJFK/NA" +
                "\nULD/1/P1P1234AF-M/K1000" +
                "\nSSR/DO NOT DROP" +
                "\nOSI/MSP PHONE MR. SMITH AT 123-4567" +
                "\nREF/PARFMAF/4321" +
                "\nDIM/K1000/CMT100-200-100/2" +
                "\nCUS//2347998" +
                "\n/EXPRESS FORWARDING" +
                "\n/FRANKFURT" +
                "\nSRI/ABCD-12345";
        new FFR(str).processMessage();
    }
    
    private void processProductInfo() {
        String pid = lineSplitter.getLine("PID");
        StringSplitter ss = lineSplitter.getStringSplitter(pid);
        ss.getNextSplitNonEmpty();
        awb.serviceCode = ss.getNextSplitNonEmpty();
        awb.rateClassCode = ss.getNextSplitNonEmpty();
        awb.uldRateClassType = ss.getNextSplitNonEmpty();
    }
}
