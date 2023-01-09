package com.lsio.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lsio.springboot.advSearch.WrkSpecificationBuilder;
import com.lsio.springboot.advSearch.WorkerSearchDto;
import com.lsio.springboot.advSearch.SearchCriteria;
import com.lsio.springboot.domain.Worker;
import com.lsio.springboot.services.advancedsearching.WorkerService;
import com.lsio.springboot.utils.APIResponse;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class AdvSearchController {

    @Autowired
    private WorkerService wrkService;

    @GetMapping("/workers")
    public ResponseEntity<APIResponse> getAllWorkers(){
        APIResponse apiResponse = new APIResponse();
        apiResponse.setData(wrkService.findAllWorker());
        apiResponse.setMessage("worker record retrieved successfully");
        apiResponse.setResponseCode(HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, apiResponse.getResponseCode());
    }

    @PostMapping("/search")
    public ResponseEntity<APIResponse> searchWorker(
            @RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestBody WorkerSearchDto workerSearchDto
        ){
            System.out.println("workerSearchDto:" + workerSearchDto);
            APIResponse apiResponse = new APIResponse();
            WrkSpecificationBuilder builder = new WrkSpecificationBuilder();
            List<SearchCriteria> criteriaList = workerSearchDto.getSearchCriteriaList();
            if(criteriaList != null){
                criteriaList.forEach(x-> {x.setDataOption(workerSearchDto.getDataOption());
                                            builder.with(x);
                });

            }

            Pageable page = PageRequest.of(pageNum, pageSize, Sort.by("wrkfirstNm")
                                    .ascending().and(Sort.by("wrklastNm"))
                                    .ascending().and(Sort.by("department")).ascending());

            Page<Worker> workerPage = wrkService.findBySearchCriteria(builder.build(), page);

            apiResponse.setData(workerPage.toList());
            apiResponse.setResponseCode(HttpStatus.OK);
            apiResponse.setMessage("Successfully retrieved worker record");

            return new ResponseEntity<>(apiResponse, apiResponse.getResponseCode());
    }

}
