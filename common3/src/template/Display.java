package template;


import bean.reference.Department;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
public @interface Display {
    public String name();
    public String label() default "";  //if no value, then use the column name
    public String searchLabel() default "";  //if no value, then use the column name
    public String tooltip() default "";
    public String button() default "";
    public String type() default "Any";    //can be Label, Text, TextArea, Combo, PopSearch
    public int labelWidth() default -1;
    public int width() default constants.Constants.DEF_WIDTH;
    public int height() default 100;
    public String defaultValue() default "";
    public boolean upCase() default true;
    public String sqlCombo() default "";
    public String[] modelCombo() default {""};
    public String linkto() default "";
    public Class linktoBean() default String.class;
    public String[] linktoColumns() default {""};
    public String linktoBeanAddWhere() default "";
    public boolean mandatory() default false;
    public boolean hidden() default false;
    public String users() default "";
    public String groups() default "";
    public String[] duties() default {""};
    public String[] viewOnDuties() default {""};
    public int gridLabelWidth() default 1;
    public int gridFieldWidth() default 1;
    public boolean addInfoOnly() default false;
    public int startCount() default 1;
    public int endCount() default 10;
    public boolean overrideMandatory() default false;
    public boolean withHelp() default false;
    public String[] mergeFields() default {""};
    public boolean verticalMerge() default false;
    public boolean hideLabel() default false;
    public boolean labelTop() default false;
    public boolean withColon() default true;
    public String leftLabel() default "";
    public int maxChar() default -1;
    public boolean enabled() default true;
    public boolean noLabel() default false;
    public String fieldPrefix() default "";
}
