package com.scm.controllers;

import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties.Apiversion.Use;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.forms.UserForm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

@Controller
public class PageController {

    // Home page 
    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("home controller called");

        //Sending data to the html page using model (LINE:11)

        model.addAttribute("title", "Home Page");
        model.addAttribute("message", "Welcome to the Home Page!");
        model.addAttribute("description", "This is the home page of our application.");

        return "home";
    }

    // About page
    @RequestMapping("/about")
    public String aboutPage(Model model){

        model.addAttribute("isLogin", false);

        System.out.println("about controller called");
        return "about";
    }

    // Services page
    @RequestMapping("/services")
    public String servicesPage(){
        System.out.println("services controller called");
        return "services";
    }

    // Contact page
    @GetMapping("/contact")
    public String contact(){
        return new String("contact");
    }

    @GetMapping("/login")
    public String login() {
        return new String("login");
    }

    @GetMapping("/register")
    public String register(Model model) {

        //send empty userform object to the html page using model
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);

        return new String("register");
    }
    
    //Processing Registration form data
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@ModelAttribute UserForm userForm) {
        System.out.println("processing registration form data");

        //fetch data

        //validate form
        //save in db
        //message success
        //redirect after processing the registration form data
        return "redirect:/login";
    }


}
