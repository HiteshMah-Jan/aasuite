/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package message;

import bean.Awb;
import bean.Flight;
import message.AbstractMessageProcessor;
import message.util.StringSplitter;
import message.util.SubstringUtil;
import util.DBClient;
import util.DateUtil;

/**
 *
 * @author Charliemagne Mark
 */
public class FSR extends AbstractMessageProcessor {
    private Awb awb = new Awb();
    
    public static void main(String[] args) {
        String message = 
                "FSR" +
                "\n057-12345675CDGLAX/T10K500/BOOKS" +
                "\nAF099/20MAR/CDGJFK" +
                "\nAA1234/22MAR/JFKLAX" +
                "\nOSI/CONSIGNEE IS LOOKING FOR THE TENTH PIECE";
        new FSR(message).processMessage();
    }
    
    public FSR(String message) {
        super(message);
    }

    @Override
    public void processMessage() {
        String mtype = lineSplitter.getNextLine();
        if (mtype.startsWith("FSR")) {
            processConsignmentDetail(awb, null, lineSplitter.getNextLine());
            while (lineSplitter.hasNextLine()) {
                String str = lineSplitter.getNextLine();
                if (str.startsWith("OSI")) {
                    processOSI(awb, str);
                }
                else {
                    Flight f = new Flight();
                    processFlightDetail(f, str);
                }
                
            }
        }
    }

    private void processFlightDetail(Flight bk, String flight) {
        StringSplitter ss = lineSplitter.getStringSplitter(flight);
        SubstringUtil tflt = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
        bk.carrier = tflt.getNextNonNumeric();
        bk.flightNumber = tflt.getNextInt() + "";
        bk.flightDate = ss.getNextSplitDate();

        tflt = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
        bk.origin = tflt.getNextSubstring(3);
        bk.destination = tflt.getNextSubstring(3);
        //check if flight exist
        Flight f = (Flight) DBClient.getFirstRecord("SELECT a FROM Flight a WHERE a.carrier='"+bk.carrier+"' AND a.flightNumber='"+bk.flightNumber+"' AND a.flightDate='"+DateUtil.formatDateToSql(bk.flightDate)+"' AND a.origin='"+bk.origin+"'");
        if (f==null) {
            bk.save();
        }
        else {
            bk.seq = f.seq;
        }
    }

}
