package com.example.EasyDoctor.services;

import com.example.EasyDoctor.DTO.DoctorsRegistrationDto;
import com.example.EasyDoctor.model.Doctors;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface DoctorsService extends UserDetailsService {
    Doctors save(DoctorsRegistrationDto registrationDto);
}
