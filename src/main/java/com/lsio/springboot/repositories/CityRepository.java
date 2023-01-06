package com.lsio.springboot.repositories;

import com.lsio.springboot.entities.City;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.domain.Sort;

@Repository
public interface CityRepository extends JpaRepository<City,Long>{

    City findByCityname(String cityname);

    // @Query(value = "SELECT * FROM Ci", nativeQuery = true)
    // City sortPagFilter(String cityname);

    // Page<City> findByPublished(boolean published, Pageable pageable);
    // Page<City> findByTitleContaining(String title, Pageable pageable);
    
}
