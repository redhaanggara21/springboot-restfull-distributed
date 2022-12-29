package com.lsio.springboot.repositories;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lsio.springboot.entities.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
    Employee findById(int id);
    List<Employee> findByEmployeename(String employeename);

    List<Employee> findByDepartmentAndAgeGreaterThanEqual(String department,int age);

    List<Employee> findByEmployeenameStartingWith(String employeename);

    List<Employee> findByEmployeenameContaining(String employeename);

    List<Employee> findByEmployeenameEndingWith(String employeename);
    
    List<Employee> findTop5ByAge(int age);
    List<Employee> findByAgeBetween(int startage, int endage);
    List<Employee> findByAgeIn(List<Integer> agegroup);

    List<Employee> findByJoiningdateAfter(Date date);
    List<Employee> findByJoiningdateBefore(Date date);
    List<Employee> findByJoiningdateBetween(Date startdate, Date enddate);
    List<Employee> findByJoiningdateBetweenAndDepartment(Date startdate, Date enddate, String department);

    List<Employee> findByLeftonIsNull();

    List<Employee> findByEmployeenameEquals(String employeename);
    List<Employee> findByEmployeenameIsNot(String employeename);
    List<Employee> findByEmployeenameIsNull();
    List<Employee> findByEmployeenameIsNotNull();

    List<Employee> findByEmployeenameOrderByJoiningdateAsc(String employeename);
    List<Employee> findByEmployeenameOrderByJoiningdateDesc(String employeename);

    List<Employee> findByLeftjobTrue();
    List<Employee> findByLeftjobFalse();
    List<Employee> findByLeftjob(boolean leftjob);

    @Query(value ="select Gettotalemployees()", nativeQuery = true)
    int NqgetTotalEmployees();

    @Query(value ="select procsingleoutput(?1)", nativeQuery = true)
    int NqGetEmployeeAge(int employeeid);

    @Query(value ="select * from procmultipleoutputs(?1)", nativeQuery = true)
    Map<String,?> NqGetNameandDept(int employeeid);

    @Procedure(procedureName = "Gettotalemployees")
    int ProcgetTotalEmployees();

    @Procedure(procedureName ="procsingleoutput")
    int ProcGetEmployeeAge(int employeeid);

    @Procedure(name ="emp.GetEmpnameAndDept")
    Map<String,?> ProcGetNameandDept(int employeeid);

    @Query(value ="select * from getemployeebyidtableset(?1)", nativeQuery = true)
    Map<String,?> NqGetIdAndName(int employeeid);

    @Procedure(name ="emp.GetEmpIdandName")
    Map<String,?> ProcGetIdandDept(int employeeid);

}
