package com.pawan.websockets.controllers;

import com.pawan.websockets.entity.RegisterDto;
import com.pawan.websockets.entity.User;
import com.pawan.websockets.services.RepoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    @Autowired
    RepoService repoService;
    @GetMapping("/register")
    public String register(Model model) {
        RegisterDto user=new RegisterDto();
        model.addAttribute(user);
        model.addAttribute("success",false);
        return "register";
    }
    @PostMapping("/register")
    public String register(Model model,
                           @Valid @ModelAttribute RegisterDto registerDto,
                           BindingResult result){

         if(!registerDto.getPassword().equals(registerDto.getConfirmPassword())){
             result.addError(
                     new FieldError("registerDto","confirmPassword","Password and Confirm Password do not match")

             );
         }
        User user= repoService.findByUsername(registerDto.getUsername());
         if(user!=null){
             result.addError(
                     new FieldError("registerDto","username","username is already used")
             );
         }
         if(result.hasErrors()){
             return "register";
         }
         try {
             var bCryptEncoder =new BCryptPasswordEncoder();

             User newUser=new User();
             newUser.setUsername(registerDto.getUsername());
             newUser.setPassword(registerDto.getPassword());
             newUser.setRole("USER");
             newUser.setPassword(bCryptEncoder.encode(registerDto.getPassword()));

             repoService.save(newUser);

             model.addAttribute("registerDto", new RegisterDto());
             model.addAttribute("success",true);
         }
             catch(Exception ex){
                 result.addError(
                         new FieldError("registerDto","username",ex.getMessage())
                 );
         }
        return "register";
    }
}
