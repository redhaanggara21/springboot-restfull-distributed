package com.lsio.springboot.advSearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerSearchDto {

    private List<SearchCriteria> searchCriteriaList;
    private String dataOption;

}
