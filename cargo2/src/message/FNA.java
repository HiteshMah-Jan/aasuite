/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package message;

import message.AbstractMessageProcessor;

/**
 *
 * @author Charliemagne Mark
 */
public class FNA extends AbstractMessageProcessor {
    public static void main(String[] args) {
        String message = 
                "FNA/1" +
                "\nACK/INVALID AWB NO" +
                "\n/INVALID STATUS CODE" +
                "\nFFR/4" +
                "\n125-92345675FRAPHL/T12K950/CLOTHING" +
                "\nBA171/19MAR/LHRJFK/UN" +
                "\nREF/FRAFCBA";
        new FNA(message).processMessage();
    }
    
    public FNA(String message) {
        super(message);
    }

    @Override
    public void processMessage() {
        String mtype = lineSplitter.getNextLine();
        if (mtype.startsWith("FNA")) {
            putReceivedMessage();
        }
    }

}
