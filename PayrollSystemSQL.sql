drop table Employee;
drop table Hourly_Employee;
drop table Salary_Employee;
drop table Users;
drop table Timecard;
drop table Withholding_Type;
drop table Payroll;

CREATE TABLE Employee (
  Employee_ID INT NOT NULL,
  Employee_Type INT,
  First_Name VARCHAR(50),
  Last_Name VARCHAR(50),
  SSN bigint,
  User_ID VARCHAR(50),
  Password VARCHAR(50),
  User_Role VARCHAR(50),
  
  PRIMARY KEY (Employee_ID) 
);

INSERT INTO Employee 
  (Employee_ID, Employee_Type, First_Name, Last_Name, SSN, User_ID, Password, User_Role)
VALUES 
  (1001, 1, 'Bob', 'Smith', 555121234, 'x', 'x', 'Employee');

INSERT INTO Employee 
  (Employee_ID, Employee_Type, First_Name, Last_Name, SSN, User_ID, Password, User_Role)
VALUES 
  (1002, 1, 'Jane', 'Doe', 555121234, 'Mgt1', 'mgt1', 'Mgt');
INSERT INTO Employee 
  (Employee_ID, Employee_Type, First_Name, Last_Name, SSN, User_ID, Password, User_Role)
VALUES 
  (1003, 2, 'Mikayla', 'Stokes', 555121234, 'Admin', 'admin', 'Admin');
CREATE TABLE Hourly_Employee (
  Employee_ID INT NOT NULL,
  Hourly_Rate DOUBLE,
  Overtime_Rate DOUBLE,
  
  PRIMARY KEY(Employee_ID) 
);

INSERT INTO Hourly_Employee 
  (Employee_ID, Hourly_Rate, Overtime_Rate)
VALUES 
  (1001, 12.75, 1.5);

INSERT INTO Hourly_Employee 
  (Employee_ID, Hourly_Rate, Overtime_Rate)
VALUES 
  (1002, 12.75, 1.5);
CREATE TABLE Salary_Employee (
  Employee_ID INT NOT NULL,
  Salary DOUBLE,
  
  PRIMARY KEY(Employee_ID) 
);

INSERT INTO Salary_Employee 
  (Employee_ID, Salary)
VALUES 
  (1003, 60000);
INSERT INTO Salary_Employee 
  (Employee_ID, Salary)
VALUES 
  (1003, 70000);

CREATE TABLE Users (
    User_Type_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,
    User_Type VARCHAR(50),
    Update_User_Roles Boolean,
    Calculate_Payroll Boolean,
    View_Payroll Boolean,
    All_Timecards Boolean,
    Own_Timecards Boolean,
    
    PRIMARY KEY (User_Type_ID)
);
INSERT INTO Users 
  (User_Type, Update_User_Roles, Calculate_Payroll, View_Payroll,All_Timecards, Own_Timecards)
VALUES 
  ('Admin', true, true, true, true, false);

INSERT INTO Users 
  (User_Type, Update_User_Roles, Calculate_Payroll, View_Payroll,All_Timecards, Own_Timecards)
VALUES 
  ('Mgt', false, true, true, true, false);

INSERT INTO Users 
  (User_Type, Update_User_Roles, Calculate_Payroll, View_Payroll,All_Timecards, Own_Timecards)
VALUES 
  ('Employee', false, false, false, false, true);

CREATE TABLE Timecard (
    Timecard_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,    
    Timecard_Date DATE,
    Employee_ID INT NOT NULL,
    Hours_Worked DOUBLE,
    Overtime_Hours Double,
    
    PRIMARY KEY (Timecard_ID)
);

INSERT INTO Timecard 
  (Timecard_Date, Employee_ID, Hours_Worked, Overtime_Hours)
VALUES 
  ('4/15/2022', 1001, 8, 2.5);

INSERT INTO Timecard 
  (Timecard_Date, Employee_ID, Hours_Worked, Overtime_Hours)
VALUES 
  ('4/15/2022', 1002, 9, 2.5);

INSERT INTO Timecard 
  (Timecard_Date, Employee_ID, Hours_Worked, Overtime_Hours)
VALUES 
  ('4/14/2022', 1001, 6, 2.5);

INSERT INTO Timecard 
  (Timecard_Date, Employee_ID, Hours_Worked, Overtime_Hours)
VALUES 
  ('4/15/2022', 1002, 9, 2.5);

INSERT INTO Timecard 
  (Timecard_Date, Employee_ID, Hours_Worked, Overtime_Hours)
VALUES 
  ('4/14/2022', 1001, 6, 2.5);


INSERT INTO Timecard 
  (Timecard_Date, Employee_ID, Hours_Worked, Overtime_Hours)
VALUES 
  ('4/8/2022', 1001, 8, 2.5);

INSERT INTO Timecard 
  (Timecard_Date, Employee_ID, Hours_Worked, Overtime_Hours)
VALUES 
  ('4/25/2022', 1002, 8, 2.5);

CREATE TABLE Withholding_Type (
    Type_ID INT NOT NULL,    
    Description VARCHAR(50),
    Amount Double,
    Rate DOUBLE,
    
    PRIMARY KEY (Type_ID)
);

INSERT INTO Withholding_Type  
  (Type_ID, Description, Amount, Rate)
VALUES 
  (1, 'Income Tax', 0.0, 10.0);

INSERT INTO Withholding_Type  
  (Type_ID, Description, Amount, Rate)
VALUES 
  (2, 'Health Insurance', 257.25, 0.0);

CREATE TABLE Payroll (
    Payroll_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,    
    Payroll_Date DATE,
    Employee_ID INT,
    Gross_Pay Double,
    Total_Deductions DOUBLE,
    Net_Pay Double,
    
    PRIMARY KEY (Payroll_ID)
);
