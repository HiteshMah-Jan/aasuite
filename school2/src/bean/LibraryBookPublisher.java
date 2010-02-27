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
@Table(name = "LibraryBookPublisher")
@UITemplate(columnSearch={"publisherName"}, gridCount=4, title="Book Publisher")
@ChildRecords({
    @ChildRecord(entity=LibraryBooks.class, sql="SELECT a FROM LibraryBooks a WHERE a.publisher='${publisherName}'"),
    @ChildRecord(entity=LibraryBorrowedBooks.class, sql="SELECT a FROM LibraryBorrowedBooks a, LibraryBooks b WHERE a.isbn=b.isbn AND b.publisher='${publisherName}'")
})
@Displays({
        @Display(name="publisherName",gridFieldWidth=3),
        @Display(name="address",gridFieldWidth=3),
        @Display(name="remarks",gridFieldWidth=3)
})
public class LibraryBookPublisher extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "publisherName", nullable = false)
    public String publisherName;
    @Column(name = "address")
    public String address;
    @Column(name = "remarks")
    public String remarks;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "publisherName","address");
    }

    @Override
    public String toString() {
        return publisherName;
    }

    public java.lang.String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(java.lang.String publisherName) {
        this.publisherName = publisherName;
    }

    public java.lang.String getAddress() {
        return address;
    }

    public void setAddress(java.lang.String address) {
        this.address = address;
    }

    public java.lang.String getRemarks() {
        return remarks;
    }

    public void setRemarks(java.lang.String remarks) {
        this.remarks = remarks;
    }

}
