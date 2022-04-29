drop table Employee;
drop table Hourly_Employee;
drop table Salary_Employee;
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
  
  PRIMARY KEY(Employee_ID) 
);

INSERT INTO Employee 
  (Employee_ID, Employee_Type, First_Name, Last_Name, SSN, User_ID, Password)
VALUES 
  (1001, 1, 'Bob', 'Smith', 555121234, 'x', 'x');

INSERT INTO Employee 
  (Employee_ID, Employee_Type, First_Name, Last_Name, SSN, User_ID, Password)
VALUES 
  (1002, 2, 'Jane', 'Doe', 555121234, 'User2', '2');
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
  (1002, 60000);

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
