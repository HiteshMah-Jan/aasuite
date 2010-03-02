package bean.extension;

import bean.reference.GradeLevel;
import template.UITemplate;
import template.Displays;
import template.Display;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: Apr 7, 2009
 * Time: 8:26:44 AM
 * To change this template use File | Settings | File Templates.
 */
@UITemplate(template=template.screen.TemplateSinglePage.class,
    criteriaSearch={"code"},
    columnSearch={"code","elaItemCount","mathItemCount","sciItemCount","cognitiveItemCount","affectiveItemCount","psychomotorItemCount"}, gridCount=8)
@Displays({
        @Display(name="code",gridFieldWidth = 3),
        @Display(name="elaItemCount"),
        @Display(name="mathItemCount"),
        @Display(name="sciItemCount"),
        @Display(name="cognitiveItemCount"),
        @Display(name="affectiveItemCount"),
        @Display(name="psychomotorItemCount"),
        @Display(name="pracItemCount")
})
public class GradeLevelExamExt extends GradeLevel {
    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "code");
    }
}
