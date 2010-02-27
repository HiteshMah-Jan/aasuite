/*
 * PatientLaboratory.java
 *
 * Created on Oct 30, 2007, 8:48:29 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

import service.util.AbstractIBean;
import service.util.IBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;
import util.BeanUtil;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "ImageTable")
@UITemplate(canBackup=false, template = TemplateDefault.class, columnSearch = {"recordId", "image"})
@Displays({
        @Display(name="recordId"),
        @Display(name="recTable"),
        @Display(name="image"),
        @Display(name="enteredBy"),
        @Display(name="enteredDate")
})
public class ImageTable extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "recordId", nullable = false, length = 40)
    public String recordId;
    @Column(name = "recTable", nullable = false, length = 40)
    public String recTable;
    @Lob
    @Column(name = "image", nullable = false, length = 2097152)
    public byte[] image;
    @Column(name = "enteredBy", length = 100)
    public String enteredBy;
    @Column(name = "enteredDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
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
        return "Image Table[" + seq + "]";
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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

    public static List<byte[]> getImageList(AbstractIBean source) {
        if (source == null) {
            return new ArrayList<byte[]>();
        } else {
            if (source instanceof AbstractIBean && source != null) {
                List<byte[]> retList = new ArrayList<byte[]>();
                if (!source.isEmptyKey()) {
                    List lst = source.extractImages();
                    if (lst!=null) {
                        for (Object obj : lst) {
                            ImageTable imgTable = (ImageTable) obj;
                            retList.add(imgTable.getImage());
                        }
                    }
                    return retList;
                }
            }
        }
        return new ArrayList<byte[]>();
    }

    public static void saveImages(AbstractIBean bean, List<byte[]> lstImages) {
        String beanCls = bean.getClass().getSimpleName();
        String key = bean.keyVal().toString();
        //remove all the images from the database
        bean.runSQL("DELETE FROM ImageTable a WHERE a.recTable='" + bean._Table() + "' AND a.recordId='" + key + "'");

        List<IBean> images = new ArrayList<IBean>();
        for (byte[] image : lstImages) {
            Logger.getLogger("global").log(Level.INFO, "BYTE SIZE == " + image.length);
            ImageTable img = new ImageTable();
            img.setImage(image);
            img.setRecTable(bean._Table());
            img.setRecordId(key.trim());
            images.add(img);
        }
        bean.save(images);
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
        if (image == null) {
            return 0;
        }
        return image.length;
    }

	@Override
	public void setupIndex() {
		runIndex(1, "recTable", "recordId");
	}
}
