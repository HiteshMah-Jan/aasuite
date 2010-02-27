/*
 * ChannelCommand.java
 * 
 * Created on Feb 27, 2008, 4:17:14 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.jxta.temp;

import java.io.Serializable;

/**
 *
 * @author Entokwaa
 */
public class ChannelCommand implements Serializable {
    public static int MAXIMIZE_WINDOW = 1;
    public static int MINIMIZE_WINDOW = 2;
    public static int HIDE_WINDOW = 3;
    public static int END_MEETING = 4;

    private int command;
    private String fromUser;
    private Object commandObj;

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public Object getCommandObj() {
        return commandObj;
    }

    public void setCommandObj(Object commandObj) {
        this.commandObj = commandObj;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

}
