package database;

import domain.WithholdingType;
import exceptions.RecordNotFoundException;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class WithholdingTypeDA {
    private static ArrayList<WithholdingType> withholdingTypes = new ArrayList<WithholdingType>(5);
    
    public static void add(WithholdingType wt) {
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        System.out.println("transaction:  " + trans);
        try {
            trans.begin();
            em.persist(wt);
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

    public static ArrayList<WithholdingType> getWithholdingTypes() throws RecordNotFoundException {
        withholdingTypes.clear();
                
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        
        String qString = "Select wt FROM WithholdingType wt ";
        TypedQuery<WithholdingType> q = em.createQuery(qString, WithholdingType.class);
        
        
        List<WithholdingType> withholding;
        
        try{
            withholding = q.getResultList();
            withholdingTypes = new ArrayList(withholding);
        }
        catch(NoResultException e){
            RecordNotFoundException ex = new RecordNotFoundException("Employee not found");
            throw ex;
        }
        finally{
            em.close();
        }
        
        return withholdingTypes;
    }
    
    public static void initialize() {
        
    }
}