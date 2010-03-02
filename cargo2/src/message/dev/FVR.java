/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package message.dev;

import bean.Flight;
import message.AbstractMessageProcessor;
import message.util.StringSplitter;
import message.util.SubstringUtil;

/**
 *
 * @author Charliemagne Mark
 */
public class FVR extends AbstractMessageProcessor {
    public static void main(String[] args) {
        String message = 
                "FVR/1" +
                "\nSAR/YMXAMS/26MAR/KL678";
        new FVR(message).processMessage();
    }
    
    public FVR(String message) {
        super(message);
    }

    @Override
    public void processMessage() {
        String mtype = lineSplitter.getNextLine();
        if (mtype.startsWith("FVR")) {
            putReceivedMessage();
            Flight flt = new Flight();
            processFlightDetail(flt, lineSplitter.getNextLine());
            System.out.println("FIN");
        }
    }
    
    private void processFlightDetail(Flight bk, String flight) {
        StringSplitter ss = lineSplitter.getStringSplitter(flight);
        ss.getNextSplitNonEmpty();
        SubstringUtil tmp = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
        bk.origin = tmp.getNextSubstring(3);
        bk.destination = tmp.getNextSubstring(3);
        
        bk.flightDate = ss.getNextSplitDate();

        SubstringUtil tflt = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
        bk.carrier = tflt.getNextNonNumeric();
        bk.flightNumber = tflt.getNextInt() + "";
        
        bk.departureTime = ss.getNextSplit();
        
        tflt = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
        bk.arrivalDate = getDate(tflt.getNextSubstring(5));
        bk.arrivalTime = tflt.getNextSubstring(4);
        
        bk.connectionRestriction = ss.getNextSplit();
        bk.aircraftType = ss.getNextSplit();
        bk.serviceCode = ss.getNextSplit();
        
        tflt = lineSplitter.getSubstringUtil(ss.getNextSplit());
        tflt.getNextNonNumeric();
        bk.availWeight = tflt.getNextDouble();
        tflt = lineSplitter.getSubstringUtil(ss.getNextSplit());
        tflt.getNextNonNumeric();
        bk.availVolume = tflt.getNextDouble();
    }
}
