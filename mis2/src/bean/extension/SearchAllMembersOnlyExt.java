/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSearchOnly;
import bean.Member;

/**
 *
 * @author disney
 */
@UITemplate(template = TemplateSearchOnly.class, gridCount=4, columnSearch = {"lastName", "firstName", "center"})
@Displays({
        @Display(name="lastName", type="Label"),
        @Display(name="firstName", type="Label"),
        @Display(name="center", type="Label"),
        @Display(name="shareDate", type="Label")
})
public class SearchAllMembersOnlyExt extends Member {
      public static void main(String[] args) {
        view(SearchAllMembersOnlyExt.class);
    }
}
