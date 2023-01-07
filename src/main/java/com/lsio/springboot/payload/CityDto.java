package com.lsio.springboot.payload;

import java.beans.Transient;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.lsio.springboot.entities.Country;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityDto {

    @NotEmpty
    private Long city_id;

    @NotEmpty
    private String cityname;
  
    @NotEmpty
    @Size(min = 2, message = "city code title should have at least 2 characters")
    private String citycode;

    @NotEmpty
    @Size(min = 3, message = "beanid should have at least 3 characters")
    private String beanid;

    @NotEmpty
    private int countryid;

    @NotEmpty
    private Set<Country> country;
}
