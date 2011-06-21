/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package template;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * @author Entokwaa
 */
@Retention(RUNTIME)
public @interface ActionButton {
    public String name();
    public String label();
    public String icon() default "";
    public boolean top() default false;   
    public boolean parentOnly() default true;   
    public String[] duties() default {""};
}
