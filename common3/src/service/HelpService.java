/*
 * Help.java
 *
 * Created on Sep 8, 2007, 3:16:30 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import bean.admin.Help;
import constants.Constants;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.IService;
import service.ReturnStruct;
import service.util.AbstractIBean;
import util.BeanUtil;
import util.Log;

/**
 *
 * @author Budoy Entokwa
 */
public class HelpService implements IService {

    @SuppressWarnings(value = "unchecked")
    public ReturnStruct callService(ParamStruct param) {
        ReturnStruct ret = new ReturnStruct();
        if (param.getActionCommand() == Constants.ADD_HELP) {
            Help hlp = (Help) param.getData();
            hlp.save();
            createHelpFile(hlp);
        } else {
            String helpName = (String) param.getData();
            Help hlp = (Help) AbstractIBean.firstRecord("SELECT a FROM Help a WHERE a.name='",helpName,"'");
            ret.setData(hlp);
        }
        ret.setStatus(Constants.SUCCESS);
        return ret;
    }

    private void createHelpFile(Help hlp) {
        String helpName = hlp.getName();
        File f = new File(Constants.HELP_DIR, BeanUtil.concat(helpName,".html"));
        Log.out("HELP FILE === ",f.getAbsolutePath());
        if (!f.exists()) {
            try {
                RandomAccessFile raf = new RandomAccessFile(f, "rw");
                raf.writeBytes(BeanUtil.concat("<html><h2>",helpName,"</h2></html>"));
                raf.close();
            } catch (Exception ex) {
                Logger.getLogger("global").log(Level.SEVERE, null, ex);
            }
        }
    }
}
