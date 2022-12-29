package com.lsio.springboot.entities;

import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.lsio.springboot.Pojos.EmpIdName;

@Entity

@SqlResultSetMapping(
    name = "EmpIdAndName", classes = {
        @ConstructorResult(targetClass = EmpIdName.class,
        columns = {
            @ColumnResult(name = "employee_id"),
            @ColumnResult(name ="employeename")            
        }
        )
    }
)

@NamedStoredProcedureQueries({
@NamedStoredProcedureQuery(name = "emp.GetEmpnameAndDept", procedureName = "procmultipleoutputs",
parameters = {
    @StoredProcedureParameter(mode = ParameterMode.IN, name="employeeid", type = Integer.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name="empname", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name="dept", type = String.class)
}),
@NamedStoredProcedureQuery(name = "emp.GetEmpIdandName", procedureName = "getemployeebyidtableset", resultSetMappings = {"EmpIdAndName"},
parameters = {
    @StoredProcedureParameter(mode = ParameterMode.IN, name="employeeid", type = Integer.class)
}),
@NamedStoredProcedureQuery(name = "emp.GetAllEmployees", procedureName = "getemployeebyidrefcursor", resultClasses = {Employee.class},
parameters = {
    @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class)
})
})

@Table(name="employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int id;

    @NotNull
    private String employeename;

    @NotNull(message = "Department is required")
    @Pattern(regexp = "^[0-9A-Z]*$", message = "Department accepts only Alphanumeric value")
    @Size(min = 10, max = 50, message = "Department accepts only upto 50 character and minimum 10 characters")
    private String department;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date joiningdate;

    @NotNull(message = "Age is required")
    @Min(value =18, message = "The minimum age requirement is 18")
    private int age;

    @NotNull
    private String address;

    @NotNull
    private float salary;
    
    private ZonedDateTime lefton;

    @NotNull
    private boolean leftjob;


    public Employee(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getJoiningdate() {
        return joiningdate;
    }

    public void setJoiningdate(Date joiningdate) {
        this.joiningdate = joiningdate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public ZonedDateTime getLefton() {
        return lefton;
    }

    public void setLefton(ZonedDateTime lefton) {
        this.lefton = lefton;
    }

    public boolean isLeftjob() {
        return leftjob;
    }

    public void setLeftjob(boolean leftjob) {
        this.leftjob = leftjob;
    }

    
}
