/*
 * ValidateUtil.java
 *
 * Created on Aug 26, 2007, 10:23:39 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.io.IOException;
import org.apache.commons.validator.ValidatorException;
import org.apache.commons.validator.ValidatorResources;
import org.apache.commons.validator.ValidatorResults;
import org.xml.sax.SAXException;

/**
 *
 * @author Budoy Entokwa
 */
public class ValidateUtil {
	static ValidatorResources resources;
	static ValidateUtil util;

	public static ValidateUtil getInstance() {
		if (util==null) util = new ValidateUtil();
		return util;
	}
	
    public boolean isValid(Object obj) throws IOException, SAXException, ValidatorException {
		if (resources==null) {
	        java.io.InputStream in = DataUtil.getResource("BeanValidator.xml");
		    resources = new ValidatorResources(in);
		}
        org.apache.commons.validator.Validator validator = new org.apache.commons.validator.Validator(resources, obj.getClass().getName());
		validator.setParameter(org.apache.commons.validator.Validator.BEAN_PARAM, obj);
        ValidatorResults value = validator.validate();
		value.getPropertyNames();
		return true;
    }
	
}
