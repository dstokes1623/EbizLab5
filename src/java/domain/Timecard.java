package domain;

import database.TimecardDA;
import exceptions.RecordNotFoundException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.NumberFormat;

import java.util.ArrayList;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Timecard implements Serializable{
    @Id
    @Column(name = "Timecard_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int timecardID;
    @Column(name = "Timecard_Date")
    private Date date;
    @Column(name = "Employee_ID")
    private int employeeID;
    @Column(name = "Hours_Worked")
    private double hoursWorked;
    @Column(name = "Overtime_Hours")
    private double overtimeHours;
    
    public Timecard(){
        this.setTimecardID(-1);
        this.setDate(new Date());
        this.setHoursWorked(0.0);
        this.setOvertimeHours(0.0);
    }
    
    public void add() {
        TimecardDA.add(this);
    }
    
    public void delete(){
        TimecardDA.delete(this);
    }
    
    public static Timecard find(int id) throws RecordNotFoundException{
        return TimecardDA.find(id);
    }

    public Date getDate() {
        return date;
    }
    
    public String getDateShort(){
        return DateFormat.getDateInstance(DateFormat.SHORT).format(date);
    } 
    
    public String getDateFormatted(){
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
        return dateFormat.format(date);
    }

    public int getEmployeeID() {
        return employeeID;
    }
    
    public static ArrayList<Timecard> getAllTimecards() throws RecordNotFoundException{
        return TimecardDA.getAllTimecards();
    }
    
    public static ArrayList<Timecard> getEmployeeTimecards(int ID) throws RecordNotFoundException{
        return TimecardDA.getEmployeeTimecards(ID);
    }
    
    public static ArrayList<Timecard> getEmployeeTimecards(int ID, Date begDate, Date endDate) throws RecordNotFoundException {
        return TimecardDA.getEmployeeTimecards(ID, begDate, endDate);
    }
    
    public double getHoursWorked() {
        return hoursWorked;
    }
    
    public String getHoursWorkedFormatted() {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMinimumFractionDigits(2);
        return numberFormat.format(hoursWorked);
    }

    public double getOvertimeHours() {
        return overtimeHours;
    }
    
    public String getOvertimeHoursFormatted(){
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMinimumFractionDigits(2);
        return numberFormat.format(overtimeHours);
    }

    public int getTimecardID() {
        return timecardID;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public void setOvertimeHours(double overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public void setTimecardID(int timecardID) {
        this.timecardID = timecardID;
    }
    
    @Override
    public String toString(){
        return timecardID + " " + getDateFormatted() + "  " + employeeID + "  " + hoursWorked + "  " + overtimeHours;
    }
    
    public void update() throws RecordNotFoundException{
        TimecardDA.update(this);
    }
}