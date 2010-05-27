package springbean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.swing.JLabel;

import constants.Constants;

import service.IService;
import service.ParamStruct;
import service.ReturnStruct;
import service.util.AbstractIBean;
import service.util.CallService;
import util.BeanUtil;
import util.DBClient;
import util.DateUtil;
import util.Log;
import util.PanelUtil;
import bean.Services;
import bean.reference.Department;

/**
 *
 * @author Budoy Entokwa
 */
public class AAAConfig implements IService {

    private static AAAConfig config;

    String title;
    String rootFolder = "c:/";
    String bootStrap;
    public static boolean server;


    public String getReportFolder() {
        return bean.admin.AppConfig.getReportFolder();
    }
    
    public String getBootStrap() {
        return bootStrap;
    }

    public void setBootStrap(String bootStrap) {
        this.bootStrap = bootStrap;
    }

    public String getRootFolder() {
        return rootFolder;
    }

    public void setRootFolder(String rootFolder) {
        this.rootFolder = rootFolder;
        constants.Constants.ROOT_FOLDER = rootFolder;
    }

    public static AAAConfig getInstance() {
    	return getInstance(null);
    }
    
    public static AAAConfig getServerInstance() {
        if (config==null) {
//        	Log.out("SERVER CALLED");
            config = new springbean.AAAConfig();
            server = true;
        }
        return config;
    }

    public static AAAConfig getInstance(String[] args) {
        if (config==null) {
            config = new springbean.AAAConfig();
            if (args!=null) {
            	config.setHost(args[0]);
            }
            if (args.length>1 && args[0].length() > 10) {
            	Constants.cachehost = args[1];
            }
//            call all services here
            List<Services> lst = DBClient.getListServerCache("SELECT a FROM Services a");
            if (lst!=null) {
                for (Services serv:lst) {
                	if (serv.code.contains(".")) {
                    	CallService.callService("", 1, serv.code);
                	}
                	else {
                    	CallService.callService("", 1, BeanUtil.concat("springbean.",serv.code));
                	}
                }
            }
        }
        return config;
    }
    
    public AAAConfig() {
    }

    public String getHost() {
        return constants.Constants.host;
    }

    public void setHost(String host) {
        constants.Constants.host = host;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        constants.Constants.appTitle = title;
    }

    public static void setup() {
    }
    
    public void setupDB(String table) {
        try {
            AbstractIBean b = (AbstractIBean) PanelUtil.getBeanClass(table).newInstance();
            if ("seq".equals(b._Key()) || "personId".equals(b._Key())) {
            	if (b.isSuperBean()) {
                    DBClient.runSQLNative("ALTER TABLE ",b._Table()," MODIFY COLUMN ",b._Key()," INTEGER NOT NULL DEFAULT NULL AUTO_INCREMENT;");
            	}
            }
            runTableAlter(b.getClass().getSimpleName());
            b.setupIndex();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setupDB(final JLabel lbl) {
    	Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
		        //test all beans sql
		        List<String> lst = test.XMLTester.getAllBeanNames();
		        for (String string : lst) {
		            setupDB(string);
		        }
		        for (String string : lst) {
		            DBClient.getFirstRecord("SELECT a FROM ",string," a");
		        }
		        lbl.setText("SETUP FINISH");
			}
    	});
    	t.start();
    }

    public void clearCache() {
        List<String> lst = test.XMLTester.getAllBeanNames();
        for (String string : lst) {
            try {
				AbstractIBean b = (AbstractIBean) PanelUtil.getBeanClass(string).newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    }

    public String getNow() {
        return DateUtil.formatDate(constants.Constants.useDate, "MMM dd, yyyy");
    }
    
    public Class bootStrapClass() {
        if (bootStrap!=null && !bootStrap.isEmpty()) {
            try {
                return Class.forName(bootStrap);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AAAConfig.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return getClass();
    }

    public void callSetup() {
        CallService.callService("", callSetup, AAAConfig.class.getName());
    }

    int callSetup = 1;
    
    @Override
    public ReturnStruct callService(ParamStruct param) {
        if (param.getActionCommand()==callSetup) {
            Department.list("SELECT a FROM Department a");
        }
        return null;
    }
    
    public static String getPackageType() {
        return "COMPLETE";
    }

    private static void outSQL(String bean) {
        Class cls = PanelUtil.getBeanClass(bean);
        try {
        	StringBuffer sb = new StringBuffer();
            AbstractIBean b = (AbstractIBean) cls.newInstance();
            String table = b._Table();
            Field[] flds = cls.getFields();
            String sql = "";
            for (Field f : flds) {
                try {
                    //check existence of field
                    String fname = f.getName();
                    Class type = f.getType();
                    String dtype = " VARCHAR ";

                    Column col = f.getAnnotation(Column.class);
                    if (col==null) {
                    	Log.out("FIELD PROBLEM for ",f.getName());
                    	continue;
                    }
                    if (type == double.class || type == Double.class) {
                        dtype = "DOUBLE";
                    } else if (type == int.class || type == Integer.class) {
                        dtype = "INTEGER";
                    } else if (type == boolean.class || type == Boolean.class) {
                        dtype = "BIT";
                    } else if (type == Date.class) {
                        dtype = "DATE";
                    }
                    int len = col.length();
                    if (len == 0) {
                        len = 50;
                    }
                    sql = BeanUtil.concat("ALTER TABLE ",table," ADD (",fname," ",dtype,"(",len,"));");
                    if (type==boolean.class || type==Date.class || type==double.class || type==int.class) {
                        sql = BeanUtil.concat("ALTER TABLE ",table," ADD (",fname," ",dtype,");");
                    }
                    sb.append(sql).append("\n");
                    if (type==boolean.class) {
                        sql = BeanUtil.concat("UPDATE ",table," SET ",fname,"=0 WHERE ",fname," is null;");
                        sb.append(sql).append("\n");
                    }
                } catch (Exception ex) {
                	ex.printStackTrace();
                	Log.out("ERROR ",sql);
                }
            }
        } catch (Exception e) {
        	Log.out("ERROR ",e.getMessage());
        }
    }

    static List<String> lstAlter = new ArrayList();
    public static void runTableAlter(final String bean) {
    	outSQL(bean);
        Class cls = PanelUtil.getBeanClass(bean);
        List<String> lst = new ArrayList<String>();
        try {
        	StringBuffer sb = new StringBuffer();
            AbstractIBean b = (AbstractIBean) cls.newInstance();
            String table = b._Table();
            Field[] flds = cls.getFields();
            String sql = "";
            for (Field f : flds) {
            	if (lstAlter.contains(BeanUtil.concat(table,"-",f.getName()))) {
            		Log.out("ALREADY RUN ",table,"-",f.getName());
            		continue;
            	}
            	lstAlter.add(BeanUtil.concat(table,"-",f.getName()));
                try {
                    //check existence of field
                    String fname = f.getName();
                    Class type = f.getType();
                    String dtype = " VARCHAR ";

                    Column col = f.getAnnotation(Column.class);
                    if (col==null) {
                    	Log.out("FIELD PROBLEM for ",f.getName());
                    	continue;
                    }
                    if (type == double.class || type == Double.class) {
                        dtype = "DOUBLE";
                    } else if (type == int.class || type == Integer.class) {
                        dtype = "INTEGER";
                    } else if (type == boolean.class || type == Boolean.class) {
                        dtype = "BIT";
                    } else if (type == Date.class) {
                        dtype = "DATE";
                    }
                    int len = col.length();
                    if (len == 0) {
                        len = 50;
                    }
                    sql = BeanUtil.concat("ALTER TABLE ",table," ADD (",fname," ",dtype,"(",len,"));");
                    if (type==boolean.class || type==Date.class || type==double.class || type==int.class) {
                        sql = BeanUtil.concat("ALTER TABLE ",table," ADD (",fname," ",dtype,");");
                    }
                    Log.out("RUN ",sql);
//                    sb.append(sql).append("\n");
                    lst.add(sql);
                    if (type==boolean.class) {
                        sql = BeanUtil.concat("UPDATE ",table," SET ",fname,"=0 WHERE ",fname," is null;");
//                        sb.append(sql).append("\n");
                        lst.add(sql);
                    }
                } catch (Exception ex) {
                	ex.printStackTrace();
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        DBClient.runBatchNative(lst);
    }

    private String module;

	public String getModule() {
		if (module==null) {
			if (title==null) title = "SchoolSoft";
			module = title.toLowerCase().replace("soft", "");
		}
		constants.Constants.module = module;
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}
}
