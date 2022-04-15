package domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "Salary_Employee")
@DiscriminatorValue("2")
public class SalaryEmployee extends Employee implements Serializable{
    
    @Column(name = "Salary")
    private double salary;
    
    @Override
    public double calculateGrossPay(Date date) {
        return salary/52.0;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public void setSalary(double s) {
        this.salary = s;
    }
    
    @Override
    public String toString(){
        return super.toString() + "  " + salary;
    }
}