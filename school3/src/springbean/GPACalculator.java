package springbean;

import java.io.File;
import java.io.FileNotFoundException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import util.BeanUtil;
import bean.Enrollment;
import bean.admin.AppConfig;

public class GPACalculator {
	public static double calculateGPA(Enrollment e) {
		String reportDir = AppConfig.getReportFolder();
		if (reportDir==null || reportDir.isEmpty()) {
			reportDir = "ESS";
		}
		String filename = BeanUtil.concat(constants.Constants.ROOT_FOLDER,"tmp/designing/clients/",reportDir.toUpperCase(),"/formula/gpaformula",e.gradeLevel,".js");
		File f = new File(filename);
		if (f.exists()) {
	        ScriptEngineManager factory = new ScriptEngineManager();
	        // create a JavaScript engine
	        ScriptEngine engine = factory.getEngineByName("JavaScript");
	        // evaluate JavaScript code from String
	        try {
				engine.eval(new java.io.FileReader(filename));

		        Invocable inv = (Invocable) engine;
		        // invoke the global function named "hello"
		        Object ret = inv.invokeFunction("runFormula", e);
		        return Double.parseDouble(ret.toString());
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (ScriptException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return -1;
	}
}
