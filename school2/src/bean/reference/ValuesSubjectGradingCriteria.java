/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.reference;

import java.io.Serializable;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "ValuesSubjectGradingCriteria")
@UITemplate(columnSearch={"code","category","description"},criteriaSearch={"code","category","description"} ,gridCount=2, title=" Values Subject Grading Criteria")

 @DisplayGroups({
    @DisplayGroup(title = "Preschool", gridCount = 6, fields = {"n1","k1","p1"}),
    @DisplayGroup(title = "Grade 1-4", gridCount = 8, fields = {"g1", "g2", "g3", "g4"}),
    @DisplayGroup(title = "Grade 5-6", gridCount = 4, fields = {"g5", "g6"}),
    @DisplayGroup(title = "High School", gridCount = 8, fields = {"h1", "h2", "h3", "h4"})
   
})
@Displays({
//        @Display(name="subject", linktoBean=Subject.class, label="Subject", gridFieldWidth=3, width=-1, type="PopSearch"),
        @Display(name="code",width=220),
        @Display(name="category", type="Combo", modelCombo={"MEGASKILLS","VALUES PROMOTED","ZL2 COMPONENTS","AFFECTIVE DEVELOPMENT","PSYCHOMOTOR DEVELOPMENT"},width=-1),
        @Display(name="description",width=-1),
        @Display(name="n1",label="N"),
       // @Display(name="n2"),
        @Display(name="k1",label="K"),
       // @Display(name="k2"),
        @Display(name="p1",label="P"),
       // @Display(name="p2"),
        @Display(name="g1"),
        @Display(name="g2"),
        @Display(name="g3"),
        @Display(name="g4"),
        @Display(name="g5"),
        @Display(name="g6"),
       // @Display(name="g7"),
        @Display(name="h1"),
        @Display(name="h2"),
        @Display(name="h3"),
        @Display(name="h4")
        
})
public class ValuesSubjectGradingCriteria extends AbstractIBean implements Serializable {
	
	@Override
	public boolean cacheClient() {
		return true;
	}

    public static void main(String[] args) {
        view(ValuesSubjectGradingCriteria.class);
    }
    

    @Id
    @Column(name = "code", nullable = false)
    public String code;
    @Column(name = "category")
    public String category;
    @Column(name = "description")
    public String description;
    @Column(name = "n1")
    public boolean n1;
    @Column(name = "n2")
    public boolean n2;
    @Column(name = "k1")
    public boolean k1;
    @Column(name = "k2")
    public boolean k2;
    @Column(name = "p1")
    public boolean p1;
    @Column(name = "p2")
    public boolean p2;
    @Column(name = "g1")
    public boolean g1;
    @Column(name = "g2")
    public boolean g2;
    @Column(name = "g3")
    public boolean g3;
    @Column(name = "g4")
    public boolean g4;
    @Column(name = "g5")
    public boolean g5;
    @Column(name = "g6")
    public boolean g6;
    @Column(name = "g7")
    public boolean g7;
    @Column(name = "h1")
    public boolean h1;
    @Column(name = "h2")
    public boolean h2;
    @Column(name = "h3")
    public boolean h3;
    @Column(name = "h4")
    public boolean h4;

    
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isG1() {
        return g1;
    }

    public void setG1(boolean g1) {
        this.g1 = g1;
    }

    public boolean isG2() {
        return g2;
    }

    public void setG2(boolean g2) {
        this.g2 = g2;
    }

    public boolean isG3() {
        return g3;
    }

    public void setG3(boolean g3) {
        this.g3 = g3;
    }

    public boolean isG4() {
        return g4;
    }

    public void setG4(boolean g4) {
        this.g4 = g4;
    }

    public boolean isG5() {
        return g5;
    }

    public void setG5(boolean g5) {
        this.g5 = g5;
    }

    public boolean isG6() {
        return g6;
    }

    public void setG6(boolean g6) {
        this.g6 = g6;
    }

    public boolean isG7() {
        return g7;
    }

    public void setG7(boolean g7) {
        this.g7 = g7;
    }

    public boolean isH1() {
        return h1;
    }

    public void setH1(boolean h1) {
        this.h1 = h1;
    }

    public boolean isH2() {
        return h2;
    }

    public void setH2(boolean h2) {
        this.h2 = h2;
    }

    public boolean isH3() {
        return h3;
    }

    public void setH3(boolean h3) {
        this.h3 = h3;
    }

    public boolean isH4() {
        return h4;
    }

    public void setH4(boolean h4) {
        this.h4 = h4;
    }

    public boolean isK1() {
        return k1;
    }

    public void setK1(boolean k1) {
        this.k1 = k1;
    }

    public boolean isK2() {
        return k2;
    }

    public void setK2(boolean k2) {
        this.k2 = k2;
    }

    public boolean isN1() {
        return n1;
    }

    public void setN1(boolean n1) {
        this.n1 = n1;
    }

    public boolean isN2() {
        return n2;
    }

    public void setN2(boolean n2) {
        this.n2 = n2;
    }

    public boolean isP1() {
        return p1;
    }

    public void setP1(boolean p1) {
        this.p1 = p1;
    }

    public boolean isP2() {
        return p2;
    }

    public void setP2(boolean p2) {
        this.p2 = p2;
    }

    @Override
    public String popupSearch(String criteria) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}



