package com.example.EasyDoctor.services;

import com.example.EasyDoctor.model.Doctors;
import com.example.EasyDoctor.repository.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DoctorService {
    
    @Autowired
    private DoctorsRepository doctorsRepository;

    public List<Doctors> listAll(String keyword) {
        if (keyword != null ) return doctorsRepository.findAll(keyword);
        return doctorsRepository.findAll();
    }

    public void save(Doctors doctors){
        doctorsRepository.save(doctors);
    }

    public Doctors get(Long id) {
        return doctorsRepository.findById(id).get();
    }

    public void delete(Long id) {
        doctorsRepository.deleteById(id);
    }
}



//    public List<Doctors> listAll(String keyword) {
//        if (keyword != null ) {
//            return doctorsRepository.findBySpecialization(keyword);
//        }
//        return doctorsRepository.findAll();
//    }