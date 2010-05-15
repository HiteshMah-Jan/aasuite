<%@ page import="java.io.*, java.util.*, java.util.zip.*, java.sql.*, javax.servlet.*, java.util.logging.*" %>
<%!
public static class CacheStruct implements Serializable {

    private String serviceName;
    private String key;
    private Object data;

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getKey() {
        return key;
    }

    public Object getData() {
        return data;
    }

    public CacheStruct read(InputStream is) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(is);
            List lst = (List) new ZipUtil().getUnzipObject((byte[]) ois.readObject());
            CacheStruct param = new CacheStruct();
            param.serviceName = lst.get(0).toString();
            param.key = lst.get(1).toString();
            if (lst.size() > 2) {
                param.data = lst.get(2);
            }
            return param;
        } catch (Exception ex) {            
            Logger.getLogger(CacheStruct.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ois.close();
                is.close();
            } catch (Exception ex) {
                Logger.getLogger(CacheStruct.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void write(ServletOutputStream os, byte[] data) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(os);
            List lst = new ArrayList();
            if (data == null) {
                lst.add(null);
            } else {
                lst.add(new ZipUtil().getObject(data));
            }
            oos.writeObject(new ZipUtil().getZipBytes(lst));
        } catch (IOException ex) {
            Logger.getLogger(CacheStruct.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                oos.flush();
                oos.close();
                os.flush();
                os.close();
            } catch (Exception ex) {
                Logger.getLogger(CacheStruct.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
%>