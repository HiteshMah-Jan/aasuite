

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBCacheClient {

    private static String create =
            "CREATE TABLE  cache (  "
            + "	cacheservice varchar(100) NOT NULL, "
            + "	cachekey varchar(250) NOT NULL, "
            + "	cacheobject blob NOT NULL, "
            + "PRIMARY KEY (`cacheservice`,`cachekey`) )";
    static Properties properties;

    private static String getProperty(String key) {
        if (properties == null) {
            try {
                properties = new Properties();
                InputStream is = DBCacheClient.class.getResourceAsStream("/connection.properties");
                properties.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties.getProperty(key);
    }

    private static Connection getConnection() {
        Connection conn = null;
        try {
            String userName = getProperty("username");
            String password = getProperty("password");
            String url = "jdbc:mysql://" + getProperty("host") + ":" + getProperty("port") + "/" + getProperty("database");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
//			System.out.println("Database connection established");
        } catch (Exception e) {
            e.printStackTrace();
//			System.err.println("Cannot connect to database server");
        }
        return conn;
    }

    private static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setupDB() {
//        Connection con = getConnection();
//        try {
//            con.createStatement().execute(create);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closeConnection(con);
//        }
    }

    public static byte[] getCache(CacheStruct cache) {
        byte[] obj = null;
        Connection con = getConnection();
        try {
            PreparedStatement pstmt1 = con.prepareStatement("SELECT cacheobject FROM cache WHERE cacheservice=? AND cachekey=?");
            System.out.println("Cache Request -- " + cache);
            System.out.println("Cache Request -- " + cache.getServiceName() + ":" + cache.getKey());
            pstmt1.setString(1, cache.getServiceName());
            pstmt1.setString(2, cache.getKey());
            ResultSet rs = pstmt1.executeQuery();
            if (rs.next()) {
                System.out.println("Extract Request -- " + cache.getServiceName() + ":" + cache.getKey());
                Blob b = rs.getBlob(1);
                obj = ZipUtil.getBytes(b.getBinaryStream());
            } else {
                if (cache.getData() != null) {
                    System.out.println("Insert Request -- " + cache.getServiceName() + ":" + cache.getKey());
//					insert new cache
                    PreparedStatement pstmt2 = con.prepareStatement("INSERT INTO cache VALUES (?,?,?)");
                    pstmt2.setString(1, cache.getServiceName());
                    pstmt2.setString(2, cache.getKey());
                    byte[] b = obj = ZipUtil.getBytes(cache.getData());
                    InputStream is = new ByteArrayInputStream(obj);
                    pstmt2.setBinaryStream(3, is, b.length);
                    pstmt2.execute();
                    is.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }
        return obj;
    }

    public static void main(String[] args) {
        setupDB();
        CacheStruct c = new CacheStruct();
        c.setServiceName("serv");
        c.setKey("key222");
//		c.setData("data");
        getCache(c);
        getCache(c);
        getCache(c);
        getCache(c);
        getCache(c);
        getCache(c);
        getCache(c);
        getCache(c);
        getCache(c);
        getCache(c);
    }
}
