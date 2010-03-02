/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package message;

import java.util.List;

import message.util.StringSplitter;
import message.util.SubstringUtil;
import util.DBClient;
import util.DateUtil;
import bean.Awb;
import bean.Flight;
import bean.UldNumber;
import bean.awb.AwbFlt;
import bean.reference.TraceStatus;

/**
 *
 * @author Charliemagne Mark
 */
public class FFM extends AbstractMessageProcessor {
    Flight flt = new Flight();
    
    public static void main(String[] args) {
        String message = 
                "FFM/5" +
                "\n1/KL2084/18MAR/HAM" +
                "\nAMS" +
                "\n074-12345632HAMAMS/T4K8MC0.16/VIDEO CASSETTES" +
                "\n074-12345641HAMYYZ/T15K160MC0.27/WATCHES" +
                "\n/YYZKL667/19MAR" +
                "\n074-12345653HAMAMS/T3K41MC0.2/COMP TERMINALS" +
                "\n074-12345653HAMAMS/T1K1MC0.01/NEWSFILM" +
                "\nULD/P1G1234KL" +
                "\n/LISIB785/19MAR" +
                "\n/2" +
                "\n074-12345664HAMLIS/T200K500MC0.53/BALL BEARINGS" +
                "\n074-12345653HAMLIS/T4K10MC0.06/PRESSURE GAUGES" +
                "\n074-12345686TYOLIS/T3K300MC1.36/RADIOS" +
                "\nULD/AVM9243KL" +
                "\n07412345690HAMAMS/T60K900MC2.25/MACHINE PARTS" +
                "\nRTM" +
                "\n074-35775331HAMRTM/T1K72MC0.6/PURSES" +
                "\nOSI/CTC CNE PRIOR TO ARRIVAL" +
                "\n/DUE TO URGENCE OF DISTRIBUTION" +
                "\n074-67534415HAMRTM/T10K50MC0.15/BOOKS" +
                "\nSCI/1234567" +
                "\nULD/AVM9876KL" +
                "\n074-53153155HAMRTM/T100K1200MC3/MAGAZINES" +
                "\nLAST";
        new FFM(message).processMessage();
    }
    
    public FFM(String message) {
        super(message);
    }

    @Override
    public void processMessage() {
        String mtype = lineSplitter.getNextLine();
        if (mtype.startsWith("FFM")) {
            processFlightDetail(flt, lineSplitter.getNextLine());
            String unloadingPoint = null;
            Awb awb = null;
            while (lineSplitter.hasNextLine()) {
                String str = lineSplitter.getNextLine();
                if (str.trim().length()==3 || (str.trim().length()==7 && str.endsWith("/NIL"))) {
                    //this is point of unloading
                    unloadingPoint = str.substring(0, 3);
                    if (flt.destination==null || flt.destination.equals("--") || flt.destination.isEmpty()) {
                    	flt.destination = unloadingPoint;
                    	System.out.println("UNLOADING == "+unloadingPoint);
                    	flt.save();
                    }
                }
                else if (str.startsWith("ULD/")) {
                    UldNumber uld = processULD(str);
                }
                else if (str.charAt(3)=='-') {
                    awb = new Awb();
                    processConsignmentDetail(awb, null, str);
                    processFlightDetail(awb, flt);
                }
                else if (str.startsWith("DIM/")) {
                    processDimension(awb, str);
                }
                else if (str.startsWith("OSI/")) {
                    processOSI(awb, str);
                }
                else if (str.startsWith("SCI/")) {
                    processSCI(awb, str);
                }
            }
        }
    }

    protected void processFlightDetail(Awb awb, Flight f) {
    	if (awb.isEmptyKey()) {
    		awb.save();
    	}
        AwbFlt bk = awb.findFlight(f);
        if (bk==null) {
        	bk = new AwbFlt();
            bk.carrier = f.carrier;
            bk.flightNumber = f.flightNumber;
            bk.flightDate = f.flightDate;
            bk.origin = f.origin;
        }
        bk.destination = f.destination;
        bk.status = TraceStatus.extractStatus("FFM");
        bk.save();
    }

    private void processFlightDetail(Flight bk, String flight) {
        StringSplitter ss = lineSplitter.getStringSplitter(flight);
        ss.getNextSplit();
        SubstringUtil tflt = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
        bk.carrier = tflt.getNextNonNumeric();
        bk.flightNumber = tflt.getNextInt() + "";
        bk.flightDate = ss.getNextSplitDate();
        bk.origin = ss.getNextSplit();
        //check if flight exist
        Flight f = (Flight) DBClient.getFirstRecord("SELECT a FROM Flight a WHERE a.carrier='"+bk.carrier+"' AND a.flightNumber='"+bk.flightNumber+"'");
        if (f==null) {
            bk.save();
        }
        else {
            bk.seq = f.seq;
        }
    }

    private UldNumber processULD(String str) {
        StringSplitter ss = lineSplitter.getStringSplitter(str);
        ss.getNextSplit();
        UldNumber uld = new UldNumber();
        String s = ss.getNextSplit();
        if (s.contains("-")) {
            uld.uld = s.substring(0, s.indexOf("-"));
            uld.loadingIndicator = s.substring(s.indexOf("-"));
        }
        else {
            uld.uld = s;
        }
        uld.remarks = ss.getNextSplit();
        return uld;
    }
}
