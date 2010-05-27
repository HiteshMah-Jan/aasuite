package component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import springbean.AAAConfig;
import util.BeanUtil;
import util.Log;

import com.calipso.reportgenerator.client.ReportManagerService;
import com.calipso.reportgenerator.common.IReportManager;
import com.calipso.reportgenerator.common.InfoException;
import com.calipso.reportgenerator.common.LanguageTraslator;
import com.calipso.reportgenerator.common.ReportGeneratorConfiguration;
import com.calipso.reportgenerator.userinterface.ReportViewer;
import com.calipso.reportgenerator.userinterface.ReportViewerToolBar;

public class OlapRunner {

	private String callingProgramId = "";
	private String configPath;
	private String userId;
	private String reportDefinitionId;
	private String reportViewId = "";
	private String distributedHost = "";
	private boolean visibleActions = true;
	private Map params;
	private String microReportFileName = "";
	private boolean serverLocation = true;
	public static ReportGeneratorConfiguration config;
	public static ReportViewer reportViewer;

	public OlapRunner(String configPath, String args[]) {
		configPath = BeanUtil.concat("C:/tmp/olap/",AAAConfig.getInstance().getModule().toLowerCase());
		this.configPath = configPath;
		System.setProperty("ConfigPath", configPath);
		this.userId = getArg("UserId", args);
		this.reportDefinitionId = getArg("ReportDefinitionId", args);
		this.reportViewId = getArg("ReportViewId", args);
		this.callingProgramId = getArg("ProgramId", args);
		this.microReportFileName = getArg("MicroReportFileName", args);
		this.distributedHost = getArg("DistributedHost", args);
		if (getArg("Location", args).equalsIgnoreCase("local")) {
			serverLocation = false;
		}
		if (getArg("VisibleActions", args).equalsIgnoreCase("false")) {
			this.visibleActions = false;
		}
		initSystemParams();
		fillReportParams(args);
	}

	/**
	 * Si algun parametro es "" y esta especificado en las System.getProperties
	 * (no null ni ""), entonces setea el argumento
	 */
	private void initSystemParams() {
		if (configPath.equalsIgnoreCase("")
				&& System.getProperty("ConfigPath") != null
				&& !System.getProperty("ConfigPath").equalsIgnoreCase("")) {
			this.configPath = System.getProperty("ConfigPath");
		}
		if (userId.equalsIgnoreCase("") && System.getProperty("UserId") != null
				&& !System.getProperty("UserId").equalsIgnoreCase("")) {
			this.userId = System.getProperty("UserId");
		}
		if (reportDefinitionId.equalsIgnoreCase("")
				&& System.getProperty("ReportDefinitionId") != null
				&& !System.getProperty("ReportDefinitionId").equalsIgnoreCase(
						"")) {
			this.reportDefinitionId = System.getProperty("ReportDefinitionId");
		}
		if (distributedHost.equalsIgnoreCase("")
				&& System.getProperty("DistributedHost") != null
				&& !System.getProperty("DistributedHost").equalsIgnoreCase("")) {
			this.distributedHost = System.getProperty("DistributedHost");
		}
		if (reportViewId.equalsIgnoreCase("")
				&& System.getProperty("ReportViewId") != null
				&& !System.getProperty("ReportViewId").equalsIgnoreCase("")) {
			this.reportViewId = System.getProperty("ReportViewId");
		}
		if (callingProgramId.equalsIgnoreCase("")
				&& System.getProperty("CallingProgramId") != null
				&& !System.getProperty("CallingProgramId").equalsIgnoreCase("")) {
			this.callingProgramId = System.getProperty("CallingProgramId");
		}
		if (microReportFileName.equalsIgnoreCase("")
				&& System.getProperty("MicroReportFileName") != null
				&& !System.getProperty("MicroReportFileName").equalsIgnoreCase(
						"")) {
			this.microReportFileName = System
					.getProperty("MicroReportFileName");
		}
		if (serverLocation && System.getProperty("Location") != null
				&& !System.getProperty("Location").equalsIgnoreCase("")) {
			if (System.getProperty("Location").equalsIgnoreCase("local")) {
				this.serverLocation = false;
			}
		}
		if (visibleActions && System.getProperty("VisibleActions") != null
				&& !System.getProperty("VisibleActions").equalsIgnoreCase("")) {
			if (System.getProperty("VisibleActions").equalsIgnoreCase("false")) {
				this.visibleActions = false;
			}
		}
	}

	/**
	 * Llena los parámetros del reporte
	 */
	private void fillReportParams(String args[]) {
		String paramName;
		String paramValue;
		if (!this.reportDefinitionId.equals("")) {
			if (args != null) {
				int indexParam;
				int indexValue;
				for (int i = 0; i < args.length; i++) {
					indexParam = args[i].indexOf("Param-");
					indexValue = args[i].indexOf("=");
					if ((indexParam >= 0) && (indexValue >= 0)) {
						paramName = args[i].substring(("Param-").length()
								+ indexParam, indexValue);
						paramValue = args[i].substring(indexValue + 1, args[i]
								.length());
						getParams().put(paramName, paramValue);
					}
				}
			}
		}
	}

	private Map getParams() {
		if (params == null) {
			params = new HashMap();
		}
		return params;
	}

	private String getArg(String s, String args[]) {
		String returnValue = "";
		if (args != null) {
			int index;
			for (int i = 0; (i < args.length) && returnValue.equals(""); i++) {
				index = args[i].indexOf(s);
				if (index >= 0) {
					returnValue = args[i].substring(s.length() + index + 1,
							args[i].length());
				}
			}
		}
		return returnValue;
	}

	public static void main(String args[]) throws InfoException {
		AAAConfig.getInstance();
		OlapRunner reportViewerApp = new OlapRunner(BeanUtil.concat("C:/tmp/olap/",AAAConfig.getInstance().getModule().toLowerCase()), args);
		reportViewerApp.show();
		ReportViewerToolBar bar = reportViewerApp.reportViewer.getToolBar();
		JButton btn = new JButton("Freeze");
		bar.add(btn);
		reportViewerApp.reportViewer.setVisible(true);
	}

	public void show() throws InfoException {
		IReportManager service = ReportManagerService.getReportManagerService(configPath, null, distributedHost);
		checkLicence(service);
		boolean validate = true;
		if (validate) { 
			if (microReportFileName.equals("")) {
				reportViewer = new ReportViewer("root",
						reportDefinitionId, reportViewId, configPath, service,  
						visibleActions, params);
			} else {
				reportViewer = new ReportViewer("root",
						microReportFileName, serverLocation, configPath, service,
						visibleActions, params);
			}
			reportViewer.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
//			reportViewer.getJMenuBar().setVisible(false);
			reportViewer.setTitle("OLAP");
			reportViewer.setVisible(true);
			reportViewer.setVisible(false);
		} else {
			JOptionPane.showMessageDialog(null, LanguageTraslator
					.traslate("364"), LanguageTraslator.traslate("231"),
					JOptionPane.ERROR_MESSAGE);
			Log.out(LanguageTraslator.traslate("364"));
			System.exit(0);
		}
	}

	private void checkLicence(IReportManager service) {
		try {
			service.validateUser("root", "1", ReportManagerService.getConfiguration().getUsersRepositoryPath());
		} catch (InfoException e) {
			e.printStackTrace();
		}
	}
}
