package template;

import java.lang.annotation.Retention;
import template.report.ReportTemplate1;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
public @interface Report {
    public String reportTitle() default "";
    public String reportFile() default "";
    public Class reportTemplate() default ReportTemplate1.class;
    public String reportSql() default "";
    public String[] reportFields() default {};
    public int reportGroups() default 0;
    public String users() default "";
    public String groups() default "";
    public String duties() default "";
}
