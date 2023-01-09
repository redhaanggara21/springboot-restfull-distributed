package com.lsio.springboot.controllers;

import java.util.List;
import java.util.Set;

import com.lsio.springboot.Pojos.CityRequest;
import com.lsio.springboot.Pojos.CourseRequest;
import com.lsio.springboot.entities.City;
import com.lsio.springboot.entities.Country;
import com.lsio.springboot.entities.Course;
import com.lsio.springboot.payload.CityResponse;
import com.lsio.springboot.services.CityService;
import com.lsio.springboot.services.CountryService;
import com.lsio.springboot.services.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lsio.springboot.utils.AppConstants;
@RestController
@RequestMapping()
public class HomeController {
    
    @Autowired
    CityService cityService;
    @Autowired
    CourseService courseService;

    @Autowired
    CountryService countryService;


    @GetMapping("sayhello")
    public String sayHello(){
        return "Hello User";
    }

    @GetMapping("getcities")
    public CityResponse getCities(
        @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
        // @RequestParam(value = "city_id,city_name", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String[] sortBy,
        @RequestParam(value = "id", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
        @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
        @RequestParam(value = "status", defaultValue = AppConstants.DEFAULT_FILTER_STATUS, required = false) String status,
        @RequestParam(value = "title", defaultValue = AppConstants.DEFAULT_FILTER_FIND_NAME, required = false) String title
    ){
        return cityService.findPaginated(
            pageNo, 
            pageSize,
            sortBy,
            sortDir,
            status,
            title
        );
    }

    @PostMapping("addcity")
    public City SaveCity(@RequestBody City city){
        System.out.println(city);
        return cityService.saveCity(city);
    }

    @GetMapping("getcity")
    public City getCity(String cityname){
        return cityService.getCity(cityname);
    }

    @PostMapping("savecity")
    public City addCity(@RequestBody CityRequest cityrequest){
        return cityService.addCity(cityrequest);
    }

    @PostMapping("addcourse")
    public Course addCourse(@RequestBody CourseRequest courseRequest){
        return courseService.addCourseWithContents(courseRequest);
    }
    
    @PostMapping("addcountry")
    public Country addCountry(@RequestBody Country country){
        return countryService.saveCountry(country);
    }

    @GetMapping("countrystartswith")
    public List<Country> countryStartsWith(@RequestParam String countryname){
        return cityService.findByCountryNameStartsWithOrderByPopulation(countryname);
    }
    
    @GetMapping("getcountries")
    public List<Country> getAllCountries(){
        return cityService.getAllCountries();
    }

    @GetMapping("getcountrycontaining")
    public List<Country> getCountryContaining(@RequestParam String substring){
        return cityService.getCountryContaining(substring);
    }

    @GetMapping("getcountry")
    public Country getCountry(@RequestParam int id){
        return cityService.getCountry(id);
    }

    @GetMapping("getcountrybyname")
    public List<Country> getCountryByName(@RequestParam String prefix){
        return cityService.getCountryByName(prefix);
    }

    @GetMapping("getcountrybynameandpop")
    public List<Object[]> getCountryByNameandPop(@RequestParam String prefix, @RequestParam long population){
        return cityService.getCountryByNameandPop(prefix, population);
    }
    
    @GetMapping("getcountrybyids")
    public List<Country> getCountryByIds(@RequestBody Set<Integer> ids){
        return cityService.getCountryByIds(ids);
    }
    
}
