package com.example.EasyDoctor.controller;


import com.example.EasyDoctor.repository.DoctorsRepository;
import com.example.EasyDoctor.services.DoctorService;
import com.example.EasyDoctor.model.Doctors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.print.Doc;
import java.util.List;

@Controller
public class DoctorsController {

    @Autowired // This means to get the bean called DoctorsRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    // private DoctorsRepository doctorsRepository;
    private DoctorService service;

    // @PostMapping(path="doc/add")
    // public @ResponseBody
    // String addNewDoctors (@RequestParam String name, @RequestParam String surname, @RequestParam String email, @RequestParam String specialization) {
    //     // @ResponseBody means the returned String is the response, not a view name
    //     // @RequestParam means it is a parameter from the GET or POST request
    //     Doctors n = new Doctors();
    //     n.setName(name);
    //     n.setSurname(surname);
    //     n.setEmail(email);
    //     n.setSpecialization(specialization);
    //     doctorsRepository.save(n);
    //     return "Saved";
    // }

//    @RequestMapping(path = "doc/search")
//    public String Search(Model model, @Param("Keyword") String keyword) {
//        List<Doctors> docs = service.listAll(keyword);
//        model.addAttribute("docs", docs);
//        model.addAttribute("keyword",keyword);
//
//        return "search";
//    }

    @RequestMapping(path = "/doc/add")
    public String addNewDoctor(Model model) {
        Doctors doctors = new Doctors();
        model.addAttribute("doctors", doctors);

        return "add_doctor";
    }

    @RequestMapping(value = "/doc/save", method = RequestMethod.POST)
    public String saveDoctor(@ModelAttribute("doctors") Doctors doctors){
       service.save(doctors);
        return "docall";
    }

    @RequestMapping("/doc/edit/{id}")
    public ModelAndView showEditDoctorPage(@PathVariable(name = "id")int id) {
        ModelAndView mav = new ModelAndView(("edit_doctor"));
        Doctors doctors = service.get((long) id);
        mav.addObject("doctors", doctors);

        return mav;
    }

    @RequestMapping("/doc/delete/{id}")
    public String deleteDoctor(@PathVariable(name = "id")int id) {
        service.delete((long) id);

        return "docall";
    }



    @RequestMapping(path="/doc/all")
    public String docAll(Model model,@Param("keyword") String keyword) {
        List<Doctors> docs = service.listAll(keyword);
        model.addAttribute("docs", docs);

        return "docall";
    }
//    public @ResponseBody Iterable<Doctors> getAllDoctors() {
//        // This returns a JSON or XML with the doctors
//        return doctorsRepository.findAll();
//    }

    // @PostMapping(path="/doc/search")
    // public String docSearch(@ModelAttribute("search") String search, Model model) {
    //     model.addAttribute("search", search);
    //     List<Doctors> docs = doctorsRepository.findBySpecialization(search);
    //     model.addAttribute("docs", docs);
    //     return "search.html";
    // }
}

