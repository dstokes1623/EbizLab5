/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import domain.UserRole;
import exceptions.RecordNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author stoke
 */
public class UserRoleDA {
     private static ArrayList<UserRole> userRoles = new ArrayList<UserRole>(3);
    
    public static void add(UserRole ur) {
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        ur.setUserTypeID(-1);
        EntityTransaction trans = em.getTransaction();
        System.out.println("transaction:  " + trans);
        try {
            trans.begin();
            em.persist(ur);
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
    
    public static UserRole find(int ID) throws RecordNotFoundException{
        UserRole ur= null;
                
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        
        try{
            ur = em.find(UserRole.class, ID);
            return ur;
        }
        catch(NoResultException e){
            RecordNotFoundException ex = new RecordNotFoundException("User Role not found");
            throw ex;
        }
        finally{
            em.close();
        }
        
    }
    public static UserRole findByRoleType(String roleType) throws RecordNotFoundException{
        UserRole ur= null;
        
        EntityManagerFactory emf = PayrollSystemDA.getEmFactory();
        System.out.println("Entity Manager Factory: " + emf);
        
        EntityManager em = emf.createEntityManager();
        
        String qString = "SELECT ur FROM UserRole ur " +
                "Where ur.userType = :userType";
        TypedQuery<UserRole> q = em.createQuery(qString, UserRole.class);
        q.setParameter("userType", roleType);
        System.out.println(q);
        
        
        try{
            ur = q.getSingleResult();
            return ur;
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

    public static ArrayList<UserRole> getUserRoles() throws RecordNotFoundException {
        userRoles.clear();
                
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        
        String qString = "Select ur FROM UserRole ur ";
        TypedQuery<UserRole> q = em.createQuery(qString, UserRole.class);
        System.out.println(q);
        
        List<UserRole> uRoles;
        
        try{
            uRoles = q.getResultList();
            userRoles = new ArrayList(uRoles);
            System.out.println(userRoles);
            
        }
        catch(NoResultException e){
            RecordNotFoundException ex = new RecordNotFoundException("Employee not found");
            throw ex;
        }
        finally{
            em.close();
        }
        
        return userRoles;
    }
     public static void delete(UserRole u){
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        
       EntityTransaction trans = em.getTransaction();
       String qString = "DELETE FROM UserRole ur " +
                 "WHERE ur.userTypeID = :id";
        Query q = em.createQuery(qString);
        q.setParameter("id", u.getUserTypeID());
        int count = 0;
        try {
            trans.begin();
            System.out.println("trans started");
            System.out.println(u);
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
     public static void update(UserRole ur) throws RecordNotFoundException {
        UserRole userRole = find(ur.getUserTypeID());
        int userTypeID = ur.getUserTypeID();
        String userType = ur.getUserType();
        boolean updateUserRoles = ur.isUpdateUserRoles();
        boolean calculatePayroll = ur.isCalculatePayroll();
        boolean viewPayroll = ur.isViewPayroll();
        boolean allTimecards = ur.isAllTimecards();
        boolean ownTimecards = ur.isOwnTimecards();
        
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        
        EntityTransaction trans = em.getTransaction();
        String qString = "UPDATE UserRole ur SET ur.userType = :userType, ur.updateUserRoles = :updateUserRoles, "
                + "ur.calculatePayroll = :calculatePayroll, ur.viewPayroll = :viewPayroll, ur.allTimecards = :allTimecards, ur.ownTimecards = :ownTimecards " +
                         "WHERE ur.userTypeID = :userTypeID";
        
        Query q = em.createQuery(qString);
        System.out.println(q);
        q.setParameter("userType", userType);
         System.out.println("userType= " + userType);
        q.setParameter("updateUserRoles", updateUserRoles);
         System.out.println("updateUserRoles = " + updateUserRoles);
        q.setParameter("calculatePayroll", calculatePayroll);
         System.out.println("calculatePayroll = " + calculatePayroll);
        q.setParameter("viewPayroll", viewPayroll);
         System.out.println("viewPayroll = " + viewPayroll);
        q.setParameter("allTimecards", allTimecards);
         System.out.println("allTimecards = " + allTimecards);
        q.setParameter("ownTimecards", ownTimecards);
         System.out.println("ownTimecards = " + ownTimecards);
        q.setParameter("userTypeID", userRole.getUserTypeID());
        try {
        trans.begin();
        System.out.println("updating");
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
