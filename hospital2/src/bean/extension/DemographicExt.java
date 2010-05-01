package bean.extension;

import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;
import bean.Patient;
import bean.Physician;
import bean.reference.Department;

@UITemplate(template = TemplateTabSinglePage.class, gridCount = 4, 
		columnSearch = {"lastName", "firstName", "department"}, criteriaSearch = {"lastName","firstName","department"} ,showImages=true)
@Displays({
        
        @Display(name="lastName", enabled=false),
        @Display(name="firstName", enabled=false),
        @Display(name="department",width=-1,type="PopSearch", linktoBean=Department.class, enabled=false),
        @Display(name="physicianId",label="Physician",width=-1, type = "PopSearch", linktoBean=Physician.class, enabled=false),
        @Display(name="mobilePhone", enabled=false),
        @Display(name="email", enabled=false),
        @Display(name="im1", enabled=false),
        @Display(name="im2", enabled=false),
        
        
        @Display(name="section", addInfoOnly=true, label="Section/Location", enabled=false),
        @Display(name="birthDate", addInfoOnly=true, enabled=false),
        @Display(name="age",width=30, addInfoOnly=true, enabled=false),
        @Display(name="gender", type="Combo", modelCombo={"MALE", "FEMALE"}, addInfoOnly=true,width=-1, enabled=false),
        @Display(name="maritalStatus", type="Combo", modelCombo={"SINGLE","MARRIED"}, addInfoOnly=true, enabled=false),
        
     
        @Display(name="placeOfBirth", addInfoOnly=true,width=150, enabled=false),
        @Display(name="citizenship", addInfoOnly=true,width=150, enabled=false),
        @Display(name="religion", addInfoOnly=true,width=150, enabled=false),
        @Display(name="contactNumber", addInfoOnly=true,gridFieldWidth=3,width=-1, enabled=false),
        @Display(name="address", addInfoOnly=true,gridFieldWidth=3,width=-1, enabled=false)
       
})
@DisplayGroups({
    @DisplayGroup(title="Contact", fields={"mobilePhone", "email", "im1", "im2"})
})
public class DemographicExt extends Patient {

}
