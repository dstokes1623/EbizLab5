package database;

import domain.Payroll;
import exceptions.RecordNotFoundException;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class PayrollDA {
    private static ArrayList<Payroll> payrollRecords = new ArrayList<Payroll>(5);
    
    public static void add(Payroll p) {
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        
        EntityTransaction trans = em.getTransaction();
        System.out.println("transaction:  " + trans);
        try {
            trans.begin();
            em.persist(p);
            trans.commit();
        }
        catch (Exception ex) {
            if(trans.isActive()){
               trans.rollback();
            }
        }
        finally {
            em.close();
        }
    }

    public static ArrayList<Payroll> getPayrollRecords() throws RecordNotFoundException {
        payrollRecords.clear();
                
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        
        String qString = "Select pay FROM Payroll pay ";
        TypedQuery<Payroll> q = em.createQuery(qString, Payroll.class);
        
        
        List<Payroll> tCards;
        
        try{
            tCards = q.getResultList();
            payrollRecords = new ArrayList(tCards);
        }
        catch(NoResultException e){
            RecordNotFoundException ex = new RecordNotFoundException("Employee not found");
            throw ex;
        }
        finally{
            em.close();
        }
        
        return payrollRecords;
    }
    public static ArrayList<Payroll> getPayrollRecordsByID(int ID) throws RecordNotFoundException {
        payrollRecords.clear();
                
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        
        String qString = "Select pay FROM Payroll pay " +
                "Where pay.employeeID = :id";
        TypedQuery<Payroll> q = em.createQuery(qString, Payroll.class);
        q.setParameter("id", ID);
        
        
        List<Payroll> payroll;
        
        try{
            payroll = q.getResultList();
            payrollRecords = new ArrayList(payroll);
        }
        catch(NoResultException e){
            RecordNotFoundException ex = new RecordNotFoundException("Employee not found");
            throw ex;
        }
        finally{
            em.close();
        }
        
        return payrollRecords;
    }
}