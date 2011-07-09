/*
 * Building.java
 *
 * Created on Dec 2, 2007, 12:37:12 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.reference;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import template.Display;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "ComboTypes")
@UITemplate(gridCount = 4, columnSearch = {"code","value"})
@DiscriminatorValue("OFFENSE")
@Displays({
        @Display(name="code",gridFieldWidth=3),
        @Display(name="value",gridFieldWidth=3,width=500),
        @Display(name="description",type="TextArea",gridFieldWidth=3,width=-1)
})
public class Offense extends ComboTypes implements Serializable {    
    public static void main(String[] args) {
        view(Offense.class);
    }
}
