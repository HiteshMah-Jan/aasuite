/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import java.io.Serializable;
import template.*;
import template.screen.TemplateSearchOnly;

/**
 *
 * @author Entokwaa
 */
@UITemplate( template=TemplateSearchOnly.class,criteriaSearch={"activity","activityDate"},columnSearch={"activity","activityDate","location","activityFee","description"},gridCount=4,title="School Activity")
@Displays({
        @Display(name="activity",width=200),
        @Display(name="activityDate"),
        @Display(name="location",width=-1),
        @Display(name="activityFee"),
        
        @Display(name="description",gridFieldWidth=3,width=-1)
})
public class SchoolActivityExt extends bean.SchoolActivity implements Serializable {
    public static void main(String[] args) {
        view(SchoolActivityExt.class);
    }
}