/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.Center;
import bean.Member;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSearchOnly;

/**
 *
 * @author disney
 */
@UITemplate(template = TemplateSearchOnly.class, gridCount=4, columnSearch = {"lastName", "firstName", "center"})
@Displays({
        @Display(name="lastName"),
        @Display(name="firstName"),
        @Display(name="center", type="PopSearch", linktoBean=Center.class),
        @Display(name="shareDate")
})
public class SearchAllMembersOnlyExt extends Member {
      public static void main(String[] args) {
        view(SearchAllMembersOnlyExt.class);
    }
}
