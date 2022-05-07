/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import domain.UserRole;
import exceptions.RecordNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Boolean.parseBoolean;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author stoke
 */
public class UserRoleServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, RecordNotFoundException {
       HttpSession session = request.getSession();
                
        String url = "/userRoles.jsp";
        String option = request.getParameter("option");
        
        if(option.equals("users")){
            url = "/userRoles.jsp";
            
            
        String message = "";
                
        ArrayList<UserRole> ur = new ArrayList<UserRole>();
        try{
            ur = UserRole.getUserRoles();
            session.setAttribute("userRoles", ur);
        }
        
        catch(Exception e){
            url = "/main.jsp";
            message = e.getMessage();
        }
        
        finally{
            request.setAttribute("message", message);
            session.setAttribute("userRole", ur);
        }
        }
        if (option.equals("save")){
            System.out.println("inside option = save");
            UserRole ur = (UserRole)session.getAttribute("updateUserRole");
            
            String userType = request.getParameter("userType");
            System.out.println("userType = " + userType);
            String updateUserRoles = request.getParameter("updateUserRoles");
             System.out.println("updateUserRoles = " + updateUserRoles);
            String calculatePayroll = request.getParameter("calculatePayroll");
             System.out.println("calculatePayroll = " + calculatePayroll);
            String viewPayroll = request.getParameter("viewPayroll");
             System.out.println("viewPayroll = " + viewPayroll);
            String allTimecards = request.getParameter("allTimecards");
             System.out.println("allTimecards = " + allTimecards);
            String ownTimecards = request.getParameter("ownTimecards");
             System.out.println("ownTimecards = " + ownTimecards);
            
            Boolean updateUR, calcPayroll, viewPay, allTC, ownTC;
            
            updateUR = parseBoolean(updateUserRoles);
            System.out.println("updateUR = " + updateUR);
            calcPayroll = parseBoolean(calculatePayroll);
            System.out.println("calcPayroll = " + calcPayroll);
            viewPay = parseBoolean(viewPayroll);
            System.out.println("viewPay = " + viewPay);
            allTC = parseBoolean(allTimecards);
            System.out.println("allTC = " + allTC);
            ownTC = parseBoolean(ownTimecards);
            System.out.println("ownTC = " + ownTC);
            System.out.println("Setting Attributes");
            ur.setUpdateUserRoles(updateUR);
            ur.setCalculatePayroll(calcPayroll);
            ur.setViewPayroll(viewPay);
            ur.setAllTimecards(allTC);
            ur.setOwnTimecards(ownTC);
                
            if (ur.getUserTypeID() < 0) {
                System.out.println("adding user role");
                ur.add();
            }
            
            else {
                System.out.println("Calling update");
                ur.update();
            }
            
            ArrayList userRoles = UserRole.getUserRoles();
            System.out.println(userRoles);
            session.setAttribute("userRoles", userRoles);
            url = "/userRoles.jsp";
        }
        
        if (option.equals("update")){
            String idString = request.getParameter("userTypeID");
            int id = Integer.parseInt(idString);
            UserRole ur = UserRole.find(id);
            session.setAttribute("updateUserRole", ur);
            System.out.println("User role:   " + ur);
            url = "/updateUserRole.jsp";
        }
        if (option.equals("delete")){
            String idString = request.getParameter("userTypeID");
            int id = Integer.parseInt(idString);
            UserRole ur = UserRole.find(id);
            ur.delete();
            ArrayList userRoles = UserRole.getUserRoles();
            session.setAttribute("userRoles", userRoles);
            url = "/userRoles.jsp";
        }
        System.out.println("forwarding request: " + url);
         getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
        } catch (RecordNotFoundException ex) {
            Logger.getLogger(UserRoleServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
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
        } catch (RecordNotFoundException ex) {
            Logger.getLogger(UserRoleServlet.class.getName()).log(Level.SEVERE, null, ex);
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

}
