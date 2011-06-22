/*
 * PatientLaboratory.java
 *
 * Created on Oct 30, 2007, 8:48:29 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import bean.hr.Employee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

import org.jfree.util.Log;

import service.util.AbstractIBean;
import service.util.IBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "DocumentTable")
@UITemplate(canBackup=false, template = TemplateDefault.class, gridCount = 4, columnSearch = {"recordId", "recTable"})
@Displays({
    @Display(name="recordId"),
    @Display(name="recTable"),
    @Display(name="filename"),
    @Display(name="document"),
    @Display(name="enteredBy", type = "PopSearch", linktoBean=Employee.class),
    @Display(name="enteredDate")
})
public class DocumentTable extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "recordId", nullable = false, length = 40)
    public String recordId;
    @Column(name = "recTable", nullable = false, length = 40)
    public String recTable;
    @Column(name = "filename", nullable = false, length = 40)
    public String filename;
    @Lob
    @Column(name = "document", nullable = false, length = 2097152)
    public byte[] document;
    @Column(name = "enteredBy", length = 100)
    public String enteredBy;
    @Column(name = "enteredDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date enteredDate;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return filename;
    }

    public java.lang.String getRecTable() {
        return recTable;
    }

    public void setRecTable(java.lang.String recTable) {
        this.recTable = recTable;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    public static Map<String, byte[]> getDocumentList(AbstractIBean source) {
        if (source == null) {
            Logger.getLogger("global").log(Level.INFO, "SOURCE NULL");
            return new HashMap<String, byte[]>();
        } else {
            if (source instanceof AbstractIBean) {
                Map<String, byte[]> retList = new HashMap<String, byte[]>();
                List lst = source.extractFiles();
                Logger.getLogger("global").log(Level.INFO, source.toString());
                for (Object obj : lst) {
                    Logger.getLogger("global").log(Level.INFO, "DOCUMENT EXTRACTED");
                    DocumentTable imgTable = (DocumentTable) obj;
                    retList.put(imgTable.filename, imgTable.getDocument());
                }
                return retList;
            }
        }
        return new HashMap<String, byte[]>();
    }

    public static void saveDocuments(AbstractIBean bean, Map<String, byte[]> lstDocuments) {
        if (bean == null) {
            return;
        }
        String beanCls = bean.getClass().getSimpleName();
        String key = bean.keyVal().toString();
        //remove all the images from the database
        bean.runSQL("DELETE FROM DocumentTable a WHERE a.recTable=\'",beanCls,"\' AND a.recordId=\'",key,"\'");

        List<IBean> images = new ArrayList<IBean>();
        for (String str : lstDocuments.keySet()) {
            byte[] image = lstDocuments.get(str);
            DocumentTable img = new DocumentTable();
            img.setFilename(str);
            img.setDocument(image);
            img.setRecTable(beanCls.trim());
            img.setRecordId(key.trim());
            images.add(img);
        }
        bean.save(images);
    }

    public java.lang.String getFilename() {
        return filename;
    }

    public void setFilename(java.lang.String filename) {
        this.filename = filename;
    }

    public java.lang.String getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(java.lang.String enteredBy) {
        this.enteredBy = enteredBy;
    }

    public java.util.Date getEnteredDate() {
        return enteredDate;
    }

    public void setEnteredDate(java.util.Date enteredDate) {
        this.enteredDate = enteredDate;
    }

    public int getSize() {
        if (document == null) {
            return 0;
        }
        return document.length;
    }
    
	@Override
	public void setupIndex() {
		runIndex(1, "recTable", "recordId");
	}
}
