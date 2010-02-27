/*
 * ComponentTransferHandler.java
 *
 * Created on Jan 11, 2008, 10:25:25 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component.dnd;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

/**
 *
 * @author Entokwaa
 */
public class ComponentTransferHandler extends TransferHandler {
    private static JComponent source;
    
    @Override
    public int getSourceActions(JComponent c) {
        source = c;
        Logger.getLogger("global").log(Level.INFO, "DND getSourceActions - "+c.getClass().getName());
        return TransferHandler.COPY_OR_MOVE;
    }

    @Override
    public boolean canImport(JComponent c, DataFlavor[] transferFlavors) {
        Logger.getLogger("global").log(Level.INFO, "DND canImport - "+c.getClass().getName()+" --> "+transferFlavors);
        if (source instanceof IComponentDND && c instanceof IComponentDND) {
            return true;
        }
        return false;
    }

    @Override
    public Transferable createTransferable(JComponent c) {
        Logger.getLogger("global").log(Level.INFO, "DND createTransferable - "+c.getClass().getName());
        return new StringSelection("data");
    }

    @Override
    public void exportDone(JComponent c, Transferable contents, int action) {
        Logger.getLogger("global").log(Level.INFO, "DND exportDone - "+c.getClass().getName()+" --> "+contents.toString());
    }

    @Override
    public boolean importData(JComponent c, Transferable contents) {
        Logger.getLogger("global").log(Level.INFO, "DND importData - "+c.getClass().getName()+" --> "+contents.toString());
        if (source instanceof IComponentDND && c instanceof IComponentDND) {
            try {
                component.dnd.IComponentDND src = (component.dnd.IComponentDND) source;
                component.dnd.IComponentDND target = (component.dnd.IComponentDND) c;
                target.processTransferredData(src.getDataTransfer(), source, c);
                src.refresh();
                target.refresh();
            } catch (Exception ex) {
                Logger.getLogger("global").log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }
}
