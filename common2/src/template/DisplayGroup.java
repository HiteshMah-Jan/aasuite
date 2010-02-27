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
public @interface DisplayGroup {
    public String title();
    public String[] fields() default {};
    public int color() default 1;
    public int gridCount() default -1;
    public boolean addInfoOnly() default false;
}
