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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import service.util.AbstractIBean;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author pogi
 */
@Entity
@Table(name = "BillingCodeReference")
@UITemplate(template = TemplateDefault.class, gridCount = 4, 
		columnSearch = {"code", "name", "amountPrivateT1", "amountSemiPrivate1", "amountWard1"},
		criteriaSearch = {"code", "name", "amountPrivate1", "amountSemiPrivate1", "amountWard1"}
)
@Displays({
        @Display(name="code"),
        @Display(name="name", width=200),
        @Display(name="amountPrivateT1", label="Private", addInfoOnly=true),
        
        @Display(name="amountPrivate1", label="1", labelTop=true, leftLabel="Private", width=30),
        @Display(name="amountPrivate2", label="2", labelTop=true, width=30),
        @Display(name="amountPrivate3", label="3", labelTop=true, width=30),
        @Display(name="amountPrivate4", label="4", labelTop=true, width=30),
        @Display(name="amountPrivate5", label="5", labelTop=true, width=30),
        @Display(name="amountPrivate6", label="6", labelTop=true, width=30),
        @Display(name="amountPrivate7", label="7", labelTop=true, width=30),
        @Display(name="amountPrivate8", label="8", labelTop=true, width=30),
        @Display(name="amountPrivate9", label="9", labelTop=true, width=30),
        @Display(name="amountPrivate10", label="10", labelTop=true, width=30),

        @Display(name="amountSemiPrivate1", label="Semi", width=30),
        @Display(name="amountSemiPrivate2", label="2", hideLabel=true, width=30),
        @Display(name="amountSemiPrivate3", label="3", hideLabel=true, width=30),
        @Display(name="amountSemiPrivate4", label="4", hideLabel=true, width=30),
        @Display(name="amountSemiPrivate5", label="5", hideLabel=true, width=30),
        @Display(name="amountSemiPrivate6", label="6", hideLabel=true, width=30),
        @Display(name="amountSemiPrivate7", label="7", hideLabel=true, width=30),
        @Display(name="amountSemiPrivate8", label="8", hideLabel=true, width=30),
        @Display(name="amountSemiPrivate9", label="9", hideLabel=true, width=30),
        @Display(name="amountSemiPrivate10", label="10", hideLabel=true, width=30),

        @Display(name="amountWard1", label="Ward", width=30),
        @Display(name="amountWard2", label="2", hideLabel=true, width=30),
        @Display(name="amountWard3", label="3", hideLabel=true, width=30),
        @Display(name="amountWard4", label="4", hideLabel=true, width=30),
        @Display(name="amountWard5", label="5", hideLabel=true, width=30),
        @Display(name="amountWard6", label="6", hideLabel=true, width=30),
        @Display(name="amountWard7", label="7", hideLabel=true, width=30),
        @Display(name="amountWard8", label="8", hideLabel=true, width=30),
        @Display(name="amountWard9", label="9", hideLabel=true, width=30),
        @Display(name="amountWard10", label="10", hideLabel=true, width=30)
})

@DisplayGroups({
    @DisplayGroup(title = "Amounts", 
    		gridCount = 20, 
    		fields = {
    			"amountPrivate1","amountPrivate2","amountPrivate3","amountPrivate4","amountPrivate5",
    			"amountPrivate6","amountPrivate7","amountPrivate8","amountPrivate9","amountPrivate10",
    			"amountSemiPrivate1","amountSemiPrivate2","amountSemiPrivate3","amountSemiPrivate4","amountSemiPrivate5",
    			"amountSemiPrivate6","amountSemiPrivate7","amountSemiPrivate8","amountSemiPrivate9","amountSemiPrivate10",
    			"amountWard1","amountWard2","amountWard3","amountWard4","amountWard5",
    			"amountWard6","amountWard7","amountWard8","amountWard9","amountWard10"
    			})
})
public class BillingCodeReference extends AbstractIBean implements Serializable {
	public static void main(String[] args) {
		view(BillingCodeReference.class);
	}
	
    @Id
    @Column(name = "code", nullable = false)
    public String code;
    @Column(name = "name")
    public String name;
    @Column(name = "amountPrivate1")
    public String amountPrivate1;
    @Column(name = "amountPrivate2")
    public String amountPrivate2;
    @Column(name = "amountPrivate3")
    public String amountPrivate3;
    @Column(name = "amountPrivate4")
    public String amountPrivate4;
    @Column(name = "amountPrivate5")
    public String amountPrivate5;
    @Column(name = "amountPrivate6")
    public String amountPrivate6;
    @Column(name = "amountPrivate7")
    public String amountPrivate7;
    @Column(name = "amountPrivate8")
    public String amountPrivate8;
    @Column(name = "amountPrivate9")
    public String amountPrivate9;
    @Column(name = "amountPrivate10")
    public String amountPrivate10;

    @Column(name = "amountSemiPrivate1")
    public String amountSemiPrivate1;
    @Column(name = "amountSemiPrivate2")
    public String amountSemiPrivate2;
    @Column(name = "amountSemiPrivate3")
    public String amountSemiPrivate3;
    @Column(name = "amountSemiPrivate4")
    public String amountSemiPrivate4;
    @Column(name = "amountSemiPrivate5")
    public String amountSemiPrivate5;
    @Column(name = "amountSemiPrivate6")
    public String amountSemiPrivate6;
    @Column(name = "amountSemiPrivate7")
    public String amountSemiPrivate7;
    @Column(name = "amountSemiPrivate8")
    public String amountSemiPrivate8;
    @Column(name = "amountSemiPrivate9")
    public String amountSemiPrivate9;
    @Column(name = "amountSemiPrivate10")
    public String amountSemiPrivate10;

    @Column(name = "amountWard1")
    public String amountWard1;
    @Column(name = "amountWard2")
    public String amountWard2;
    @Column(name = "amountWard3")
    public String amountWard3;
    @Column(name = "amountWard4")
    public String amountWard4;
    @Column(name = "amountWard5")
    public String amountWard5;
    @Column(name = "amountWard6")
    public String amountWard6;
    @Column(name = "amountWard7")
    public String amountWard7;
    @Column(name = "amountWard8")
    public String amountWard8;
    @Column(name = "amountWard9")
    public String amountWard9;
    @Column(name = "amountWard10")
    public String amountWard10;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Transient
    public String amountPrivateT1;
	public String getAmountPrivateT1() {
		return amountPrivate1;
	}

	public String getAmountPrivate1() {
		return amountPrivate1;
	}

	public void setAmountPrivate1(String amountPrivate1) {
		this.amountPrivate1 = amountPrivate1;
	}

	public String getAmountPrivate2() {
		return amountPrivate2;
	}

	public void setAmountPrivate2(String amountPrivate2) {
		this.amountPrivate2 = amountPrivate2;
	}

	public String getAmountPrivate3() {
		return amountPrivate3;
	}

	public void setAmountPrivate3(String amountPrivate3) {
		this.amountPrivate3 = amountPrivate3;
	}

	public String getAmountPrivate4() {
		return amountPrivate4;
	}

	public void setAmountPrivate4(String amountPrivate4) {
		this.amountPrivate4 = amountPrivate4;
	}

	public String getAmountPrivate5() {
		return amountPrivate5;
	}

	public void setAmountPrivate5(String amountPrivate5) {
		this.amountPrivate5 = amountPrivate5;
	}

	public String getAmountPrivate6() {
		return amountPrivate6;
	}

	public void setAmountPrivate6(String amountPrivate6) {
		this.amountPrivate6 = amountPrivate6;
	}

	public String getAmountPrivate7() {
		return amountPrivate7;
	}

	public void setAmountPrivate7(String amountPrivate7) {
		this.amountPrivate7 = amountPrivate7;
	}

	public String getAmountPrivate8() {
		return amountPrivate8;
	}

	public void setAmountPrivate8(String amountPrivate8) {
		this.amountPrivate8 = amountPrivate8;
	}

	public String getAmountPrivate9() {
		return amountPrivate9;
	}

	public void setAmountPrivate9(String amountPrivate9) {
		this.amountPrivate9 = amountPrivate9;
	}

	public String getAmountPrivate10() {
		return amountPrivate10;
	}

	public void setAmountPrivate10(String amountPrivate10) {
		this.amountPrivate10 = amountPrivate10;
	}

	public String getAmountSemiPrivate1() {
		return amountSemiPrivate1;
	}

	public void setAmountSemiPrivate1(String amountSemiPrivate1) {
		this.amountSemiPrivate1 = amountSemiPrivate1;
	}

	public String getAmountSemiPrivate2() {
		return amountSemiPrivate2;
	}

	public void setAmountSemiPrivate2(String amountSemiPrivate2) {
		this.amountSemiPrivate2 = amountSemiPrivate2;
	}

	public String getAmountSemiPrivate3() {
		return amountSemiPrivate3;
	}

	public void setAmountSemiPrivate3(String amountSemiPrivate3) {
		this.amountSemiPrivate3 = amountSemiPrivate3;
	}

	public String getAmountSemiPrivate4() {
		return amountSemiPrivate4;
	}

	public void setAmountSemiPrivate4(String amountSemiPrivate4) {
		this.amountSemiPrivate4 = amountSemiPrivate4;
	}

	public String getAmountSemiPrivate5() {
		return amountSemiPrivate5;
	}

	public void setAmountSemiPrivate5(String amountSemiPrivate5) {
		this.amountSemiPrivate5 = amountSemiPrivate5;
	}

	public String getAmountSemiPrivate6() {
		return amountSemiPrivate6;
	}

	public void setAmountSemiPrivate6(String amountSemiPrivate6) {
		this.amountSemiPrivate6 = amountSemiPrivate6;
	}

	public String getAmountSemiPrivate7() {
		return amountSemiPrivate7;
	}

	public void setAmountSemiPrivate7(String amountSemiPrivate7) {
		this.amountSemiPrivate7 = amountSemiPrivate7;
	}

	public String getAmountSemiPrivate8() {
		return amountSemiPrivate8;
	}

	public void setAmountSemiPrivate8(String amountSemiPrivate8) {
		this.amountSemiPrivate8 = amountSemiPrivate8;
	}

	public String getAmountSemiPrivate9() {
		return amountSemiPrivate9;
	}

	public void setAmountSemiPrivate9(String amountSemiPrivate9) {
		this.amountSemiPrivate9 = amountSemiPrivate9;
	}

	public String getAmountSemiPrivate10() {
		return amountSemiPrivate10;
	}

	public void setAmountSemiPrivate10(String amountSemiPrivate10) {
		this.amountSemiPrivate10 = amountSemiPrivate10;
	}

	public String getAmountWard1() {
		return amountWard1;
	}

	public void setAmountWard1(String amountWard1) {
		this.amountWard1 = amountWard1;
	}

	public String getAmountWard2() {
		return amountWard2;
	}

	public void setAmountWard2(String amountWard2) {
		this.amountWard2 = amountWard2;
	}

	public String getAmountWard3() {
		return amountWard3;
	}

	public void setAmountWard3(String amountWard3) {
		this.amountWard3 = amountWard3;
	}

	public String getAmountWard4() {
		return amountWard4;
	}

	public void setAmountWard4(String amountWard4) {
		this.amountWard4 = amountWard4;
	}

	public String getAmountWard5() {
		return amountWard5;
	}

	public void setAmountWard5(String amountWard5) {
		this.amountWard5 = amountWard5;
	}

	public String getAmountWard6() {
		return amountWard6;
	}

	public void setAmountWard6(String amountWard6) {
		this.amountWard6 = amountWard6;
	}

	public String getAmountWard7() {
		return amountWard7;
	}

	public void setAmountWard7(String amountWard7) {
		this.amountWard7 = amountWard7;
	}

	public String getAmountWard8() {
		return amountWard8;
	}

	public void setAmountWard8(String amountWard8) {
		this.amountWard8 = amountWard8;
	}

	public String getAmountWard9() {
		return amountWard9;
	}

	public void setAmountWard9(String amountWard9) {
		this.amountWard9 = amountWard9;
	}

	public String getAmountWard10() {
		return amountWard10;
	}

	public void setAmountWard10(String amountWard10) {
		this.amountWard10 = amountWard10;
	}

	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
    
	@Override
	public void setupIndex() {
		runIndex(1, "outPatientId");
	}
}
