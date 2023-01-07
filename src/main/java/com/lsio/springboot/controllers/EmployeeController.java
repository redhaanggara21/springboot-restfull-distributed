package com.lsio.springboot.controllers;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import com.lsio.springboot.entities.Employee;
import com.lsio.springboot.repositories.EmployeeRepository;
import com.lsio.springboot.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employees/")
@Validated
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("addemployees")
    public List<Employee> addAllEmployees(@RequestBody List<Employee> employees)
    {
        return employeeService.saveAllEmployees(employees);
    }

    @PostMapping("addemployee")
    public Employee addEmployee(@Valid @RequestBody Employee employee)
    {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("allemployees")
    public List<Employee> getAllEmployees()
    {
        return employeeService.findAllEmployees();
    }

    @GetMapping("employeeswithname")
    public List<Employee> getAllEmployeesWithName(@RequestParam String employeename)
    {
        return employeeService.findEmployeesByName(employeename);
    }

    @GetMapping("employeebyid")
    public Employee getEmployeeById(@RequestParam @Min(value =1, message = "Employee ID should be greater than 0") int id)
    {
        return employeeService.findEmployeeById(id);
    }

    @GetMapping("employeesbyids")
    public List<Employee> getEmployeesByIds(@RequestBody List<Integer> ids)
    {
        return employeeService.findAllEmployeesByIds(ids);
    }

    @GetMapping("empbydeptandage")
    public List<Employee> getEmpByDeptAndAge(@RequestParam String department, @RequestParam int age)
    {
        return employeeRepository.findByDepartmentAndAgeGreaterThanEqual(department, age);
    }

    @GetMapping("employeesbyname")
    public List<Employee> getEmployeesByName(@RequestParam int age)
    {
        return employeeRepository.findTop5ByAge(age);
    }

    @GetMapping("totalemployees")
    public int getTotalEmployees(){
        return employeeService.Gettotalemployees();
    }

    @GetMapping("getage")
    public int getEmployeeAge(@RequestParam int employeeid){
        return employeeService.getEmployeeAge(employeeid);
    }

    @GetMapping("getnameanddept")
    public Map<String,?> getNameAndDept(@RequestParam int employeeid){
        return employeeService.getEmpNameandDept(employeeid);
    }

    @GetMapping("getidandname")
    public Object getIdAndName(@RequestParam int employeeid){
        return employeeService.getIdAndName(employeeid);
    }
    
    @GetMapping("getallempref")
    public Object getAllEmpRef(@RequestParam int employeeid){
        return employeeService.getAllEmpRef(employeeid);
    }

    @GetMapping("/employees")
    public ResponseEntity<APIResponse> getAll(){
        APIResponse apiResponse = new APIResponse();
        apiResponse.setData(empService.findAllEmployee());
        apiResponse.setMessage("Employee record retrieved successfully");
        apiResponse.setResponseCode(HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, apiResponse.getResponseCode());
    }

    @PostMapping("/search")
    public ResponseEntity<APIResponse> searchEmployees(@RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
                                                       @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                       @RequestBody EmployeeSearchDto employeeSearchDto){
        System.out.println("employeeSearchDto:" + employeeSearchDto);
        APIResponse apiResponse = new APIResponse();
        EmpSpecificationBuilder builder = new EmpSpecificationBuilder();
        List<SearchCriteria> criteriaList = employeeSearchDto.getSearchCriteriaList();
        if(criteriaList != null){
            criteriaList.forEach(x-> {x.setDataOption(employeeSearchDto.getDataOption());
                                        builder.with(x);
            });

        }

        Pageable page = PageRequest.of(pageNum, pageSize, Sort.by("empfirstNm")
                                   .ascending().and(Sort.by("emplastNm"))
                                   .ascending().and(Sort.by("department")).ascending());

        Page<Employee> employeePage = empService.findBySearchCriteria(builder.build(), page);

        apiResponse.setData(employeePage.toList());
        apiResponse.setResponseCode(HttpStatus.OK);
        apiResponse.setMessage("Successfully retrieved employee record");

        return new ResponseEntity<>(apiResponse, apiResponse.getResponseCode());
    }

}
