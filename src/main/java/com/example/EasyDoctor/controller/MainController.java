// You need to create a controller to handle HTTP requests to your application
package com.example.EasyDoctor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // This means this class is a controller
public class MainController {
 @RequestMapping("/")
    public String index() {
     return "index";
 }
}

