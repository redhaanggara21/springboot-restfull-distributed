package com.lsio.springboot.services;

import java.util.List;
import java.util.Set;

import javax.persistence.criteria.Order;

import com.lsio.springboot.Pojos.CityRequest;
import com.lsio.springboot.entities.City;
import com.lsio.springboot.entities.Country;
import com.lsio.springboot.repositories.CityRepository;
import com.lsio.springboot.repositories.CountryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired CityRepository cityresRepository;
    @Autowired CountryRepository countryRepository;

    public CityService(){
        
    }

    public List<City> getCities(){

        return cityresRepository.findAll();

    }

	public City saveCity(City city) {

        return cityresRepository.save(city);

	}

	public City getCity(String cityname) {
		return cityresRepository.findByCityname(cityname);
	}

	public City addCity(CityRequest cityrequest) {
        Country country = countryRepository.findById(cityrequest.country_id);

        City city = new City();
        city.setCityname(cityrequest.cityname);
        city.setCitycode(cityrequest.citycode);
        city.setCountry(country);

    	return cityresRepository.save(city);
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
