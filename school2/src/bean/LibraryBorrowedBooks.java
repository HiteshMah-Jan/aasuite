/*
 * BorrowedBooks.java
 *
 * Created on Nov 28, 2007, 9:39:38 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.ActionButton;
import template.ActionButtons;
import template.Display;
import template.Displays;
import template.UITemplate;
import util.DBClient;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "LibraryBorrowedBooks")
@UITemplate(columnSearch={"borrowerName", "dateBorrowed", "isReturned", "dateReturned", "penalty"}, gridCount=4, title="Borrowed Books")
@ActionButtons({
    @ActionButton(name="btnReturn", label="Return Selected Book")
})
@Displays({
        @Display(name="isbn",gridFieldWidth=3),
        @Display(name="personId", label="Borrower"),
        @Display(name="librarian"),
//        @Display(name="personId", gridFieldWidth=3, width=-1, type="PopSearch", label="Borrower", linktoBean=Person.class),
//        @Display(name="librarian", gridFieldWidth=3, width=-1, type="PopSearch", linktoBean=Employee.class),
        @Display(name="dateBorrowed", width=-1),
        @Display(name="dueDate", width=-1),
        @Display(name="isReturned", width=-1, type="CheckBox"),
        @Display(name="dateReturned", width=-1)
})
public class LibraryBorrowedBooks extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    private Integer seq;
    @Column(name = "isbn", nullable = false)
    public String isbn;
    @Column(name = "personId", nullable = false)
    public int personId;
    @Column(name = "librarian")
    public int librarian;
    @Column(name = "dateBorrowed")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date dateBorrowed;
    @Column(name = "dueDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date dueDate;
    @Column(name = "isReturned")
    public boolean isReturned;
    @Column(name = "dateReturned")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date dateReturned;
    @Column(name = "penalty")
    public double penalty;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "isbn","librarian", "dateBorrowed","dueDate");
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    @Override
    public String getComboDisplay() {
        return super.getComboDisplay();
    }

    @Override
    public String toString() {
        return isbn;
    }

    public Date getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(Date dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public Date getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(Date dateReturned) {
        this.dateReturned = dateReturned;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isIsReturned() {
        return isReturned;
    }

    public void setIsReturned(boolean isReturned) {
        this.isReturned = isReturned;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getLibrarian() {
        return librarian;
    }

    public void setLibrarian(int librarian) {
        this.librarian = librarian;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Transient
    Person person;

    public String getBorrowerName() {
        if (this.personId == 0) {
            return null;
        }
        if (person == null) {
            person = (Person) firstRecord("SELECT a FROM Person a WHERE a.personId=" + this.personId);
            if (person==null) return "";
        }
        return person.toString();
    }

    public void returnBook() {
        this.isReturned = true;
        this.dateReturned = constants.Constants.useDate;
        save();
    }

	@Override
	public void save() {
		super.save();
	}
    
    
}
