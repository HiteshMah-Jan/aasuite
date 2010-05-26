/*
 * ServerServlet.java
 *
 * Created on Sep 16, 2007, 9:05:28 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import service.util.AbstractIBean;
import template.report.AbstractReportTemplate;
import util.BeanUtil;
import util.PanelUtil;

/**
 *
 * @author Budoy Entokwa
 */
public class ReportServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bean = request.getParameter("bean");
        String title = request.getParameter("title");
        Class cls = PanelUtil.getBeanClass(bean);
        util.Log.info("Report -> ",bean,":",title,":",cls.getSimpleName());
        Object obj = request.getSession().getAttribute(BeanUtil.concat(bean,"-",title));
        AbstractIBean ibean = (AbstractIBean) request.getSession().getAttribute(BeanUtil.concat(bean,"Obj"));
        byte[] b = null;
        if (obj instanceof template.Report) {
            template.Report rep = (template.Report) obj;
            b = AbstractReportTemplate.getInstance(rep).getReportFromFileTemplate(ibean);
        } else {
            b = AbstractReportTemplate.getInstance(cls).getAllRecordReport();
        }
        response.setContentType("application/pdf");
        response.setContentLength(b.length);
        response.setHeader("Content-disposition","filename=report.pdf");
        OutputStream out = response.getOutputStream();
        out.write(b);
        out.close();
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
