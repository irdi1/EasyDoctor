package com.example.EasyDoctor.services;

import com.example.EasyDoctor.DTO.DoctorsRegistrationDto;
import com.example.EasyDoctor.model.Doctors;
import com.example.EasyDoctor.model.Role;
import com.example.EasyDoctor.repository.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DoctorServiceImpl implements DoctorsService {

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public DoctorServiceImpl(DoctorsRepository doctorsRepository) {
        super();
        this.doctorsRepository = doctorsRepository;
    }

    @Override
    public Doctors save(DoctorsRegistrationDto registrationDto) {
        Doctors doctors = new Doctors(registrationDto.getName(),registrationDto.getSurname(),
                registrationDto.getEmail(), passwordEncoder.encode(registrationDto.getPassword()), registrationDto.getSpecialization(),
                Arrays.asList(new Role("ROLE_DOCTOR")));

        return doctorsRepository.save(doctors);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Doctors doctors = doctorsRepository.findByEmail(username);
        if (doctors == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(doctors.getEmail(), doctors.getPassword(), mapRolesToAuthorities(doctors.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

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