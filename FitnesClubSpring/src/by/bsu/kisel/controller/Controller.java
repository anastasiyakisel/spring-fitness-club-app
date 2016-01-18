/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.controller;

import by.bsu.kisel.command.Command;
import by.bsu.kisel.connectionpool.ConnectionPool;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.manager.MessageManager;
import by.bsu.kisel.manager.PathManager;
import by.bsu.kisel.model.RequestHelper;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Kisel Anastasia
 */
@WebServlet(name="Controller", urlPatterns={"/Controller"})
public class Controller extends HttpServlet {
    private static Logger log = Logger.getLogger(Controller.class);
    
    public static MessageManager MSG_MANAGER;
    
    public static WebApplicationContext webContext ;
    
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws ResourceCreationException 
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ResourceCreationException
            {                
            String page = null;            
            //defining command from jsp
            Command command =RequestHelper.getInstance().getCommand(request);
            page = command.execute(request);
            RequestDispatcher dispatcher =getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        
     }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (ResourceCreationException e) {
			e.printStackTrace();
		}
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (ResourceCreationException e) {			
			e.printStackTrace();
		}
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    @Override
    public void init() throws ServletException {
        super.init();
        PathManager.setAbsolutePath(getServletContext().getRealPath(PathManager.ROOT));
        webContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		MSG_MANAGER = (MessageManager)webContext.getBean("MessageManager");
    }
    
    public void destroy(){
        try {
            ConnectionPool.getInstance().releasePool();
        } catch (DAOSQLException ex) {
            log.error("Can't execute query!");
        }
        
    }
}
