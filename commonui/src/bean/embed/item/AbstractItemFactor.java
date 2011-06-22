package bean.embed.item;

import javax.persistence.Embeddable;

@Embeddable
public class AbstractItemFactor {
	public String factor1;
	public String factor2;
	public String factor3;
	public String factor4;
	public String getFactor1() {
		return factor1;
	}
	public void setFactor1(String factor1) {
		this.factor1 = factor1;
	}
	public String getFactor2() {
		return factor2;
	}
	public void setFactor2(String factor2) {
		this.factor2 = factor2;
	}
	public String getFactor3() {
		return factor3;
	}
	public void setFactor3(String factor3) {
		this.factor3 = factor3;
	}
	public String getFactor4() {
		return factor4;
	}
	public void setFactor4(String factor4) {
		this.factor4 = factor4;
	}

}
