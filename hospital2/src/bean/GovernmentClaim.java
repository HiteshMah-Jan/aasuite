/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Charliemagne
 */
@Entity
@Table(name = "GovernmentClaim")
@UITemplate(template=TemplateSinglePage.class, gridCount=2, columnSearch={"dateClaimed", "amount"})
@Displays({
        @Display(name="dateClaimed"),
        @Display(name="amount",width=-1)
       
})
        @Reports( {
		

})
public class GovernmentClaim extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column (name= "opdId")
    public int opdId;
    @Column(name = "dateClaimed")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date dateClaimed;
    @Column(name = "amount")
    public double amount;

    
    
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDateClaimed() {
        return dateClaimed;
    }

    public void setDateClaimed(Date dateClaimed) {
        this.dateClaimed = dateClaimed;
    }

    public int getOpdId() {
        return opdId;
    }

    public void setOpdId(int opdId) {
        this.opdId = opdId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    
    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, this._Key());
        

    }
}

