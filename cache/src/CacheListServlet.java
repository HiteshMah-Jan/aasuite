/*
 * ServerServlet.java
 *
 * Created on Sep 16, 2007, 9:05:28 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;


/**
 *
 * @author Budoy Entokwa
 */
public class CacheListServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        DBCacheClient.setupDB();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CacheStruct param = CacheStruct.read(request.getInputStream());
            byte[] data = DBCacheClient.getCache(param);
            CacheStruct.write(response.getOutputStream(), data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
