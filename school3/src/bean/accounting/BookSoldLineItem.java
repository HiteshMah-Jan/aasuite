/*
 * Paymentmethod.java
 *
 * Created on Nov 23, 2007, 8:40:57 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.accounting;

import java.io.Serializable;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;

/**
 *
 * @author pogi
 */
@Entity
@Table(name = "BookSoldLineItem")
@UITemplate(template = TemplateSinglePage.class, gridCount = 4, columnSearch = {"book", "amount"})
@Displays({
        @Display(name="seq"),
        @Display(name="bookSoldId"),
        @Display(name="book"),
        @Display(name="amount")
       
        })
public class BookSoldLineItem extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "bookSoldId", nullable = false)
    public int bookSoldId;
    @Column(name = "book", nullable = false)
    public String book;
    @Column(name = "amount")
    public double amount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public int getBookSoldId() {
        return bookSoldId;
    }

    public void setBookSoldId(int bookSoldId) {
        this.bookSoldId = bookSoldId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
   
     @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
    
}
