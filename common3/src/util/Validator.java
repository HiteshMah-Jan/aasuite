/*
 * Validator.java
 *
 * Created on Aug 26, 2007, 11:20:31 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import org.apache.commons.validator.Arg;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.ValidatorException;
import org.apache.commons.validator.util.ValidatorUtils;

/**
 *
 * @author Budoy Entokwa
 */
public class Validator {

	public static String getMessage(String key) {
		return key;
	}
	
	public static boolean validateRequired(Object bean, Field field) throws ValidatorException {
        String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
        boolean b = GenericValidator.isBlankOrNull(value);
        Arg message = field.getArg(0);
        if (b) {
            throw new ValidatorException(getMessage(message.getKey()));
        }
        return true;
    }

    public static boolean validateByte(Object bean, Field field) throws ValidatorException {
        String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
        boolean b = GenericValidator.isByte(value);
        Arg message = field.getArg(0);
        if (!b) {
            throw new ValidatorException(getMessage(message.getKey()));
        }
        return b;
    }

    public static boolean validateShort(Object bean, Field field) throws ValidatorException {
        String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
        boolean b = GenericValidator.isShort(value);
        Arg message = field.getArg(0);
        if (!b) {
            throw new ValidatorException(getMessage(message.getKey()));
        }
        return b;
    }

    public static boolean validateInt(Object bean, Field field) throws ValidatorException {
        String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
        boolean b = GenericValidator.isInt(value);
        Arg message = field.getArg(0);
        if (!b) {
            throw new ValidatorException(getMessage(message.getKey()));
        }
        return b;
    }

    public static boolean validatePositive(Object bean, Field field) throws ValidatorException {
        String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
        boolean b = GenericValidator.isInt(value);
		b = b && Integer.parseInt(value) > 0;
        Arg message = field.getArg(0);
        if (!b) {
            throw new ValidatorException(getMessage(message.getKey()));
        }
        return b;
    }

    public static boolean validateLong(Object bean, Field field) throws ValidatorException {
        String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
        boolean b = GenericValidator.isLong(value);
        Arg message = field.getArg(0);
        if (!b) {
            throw new ValidatorException(getMessage(message.getKey()));
        }
        return b;
    }

    public static boolean validateFloat(Object bean, Field field) throws ValidatorException {
        String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
        boolean b = GenericValidator.isFloat(value);
        Arg message = field.getArg(0);
        if (!b) {
            throw new ValidatorException(getMessage(message.getKey()));
        }
        return b;
    }

    public static boolean validateDouble(Object bean, Field field) throws ValidatorException {
        String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
        boolean b = GenericValidator.isDouble(value);
        Arg message = field.getArg(0);
        if (!b) {
            throw new ValidatorException(getMessage(message.getKey()));
        }
        return b;
    }

    public static boolean validateEmail(Object bean, Field field) throws ValidatorException {
        String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
        boolean b = GenericValidator.isEmail(value);
        Arg message = field.getArg(0);
        if (!b) {
            throw new ValidatorException(getMessage(message.getKey()));
        }
        return b;
    }
}
