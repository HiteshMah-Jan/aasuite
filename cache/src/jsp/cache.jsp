<%@ page language="java" %>
<%@ page import="java.io.*, java.util.*, java.util.zip.*, java.sql.*, javax.servlet.*, java.util.logging.*" %>
<%@ include file="cacheStruct.jsp" %>
<%@ include file="dbCacheClient.jsp" %>
<%@ include file="zipUtil.jsp" %>
<%
        try {
            CacheStruct param = new CacheStruct().read(request.getInputStream());
            byte[] data = new DBCacheClient().getCache(param);
            new CacheStruct().write(response.getOutputStream(), data);
        } catch (Exception e) {
            e.printStackTrace();
        }

%>
