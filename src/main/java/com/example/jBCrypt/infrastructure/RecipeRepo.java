package com.example.jBCrypt.infrastructure;

import com.example.jBCrypt.domain.MyUser;
import com.example.jBCrypt.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepo extends JpaRepository<Recipe, Long> {
    List<Recipe> findAllByMyUser(MyUser myUser);
}
