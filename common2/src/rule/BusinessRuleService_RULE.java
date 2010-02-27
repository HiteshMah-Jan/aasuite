/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rule;

import bean.admin.BusinessRuleService;
import javax.swing.JComponent;
import util.ScriptRunner;

/**
 *
 * @author Charliemagne Mark
 */
public class BusinessRuleService_RULE extends BusinessRuleWrapper {

    @Override
    public void runFocusLost(JComponent comp) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void runOnClick(JComponent comp) {
//        throw new UnsupportedOperationException("Not supported yet.");
        if ("btnFor".equals(comp.getName())) {
            appendFor();
        } else if ("btnFore".equals(comp.getName())) {
            appendFore();
        } else if ("btnIf".equals(comp.getName())) {
            appendIf();
        } else if ("btnIfElse".equals(comp.getName())) {
            appendIfElse();
        } else if ("btnTry".equals(comp.getName())) {
            appendTry();
        } else if ("btnPrint".equals(comp.getName())) {
            appendPrint();
        } else if ("btnSelect".equals(comp.getName())) {
            appendSelect();
        } else if ("btnSelectList".equals(comp.getName())) {
            appendSelectList();
        } else if ("btnTest".equals(comp.getName())) {
            appendTest();
        }
    }

    private void insertCode(String str) {
        String oldRule = getValue("rule");
        setValue("rule", oldRule + "\n" + str);
    }

    private void appendFor() {
        StringBuffer sb = new StringBuffer();
        sb.append("for (int i = 0; i < arr.length; i++) {\n");
        sb.append("\tObject object = (Object)arr[i];\n");
        sb.append("\t//code here");
        sb.append("}");
        insertCode(sb.toString());
    }

    private void appendFore() {
        String beanName = "Department";
        StringBuffer sb = new StringBuffer();
        sb.append("for (" + beanName + " bean : lst) {\n");
        sb.append("\t//code here");
        sb.append("}");
        insertCode(sb.toString());
    }

    private void appendIf() {
        StringBuffer sb = new StringBuffer();
        sb.append("if (sample.equals(\"sample\")) {\n");
        sb.append("\t//code here\n");
        sb.append("}");
        insertCode(sb.toString());
    }

    private void appendIfElse() {
        StringBuffer sb = new StringBuffer();
        sb.append("if (sample.equals(\"sample\")) {\n");
        sb.append("\t//code here\n");
        sb.append("}\n");
        sb.append("else {\n");
        sb.append("\t//code here\n");
        sb.append("}");
        insertCode(sb.toString());
    }

    private void appendPrint() {
        insertCode("printc sample;");
    }

    private void appendSelect() {
        String beanName = "Department";
        String key = "department";
        Object keyVal = "ACCOUNTING";
        insertCode(beanName + " bean = DBClient.getFirstRecord(\"SELECT a FROM " + beanName + " a WHERE a." + key + "=" + keyVal + "\");");
    }

    private void appendSelectList() {
        String beanName = "Department";
        insertCode("\nList<" + beanName + "> lst = DBClient.getList(\"SELECT a FROM " + beanName + " a\");");
    }

    private void appendTest() {
        String txt = getValue("rule");
        ScriptRunner.runGroovyBackground(txt, null, null);
    }

    private void appendTry() {
        StringBuffer sb = new StringBuffer();
        sb.append("try {\n");
        sb.append("\t//code here\n");
        sb.append("}\n");
        sb.append("catch (Exception e) {\n");
        sb.append("\te.printStackTrace();\n");
        sb.append("}");
        insertCode(sb.toString());
    }
}
