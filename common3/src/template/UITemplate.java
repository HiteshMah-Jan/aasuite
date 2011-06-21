package template;

import java.lang.annotation.Retention;
import template.report.ReportTemplate1;
import template.screen.TemplateDefault;
import template.screen.TemplateSearchOnly;
import template.screen.TemplateTabPage;
import template.screen.TemplateSinglePage;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
public @interface UITemplate {
    public int width() default 650;
    public String[] criteriaSearch() default {};
    public String[] columnSearch();
    public int topMargin() default 0;
    public Class template() default TemplateSinglePage.class;
    public String helpName() default "";
    public String title() default "";
    public String description() default "";
    public String select() default "";
    public String orderBy() default "";
    public int gridCount() default 4;

    //note: the layout will still be manipulated by the template use
    public int imagesWidth() default 150;
    public int imagesHeight() default 150;
    public int imagesLocation() default 2; //1=top, 2=right, 3=down, 4=left, 5=new tab
    public String[] imagesLabel() default {};
    public boolean showImages() default false;
    public boolean imageInsideForm() default false;
    public boolean imageEditable() default true;
    public boolean showFiles() default false;
    public boolean showForm() default true;
    public boolean showCriteria() default true;
    public boolean showChild() default true;
    public boolean showChart() default false;
    
    public boolean alertOnDelete() default true;

    //note: report template
    public String reportTitle() default "";
    public Class reportTemplate() default ReportTemplate1.class;
    public String[] reportFields() default {};
    public int reportGroups() default 0;

    public boolean canBackup() default true;
    public boolean deleteOnUpload() default true;
    
    public String rule() default "";

    public boolean canSave() default true;
    public boolean canDelete() default true;
    public boolean canNew() default true;
    public String sumFooter() default "";
    public String editableCol() default "";
    public String tableColumnWidth() default "";
    public boolean autoResizeTable() default true;
}
