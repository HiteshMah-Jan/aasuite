/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import bean.reference.Allergy;
import template.report.AbstractReportTemplate;

/**
 *
 * @author Entokwaa
 */
public class ReportTester {

    public static void main(String[] args) throws Exception {
        AbstractReportTemplate.getInstance(Allergy.class).showReport();
    }
}
