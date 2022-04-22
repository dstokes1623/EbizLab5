package domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-04-20T21:09:09")
@StaticMetamodel(Payroll.class)
public class Payroll_ { 

    public static volatile SingularAttribute<Payroll, Date> date;
    public static volatile SingularAttribute<Payroll, Double> netPay;
    public static volatile SingularAttribute<Payroll, Double> totalDeductions;
    public static volatile SingularAttribute<Payroll, Double> grossPay;
    public static volatile SingularAttribute<Payroll, Integer> employeeID;
    public static volatile SingularAttribute<Payroll, Integer> payrollID;

}