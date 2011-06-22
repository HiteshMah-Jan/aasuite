package bean.banking;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import bean.embed.AbstractPayment;

import service.util.AbstractIBean;

@Entity
@Table(name = "IncomingPayment")
public class IncomingPayment extends AbstractIBean implements Serializable{
	@Id
	public Integer seq;
    @Embedded
    public AbstractPayment payment;


	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}


	public Integer getSeq() {
		return seq;
	}


	public void setSeq(Integer seq) {
		this.seq = seq;
	}


	public AbstractPayment getPayment() {
		return payment;
	}


	public void setPayment(AbstractPayment payment) {
		this.payment = payment;
	}

}
