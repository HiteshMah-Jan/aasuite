/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package template;

import java.lang.annotation.Retention;
import template.screen.AddInfoTemplateDefault;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * @author Entokwaa
 */
@Retention(RUNTIME)
public @interface ParentAddInfo {
    public Class template() default AddInfoTemplateDefault.class;
    public String title();
    public String hideGroup() default "";
    public String[] displayFields() default {};
    public String users() default "";
    public String groups() default "";
    public String[] duties() default {""};
    public int gridCount() default -1;
}
