package com.skypro.connecting_libraries_6.service;

import com.skypro.connecting_libraries_6.model.Ingredient;

import java.util.Collection;

public interface IngredientService {
    Ingredient addIngredient(Ingredient ingredient);

    Ingredient getIngredient(Integer id);

    Collection<Ingredient> getAll();

    Ingredient removeIngredient(int id);

    Ingredient updateIngredient(int id, Ingredient ingredient);
}

