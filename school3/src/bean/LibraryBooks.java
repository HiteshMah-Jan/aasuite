/*
 * Librarybooks.java
 *
 * Created on Nov 28, 2007, 9:06:56 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import service.util.AbstractIBean;
import service.util.ChartBean;
import template.ActionButton;
import template.ActionButtons;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateListOnly;
import template.screen.ChildTemplateListPopupDownButton;
import template.screen.TemplateTabPage;
import template.screen.TemplateTabSinglePage;
import util.BeanUtil;
import util.DBClient;
import util.DateUtil;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "LibraryBooks")
@UITemplate(showChart=false,template=TemplateTabSinglePage.class,
		columnSearch={"callNumber","isbn","title","subtitle","shelfOrGroup","accessionNumber","author","availPieces"},
		criteriaSearch={"callNumber","isbn","title","subtitle","shelfOrGroup","author"},
		gridCount=4, title="Library", showImages=true)
@ChildRecords(
    {@ChildRecord(template=ChildTemplateListPopupDownButton.class, entity=LibraryBorrowedBooks.class, sql="SELECT a FROM LibraryBorrowedBooks a WHERE a.isbn='${isbn}'", fieldMapping={"isbn","isbn"})}
)
@ActionButtons({
//    @ActionButton(name="btnScanner", label="Use Scanner"),
    @ActionButton(name="btnBorrow", label="Borrow This Book"),
    @ActionButton(name="btnReturn", label="Return This Book")
})
@Displays({
//        @Display(name="isbn",width=160, label="Dewey Decimal"),
    @Display(name="callNumber",width=-1, mandatory=true),
    @Display(name="isbn",width=160),
    @Display(name="accessionNumber",width=160),
    @Display(name="barcode",width=-1),
    @Display(name="title",gridFieldWidth=3,width=-1),
    @Display(name="subtitle",gridFieldWidth=3,width=-1),
    @Display(name="shelfOrGroup", type="PopSearch", linktoBean=LibraryBookShelf.class,label="Subject",width=-1),
    @Display(name="publisher", type="PopSearch", linktoBean=LibraryBookPublisher.class,width=-1),
    @Display(name="author",width=-1),
    @Display(name="otherAuthor",width=-1),
    @Display(name="copyrightDate",width=-1),
    @Display(name="dateAcquired",width=-1),
    @Display(name="bookDescription",width=-1),
    @Display(name="pieces"),        
    @Display(name="availPieces", type="Label"),        
    @Display(name="price"),        
    @Display(name="location", type="Combo", modelCombo={"PRE SCHOOL","GRADE SCHOOL","HIGH SCHOOL","OTHER LIB1","OTHER LIB2"})        
})
@Reports({
    @template.Report(reportFile="BorrowedBooks", reportTitle="Borrowed Books", reportSql="${isbn}"),
    @template.Report(reportFile="LibraryCardCatalog", reportTitle="Card Catalog", reportSql="${isbn}"),
    @template.Report(reportFile="Books", reportTitle="Library Books", reportSql="${isbn}")
})
public class LibraryBooks extends AbstractIBean implements Serializable {
	@Override
	public void save() {
		searchStr = BeanUtil.concat(callNumber," ",isbn," ",title," ",subtitle," ",shelfOrGroup," ",author);
		super.save();
	}

	@Id
    @Column(name = "isbn", nullable = false)
    public String isbn;
    @Column(name = "title", nullable = false)
    public String title;
    @Column(name = "shelfOrGroup")
    public String shelfOrGroup;
    @Column(name = "subtitle")
    public String subtitle;
    @Column(name = "author", nullable = false)
    public String author;
    @Column(name = "otherAuthor")
    public String otherAuthor;

    @Column(name = "publisher")
    public String publisher;
    @Column(name = "pieces")
    public int pieces;
    @Column(name = "availPieces")
    public int availPieces;
    @Column(name = "price")
    public int price;

    @Column(name = "bookDescription")
    public String bookDescription;
    @Column(name = "location")
    public String location;

    @Column(name = "copyrightDate")
    public String copyrightDate;
    
    @Column(name = "dateAcquired")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date dateAcquired;

    @Column(name = "barcode")
    public String barcode;
    @Column(name = "callNumber")
    public String callNumber;
    @Column(name = "accessionNumber")
    public String accessionNumber;

    @Column(name = "searchStr", length=1000)
    public String searchStr;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria,"callNumber","isbn","title","subtitle","shelfOrGroup","accessionNumber","author");
    }

    public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getAvailPieces() {
		return availPieces;
	}

	public void setAvailPieces(int availPieces) {
		this.availPieces = availPieces;
	}

	public String getCopyrightDate() {
        return copyrightDate;
    }

    public void setCopyrightDate(String copyrightDate) {
        this.copyrightDate = copyrightDate;
    }
    
    public String getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(String accessionNumber) {
        this.accessionNumber = accessionNumber;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    @Override
    public String toString() {
        return isbn;
    }

    public java.lang.String getIsbn() {
        return isbn;
    }

    public void setIsbn(java.lang.String isbn) {
        this.isbn = isbn;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public java.lang.String getPublisher() {
        return publisher;
    }

    public void setPublisher(java.lang.String publisher) {
        this.publisher = publisher;
    }

    public java.lang.String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(java.lang.String subtitle) {
        this.subtitle = subtitle;
    }

    public java.util.Date getDateAcquired() {
        return dateAcquired;
    }

    public void setDateAcquired(java.util.Date dateAcquired) {
        this.dateAcquired = dateAcquired;
    }

    public java.lang.String getOtherAuthor() {
        return otherAuthor;
    }

    public void setOtherAuthor(java.lang.String otherAuthor) {
        this.otherAuthor = otherAuthor;
    }

    public java.lang.String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(java.lang.String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public java.lang.String getAuthor() {
        return author;
    }

    public void setAuthor(java.lang.String author) {
        this.author = author;
    }

    public java.lang.String getTitle() {
        return title;
    }

    public void setTitle(java.lang.String title) {
        this.title = title;
    }

    public java.lang.String getShelfOrGroup() {
        return shelfOrGroup;
    }

    public void setShelfOrGroup(java.lang.String shelfOrGroup) {
        this.shelfOrGroup = shelfOrGroup;
    }
    
    public int extractAvailablePieces() {
        List lst = list("SELECT a FROM LibraryBorrowedBooks a WHERE a.isbn='",isbn,"' AND (a.isReturned IS null OR a.isReturned='false')");
        if (lst==null || lst.isEmpty()) return pieces;
        return pieces - lst.size();
    }
    
    public List extractStudentBorrowThisBook() {
        List lst = list("SELECT a FROM Person a, LibraryBorrowedBooks b WHERE a.personId=b.personId AND b.isbn='",isbn,"' AND (b.isReturned IS null OR b.isReturned='false')");
        return lst;
    }
    
    public void borrowBook(int personId, Date dueDate) {
        LibraryBorrowedBooks bor = new LibraryBorrowedBooks();
        bor.setDateBorrowed(constants.Constants.useDate);
        bor.setIsbn(isbn);
        bor.setDueDate(dueDate);
        bor.setPersonId(personId);
        bor.save();
        
        updateAvailPieces();
    }
    
    public void returnBook(int personId, double penalty) {
        LibraryBorrowedBooks book = (LibraryBorrowedBooks) firstRecord("SELECT a FROM LibraryBorrowedBooks a WHERE a.personId=",personId," AND a.isbn='",isbn,"' AND (a.isReturned IS null OR a.isReturned='false')");
        book.setDateReturned(constants.Constants.useDate);
        book.setIsReturned(true);
        book.penalty = penalty;
        book.save();
        
        updateAvailPieces();
    }

    public void updateAvailPieces() {
        int borrowedCount = (int) DBClient.getSingleColumnDouble("SELECT COUNT(*) FROM LibraryBorrowedBooks WHERE isbn='",isbn,"' AND isReturned=0");
        availPieces = pieces-borrowedCount;
		save();
    }
    
    public java.lang.String getBarcode() {
        return barcode;
    }

    public void setBarcode(java.lang.String barcode) {
        this.barcode = barcode;
    }

    @Override
    public Vector allChart() {
        java.util.Vector vec = new java.util.Vector();
        vec.add(ChartBean.getPieInstance(this, "Borrowing Performance By Subject/Shelf",BeanUtil.concat("SELECT a.shelfOrGroup, COUNT(b.isbn) FROM LibraryBooks a, LibraryBorrowedBooks b WHERE a.isbn=b.isbn GROUP BY a.shelfOrGroup")));
        vec.add(ChartBean.getPieInstance(this, "Count By Subject/Shelf",BeanUtil.concat("SELECT a.shelfOrGroup, COUNT(a.pieces) FROM LibraryBooks a GROUP BY a.shelfOrGroup")));
        vec.add(ChartBean.getNativeBarInstance(this, "Borrow Per Month",BeanUtil.concat("SELECT ",DateUtil.getSQLYear("a.dateBorrowed"),",",DateUtil.getSQLMonthName("a.dateBorrowed"),", COUNT(a.isbn) FROM LibraryBorrowedBooks a WHERE a.isbn='",isbn,"' GROUP BY ",DateUtil.getSQLYear("a.dateBorrowed"),",",DateUtil.getSQLMonthName("a.dateBorrowed"))));
        return vec;
    }

    public static LibraryBooks createLibraryBooksObj(String isbn, String title, String author, int pieces) {
        LibraryBooks stud = new LibraryBooks();
        stud.isbn = isbn;
        stud.title = title;
        stud.author = author;
        stud.pieces = pieces;
        return stud;
    }
    
    @Override
    protected void runSetup() {
        createLibraryBooksObj("0-7534-0502-4", "CAMELS HAVE HUMPS", "ANITA GANERI", 1).save();
    }    
}
