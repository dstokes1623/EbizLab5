package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "Hourly_Employee")
@DiscriminatorValue("1")
public class HourlyEmployee extends Employee implements Serializable{
    
    @Column(name = "Hourly_Rate")
    private double hourlyRate;
    @Column(name = "Overtime_Rate")
    private double overtimeRate;
    
    public double calculateGrossPay(Date date){
        ArrayList<Timecard> timecards;
        Timecard timecard;
        Date beginDate, endDate, timecardDate;
        Calendar calendar = Calendar.getInstance();
        double grossPay = 0;
        
        endDate = date;
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -6);
        beginDate = calendar.getTime();
        
        timecards = Timecard.getEmployeeTimecards(this.getEmployeeID(), beginDate, endDate);
        
        for(int i = 0; i < timecards.size(); i++) {
            timecard = timecards.get(i);
            timecardDate = timecard.getDate();
            if(timecardDate.compareTo(beginDate) >= 0 && timecardDate.compareTo(endDate) <= 0 ){
                grossPay += timecard.getHoursWorked() * this.getHourlyRate();
                grossPay += timecard.getOvertimeHours() * this.getHourlyRate() * this.getOvertimeRate();
            }
        }
        return grossPay;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getOvertimeRate() {
        return overtimeRate;
    }

    @Override
    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
    
    @Override
    public void setOvertimeRate(double overtimeRate) {
        this.overtimeRate = overtimeRate;
    }
    
    @Override
    public String toString(){
        return super.toString() + "  " + hourlyRate + "  " + overtimeRate;
    }
}