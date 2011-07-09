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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "LibraryBookShelf")
@UITemplate(columnSearch={"shelf"}, gridCount=4, title="Book Shelf")
@ChildRecords({
    @ChildRecord(entity=LibraryBooks.class, sql="SELECT a FROM LibraryBooks a WHERE a.shelfOrGroup='${shelf}'"),
    @ChildRecord(entity=LibraryBorrowedBooks.class, sql="SELECT a FROM LibraryBorrowedBooks a, LibraryBooks b WHERE a.isbn=b.isbn AND b.shelfOrGroup='${shelf}'")
})
@Displays({
        @Display(name="shelf",gridFieldWidth=3),
        @Display(name="remarks",gridFieldWidth=3)
})
public class LibraryBookShelf extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "shelf")
    public String shelf;
    @Column(name = "remarks")
    public String remarks;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "shelf");
    }

    @Override
    public String toString() {
        return shelf;
    }

   public java.lang.String getRemarks() {
        return remarks;
    }

    public void setRemarks(java.lang.String remarks) {
        this.remarks = remarks;
    }

    public java.lang.String getShelf() {
        return shelf;
    }

    public void setShelf(java.lang.String shelf) {
        this.shelf = shelf;
    }

    @Override
    public boolean cacheClient() {
        return true;
    }
}
