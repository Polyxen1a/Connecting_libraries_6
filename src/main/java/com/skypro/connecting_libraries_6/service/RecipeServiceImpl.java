package com.skypro.connecting_libraries_6.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skypro.connecting_libraries_6.Exceptions.FileProcessingException;
import com.skypro.connecting_libraries_6.model.Recipe;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class RecipeServiceImpl implements RecipeService {

    private final FileService fileService;

    private Map<Integer, Recipe> recipeMap = new HashMap<>();
    private static Integer id = 0;

    public Map<Integer, Recipe> getRecipeMap() {
        return recipeMap;
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipeMap.put(id++, recipe);
        saveToFileRecipe();
        return recipe;
    }

    @Override
    public Recipe getRecipe(Integer id) {
        if (!recipeMap.containsKey(id)) {
            throw new NotFoundException("Рецепт с заданным id не найден");
        }
        return recipeMap.get(id);
    }

    @Override
    public Collection<Recipe> getAll() {
        return recipeMap.values();
    }

    @Override
    public Recipe removeRecipe(int id) {
        if (recipeMap.containsKey(id)) {
            throw new NotFoundException("Рецепт с заданным id не найден");
        }
        return recipeMap.remove(id);
    }

    @Override
    public Recipe updateRecipe(int id, Recipe recipe) {
        if (recipeMap.containsKey(id)) {
            throw new NotFoundException("Рецепт с заданным id не найден");
        }
        return recipeMap.put(id, recipe);
    }
    @PostConstruct
    private void saveToFileRecipe() throws FileProcessingException {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            fileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new FileProcessingException("Рецепт не удалось сохранить");
        }
    }
}
