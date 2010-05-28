/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.reference;

import service.util.AbstractIBean;
import util.BeanUtil;

/**
 *
 * @author Entokwaa
 */
public abstract class ReferenceType extends AbstractIBean {

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "type");
    }
    
    public String getType() {
        return "";
    }

    public String getValue() {
        return "";
    }


    @Override
    public String toString() {
        return getType();
    }

    @Override
    public String getComboDisplay() {
        if (getType() == null || getType() == BeanUtil.concat(0)) {
            return "";
        } else {
            return getType();
        }
    }
}
