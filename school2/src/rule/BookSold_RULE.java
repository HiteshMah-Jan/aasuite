/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import bean.accounting.BookSold;
import bean.Student;
import bean.admin.AppConfig;
import bean.reference.GradeLevel;
import bean.reference.BookSaleRef;

import javax.swing.JComponent;

import util.PanelUtil;
import util.DBClient;
import util.BeanUtil;

import java.util.List;

import service.util.AbstractIBean;
import template.screen.component.PopSearchRenderer;
import component.LookupTableFieldPallete;
import component.JTextFieldPallete;

/**
 *
 * @author alex
 */
public class BookSold_RULE extends BusinessRuleWrapper {

    @Override
    public void runFocusLost(JComponent comp) {
    	if ("personId".equals(comp.getName())) {
    		changeCategory();
    	}
        if (comp.getName().startsWith("book")) {
            JTextFieldPallete l = (JTextFieldPallete) usedComp;
            if (l.isEmpty()) {
                String amt = comp.getName().replace("book", "bookAmount");
                setValue(amt, "0.0");
                BookSold book = (BookSold) this.getBean();
                book.completeSet = false;
            }
            else {
                BookSold book = (BookSold) this.getBean();
                int bookId = (int) BeanUtil.getDoubleValue(book, comp.getName());
                BookSaleRef b = (BookSaleRef) AbstractIBean.extractObject(BookSaleRef.class.getSimpleName(), bookId+"");
                String amt = comp.getName().replace("book", "bookAmount");
                setValue(amt, b.amount);
            }
        }
        calculateTotal();
    }

    protected void changeCategory() {
    	BookSold op = (BookSold) this.getBean();
        if (op.personId>0) {
            setValue("payerCategory", "STUDENT");
        }
    }
    
    public void calculateTotal() {
        BookSold book = (BookSold) this.getBean();
        setValue("totalAmount", book.extractTotalAmount());
    }

    @Override
    public void runOnClick(JComponent comp) {
        if ("btnCompleteSet".equals(comp.getName())) {
            completeSet();
        }
    }

    public void completeSet() {
        BookSold b = (BookSold) this.getBean();
        if (b.completeSet) {
            if (b.personId==0) {
                if (!PanelUtil.showPrompt(usedComp, "You did not select a student.\nDo you want to continue?")) {
                	return;
                }
            }
            GradeLevel lvl = null;
            if (b.personId>0) {
                Student stud = (Student) Student.extractObject(b.personId);
                lvl = (GradeLevel) GradeLevel.extractObject(GradeLevel.class.getSimpleName(), stud.gradeLevel);
            }
            else {
                lvl = (GradeLevel) GradeLevel.extractObject(GradeLevel.class.getSimpleName(), "G1");
            }
            List lst = lvl.extractCacheListBeans(GradeLevel.class);
            lvl = (GradeLevel) PanelUtil.showPromptMessage(null, "Choose grade level", "", lst, lvl);

//            List<BookSaleRef> lstBooks = DBClient.getList("SELECT a FROM BookSaleRef a WHERE a.schoolYear='"+ AppConfig.getSchoolYear()+"' AND a.gradeLevel='"+lvl.code+"'");
            b.gradeLevel = lvl.code;
            List<BookSaleRef> lstBooks = DBClient.getList("SELECT a FROM BookSaleRef a WHERE a.gradeLevel='"+lvl.code+"'");
            for (int i=0; lstBooks!=null && i<lstBooks.size(); i++) {
                BookSaleRef book = lstBooks.get(i);
                setValue("book"+(i+1), book.seq);
                setValue("bookAmount"+(i+1), book.amount);
            }
        }
    }
}
