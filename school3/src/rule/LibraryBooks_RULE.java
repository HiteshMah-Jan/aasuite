/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import bean.LibraryBooks;
import common2.Common2View;
import javax.swing.JComponent;
import template.screen.TransactionPanel;
import ui.action.LibraryAction;

/**
 *
 * @author Entokwaa
 */
public class LibraryBooks_RULE extends BusinessRuleWrapper {

    @Override
    public void runFocusLost(JComponent comp) {
    }

    @Override
    public void runOnClick(JComponent comp) {
        if (comp.getName().equals("btnBorrow")) {
            LibraryBooks book = (LibraryBooks) this.getBean();
            LibraryAction.borrowBook(book);
            redisplayRecord();
        }
        else if (comp.getName().equals("btnReturn")) {
            LibraryBooks book = (LibraryBooks) this.getBean();
            LibraryAction.returnBook(book);
            redisplayRecord();
        }
        else if (comp.getName().equals("btnScanner")) {
            LibraryBooks book = (LibraryBooks) this.getBean();
            LibraryAction.showBarcode(book);
            redisplayRecord();
        }
    }

}
