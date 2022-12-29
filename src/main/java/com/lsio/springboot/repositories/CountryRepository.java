package com.lsio.springboot.repositories;

import java.util.List;
import java.util.Set;

import com.lsio.springboot.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Integer> {

    Country findById(int id);

    @Query("from Country where id = ?1")
    Country getById(int id);

    @Query("from Country where id in (?1)")
    List<Country> getByIds(Set<Integer> ids);

    List<Country> findByCountrynameAndPopulationGreaterThanEqual(String countryname, long population);

    List<Country> findByCountrynameAndPopulationLessThanEqual(String countryname, long population);
    List<Country> findByCountrynameAndPopulationLessThan(String countryname, long population);
    List<Country> findByCountrynameAndPopulationGreaterThan(String countryname, long population);

    List<Country> findByCountrynameStartsWithOrderByPopulationAsc(String prefix);

    @Query("select c from Country c where c.countryname like ?1% ORDER BY c.population ASC")
    List<Country> getByCountryname(String prefix);

    @Query("select c.countryname,c.population from Country c where c.countryname like :countryname% and c.population >= :population")
    List<Object[]> getByCnameAndPopulation(@Param("countryname") String countryname,@Param("population") long population);

    @Query(value = "select c.country_name,c.population,c.gdp,ct.city_name from countries c left join cities ct on c.country_id=ct.country_id where c.country_name like :countryname% and c.population >= :population", nativeQuery = true)
    List<Object[]> getByCnameAndPopulationNative(@Param("countryname") String countryname,@Param("population") long population);

    List<Country> findByCountrynameStartsWithOrderByPopulationDesc(String suffix);

    List<Country> findByCountrynameStartsWith(String prefix);
    List<Country> findByCountrynameEndsWith(String suffix);

    List<Country> findByCountrynameContaining(String substring);
    

}
