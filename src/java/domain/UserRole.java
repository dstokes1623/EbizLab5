/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import database.UserRoleDA;
import exceptions.RecordNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.*;

/**
 *
 * @author stoke
 */
@Entity
@Table(name = "Users")
public class UserRole implements Serializable {
    @Id
    @Column(name = "User_Type_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userTypeID;
    @Column(name = "User_Type")
    private String userType;
    @Column(name = "Update_User_Roles")
    private boolean updateUserRoles;
    @Column(name = "Calculate_Payroll")
    private boolean calculatePayroll;
    @Column(name = "View_Payroll")
    private boolean viewPayroll;
    @Column(name = "All_Timecards")
    private boolean allTimecards;
    @Column(name = "Own_Timecards")
    private boolean ownTimecards;
    
    public UserRole(){
        this.setUserTypeID(-1);
        this.setAllTimecards(false);
        this.setCalculatePayroll(false);
        this.setOwnTimecards(false);
        this.setUpdateUserRoles(false);
        this.setUserType("");
        this.setViewPayroll(false);
    }
    public int getUserTypeID() {
        return userTypeID;
    }

    public void setUserTypeID(int userTypeID) {
        this.userTypeID = userTypeID;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public boolean isUpdateUserRoles() {
        return updateUserRoles;
    }

    public void setUpdateUserRoles(boolean updateUserRoles) {
        this.updateUserRoles = updateUserRoles;
    }

    public boolean isCalculatePayroll() {
        return calculatePayroll;
    }

    public void setCalculatePayroll(boolean calculatePayroll) {
        this.calculatePayroll = calculatePayroll;
    }

    public boolean isViewPayroll() {
        return viewPayroll;
    }

    public void setViewPayroll(boolean viewPayroll) {
        this.viewPayroll = viewPayroll;
    }

    public boolean isAllTimecards() {
        return allTimecards;
    }

    public void setAllTimecards(boolean allTimecards) {
        this.allTimecards = allTimecards;
    }

    public boolean isOwnTimecards() {
        return ownTimecards;
    }

    public void setOwnTimecards(boolean ownTimecards) {
        this.ownTimecards = ownTimecards;
    }
     public void add() {
        UserRoleDA.add(this);
    }
    public void delete(){
        UserRoleDA.delete(this);
    }
    public static UserRole find(int id) throws RecordNotFoundException{
        return UserRoleDA.find(id);
    }
    public static UserRole findByRoleType(String userType) throws RecordNotFoundException {
        return UserRoleDA.findByRoleType(userType);
    }
     public static ArrayList<UserRole> getUserRoles() throws RecordNotFoundException{
        return UserRoleDA.getUserRoles();
        
    }
     public void update() throws RecordNotFoundException{
        UserRoleDA.update(this);
    }
}
