package servlets;

import domain.Employee;
import domain.PayrollSystem;
import domain.UserRole;
import exceptions.LoginException;
import exceptions.RecordNotFoundException;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, RecordNotFoundException {
        
        HttpSession session = request.getSession();
        PayrollSystem.initialize();
                
        String url = "/main.jsp";
        String userID = request.getParameter("userID");
        String password = request.getParameter("password");
        String message = "";
                
        Employee employee = null;
        UserRole ur = null;
        try{
            employee = PayrollSystem.login(userID, password);
            ur = UserRole.findByRoleType(employee.getUserRole());
            System.out.println("employee = " + employee);
             System.out.println("userRole = " + ur);
        }
        
        catch(LoginException e){
            url = "/login.jsp";
            message = e.getMessage();
        }
        
        finally{
            request.setAttribute("message", message);
            session.setAttribute("employee", employee);
            session.setAttribute("userRole", ur);
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }
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
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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