package com.lsio.springboot.config;


import org.springframework.context.annotation.Configuration;
import com.lsio.springboot.entities.Supervisor;
import com.lsio.springboot.repositories.SupervisorRepository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class AppLoadingData {

    private final SupervisorRepository repo;

    public AppLoadingData(SupervisorRepository repo) {
        this.repo = repo;
    }

    @PostConstruct
    private void loadingData() throws ParseException {

        var dob = this.date("1990-02-11");
        var hiredDate = this.date("2012-02-11");
        List<Supervisor> supervisers = new ArrayList<>();
        
        supervisers.add(new Supervisor(1, "102299", "Jala", "****", "Makav", "IT", "0892547**", hiredDate,
                "Backend", "6", "M", dob, new BigDecimal(250_000),
                123_000.0, 10_000.0));


        supervisers.add(new Supervisor(2, "102298", "Mala", "****", "Salav", "IT", "0892547**", hiredDate,
                "Backend", "6", "F", dob, new BigDecimal(230_000),
                123_00.0, 1_000.0));

        repo.saveAll(supervisers);
    }

    protected Date date(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.parse(date);
    }
}
