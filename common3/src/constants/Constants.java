package constants;

import java.awt.Color;
import java.awt.Cursor;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/*
 * Constants.java
 *
 * Created on Sep 15, 2007, 11:40:31 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.logging.Level;
import java.util.logging.Logger;

import util.BeanUtil;
import util.DataUtil;

/**
 *
 * @author Budoy Entokwa
 */
public class Constants {
    public static final boolean SimpleLookAndFeel = true;
    public static final Cursor busyCursor = new Cursor(Cursor.WAIT_CURSOR);
    public static final Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);

    public final static int DEF_WIDTH = 120;
    public final static int HALF_WIDTH = 60;
    public final static int QUARTER_WIDTH = 30;

    public static Date useDate = new Date();
    public final static String CANCELLED = "CANCELLED";
	
    public static String ROOT_FOLDER = "c:/";
    public static String defaultProcess;
    public static String productName;
    public static boolean IS_SINGLE_USER = false;
    public static String appTitle = "CargoSoft";
    public static String host = System.getProperty("host");
    public static String cachehost;
    public static String module = "";

    public static String REPORT_ADD_WHERE = "REPORT_ADD_WHERE";
//	public final static String host = "localhost";
//	public final static String port = "8081";
    public static String PERSISTENCE_UNIT = "pu";
    public static String PERSISTENCE_SERVICE = "service.Persistence";
    public static String LOGIN_SERVICE = "service.Login";

    public static final int SUCCESS = 2;
    public static final int FAIL = -1000004;

    public static final int INSERT = 1;
    public static final int DELETE = 2000003;
    public static final int UPDATE = 3;
    public static final int SELECT = 14;
    public static final int SELECT_LIST = 41;
    public static final int SELECT_LIST_SERVERCACHE = 10041;
    public static final int SELECT_BATCH = 419;
    public static final int SELECT_BATCH_SERVERCACHE = 100419;
    public static final int SELECT_AND_UPDATE_BATCH = 491;
    public static final int SELECT_BATCH_NOCACHE = 429;
    public static final int SELECT_NATIVE = 5;
    public static final int SELECT_SINGLE = 6;
    public static final int SELECT_SINGLE_COLUMN = 61;
    public static final int EXECUTE_ONLY = 7;
    public static final int EXECUTE_NATIVE_ONLY = 71;
    public static final int BATCH_BEANS = 8;
    public static final int SAVE_SUBRECORDS = 2;
    public static final int CACHE_RECORD = 232;
	public static final int RUN_BATCH = 1212;
	public static final int RUN_BATCH_NATIVE = 1213;

    public static final int FLIGHT_SCHEDULE_BUILD_FLIGHTS = 1001;
    public static final int SHIPMENT_TRACKER = 1002;
    public static final int AVAILABLE_FLIGHTS = 1003;

    public static final int ADD_HELP = 1000003;
    public static final int GET_HELP = 1000004;

    public static final int LOGIN = 2000003;
    public static String HELP_DIR;

    public static int PAYROLL_MID_MONTH = 1;
    public static int PAYROLL_MONTH = 2;

    public static int PAYROLL_TYPE = PAYROLL_MID_MONTH;

    public static String user="";
    public static String password="";
    public static String url="";
    public static String driver="";

    public static class Services {
        public static final String HELP_SERVICE = "service.HelpService";
        public static final String BOOKING_SERVICE = "service.cargo.BookingService";
        public static final String FLIGHT_SCHEDULE_SERVICE = "service.cargo.FlightService";
    }

    public static class DEFAULT_EVENT_RULE {
        public static final String NEW = "NEW";
        public static final String SAVE = "SAVE";
        public static final String DELETE = "DELETE";
        public static final String REFRESH = "REFRESH";
        public static final String ROBOT = "ROBOT";
    }

    public static final String licencename = "soft-labs.key";
    public static final String SUPPORT_EMAIL = "support@soft-labs.com";
    public static final String SALES_EMAIL = "sales@soft-labs.com";
    public static Map licenceMap;
    public static Map<String, String> configMap;

    private static Map getLicenceMap() {
        if (licenceMap != null) {
            return licenceMap;
        }
        FileInputStream fis = null;
        try {
            java.io.File f = new java.io.File(licencename);
            if (!f.exists()) {
                return null;
            }
            util.Log.info("Licence path == ",f.getAbsolutePath());
            fis = new java.io.FileInputStream(f);
            java.io.ObjectInputStream ois = new java.io.ObjectInputStream(fis);
            licenceMap = (java.util.Map) ois.readObject();
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
                Logger.getLogger("global").log(Level.SEVERE, null, ex);
            }
        }
        return licenceMap;
    }

    public static boolean isLicenced() {
        Map map = getLicenceMap();
        if (map == null) {
            return false;
        }
        String product = (String) map.get("product");
        if (!product.equalsIgnoreCase(productName)) {
            return false;
        }
        return true;
    }

    public static String getCustomerName() {
        if (isLicenced()) {
            Map map = getLicenceMap();
            return (String) map.get("customerName");
        }
        return BeanUtil.concat("Corporate licence [",SALES_EMAIL);
    }

    public static String getCompanyName() {
        if (isLicenced()) {
            Map map = getLicenceMap();
            return (String) map.get("companyName");
        }
        return BeanUtil.concat("Please email ",SALES_EMAIL);
    }

    public static String getPackageType() {
        if (isLicenced()) {
            Map map = getLicenceMap();
            return (String) map.get("packageType");
        }
        return BeanUtil.concat("Please email ",SALES_EMAIL);
    }

    public static String getLicenceType() {
        if (isLicenced()) {
            Map map = getLicenceMap();
            Integer i = (Integer) map.get("userCount");
            if (i == 1) {
                return "Single User Licence";
            } else {
                return BeanUtil.concat("Corporate licence [",i," users]");
            }
        }
        return BeanUtil.concat("Please email ",SALES_EMAIL);
    }

    
    public static Map<String, String> getConfigData() {
        if (configMap!=null) return configMap;
        java.io.FileInputStream fis = null;
        try {
            File f = new File("config.dat");
            if (!f.exists()) {
                saveConfig();
            }
            fis = new java.io.FileInputStream(f);
            java.io.ObjectInputStream ois = new java.io.ObjectInputStream(fis);
            configMap = (java.util.Map) ois.readObject();
            return configMap;
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger("global").log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public static void saveConfig() {
        try {
            File f = new java.io.File("config.dat");
            java.io.FileOutputStream fout = new java.io.FileOutputStream(f);
            java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(fout);
            Map<String, String> map = new HashMap<String, String>();
            map.put("host", BeanUtil.concat(host));
            map.put("user", BeanUtil.concat(user));
            map.put("password", BeanUtil.concat(password));
            map.put("url", BeanUtil.concat(url));
            map.put("driver", BeanUtil.concat(driver));
            oos.writeObject(map);
            oos.close();
        } catch (java.lang.Exception e) {
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, "FAILED SAVING LICENCE", e);
            return;
        }
    }

    public static boolean isValidLicence(File f) {
        java.io.FileInputStream fis = null;
        try {
            fis = new java.io.FileInputStream(f);
            java.io.ObjectInputStream ois = new java.io.ObjectInputStream(fis);
            licenceMap = (java.util.Map) ois.readObject();
            boolean b = isLicenced();
            if (b) {
                //save licence now
                saveLicence(licenceMap);
            }
            return b;
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger("global").log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public static void saveLicence(Map map) {
        try {
            File f = new java.io.File(constants.Constants.licencename);
            java.io.FileOutputStream fout = new java.io.FileOutputStream(f);
            java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(fout);
            oos.writeObject(map);
            oos.close();
        } catch (java.lang.Exception e) {
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, "FAILED SAVING LICENCE", e);
            return;
        }
    }

    public static void saveLicence(File map) {
        try {
            byte[] b = DataUtil.getBytes(map);
            ByteArrayInputStream bais = new ByteArrayInputStream(b);
            File f = new java.io.File(constants.Constants.licencename);
            DataUtil.writeToFile(bais, f);
            bais.close();
        } catch (java.lang.Exception e) {
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, "FAILED SAVING LICENCE", e);
            return;
        }
    }
    
    public static File getSaveLicence() {
        File f = new java.io.File(constants.Constants.licencename);
        return f;
    }

    public static class Products {
        public static final String CLINIC = "CLINIC";
        public static final String PRESCHOOL = "PRESCHOOL";
        public static final String INVENTORY = "INVENTORY";
        public static final String ACCOUNTING = "ACCOUNTING";
        public static final String SCHOOL = "SCHOOL";
        public static final String HOSPITAL = "HOSPITAL";
        public static final String POS = "POS";
        public static final String LOGISTICS = "LOGISTICS";
        public static final String CARGO = "CARGO";
        public static final String CRM = "CRM";
    }
    
    public static class ChartOfAccount {
        //assets
        public static final int Cash = 10000;
        public static final int CurrentAsset =  11000;
            public static final int LoansReceivable = 11100;
            public static final int AccountsReceivable = 11200;
            public static final int Sales =     11300;
            public static final int Expenses =  11400;
        public static final int FixedAsset = 12000;
        
        public static final int CurrentLiabilities = 20000;
            public static final int AccountsPayable = 20100;
        public static final int LongTermLiabilities = 21000;
        
        public static final int Capital = 30000;
        
        public static String getClassification(int accountNumber) {
            String classification = "NO CLASSIFICATION";
            String subclassification = "NO SUB CLASSIFICATION";
            if (accountNumber >= 10000 && accountNumber < 20000) {
                classification = "ASSET";
                if (accountNumber >= 10000 && accountNumber < 11099) {
                    subclassification = "CURRENT ASSET";
                }
                if (accountNumber >= 11100 && accountNumber < 11199) {
                    subclassification = "LOANS RECEIVABLE";
                }
                if (accountNumber >= 11200 && accountNumber < 11299) {
                    subclassification = "ACCOUNTS RECEIVABLE";
                }
                if (accountNumber >= 11300 && accountNumber < 11399) {
                    subclassification = "SALES";
                }
                if (accountNumber >= 11400 && accountNumber < 11999) {
                    subclassification = "EXPENSES";
                }
                if (accountNumber >= 12000 && accountNumber < 20000) {
                    subclassification = "FIXED ASSET";
                }
            }
            if (accountNumber >= 20000 && accountNumber < 30000) {
                classification = "LIABILITIES";

                if (accountNumber >= 20000 && accountNumber < 20099) {
                    subclassification = "CURRENT LIABILITIES";
                }
                if (accountNumber >= 20100 && accountNumber < 20999) {
                    subclassification = "ACCOUNTS PAYABLE";
                }
                if (accountNumber >= 21000 && accountNumber < 30000) {
                    subclassification = "LONG TERM LIABILITIES";
                }
            }
            if (accountNumber >= 30000 && accountNumber < 40000) {
                classification = "CAPITAL";
                subclassification = "CAPITAL AND EQUITY";
            }
            return classification;
        }
        
        public static String getSubClassification(int accountNumber) {
            String classification = "NO CLASSIFICATION";
            String subclassification = "NO SUB CLASSIFICATION";
            if (accountNumber >= 10000 && accountNumber < 20000) {
                classification = "ASSET";
                if (accountNumber >= 10000 && accountNumber < 11099) {
                    subclassification = "CURRENT ASSET";
                }
                if (accountNumber >= 11100 && accountNumber < 11199) {
                    subclassification = "LOANS RECEIVABLE";
                }
                if (accountNumber >= 11200 && accountNumber < 11299) {
                    subclassification = "ACCOUNTS RECEIVABLE";
                }
                if (accountNumber >= 11300 && accountNumber < 11399) {
                    subclassification = "SALES";
                }
                if (accountNumber >= 11400 && accountNumber < 11999) {
                    subclassification = "EXPENSES";
                }
                if (accountNumber >= 12000 && accountNumber < 20000) {
                    subclassification = "FIXED ASSET";
                }
            }
            if (accountNumber >= 20000 && accountNumber < 30000) {
                classification = "LIABILITIES";

                if (accountNumber >= 20000 && accountNumber < 20099) {
                    subclassification = "CURRENT LIABILITIES";
                }
                if (accountNumber >= 20100 && accountNumber < 20999) {
                    subclassification = "ACCOUNTS PAYABLE";
                }
                if (accountNumber >= 21000 && accountNumber < 30000) {
                    subclassification = "LONG TERM LIABILITIES";
                }
            }
            if (accountNumber >= 30000 && accountNumber < 40000) {
                classification = "CAPITAL";
                subclassification = "CAPITAL AND EQUITY";
            }
            return subclassification;
        }
    
        public static boolean isRevenue(int accountNumber) {
            return accountNumber >= 10000 && accountNumber < 11400;
        }
        
        public static boolean isExpense(int accountNumber) {
            boolean b1 = accountNumber >= 11400 && accountNumber < 12000;
//            if (b1) return true;
//            return accountNumber >= 20000 && accountNumber < 21000;
            return b1;
        }
        
        public static double getBalanceSheetAmount(int accountNumber, double debit, double credit) {
            if (isExpense(accountNumber)) return 0;
            return debit-credit;
        }        

        public static String getBalanceSheetGroup(int accountNumber) {
            if (isExpense(accountNumber)) return "";
            String classification = getClassification(accountNumber);
            if (classification.equalsIgnoreCase("ASSET")) {
                return "ASSET";
            }
            return "LIABILITIES AND EQUITIES";
        }        
    }

    public static void setupConfig() {
//        Log.out(".......................SETUP CONFIG.........................");
        Map<String, String> map = getConfigData();
        if (map==null) return;
        host = map.get("host");
        user = map.get("user");
        password = map.get("password");
        url = map.get("url");
        driver = map.get("driver");
    }
    
    public static Color panelBackground;
    public static Color labelColor;
}
