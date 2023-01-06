package com.lsio.springboot.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import com.lsio.springboot.Pojos.CityRequest;
import com.lsio.springboot.entities.City;
import com.lsio.springboot.entities.Country;
import com.lsio.springboot.payload.CityDto;
import com.lsio.springboot.payload.CityResponse;
import com.lsio.springboot.repositories.CityRepository;
import com.lsio.springboot.repositories.CountryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;

@Service
public class CityService {

    @Autowired CityRepository cityRepository;
    @Autowired CountryRepository countryRepository;

    public CityService(){
        
    }

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
          return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
          return Sort.Direction.DESC;
        }
    
        return Sort.Direction.ASC;
      }
    

    public CityResponse findPaginated(
        int pageNo, 
        int pageSize, 
        String sortBy, 
        String sortDir,
        String status,
        String title
    ){

        // Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortDir).ascending()
        //         : Sort.by(sortDir).descending();

        
        // Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortDir));
        // Page<City> pageList = cityRepository.findAll(pageable);
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<City> pageList;

        if (title == null || title == ""){
            pageList = cityRepository.findAll(pageable);
        } else {
            // pageList = cityRepository.findByTitleContaining(title, pageable);
            pageList = cityRepository.findAll(pageable);
        }

        List<City> citylist = pageList.getContent();
        // List<CityDto> cityListPage = citylist.toList();

        CityResponse cityResponse = new CityResponse();
        cityResponse.setContent(citylist);
        cityResponse.setPageNo(pageList.getNumber());
        cityResponse.setPageSize(pageList.getSize());
        cityResponse.setTotalElements(pageList.getTotalElements());
        cityResponse.setTotalPages(pageList.getTotalPages());
        cityResponse.setLast(pageList.isLast());
        cityResponse.setSortBy(sortBy);
        cityResponse.setSortDir(sortDir);
        cityResponse.setStatus(status);
        cityResponse.setTitle(title);

        return cityResponse;
    }

    public List<City> getCities(){

        return cityRepository.findAll();

    }

	public City saveCity(City city) {

        return cityRepository.save(city);

	}

	public City getCity(String cityname) {
		return cityRepository.findByCityname(cityname);
	}

	public City addCity(CityRequest cityrequest) {
        Country country = countryRepository.findById(cityrequest.country_id);

        City city = new City();
        city.setCityname(cityrequest.cityname);
        city.setCitycode(cityrequest.citycode);
        city.setCountry(country);

    	return cityRepository.save(city);
	}

    public List<Country> findByCountryNameStartsWithOrderByPopulation(String countryname){

        return countryRepository.findByCountrynameStartsWithOrderByPopulationDesc(countryname);
    }

    public List<Country> getAllCountries(){

        return countryRepository.findAll(Sort.by(Direction.ASC,"population"));
    }

    public List<Country> getCountryContaining(String substring) {
        return countryRepository.findByCountrynameContaining(substring);
    }

    public Country getCountry(int id) {
        return countryRepository.getById(id);
    }

    public List<Country> getCountryByName(String prefix) {
        return countryRepository.getByCountryname(prefix);
    }

    public List<Object[]> getCountryByNameandPop(String prefix, long population) {
        return countryRepository.getByCnameAndPopulationNative(prefix, population);
    }

    public List<Country> getCountryByIds(Set<Integer> ids) {
        return countryRepository.getByIds(ids);
    }

}
