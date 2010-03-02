/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package message;

import bean.Awb;
import java.util.ArrayList;
import java.util.List;
import message.AbstractMessageProcessor;
import message.util.StringSplitter;
import message.util.SubstringUtil;
import util.DBClient;

/**
 *
 * @author Charliemagne Mark
 */
public class FHL extends AbstractMessageProcessor {
    Awb master = new Awb();
    List lstHAWB = new ArrayList<Awb>();
    
    public static void main(String[] args) {
        String message = 
                "FHL/3" +
                "\nMBI/618-12345675SINJFK/T7K1000" +
                "\nHBS/AEI12345679/SINJFK/3/K300/COMPUTER PARTS" +
                "\nTXT/MODEL 4 KEYBOARDS OTHER ASSORTED PARTS" +
                "\nHBS/AEI12345680/SINJFK/3/K300/COMPUTER PARTS" +
                "\nTXT/MODEL 5 SCREENS AND OTHER ASSORTED PARTS" +
                "\nHBS/AEI12345678/SINJFK/1/K400/4/COMPUTER PARTS" +
                "\nTXT/MODEL 3 MEMORY BOARDS AND OTHER ASSORTED PARTS" +
                "\nSHP/AIR EXPRESS INTL" +
                "\n/CARGO COMPLEX BULDG B" +
                "\n/AIRLINES ROAD" +
                "\n/SG/1738/FX/651234567" +
                "\nCNE/AIE EXPRESS INTL" +
                "\n/CENTRAL STREET 13" +
                "\n/JAMAICA/NY" +
                "\n/US/22330/TE/171812344566" +
                "\nCVD/SGD/PP/NVD/NCV/XXX";
        new FHL(message).processMessage();
    }
    
    public FHL(String message) {
        super(message);
    }

    @Override
    public void processMessage() {
        String mtype = lineSplitter.getNextLine();
        if (mtype.startsWith("FHL")) {
            processMaster(master, lineSplitter.getNextLine());
            Awb hawb = null;
            while (lineSplitter.hasNextLine()) {
                String str = lineSplitter.getNextLine();
                if (str==null || str.isEmpty()) {
                    continue;
                }
                else if (str.startsWith("HBS")) {
                    hawb = new Awb();
                    lstHAWB.add(hawb);
                    processHBS(master, hawb, str);
                }
                else if (str.startsWith("TXT")) {
                    processTXT(hawb, str);
                }
                else if (str.startsWith("SHP")) {
                    processHAWBShipper(hawb, str);
                }
                else if (str.startsWith("CNE")) {
                    processHAWBConsignee(hawb, str);
                }
                else if (str.startsWith("CVD")) {
                    processCVD(hawb, str);
                }
                else if (str.startsWith("HTS")) {
                    processHTS(hawb, str);
                }
            }
            DBClient.persistBean(lstHAWB);
            System.out.println("FIN");
        }
    }


    private void processHBS(Awb mawb, Awb hawb, String str) {
        StringSplitter ss = lineSplitter.getStringSplitter(str);
        ss.getNextSplitNonEmpty();
        
        hawb.serial = ss.getNextSplitNonEmpty();
        Awb b = (Awb) DBClient.getFirstRecord("SELECT a FROM Awb a WHERE a.masterAwbSeq="+mawb.seq+" AND a.serial='"+hawb.serial+"'");
        if (b!=null) {
            hawb = b;
        }
        SubstringUtil tairports = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
        hawb.origin = tairports.getNextSubstring(3);
        hawb.destination = tairports.getNextSubstring(3);

        hawb.descCode = mawb.descCode; 
        hawb.pieces = (int) ss.getNextSplitDouble();
        SubstringUtil tweight = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
        hawb.kgLb = tweight.getNextSubstring(1);
        hawb.weight = tweight.getNextDouble();
        hawb.slac = ss.getNextSplit();
        hawb.natureOfGoods = ss.getNextSplit();
        SubstringUtil tshc = lineSplitter.getSubstringUtil(ss.getNextSplit());
        hawb.hshc1 = tshc.getNextSubstring(3);
        hawb.hshc2 = tshc.getNextSubstring(3);
    }

    private void processHTS(Awb hawb, String str) {
        StringSplitter ss = lineSplitter.getStringSplitter(str);
        ss.getNextSplitNonEmpty();  //contains TXT
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 9; i++) {
            String string = ss.getNextSplitNonEmpty();
            sb.append(string).append(" ");
        }
        hawb.harmonizedCode = sb.toString();
    }

    private void processMaster(Awb mawb, String str) {
        StringSplitter ss = lineSplitter.getStringSplitter(str);
        ss.getNextSplitNonEmpty();
        
        SubstringUtil tawb = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
        mawb.prefix = tawb.getNextSubstring(3);
        tawb.getNextSubstring(1);
        mawb.serial = tawb.getNextSubstring(8);
        Awb b = (Awb) DBClient.getFirstRecord("SELECT a FROM Awb a WHERE a.prefix='"+mawb.prefix+"' AND a.serial='"+mawb.serial+"'");
        if (b!=null) {
            mawb = b;
        }
        mawb.origin = tawb.getNextSubstring(3);
        mawb.destination = tawb.getNextSubstring(3);

        SubstringUtil tqty = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
        mawb.descCode = tqty.getNextSubstring(1); 
        mawb.pieces = tqty.getNextInt();
        mawb.kgLb = tqty.getNextSubstring(1);
        mawb.weight = tqty.getNextDouble();
        mawb.save();
    }

    private void processTXT(Awb hawb, String str) {
        StringSplitter ss = lineSplitter.getStringSplitter(str);
        ss.getNextSplitNonEmpty();  //contains TXT
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 9; i++) {
            String string = ss.getNextSplitNonEmpty();
            sb.append(string).append("\n");
        }
        hawb.remarks = sb.toString();
    }

}
