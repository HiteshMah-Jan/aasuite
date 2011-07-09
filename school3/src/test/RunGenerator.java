/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import bean.BeanTester;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Entokwaa
 */
public class RunGenerator {
    
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("StudentAcademicHistory");
        BeanTester.displayFields(list);
    }
}
