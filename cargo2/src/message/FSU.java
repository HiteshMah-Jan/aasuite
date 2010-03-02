/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package message;

import bean.Awb;
import bean.awb.AwbFSU;
import message.util.StringSplitter;
import message.util.SubstringUtil;
import util.DateUtil;

/**
 *
 * @author Charliemagne Mark
 */
public class FSU extends AbstractMessageProcessor {
    private Awb awb = new Awb();

    public static void main(String[] args) {
        String message = 
                "FSU/11" +
                "\n077-12345675BOGNCE/T10K350" +
                "\nDLV/03NOV1615/NCE/T10K350/MICLARK";
        new FSU(message).processMessage();
    }
    
    public FSU(String message) {
        super(message);
    }

    @Override
    public void processMessage() {
        String mtype = lineSplitter.getNextLine();
        if (mtype.startsWith(getType())) { 
            processConsignmentDetail(awb, null, lineSplitter.getNextLine());
            awb.save();
            while (lineSplitter.hasNextLine()) {
                String str = lineSplitter.getNextLine();
                if (str==null || str.trim().isEmpty()) continue;
                if (str.startsWith("RCS")) {
                    processRCS(awb, str);
                }
                else if (str.startsWith("RCT")) {
                    processFSU2(awb, str, "RCT");
                }
                else if (str.startsWith("RCF")) {
                    processFSU1(awb, str, "RCF");
                }
                else if (str.startsWith("BKD")) {
                    processFSU1(awb, str, "BKD");
                }
                else if (str.startsWith("MAN")) {
                    processFSU1(awb, str, "MAN");
                }
                else if (str.startsWith("DEP")) {
                    processFSU1(awb, str, "DEP");
                }
                else if (str.startsWith("PRE")) {
                    processFSU1(awb, str, "PRE");
                }
                else if (str.startsWith("TRM")) {
                    processTRM(awb, str);
                }
                else if (str.startsWith("TFD")) {
                    processFSU1(awb, str, "TFD");
                }
                else if (str.startsWith("NFD")) {
                    processFSU2(awb, str, "NFD");
                }
                else if (str.startsWith("AWD")) {
                    processFSU2(awb, str, "AWD");
                }
                else if (str.startsWith("CCD")) {
                    processFSU2(awb, str, "CCD");
                }
                else if (str.startsWith("DLV")) {
                    processFSU2(awb, str, "DLV");
                }
                else if (str.startsWith("DIS")) {
                    processFSU1(awb, str, "DIS");
                }
                else if (str.startsWith("CRC")) {
                    processFSU2(awb, str, "CRC");
                }
                else if (str.startsWith("DDL")) {
                    processFSU2(awb, str, "DDL");
                }
                else if (str.startsWith("TGC")) {
                    processFSU2(awb, str, "TGC");
                }
                else if (str.startsWith("ARR")) {
                    processFSU1(awb, str, "ARR");
                }
                else if (str.startsWith("AWR")) {
                    processFSU1(awb, str, "AWR");
                }
                else if (str.startsWith("ULD")) {
                    processULD(awb, str);
                }
                else if (str.startsWith("OSI")) {
                    processOSI(awb, str);
                }
            }
        }
    }

    protected String getType() {
        return "FSU";
    }
    
    private void processFSU1(Awb awb, String str, String status) {
        StringSplitter ss = lineSplitter.getStringSplitter(str);
        ss.getNextSplit();
        AwbFSU fsu = new AwbFSU();
        fsu.awbSeq = awb.seq;
        fsu.status = status;
        SubstringUtil sub = lineSplitter.getSubstringUtil(ss.getNextSplit());
        fsu.carrier = sub.getNextSubstring(2);
        fsu.flightNumber = sub.getNextSubstring(4);
        
        fsu.fsuDateTime = ss.getNextSplit();
        fsu.flightDate = DateUtil.readDateNoYear(fsu.fsuDateTime);
        fsu.airport = ss.getNextSplit();

        if ("DIS".equals(status)) fsu.subCode = ss.getNextSplit();
        SubstringUtil st = lineSplitter.getSubstringUtil(ss.getNextSplit());
        fsu.descCode = st.getNextNonNumeric();
        fsu.pieces = st.getNextInt();
        fsu.weightCode = st.getNextNonNumeric();
        fsu.weight = st.getNextDouble();
        
        fsu.depInfo = ss.getNextSplit();
        fsu.arrInfo = ss.getNextSplit();
        
        st = lineSplitter.getSubstringUtil(ss.getNextSplit());
        fsu.volumeCode = st.getNextNonNumeric();
        fsu.volume = st.getNextDouble();
        fsu.save();
    }

    private void processFSU2(Awb awb, String str, String status) {
        StringSplitter ss = lineSplitter.getStringSplitter(str);
        ss.getNextSplit();
        AwbFSU fsu = new AwbFSU();
        fsu.awbSeq = awb.seq;
        fsu.status = status;
        fsu.fsuDateTime = ss.getNextSplit();
        fsu.airport = ss.getNextSplit();

        SubstringUtil st = lineSplitter.getSubstringUtil(ss.getNextSplit());
        fsu.descCode = st.getNextNonNumeric();
        fsu.pieces = st.getNextInt();
        fsu.weightCode = st.getNextNonNumeric();
        fsu.weight = st.getNextDouble();
        
        fsu.depInfo = ss.getNextSplit();
        fsu.arrInfo = ss.getNextSplit();
        fsu.save();
    }

    private void processRCS(Awb awb, String str) {
        StringSplitter ss = lineSplitter.getStringSplitter(str);
        ss.getNextSplit();
        AwbFSU fsu = new AwbFSU();
        fsu.awbSeq = awb.seq;
        fsu.status = "RCS";
        fsu.fsuDateTime = ss.getNextSplit();
        fsu.airport = ss.getNextSplit();

        SubstringUtil st = lineSplitter.getSubstringUtil(ss.getNextSplit());
        fsu.descCode = st.getNextNonNumeric();
        fsu.pieces = st.getNextInt();
        fsu.weightCode = st.getNextNonNumeric();
        fsu.weight = st.getNextDouble();
        
        fsu.recivFrom = ss.getNextSplit();

        st = lineSplitter.getSubstringUtil(ss.getNextSplit());
        fsu.volumeCode = st.getNextNonNumeric();
        fsu.volume = st.getNextDouble();
        fsu.save();
    }

    private void processTRM(Awb awb, String str) {
        StringSplitter ss = lineSplitter.getStringSplitter(str);
        ss.getNextSplit();
        AwbFSU fsu = new AwbFSU();
        fsu.awbSeq = awb.seq;
        fsu.status = "TRM";
        fsu.carrier = ss.getNextSplit();
        fsu.airport = ss.getNextSplit();

        SubstringUtil st = lineSplitter.getSubstringUtil(ss.getNextSplit());
        fsu.descCode = st.getNextNonNumeric();
        fsu.pieces = st.getNextInt();
        fsu.weightCode = st.getNextNonNumeric();
        fsu.weight = st.getNextDouble();
        
        fsu.depInfo = ss.getNextSplit();
        fsu.arrInfo = ss.getNextSplit();
        
        st = lineSplitter.getSubstringUtil(ss.getNextSplit());
        fsu.volumeCode = st.getNextNonNumeric();
        fsu.volume = st.getNextDouble();
        fsu.save();
    }

}
