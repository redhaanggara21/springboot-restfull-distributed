package com.lsio.springboot.dto.payload;

import java.util.List;
import com.lsio.springboot.entities.City;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityResponse {
    private List<City> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
    private String sortBy = "desc";
    private String sortDir = "id";
    private String status = "all";
    private String title = "";
}
