/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import domain.Payroll;
import exceptions.RecordNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.DateFormat;
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
public class PayrollServlet extends HttpServlet {
    private static DateFormat dateFormatShort = DateFormat.getDateInstance(DateFormat.SHORT);

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
                
        String url = "/payroll.jsp";
        String option = request.getParameter("option");
        
        if(option.equals("list")){
            url = "/payrollList.jsp";
            
            String dateString = request.getParameter("date");
            Date date = new Date();
            try {
                date = dateFormatShort.parse(dateString);
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
            }
        String message = "";
                
        ArrayList<Payroll> pay = new ArrayList<Payroll>();
        try{
            System.out.println("Calculating Payroll \n \n \n");
            Payroll.calculatePayroll(date);
            pay = Payroll.getPayrollRecords();
            System.out.println("Adding payroll records \n \n \n");
            session.setAttribute("payroll", pay);
        }
        
        catch(Exception e){
            url = "/payroll.jsp";
            message = e.getMessage();
        }
        
        finally{
            request.setAttribute("message", message);
            session.setAttribute("payroll", pay);
        }
        }
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
            Logger.getLogger(PayrollServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PayrollServlet.class.getName()).log(Level.SEVERE, null, ex);
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
