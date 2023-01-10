package com.lsio.springboot.repositories;

import com.lsio.springboot.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City,Long>{

    City findByCityname(String cityname);

    // @Query(value = "SELECT * FROM Ci", nativeQuery = true)
    // City sortPagFilter(String cityname);

    // Page<City> findByPublished(boolean published, Pageable pageable);
    // Page<City> findByTitleContaining(String title, Pageable pageable);
    
}
