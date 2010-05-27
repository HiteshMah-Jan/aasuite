/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.accounting;

import bean.Student;
import constants.UserInfo;
import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "MultiPayment")
@UITemplate(template=TemplateTabPage.class, gridCount = 6, columnSearch = {"payor", "schoolYear", "paymentDate", "fullAmount", "bank", "checkNum"})
@Displays({
        @Display(name="payor", width=250),
        @Display(name="schoolYear", width=120),
        @Display(name="cashier"),

        @Display(name="fullAmount", width=-1),
        @Display(name="paymentDate", gridFieldWidth=3),
        @Display(name="remarks", gridFieldWidth=5, width=-1),
        
        @Display(name="personId1", labelTop=true, label="Student", type="PopSearch", linktoBean=Student.class),
        @Display(name="plan1", labelTop=true, label="Plan", type="PopSearch", linktoBean=PaymentPlan.class),
        @Display(name="amountPerson1", labelTop=true, label="Amount"),
        @Display(name="or1", labelTop=true, label="OR", button="Print OR", width=100),

        @Display(name="personId2", hideLabel=true, type="PopSearch", linktoBean=Student.class),
        @Display(name="plan2", hideLabel=true, type="PopSearch", linktoBean=PaymentPlan.class),
        @Display(name="amountPerson2", hideLabel=true),
        @Display(name="or2", hideLabel=true, button="Print OR", width=100),

        @Display(name="personId3", hideLabel=true, type="PopSearch", linktoBean=Student.class),
        @Display(name="plan3", hideLabel=true, type="PopSearch", linktoBean=PaymentPlan.class),
        @Display(name="amountPerson3", hideLabel=true),
        @Display(name="or3", hideLabel=true, button="Print OR", width=100),

        @Display(name="personId4", hideLabel=true, type="PopSearch", linktoBean=Student.class),
        @Display(name="plan4", hideLabel=true, type="PopSearch", linktoBean=PaymentPlan.class),
        @Display(name="amountPerson4", hideLabel=true),
        @Display(name="or4", hideLabel=true, button="Print OR", width=100),

        @Display(name="personId5", hideLabel=true, type="PopSearch", linktoBean=Student.class),
        @Display(name="plan5", hideLabel=true, type="PopSearch", linktoBean=PaymentPlan.class),
        @Display(name="amountPerson5", hideLabel=true),
        @Display(name="or5", hideLabel=true, button="Print OR", width=100),

        @Display(name="personId6", hideLabel=true, type="PopSearch", linktoBean=Student.class),
        @Display(name="plan6", hideLabel=true, type="PopSearch", linktoBean=PaymentPlan.class),
        @Display(name="amountPerson6", hideLabel=true),
        @Display(name="or6", hideLabel=true, button="Print OR", width=100),

        @Display(name="personId7", hideLabel=true, type="PopSearch", linktoBean=Student.class),
        @Display(name="plan7", hideLabel=true, type="PopSearch", linktoBean=PaymentPlan.class),
        @Display(name="amountPerson7", hideLabel=true),
        @Display(name="or7", hideLabel=true, button="Print OR", width=100),

        @Display(name="personId8", hideLabel=true, type="PopSearch", linktoBean=Student.class),
        @Display(name="plan8", hideLabel=true, type="PopSearch", linktoBean=PaymentPlan.class),
        @Display(name="amountPerson8", hideLabel=true),
        @Display(name="or8", hideLabel=true, button="Print OR", width=100),

        @Display(name="personId9", hideLabel=true, type="PopSearch", linktoBean=Student.class),
        @Display(name="plan9", hideLabel=true, type="PopSearch", linktoBean=PaymentPlan.class),
        @Display(name="amountPerson9", hideLabel=true),
        @Display(name="or9", hideLabel=true, button="Print OR", width=100),

        @Display(name="personId10", hideLabel=true, type="PopSearch", linktoBean=Student.class),
        @Display(name="plan10", hideLabel=true, type="PopSearch", linktoBean=PaymentPlan.class),
        @Display(name="amountPerson10", hideLabel=true),
        @Display(name="or10", hideLabel=true, button="Print OR", width=100),

        
//        @Display(name="bank1", label = "Bank Name", type="PopSearch", linktoBean=Bank.class, labelTop=true),
        @Display(name="accountNumber1", label = "Acc.#", labelTop=true),
        @Display(name="amount1", label = "Amount", labelTop=true),
        @Display(name="bounceCheck1", label="bounceCheck", type="CheckBox", labelTop=true),
        //        @Display(name="bank2", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber2", labelTop=true, label="Acc.#"),
        @Display(name="amount2", labelTop=true, label="Amount"),
        @Display(name="bounceCheck2", label="bounceCheck", labelTop=true, type="CheckBox"),
       
        //        @Display(name="bank3", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber3", hideLabel=true),
        @Display(name="amount3", hideLabel=true),
        @Display(name="bounceCheck3", hideLabel=true, type="CheckBox"),
        
        @Display(name="accountNumber4", hideLabel=true),
        @Display(name="amount4", hideLabel=true),
        @Display(name="bounceCheck4", hideLabel=true, type="CheckBox"),
       //        @Display(name="bank5", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber5", hideLabel=true),
        @Display(name="amount5", hideLabel=true),
        @Display(name="bounceCheck5", hideLabel=true, type="CheckBox"),
       //        @Display(name="bank6", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber6", hideLabel=true),
        @Display(name="amount6", hideLabel=true),
        @Display(name="bounceCheck6", hideLabel=true, type="CheckBox"),
        //        @Display(name="bank7", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber7", hideLabel=true),
        @Display(name="amount7", hideLabel=true),
        @Display(name="bounceCheck7", hideLabel=true, type="CheckBox"),
       //        @Display(name="bank8", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber8", hideLabel=true),
        @Display(name="amount8", hideLabel=true),
        @Display(name="bounceCheck8", hideLabel=true, type="CheckBox"),
       //        @Display(name="bank9", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber9", hideLabel=true),
        @Display(name="amount9", hideLabel=true),
        @Display(name="bounceCheck9", hideLabel=true, type="CheckBox"),
       //        @Display(name="bank10", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber10", hideLabel=true),
        @Display(name="amount10", hideLabel=true),
        @Display(name="bounceCheck10", hideLabel=true, type="CheckBox")
})
@DisplayGroups({
    @DisplayGroup(gridCount=8, title="Payment Details", fields={
    "personId1","plan1","amountPerson1","or1",
    "personId2","plan2","amountPerson2","or2",
    "personId3","plan3","amountPerson3","or3",
    "personId4","plan4","amountPerson4","or4",
    "personId5","plan5","amountPerson5","or5",
    "personId6","plan6","amountPerson6","or6",
    "personId7","plan7","amountPerson7","or7",
    "personId8","plan8","amountPerson8","or8",
    "personId9","plan9","amountPerson9","or9",
    "personId10","plan10","amountPerson10","or10"
    }),
    @DisplayGroup(gridCount=12, title="Check", fields={
    "bank1","accountNumber1","amount1","bounceCheck1",
    "bank2","accountNumber2","amount2","bounceCheck2",
    "bank3","accountNumber3","amount3","bounceCheck3",
    "bank4","accountNumber4","amount4","bounceCheck4",
    "bank5","accountNumber5","amount5","bounceCheck5",
    "bank6","accountNumber6","amount6","bounceCheck6",
    "bank7","accountNumber7","amount7","bounceCheck7",
    "bank8","accountNumber8","amount8","bounceCheck8",
    "bank9","accountNumber9","amount9","bounceCheck9",
    "bank10","accountNumber10","amount10","bounceCheck10"
    })
})
public class MultiPayment extends AbstractIBean implements java.io.Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "cashier")
    public String cashier;
    @Column(name = "payor")
    public String payor;
    @Column(name = "schoolYear")
    public String schoolYear;
    @Column(name = "paymentDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date paymentDate;
    @Column(name = "fullAmount")
    public String fullAmount;
    @Column(name = "bank")
    public String bank;
    @Column(name = "checkNum")
    public String checkNum;
    
    @Column(name = "remarks")
    public String remarks;

    @Column(name = "personId1")
    public int personId1;
    @Column(name = "plan1")
    public String plan1;
    @Column(name = "amountPerson1")
    public double amountPerson1;
    @Column(name = "paymentId1")
    public int paymentId1;

    @Column(name = "personId2")
    public int personId2;
    @Column(name = "plan2")
    public String plan2;
    @Column(name = "amountPerson2")
    public double amountPerson2;
    @Column(name = "paymentId2")
    public int paymentId2;

    @Column(name = "personId3")
    public int personId3;
    @Column(name = "plan3")
    public String plan3;
    @Column(name = "amountPerson3")
    public double amountPerson3;
    @Column(name = "paymentId3")
    public int paymentId3;

    @Column(name = "personId4")
    public int personId4;
    @Column(name = "plan4")
    public String plan4;
    @Column(name = "amountPerson4")
    public double amountPerson4;
    @Column(name = "paymentId4")
    public int paymentId4;

    @Column(name = "personId5")
    public int personId5;
    @Column(name = "plan5")
    public String plan5;
    @Column(name = "amountPerson5")
    public double amountPerson5;
    @Column(name = "paymentId5")
    public int paymentId5;

    @Column(name = "personId6")
    public int personId6;
    @Column(name = "plan6")
    public String plan6;
    @Column(name = "amountPerson6")
    public double amountPerson6;
    @Column(name = "paymentId6")
    public int paymentId6;

    @Column(name = "personId7")
    public int personId7;
    @Column(name = "plan7")
    public String plan7;
    @Column(name = "amountPerson7")
    public double amountPerson7;
    @Column(name = "paymentId7")
    public int paymentId7;

    @Column(name = "personId8")
    public int personId8;
    @Column(name = "plan8")
    public String plan8;
    @Column(name = "amountPerson8")
    public double amountPerson8;
    @Column(name = "paymentId8")
    public int paymentId8;

    @Column(name = "personId9")
    public int personId9;
    @Column(name = "plan9")
    public String plan9;
    @Column(name = "amountPerson9")
    public double amountPerson9;
    @Column(name = "paymentId9")
    public int paymentId9;

    @Column(name = "personId10")
    public int personId10;
    @Column(name = "plan10")
    public String plan10;
    @Column(name = "amountPerson10")
    public double amountPerson10;
    @Column(name = "paymentId10")
    public int paymentId10;

    @Column(name = "or1")
    public String or1;
    @Column(name = "or2")
    public String or2;
    @Column(name = "or3")
    public String or3;
    @Column(name = "or4")
    public String or4;
    @Column(name = "or5")
    public String or5;
    @Column(name = "or6")
    public String or6;
    @Column(name = "or7")
    public String or7;
    @Column(name = "or8")
    public String or8;
    @Column(name = "or9")
    public String or9;
    @Column(name = "or10")
    public String or10;

    @Column(name = "bank1")
    public String bank1;
    @Column(name = "accountNumber1")
    public String accountNumber1;
    @Column(name = "amount1")
    public double amount1;
    @Column(name = "bank2")
    public String bank2;
    @Column(name = "accountNumber2")
    public String accountNumber2;
    @Column(name = "amount2")
    public double amount2;
    @Column(name = "bank3")
    public String bank3;
    @Column(name = "accountNumber3")
    public String accountNumber3;
    @Column(name = "amount3")
    public double amount3;
    @Column(name = "bank4")
    public String bank4;
    @Column(name = "accountNumber4")
    public String accountNumber4;
    @Column(name = "amount4")
    public double amount4;
    @Column(name = "bank5")
    public String bank5;
    @Column(name = "accountNumber5")
    public String accountNumber5;
    @Column(name = "amount5")
    public double amount5;
    @Column(name = "bank6")
    public String bank6;
    @Column(name = "accountNumber6")
    public String accountNumber6;
    @Column(name = "amount6")
    public double amount6;
    @Column(name = "bank7")
    public String bank7;
    @Column(name = "accountNumber7")
    public String accountNumber7;
    @Column(name = "amount7")
    public double amount7;
    @Column(name = "bank8")
    public String bank8;
    @Column(name = "accountNumber8")
    public String accountNumber8;
    @Column(name = "amount8")
    public double amount8;
    @Column(name = "bank9")
    public String bank9;
    @Column(name = "accountNumber9")
    public String accountNumber9;
    @Column(name = "amount9")
    public double amount9;
    @Column(name = "bank10")
    public String bank10;
    @Column(name = "accountNumber10")
    public String accountNumber10;
    @Column(name = "amount10")
    public double amount10;

    @Column(name = "info1")
    public String info1;
    @Column(name = "info2")
    public String info2;
    @Column(name = "info3")
    public String info3;
    @Column(name = "info4")
    public String info4;
    @Column(name = "info5")
    public String info5;
    @Column(name = "bounceCheck1")
    public boolean bounceCheck1;
    @Column(name = "bounceCheck2")
    public boolean bounceCheck2;
    @Column(name = "bounceCheck3")
    public boolean bounceCheck3;
    @Column(name = "bounceCheck4")
    public boolean bounceCheck4;
    @Column(name = "bounceCheck5")
    public boolean bounceCheck5;
    @Column(name = "bounceCheck6")
    public boolean bounceCheck6;
    @Column(name = "bounceCheck7")
    public boolean bounceCheck7;
    @Column(name = "bounceCheck8")
    public boolean bounceCheck8;
    @Column(name = "bounceCheck9")
    public boolean bounceCheck9;
    @Column(name = "bounceCheck10")
    public boolean bounceCheck10;
    @Column(name = "allCheck")
    public String allCheck;
    @Column(name = "allBounceCheck")
    public String allBounceCheck;

    
    public MultiPayment() {
        cashier = UserInfo.getUserName();
        paymentDate = constants.Constants.useDate;
//        or1 = new CashierDailyBooklet().extractNextOR(),"";
        schoolYear = springbean.SchoolConfig.getSchoolYear();
    }

    @Override
    public void save() {
        super.save();
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "schoolYear","payor", "cashier");
    }

    public static void main(String[] args) {
        view(MultiPayment.class);
    }

    public String getAllBounceCheck() {
        return allBounceCheck;
    }

    public void setAllBounceCheck(String allBounceCheck) {
        this.allBounceCheck = allBounceCheck;
    }

    public String getAllCheck() {
        return allCheck;
    }

    public void setAllCheck(String allCheck) {
        this.allCheck = allCheck;
    }

    public boolean isBounceCheck1() {
        return bounceCheck1;
    }

    public void setBounceCheck1(boolean bounceCheck1) {
        this.bounceCheck1 = bounceCheck1;
    }

    public boolean isBounceCheck10() {
        return bounceCheck10;
    }

    public void setBounceCheck10(boolean bounceCheck10) {
        this.bounceCheck10 = bounceCheck10;
    }

    public boolean isBounceCheck2() {
        return bounceCheck2;
    }

    public void setBounceCheck2(boolean bounceCheck2) {
        this.bounceCheck2 = bounceCheck2;
    }

    public boolean isBounceCheck3() {
        return bounceCheck3;
    }

    public void setBounceCheck3(boolean bounceCheck3) {
        this.bounceCheck3 = bounceCheck3;
    }

    public boolean isBounceCheck4() {
        return bounceCheck4;
    }

    public void setBounceCheck4(boolean bounceCheck4) {
        this.bounceCheck4 = bounceCheck4;
    }

    public boolean isBounceCheck5() {
        return bounceCheck5;
    }

    public void setBounceCheck5(boolean bounceCheck5) {
        this.bounceCheck5 = bounceCheck5;
    }

    public boolean isBounceCheck6() {
        return bounceCheck6;
    }

    public void setBounceCheck6(boolean bounceCheck6) {
        this.bounceCheck6 = bounceCheck6;
    }

    public boolean isBounceCheck7() {
        return bounceCheck7;
    }

    public void setBounceCheck7(boolean bounceCheck7) {
        this.bounceCheck7 = bounceCheck7;
    }

    public boolean isBounceCheck8() {
        return bounceCheck8;
    }

    public void setBounceCheck8(boolean bounceCheck8) {
        this.bounceCheck8 = bounceCheck8;
    }

    public boolean isBounceCheck9() {
        return bounceCheck9;
    }

    public void setBounceCheck9(boolean bounceCheck9) {
        this.bounceCheck9 = bounceCheck9;
    }

    public String getAccountNumber1() {
        return accountNumber1;
    }

    public void setAccountNumber1(String accountNumber1) {
        this.accountNumber1 = accountNumber1;
    }

    public String getAccountNumber10() {
        return accountNumber10;
    }

    public void setAccountNumber10(String accountNumber10) {
        this.accountNumber10 = accountNumber10;
    }

    public String getAccountNumber2() {
        return accountNumber2;
    }

    public void setAccountNumber2(String accountNumber2) {
        this.accountNumber2 = accountNumber2;
    }

    public String getAccountNumber3() {
        return accountNumber3;
    }

    public void setAccountNumber3(String accountNumber3) {
        this.accountNumber3 = accountNumber3;
    }

    public String getAccountNumber4() {
        return accountNumber4;
    }

    public void setAccountNumber4(String accountNumber4) {
        this.accountNumber4 = accountNumber4;
    }

    public String getAccountNumber5() {
        return accountNumber5;
    }

    public void setAccountNumber5(String accountNumber5) {
        this.accountNumber5 = accountNumber5;
    }

    public String getAccountNumber6() {
        return accountNumber6;
    }

    public void setAccountNumber6(String accountNumber6) {
        this.accountNumber6 = accountNumber6;
    }

    public String getAccountNumber7() {
        return accountNumber7;
    }

    public void setAccountNumber7(String accountNumber7) {
        this.accountNumber7 = accountNumber7;
    }

    public String getAccountNumber8() {
        return accountNumber8;
    }

    public void setAccountNumber8(String accountNumber8) {
        this.accountNumber8 = accountNumber8;
    }

    public String getAccountNumber9() {
        return accountNumber9;
    }

    public void setAccountNumber9(String accountNumber9) {
        this.accountNumber9 = accountNumber9;
    }

    public double getAmount1() {
        return amount1;
    }

    public void setAmount1(double amount1) {
        this.amount1 = amount1;
    }

    public double getAmount10() {
        return amount10;
    }

    public void setAmount10(double amount10) {
        this.amount10 = amount10;
    }

    public double getAmount2() {
        return amount2;
    }

    public void setAmount2(double amount2) {
        this.amount2 = amount2;
    }

    public double getAmount3() {
        return amount3;
    }

    public void setAmount3(double amount3) {
        this.amount3 = amount3;
    }

    public double getAmount4() {
        return amount4;
    }

    public void setAmount4(double amount4) {
        this.amount4 = amount4;
    }

    public double getAmount5() {
        return amount5;
    }

    public void setAmount5(double amount5) {
        this.amount5 = amount5;
    }

    public double getAmount6() {
        return amount6;
    }

    public void setAmount6(double amount6) {
        this.amount6 = amount6;
    }

    public double getAmount7() {
        return amount7;
    }

    public void setAmount7(double amount7) {
        this.amount7 = amount7;
    }

    public double getAmount8() {
        return amount8;
    }

    public void setAmount8(double amount8) {
        this.amount8 = amount8;
    }

    public double getAmount9() {
        return amount9;
    }

    public void setAmount9(double amount9) {
        this.amount9 = amount9;
    }

    public double getAmountPerson1() {
        return amountPerson1;
    }

    public void setAmountPerson1(double amountPerson1) {
        this.amountPerson1 = amountPerson1;
    }

    public double getAmountPerson10() {
        return amountPerson10;
    }

    public void setAmountPerson10(double amountPerson10) {
        this.amountPerson10 = amountPerson10;
    }

    public double getAmountPerson2() {
        return amountPerson2;
    }

    public void setAmountPerson2(double amountPerson2) {
        this.amountPerson2 = amountPerson2;
    }

    public double getAmountPerson4() {
        return amountPerson4;
    }

    public void setAmountPerson4(double amountPerson4) {
        this.amountPerson4 = amountPerson4;
    }

    public double getAmountPerson5() {
        return amountPerson5;
    }

    public void setAmountPerson5(double amountPerson5) {
        this.amountPerson5 = amountPerson5;
    }

    public double getAmountPerson6() {
        return amountPerson6;
    }

    public void setAmountPerson6(double amountPerson6) {
        this.amountPerson6 = amountPerson6;
    }

    public double getAmountPerson7() {
        return amountPerson7;
    }

    public void setAmountPerson7(double amountPerson7) {
        this.amountPerson7 = amountPerson7;
    }

    public double getAmountPerson8() {
        return amountPerson8;
    }

    public void setAmountPerson8(double amountPerson8) {
        this.amountPerson8 = amountPerson8;
    }

    public double getAmountPerson9() {
        return amountPerson9;
    }

    public void setAmountPerson9(double amountPerson9) {
        this.amountPerson9 = amountPerson9;
    }

    public double getAmountPerson3() {
        return amountPerson3;
    }

    public void setAmountPerson3(double amountPerson3) {
        this.amountPerson3 = amountPerson3;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBank1() {
        return bank1;
    }

    public void setBank1(String bank1) {
        this.bank1 = bank1;
    }

    public String getBank10() {
        return bank10;
    }

    public void setBank10(String bank10) {
        this.bank10 = bank10;
    }

    public String getBank2() {
        return bank2;
    }

    public void setBank2(String bank2) {
        this.bank2 = bank2;
    }

    public String getBank3() {
        return bank3;
    }

    public void setBank3(String bank3) {
        this.bank3 = bank3;
    }

    public String getBank4() {
        return bank4;
    }

    public void setBank4(String bank4) {
        this.bank4 = bank4;
    }

    public String getBank5() {
        return bank5;
    }

    public void setBank5(String bank5) {
        this.bank5 = bank5;
    }

    public String getBank6() {
        return bank6;
    }

    public void setBank6(String bank6) {
        this.bank6 = bank6;
    }

    public String getBank7() {
        return bank7;
    }

    public void setBank7(String bank7) {
        this.bank7 = bank7;
    }

    public String getBank8() {
        return bank8;
    }

    public void setBank8(String bank8) {
        this.bank8 = bank8;
    }

    public String getBank9() {
        return bank9;
    }

    public void setBank9(String bank9) {
        this.bank9 = bank9;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public String getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(String checkNum) {
        this.checkNum = checkNum;
    }

    public String getFullAmount() {
        return fullAmount;
    }

    public void setFullAmount(String fullAmount) {
        this.fullAmount = fullAmount;
    }

    public String getInfo1() {
        return info1;
    }

    public void setInfo1(String info1) {
        this.info1 = info1;
    }

    public String getInfo2() {
        return info2;
    }

    public void setInfo2(String info2) {
        this.info2 = info2;
    }

    public String getInfo3() {
        return info3;
    }

    public void setInfo3(String info3) {
        this.info3 = info3;
    }

    public String getInfo4() {
        return info4;
    }

    public void setInfo4(String info4) {
        this.info4 = info4;
    }

    public String getInfo5() {
        return info5;
    }

    public void setInfo5(String info5) {
        this.info5 = info5;
    }

    public String getOr1() {
        return or1;
    }

    public void setOr1(String or1) {
        this.or1 = or1;
    }

    public String getOr10() {
        return or10;
    }

    public void setOr10(String or10) {
        this.or10 = or10;
    }

    public String getOr2() {
        return or2;
    }

    public void setOr2(String or2) {
        this.or2 = or2;
    }

    public String getOr3() {
        return or3;
    }

    public void setOr3(String or3) {
        this.or3 = or3;
    }

    public String getOr4() {
        return or4;
    }

    public void setOr4(String or4) {
        this.or4 = or4;
    }

    public String getOr5() {
        return or5;
    }

    public void setOr5(String or5) {
        this.or5 = or5;
    }

    public String getOr6() {
        return or6;
    }

    public void setOr6(String or6) {
        this.or6 = or6;
    }

    public String getOr7() {
        return or7;
    }

    public void setOr7(String or7) {
        this.or7 = or7;
    }

    public String getOr8() {
        return or8;
    }

    public void setOr8(String or8) {
        this.or8 = or8;
    }

    public String getOr9() {
        return or9;
    }

    public void setOr9(String or9) {
        this.or9 = or9;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getPaymentId1() {
        return paymentId1;
    }

    public void setPaymentId1(int paymentId1) {
        this.paymentId1 = paymentId1;
    }

    public int getPaymentId10() {
        return paymentId10;
    }

    public void setPaymentId10(int paymentId10) {
        this.paymentId10 = paymentId10;
    }

    public int getPaymentId2() {
        return paymentId2;
    }

    public void setPaymentId2(int paymentId2) {
        this.paymentId2 = paymentId2;
    }

    public int getPaymentId3() {
        return paymentId3;
    }

    public void setPaymentId3(int paymentId3) {
        this.paymentId3 = paymentId3;
    }

    public int getPaymentId4() {
        return paymentId4;
    }

    public void setPaymentId4(int paymentId4) {
        this.paymentId4 = paymentId4;
    }

    public int getPaymentId5() {
        return paymentId5;
    }

    public void setPaymentId5(int paymentId5) {
        this.paymentId5 = paymentId5;
    }

    public int getPaymentId6() {
        return paymentId6;
    }

    public void setPaymentId6(int paymentId6) {
        this.paymentId6 = paymentId6;
    }

    public int getPaymentId7() {
        return paymentId7;
    }

    public void setPaymentId7(int paymentId7) {
        this.paymentId7 = paymentId7;
    }

    public int getPaymentId8() {
        return paymentId8;
    }

    public void setPaymentId8(int paymentId8) {
        this.paymentId8 = paymentId8;
    }

    public int getPaymentId9() {
        return paymentId9;
    }

    public void setPaymentId9(int paymentId9) {
        this.paymentId9 = paymentId9;
    }

    public String getPayor() {
        return payor;
    }

    public void setPayor(String payor) {
        this.payor = payor;
    }

    public int getPersonId1() {
        return personId1;
    }

    public void setPersonId1(int personId1) {
        this.personId1 = personId1;
    }

    public int getPersonId10() {
        return personId10;
    }

    public void setPersonId10(int personId10) {
        this.personId10 = personId10;
    }

    public int getPersonId2() {
        return personId2;
    }

    public void setPersonId2(int personId2) {
        this.personId2 = personId2;
    }

    public int getPersonId3() {
        return personId3;
    }

    public void setPersonId3(int personId3) {
        this.personId3 = personId3;
    }

    public int getPersonId4() {
        return personId4;
    }

    public void setPersonId4(int personId4) {
        this.personId4 = personId4;
    }

    public int getPersonId5() {
        return personId5;
    }

    public void setPersonId5(int personId5) {
        this.personId5 = personId5;
    }

    public int getPersonId6() {
        return personId6;
    }

    public void setPersonId6(int personId6) {
        this.personId6 = personId6;
    }

    public int getPersonId7() {
        return personId7;
    }

    public void setPersonId7(int personId7) {
        this.personId7 = personId7;
    }

    public int getPersonId8() {
        return personId8;
    }

    public void setPersonId8(int personId8) {
        this.personId8 = personId8;
    }

    public int getPersonId9() {
        return personId9;
    }

    public void setPersonId9(int personId9) {
        this.personId9 = personId9;
    }

    public String getPlan1() {
        return plan1;
    }

    public void setPlan1(String plan1) {
        this.plan1 = plan1;
    }

    public String getPlan10() {
        return plan10;
    }

    public void setPlan10(String plan10) {
        this.plan10 = plan10;
    }

    public String getPlan2() {
        return plan2;
    }

    public void setPlan2(String plan2) {
        this.plan2 = plan2;
    }

    public String getPlan3() {
        return plan3;
    }

    public void setPlan3(String plan3) {
        this.plan3 = plan3;
    }

    public String getPlan4() {
        return plan4;
    }

    public void setPlan4(String plan4) {
        this.plan4 = plan4;
    }

    public String getPlan5() {
        return plan5;
    }

    public void setPlan5(String plan5) {
        this.plan5 = plan5;
    }

    public String getPlan6() {
        return plan6;
    }

    public void setPlan6(String plan6) {
        this.plan6 = plan6;
    }

    public String getPlan7() {
        return plan7;
    }

    public void setPlan7(String plan7) {
        this.plan7 = plan7;
    }

    public String getPlan8() {
        return plan8;
    }

    public void setPlan8(String plan8) {
        this.plan8 = plan8;
    }

    public String getPlan9() {
        return plan9;
    }

    public void setPlan9(String plan9) {
        this.plan9 = plan9;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
    
}
