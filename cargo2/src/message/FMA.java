/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package message;

/**
 *
 * @author Charliemagne Mark
 */
public class FMA extends AbstractMessageProcessor {
    public static void main(String[] args) {
        String message = 
                "FMA" +
                "\nACK/FFR RCVD 30 JUN 1009 — WILL CONFIRM HEA BOOKING ASAP" +
                "\nFFR/4" +
                "\n014-26488125YMXGVA/T1K950/COMPUTER";
        new FMA(message).processMessage();
    }
    
    public FMA(String message) {
        super(message);
    }

    @Override
    public void processMessage() {
        String mtype = lineSplitter.getNextLine();
        if (mtype.startsWith("FMA")) {
            putReceivedMessage();
        }
    }

}
