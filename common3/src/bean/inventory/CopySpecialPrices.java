package bean.inventory;

import javax.persistence.Id;

import service.util.AbstractIBean;

public class CopySpecialPrices extends AbstractIBean{

	@Id
public String BPCode;
	public String BPName;
	
	
public static void main(String[] args) {
	view(CopySpecialPrices.class);
}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
