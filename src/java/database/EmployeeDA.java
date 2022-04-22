package database;

import domain.Employee;
import domain.HourlyEmployee;
import domain.SalaryEmployee;
import exceptions.RecordNotFoundException;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class EmployeeDA {
    
    private static ArrayList<Employee> employees = new ArrayList<Employee>(5);
    
    public static void add(Employee emp) {
        employees.add(emp);
    }
    
    public static Employee find(int ID){
        for (int i = 0; i < employees.size(); i++)
            if (employees.get(i).getEmployeeID() == ID)
                return employees.get(i);
        return null;
    }
    
    public static Employee findByUserID(String userID) throws RecordNotFoundException{
        Employee employee = null;
        
        EntityManagerFactory emf = PayrollSystemDA.getEmFactory();
        System.out.println("Entity Manager Factory: " + emf);
        
        EntityManager em = emf.createEntityManager();
        
        String qString = "SELECT emp FROM Employee emp " +
                "Where emp.userID = :id";
        TypedQuery<Employee> q = em.createQuery(qString, Employee.class);
        q.setParameter("id", userID);
        System.out.println(q);
        
        
        try{
            employee = q.getSingleResult();
            return employee;
        }
        catch(NoResultException e){
            RecordNotFoundException ex = new RecordNotFoundException("Employee not found");
            throw ex;
        }
        finally{
            em.close();
        }
        

        
    }
    
    public static void initialize(){
        
    }

    public static ArrayList<Employee> getEmployees() throws RecordNotFoundException {
        employees.clear();
                
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        
        String qString = "Select emp FROM Employee emp ";
        TypedQuery<Employee> q = em.createQuery(qString, Employee.class);
        
        
        List<Employee> tCards;
        
        try{
            tCards = q.getResultList();
            employees = new ArrayList(tCards);
        }
        catch(NoResultException e){
            RecordNotFoundException ex = new RecordNotFoundException("Employee not found");
            throw ex;
        }
        finally{
            em.close();
        }
        
        return employees;
    }
    
}