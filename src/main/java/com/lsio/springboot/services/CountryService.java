package com.lsio.springboot.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.lsio.springboot.entities.Country;
import com.lsio.springboot.repositories.CountryRepository;

@Service
public class CountryService {
    @Autowired CountryRepository countryRepository;

    public CountryService(){
        
    }

    public Country saveCountry(Country country) {

        return countryRepository.save(country);

	}

}
