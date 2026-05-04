package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
public class PageController {

    @Autowired
    private UserService userService;


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

    //Valid for validating the form data and BindingResult for handling the validation errors
    public String processRegister(@Valid @ModelAttribute UserForm userForm,BindingResult bindingResult ,HttpSession session) {
        System.out.println("processing registration form data");

        //fetch data
        System.out.println(userForm);
        //validate form

        if(bindingResult.hasErrors()){
            System.out.println("validation errors: " + bindingResult.getAllErrors());
            return "register";
        }

        //save in db

        
        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png")
        // .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png");
        


        User savedUser = userService.saveUser(user);

        System.out.println(savedUser);

        //message success
        Message message = Message.builder().content("Registration Successfull").type(MessageType.green).build();
        session.setAttribute("message", message);


        //redirect after processing the registration form data
        return "redirect:/register";
    }


}
