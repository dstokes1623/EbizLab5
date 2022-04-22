package domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-04-20T21:09:09")
@StaticMetamodel(Timecard.class)
public class Timecard_ { 

    public static volatile SingularAttribute<Timecard, Date> date;
    public static volatile SingularAttribute<Timecard, Integer> timecardID;
    public static volatile SingularAttribute<Timecard, Double> overtimeHours;
    public static volatile SingularAttribute<Timecard, Integer> employeeID;
    public static volatile SingularAttribute<Timecard, Double> hoursWorked;

}