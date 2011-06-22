/*
 * GeneralLedger.java
 *
 * Created on Nov 24, 2007, 8:44:41 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import bean.accounting.*;
import java.io.Serializable;
import template.Display;
import template.Displays;
import template.UITemplate;


/**
 *
 * @author Budoy Entokwa
 */
@UITemplate(gridCount = 4, columnSearch = {"dueDate", "company", "amount"}, select = "SELECT a FROM AccountPayable a WHERE a.paid=false ", orderBy = "a.dueDate")
@Displays({
    @Display(name = "dueDate"),
    @Display(name = "amount"),
    @Display(name = "company", gridFieldWidth = 3, width = -1),
    @Display(name = "remarks", gridFieldWidth = 3, width = -1)
})

public class AccountPayableExt extends AccountPayable implements Serializable {

    public static void main(String[] args) {
        view(AccountPayableExt.class);
    }
}
