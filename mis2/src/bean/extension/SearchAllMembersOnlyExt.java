/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.Member;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author disney
 */
@UITemplate(template = TemplateDefault.class, gridCount=4, columnSearch = {"lastName", "firstName", "center"})
@Displays({
        @Display(name="lastName"),
        @Display(name="firstName"),
        @Display(name="center"),
        @Display(name="shareDate")
})
public class SearchAllMembersOnlyExt extends Member {
      public static void main(String[] args) {
        view(SearchAllMembersOnlyExt.class);
    }
}
