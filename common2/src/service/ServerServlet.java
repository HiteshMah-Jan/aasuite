/*
 * ServerServlet.java
 *
 * Created on Sep 16, 2007, 9:05:28 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Person;
import bean.Services;
import component.SpringCall;
import constants.Constants;
import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import service.util.CallService;
import springbean.AAAConfig;
import util.BeanUtil;
import util.DBClient;

/**
 *
 * @author Budoy Entokwa
 */
public class ServerServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
		new Person();
		try {
			Class.forName("bean.Student").newInstance();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        if (Constants.HELP_DIR == null) {
            Constants.HELP_DIR = getServletContext().getRealPath("/help");
        }
        AAAConfig.getServerInstance();
        List<Services> lst = DBClient.getList("SELECT a FROM Services a");
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
        AAAConfig.setup();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Log.out("SERVLET OK1.");
        ParamStruct param = ParamStruct.read(request.getInputStream());

        String serviceName = param.getServiceName();
        IService service = SpringCall.getService(serviceName);
        ReturnStruct ret = service.callService(param);

        response.setContentType("application/octet-stream");
//        if (ret!=null) response.setContentLength(ret.size());

//        Log.out("SERVLET OK2.");
        ParamStruct.write(response.getOutputStream(), ret);
//        Log.out("SERVLET OK3.");
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
