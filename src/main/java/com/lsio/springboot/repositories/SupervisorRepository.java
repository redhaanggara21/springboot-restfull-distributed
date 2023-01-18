package com.lsio.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lsio.springboot.entities.Supervisor;
import java.util.Optional;


@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Integer> {
    Optional<Supervisor> findFirstBySupervisorNo(String empNo);
}

