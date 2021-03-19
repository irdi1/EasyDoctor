package com.example.EasyDoctor.controller;

import com.example.EasyDoctor.DTO.DoctorsRegistrationDto;
import com.example.EasyDoctor.services.DoctorsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class DoctorsRegistrationController {

    private DoctorsService doctorsService;

    public DoctorsRegistrationController(DoctorsService doctorsService){
        this.doctorsService = doctorsService;
    }
    @ModelAttribute("doctors")
    public DoctorsRegistrationDto doctorsRegistrationDto() {
        return new DoctorsRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerDoctorsAccount(@ModelAttribute("doctors") DoctorsRegistrationDto registrationDto) {
        doctorsService.save(registrationDto);
        return "redirect:/registration?success";
    }


}
