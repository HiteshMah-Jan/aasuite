<%@ page import="java.io.*, java.util.*, java.util.zip.*, java.sql.*, javax.servlet.*, java.util.logging.*" %>
<%!
public static class DBCacheClient {

    private Connection getConnection() {
        Connection conn = null;
        try {
            String userName = "cachesoftlabs";
            String password = "Pass1word";
            String url = "jdbc:mysql://cachesoftlabs.db.6046479.hostedresource.com:3306/cachesoftlabs";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
//			System.out.println("Database connection established");
        } catch (Exception e) {
            e.printStackTrace();
//			System.err.println("Cannot connect to database server");
        }
        return conn;
    }

    private void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public byte[] getCache(CacheStruct cache) {
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
                obj = new ZipUtil().getBytes(b.getBinaryStream());
            } else {
                if (cache.getData() != null) {
                    System.out.println("Insert Request -- " + cache.getServiceName() + ":" + cache.getKey());
//					insert new cache
                    PreparedStatement pstmt2 = con.prepareStatement("INSERT INTO cache VALUES (?,?,?)");
                    pstmt2.setString(1, cache.getServiceName());
                    pstmt2.setString(2, cache.getKey());
                    byte[] b = obj = new ZipUtil().getBytes(cache.getData());
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
}
%>