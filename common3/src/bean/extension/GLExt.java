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
import bean.financial.AccountChart;

import java.io.Serializable;
import template.Display;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Budoy Entokwa
 */
@UITemplate(gridCount = 4, criteriaSearch = {"accountNumber"}, columnSearch = {"accountNumber","debit","credit"})
@Displays({
        @Display(name="accountNumber", type = "PopSearch", linktoBean = AccountChart.class, gridFieldWidth=3, width=-1),
        @Display(name="debit"),
        @Display(name="credit")
})
public class GLExt extends GL implements Serializable {
    public static void main(String[] args) {
        view(GLExt.class);
    }
}
