package com.example.jBCrypt;

import com.example.jBCrypt.domain.MyUser;
import com.example.jBCrypt.domain.Recipe;
import com.example.jBCrypt.infrastructure.RecipeRepo;
import com.example.jBCrypt.infrastructure.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class controller {

    @Autowired
    RecipeRepo recipeRepo ;

    @Autowired
    UserRepo userRepo;

    @GetMapping("/")
    public String getHomePage(){
        return "index.html";
}
    @GetMapping("/profile")
    public String getHomePageWithSecret (HttpServletRequest request , Model model){
        HttpSession session = request.getSession();
        String username = session.getAttribute("username").toString();
        model.addAttribute("username",username);

        MyUser myUser = userRepo.findByUsername(username);
        model.addAttribute("recipes",recipeRepo.findAllByMyUser(myUser));
        model.addAttribute("id",myUser.id);

        return "indexWithSecret.html";
}

    @PostMapping("/addRecipe/{id}")
    public String addingRecipes (@ModelAttribute Recipe newRecipe, @PathVariable Long id ,HttpServletRequest request , Model model){
        MyUser myUser = userRepo.findById(id).orElseThrow();
        myUser.recipes.add(newRecipe);
        newRecipe.setMyUser(myUser);
        recipeRepo.save(newRecipe);

        HttpSession session = request.getSession();
        String username = session.getAttribute("username").toString();
        model.addAttribute("username",username);
        model.addAttribute("recipes",recipeRepo.findAllByMyUser(myUser));
        model.addAttribute("id",myUser.id);

        return "indexWithSecret.html";
    }

    @GetMapping("/addRecipe/{id}")
    public String addingRecipesPage (@PathVariable Long id){
        return "addRecipe.html";
    }

}
