/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Transient;
import util.Log;

/**
 *
 * @author Entokwaa
 */
public class CheckerBean {
    @Transient
    String promptOnSave;
    @Transient
    String promptOnDelete;
    @Transient
    boolean canSave = true;
    @Transient
    boolean canDelete = true;

    public boolean isCanDelete() {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }

    public boolean isCanSave() {
        return canSave;
    }

    public void setCanSave(boolean canSave) {
        this.canSave = canSave;
    }

    public String getPromptOnDelete() {
        return promptOnDelete;
    }

    public void setPromptOnDelete(String promptOnDelete) {
        this.promptOnDelete = promptOnDelete;
    }

    public String getPromptOnSave() {
        return promptOnSave;
    }

    public void setPromptOnSave(String promptOnSave) {
        this.promptOnSave = promptOnSave;
    }  
    
    public static void info(String... message) {
        Log.info(message);
    }
    public static void warning(String... message) {
        Log.warning(message);
    }
    public static void severe(String message, Exception e) {
        Log.severe(message, e);
    }
    public static void severe(String message) {
        Log.severe(message);
    }
}
