/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package template;

import java.lang.annotation.Retention;
import template.screen.ChildTemplateDefault;
import template.screen.ChildTemplateListPopup;
import template.screen.ChildTemplateListPopupDownButton;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * @author Entokwaa
 */
@Retention(RUNTIME)
public @interface ChildRecord {
    public Class entity();
    public String sql();
    public boolean cascadeDelete() default false;
    public boolean canSave() default true;
    public boolean canNew() default true;
    public boolean canDelete() default true;
    public boolean showForm() default true;
    public boolean showButtons() default true;
    public String cascadeDeleteSql() default "";
    public Class template() default ChildTemplateListPopupDownButton.class;
    public String title() default "";
    public String[] tableFields() default {""};
    public String users() default "";
    public String groups() default "";
    public String[] duties() default {""};
    public String[] fieldMapping() default {"",""};
    public boolean autoResizeTable() default true;
    public boolean sortable() default true;
}
