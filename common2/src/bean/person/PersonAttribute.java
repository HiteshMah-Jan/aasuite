/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.person;

import bean.Person;
import service.util.AbstractIBean;

/**
 *
 * @author Entokwaa
 */
public abstract class PersonAttribute extends AbstractIBean {
    public abstract int getPersonId();

    public abstract Integer getSeq();

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public String getPerson() {
        if (getPersonId() == 0) {
            return "";
        }
        Person p = (Person) AbstractIBean.firstRecord("SELECT a FROM Person a WHERE a.personId="+getPersonId());
        return p.getFormattedTitle();
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonAttribute)) {
            return false;
        }
        PersonAttribute other = (PersonAttribute) object;
        if (this.getSeq() != other.getSeq() && (this.getSeq() == null || !this.getSeq().equals(other.getSeq()))) {
            return false;
        }
        return true;
    }

	@Override
	public void setupIndex() {
		runIndex(1, "personID");
	}
}
