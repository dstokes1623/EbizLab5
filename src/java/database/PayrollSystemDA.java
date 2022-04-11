package database;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PayrollSystemDA {
    private static final EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("Payroll_SystemPU");
    
    
    public static void initialize() {
        EmployeeDA.initialize();
        TimecardDA.initialize();
        WithholdingTypeDA.initialize();
    }
    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}
