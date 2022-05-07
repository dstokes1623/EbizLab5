package database;

import domain.Timecard;
import exceptions.RecordNotFoundException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class TimecardDA {
    private static ArrayList<Timecard> timecards = new ArrayList<Timecard>(5);
    private static ArrayList<Timecard> employeeTimecards = new ArrayList<Timecard>();
    
    public static void add(Timecard tc) {
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        tc.setTimecardID(-1);
        EntityTransaction trans = em.getTransaction();
        System.out.println("transaction:  " + trans);
        try {
            trans.begin();
            em.persist(tc);
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
    
    public static void delete(Timecard t){
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        
       EntityTransaction trans = em.getTransaction();
       String qString = "DELETE FROM Timecard tc " +
                 "WHERE tc.timecardID = :id";
        Query q = em.createQuery(qString);
        q.setParameter("id", t.getTimecardID());
        int count = 0;
        try {
            trans.begin();
            System.out.println("trans started");
            System.out.println(t);
           count = q.executeUpdate();
            trans.commit();
            System.out.println("exiting trans");
        } 
        catch (Exception e) {
            if(trans.isActive()){
               trans.rollback();
            }
        }
        finally {
            em.close();
        }
    }
    
    public static Timecard find(int id) throws RecordNotFoundException{
        Timecard timecard= null;
                
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        
        try{
            timecard = em.find(Timecard.class, id);
            return timecard;
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
    public static ArrayList<Timecard> getAllTimecards() throws RecordNotFoundException {
        timecards.clear();
                
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        
        String qString = "Select tc FROM Timecard tc ";
        TypedQuery<Timecard> q = em.createQuery(qString, Timecard.class);
        
        List<Timecard> tCards;
        
        try{
            tCards = q.getResultList();
            timecards = new ArrayList(tCards);
        }
        catch(NoResultException e){
            RecordNotFoundException ex = new RecordNotFoundException("Employee not found");
            throw ex;
        }
        finally{
            em.close();
        }
        
        return timecards;
    }
    public static ArrayList<Timecard> getEmployeeTimecards(int userID) throws RecordNotFoundException {
        timecards.clear();
                
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        
        String qString = "Select tc FROM Timecard tc "
                + "WHERE tc.employeeID = :ID";
        TypedQuery<Timecard> q = em.createQuery(qString, Timecard.class);
        q.setParameter("ID", userID);
        
        List<Timecard> tCards;
        
        try{
            tCards = q.getResultList();
            employeeTimecards = new ArrayList(tCards);
        }
        catch(NoResultException e){
            RecordNotFoundException ex = new RecordNotFoundException("Employee not found");
            throw ex;
        }
        finally{
            em.close();
        }
        
        return employeeTimecards;
    }
    
    public static ArrayList<Timecard> getEmployeeTimecards(int ID, Date begDate, Date endDate) throws RecordNotFoundException {
        employeeTimecards.clear();
        Timecard timecard = null;
        
         EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        
        String qString = "Select tc FROM Timecard tc "
                + "WHERE tc.employeeID = :ID";
        TypedQuery<Timecard> q = em.createQuery(qString, Timecard.class);
        q.setParameter("ID", ID);
        
        List<Timecard> tCards;
        
        try{
            tCards = q.getResultList();
            timecards = new ArrayList(tCards);
        }
        catch(NoResultException e){
            RecordNotFoundException ex = new RecordNotFoundException("Employee not found");
            throw ex;
        }
        finally{
            em.close();
        }
        
        
        return employeeTimecards;
    }
    
    public static void update(Timecard tc) throws RecordNotFoundException {
        Timecard timecard = find(tc.getTimecardID());
        Date date = tc.getDate();
        double hoursWorked = tc.getHoursWorked();
        double overtime = tc.getOvertimeHours();
        
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        
        EntityTransaction trans = em.getTransaction();
        String qString = "UPDATE Timecard tc SET tc.date = :date, tc.hoursWorked = :hoursWorked, tc.overtimeHours = :overtime " +
                         "WHERE tc.timecardID = :id";
        Query q = em.createQuery(qString);
        q.setParameter("date", date);
        q.setParameter("hoursWorked", hoursWorked);
        q.setParameter("overtime", overtime);
        q.setParameter("id", timecard.getTimecardID());
        try {
        trans.begin();
        q.executeUpdate();
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
}