package com.lsio.springboot.advSearch;

import org.springframework.data.jpa.domain.Specification;

import com.lsio.springboot.entities.domain.Worker;

import java.util.ArrayList;
import java.util.List;

public class WrkSpecificationBuilder {

    private final List<SearchCriteria> params;

    public WrkSpecificationBuilder(){
        this.params = new ArrayList<>();
    }

    public final WrkSpecificationBuilder with(String key, String operation, Object value){
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public final WrkSpecificationBuilder with(SearchCriteria searchCriteria){
        params.add(searchCriteria);
        return this;
    }

    public Specification<Worker> build(){
        if(params.size() == 0){
            return null;
        }

        Specification<Worker> result = new WorkerSpecification(params.get(0));
        for (int idx = 1; idx < params.size(); idx++){
            SearchCriteria criteria = params.get(idx);
            result = SearchOperation.getDataOption(criteria.getDataOption()) == SearchOperation.ALL
                    ? Specification.where(result).and(new WorkerSpecification(criteria))
                    : Specification.where(result).or(new WorkerSpecification(criteria));
        }

        return result;
    }
}
