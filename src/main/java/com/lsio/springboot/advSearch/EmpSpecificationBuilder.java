package com.lsio.springboot.advSearch;

import org.springframework.data.jpa.domain.Specification;

import com.lsio.springboot.domain.Employee;
import java.util.ArrayList;
import java.util.List;

public class EmpSpecificationBuilder {

    private final List<SearchCriteria> params;

    public EmpSpecificationBuilder(){
        this.params = new ArrayList<>();
    }

    public final EmpSpecificationBuilder with(String key, String operation, Object value){
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public final EmpSpecificationBuilder with(SearchCriteria searchCriteria){
        params.add(searchCriteria);
        return this;
    }

    public Specification<Employee> build(){
        if(params.size() == 0){
            return null;
        }

        Specification<Employee> result = new EmployeeSpecification(params.get(0));
        for (int idx = 1; idx < params.size(); idx++){
            SearchCriteria criteria = params.get(idx);
            result = SearchOperation.getDataOption(criteria.getDataOption()) == SearchOperation.ALL
                    ? Specification.where(result).and(new EmployeeSpecification(criteria))
                    : Specification.where(result).or(new EmployeeSpecification(criteria));
        }

        return result;
    }
}
