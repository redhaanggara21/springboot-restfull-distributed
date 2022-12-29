package com.lsio.springboot.services;

import com.lsio.springboot.entities.Doctor;
import com.lsio.springboot.entities.Patient;
import com.lsio.springboot.repositories.DoctorRepository;
import com.lsio.springboot.repositories.PatientRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class DoctorPatientService {

    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PatientRepository patientRepository;

    public DoctorPatientService(){

    }

    public Doctor addDoctor(Doctor doctor){
        return doctorRepository.save(doctor);
    }
    
    public Patient addPatient(Patient patient){
        return patientRepository.save(patient);
    }
}
