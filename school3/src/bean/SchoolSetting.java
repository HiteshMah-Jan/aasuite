/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Entokwaa
 */
@Entity
@Table(name = "SchoolSetting")
@UITemplate(orderBy="a.seq DESC",template=template.screen.TemplateSinglePage.class,
    columnSearch={"seq","currentSemester"},
    criteriaSearch={"seq"},
    gridCount=4, title="School Settings")
@Displays({
        @Display(name="seq", type="Label", overrideMandatory=true),
        @Display(name="currentSemester", type="NumberCombo", endCount=3)
})
public class SchoolSetting extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "currentSemester")
    public int currentSemester;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "currentSemester");
    }

    public int getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(int currentSemester) {
        this.currentSemester = currentSemester;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public static SchoolSetting extractLatestSetting() {
        return (SchoolSetting) firstRecord("SELECT a FROM SchoolSetting a ORDER BY a.seq DESC");
    }
}
