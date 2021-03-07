package com.example.Bookstore.web;

import com.example.Bookstore.domain.SignupForm;
import com.example.Bookstore.domain.User;
import com.example.Bookstore.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserRepository repository;

    @RequestMapping("signup")
    public String addUser(Model model){
        model.addAttribute("signupform", new SignupForm());
        return "signup";
    }

    @PostMapping("saveuser")
    public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            if(signupForm.getPassword().equals(signupForm.getPasswordCheck())){
                String pwd = signupForm.getPassword();
                BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
                String hashPwd = bc.encode(pwd);

                User newUser = new User();
                newUser.setPasswordHash(hashPwd);
                newUser.setUsername(signupForm.getUsername());
                newUser.setRole("USER");
                newUser.setEmail(signupForm.getEmail());
                if(repository.findUserByUsername(signupForm.getUsername()) == null){
                    repository.save(newUser);
                }
                else {
                    bindingResult.rejectValue("username", "err.username", "Username already exists!");
                    return "signup";
                }
            }
            else {
                bindingResult.rejectValue("passwordCheck", "err.passCheck", "Password does not match!");
            }
        }
        else {
            return "signup";
        }
        return "redirect:/login";
    }
}
