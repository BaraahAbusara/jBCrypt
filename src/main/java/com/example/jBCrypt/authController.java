package com.example.jBCrypt;

import com.example.jBCrypt.domain.MyUser;
import com.example.jBCrypt.infrastructure.UserRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.*;


@Controller
public class authController {
    @Autowired
    UserRepo userRepo;

    @GetMapping("/signup")  //we just show the signup page before signing up
    public String getSignUpPage(){
        return "signup.html";
    }

    @PostMapping("/signup") //signing up and saving the user information to the database
    public RedirectView signUpDone(Model model, String username , String password)
    {
        if(userRepo.findByUsername(username)==null) {
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
            MyUser myuser = new MyUser(username, hashedPassword);
            userRepo.save(myuser);
            return new RedirectView("/login");
        }
        else {
//            https://stackoverflow.com/questions/9119481/how-to-present-a-simple-alert-message-in-java
//            JOptionPane.showMessageDialog(null, "The username is already user , please try another one.");
            return new RedirectView("/signupError");
        }
    }

    @GetMapping ("/signupError")
    public String signupError(){
        return "signupError.html";
    }
    @PostMapping("/signupError")
    public RedirectView signupErrorPage(Model model, String username , String password){
        if(userRepo.findByUsername(username)==null) {
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
            MyUser myuser = new MyUser(username, hashedPassword);
            userRepo.save(myuser);
            return new RedirectView("/login");
        }
        else {
            return new RedirectView("/signupError");
        }

    }


    @GetMapping("/login")
    public String loginWithSecret()
    {
        return "/loginWithSecrets.html";
    }


    @PostMapping("/login") //loginWithSecret
    public RedirectView loginWithSecretDone(HttpServletRequest request , String username , String password){
        MyUser myuser = userRepo.findByUsername(username);
        if (myuser == null || !(BCrypt.checkpw(password,myuser.getPassword()))) {
            return new RedirectView("/login"); //loginWithSecret
        }
        HttpSession session = request.getSession();
        session.setAttribute("username",username);
        return new RedirectView("/profile");
    }

    @PostMapping("/logout")
    public RedirectView logoutWithSecret(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return new RedirectView ("/");
    }

}
