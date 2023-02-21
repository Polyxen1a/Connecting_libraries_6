package com.skypro.connecting_libraries_6.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skypro.connecting_libraries_6.Exceptions.FileProcessingException;
import com.skypro.connecting_libraries_6.Exceptions.IngredientExistsException;
import com.skypro.connecting_libraries_6.model.Ingredient;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.webjars.NotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class IngredientServiceImpl implements IngredientService {

    private final FileService fileService;

    public IngredientServiceImpl(@Qualifier("ingredientFileService") FileService fileService) {
        this.fileService = fileService;
    }

    private Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    private static Integer id = 0;

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        if (ingredientMap.containsValue((ingredient))) {
            throw new IngredientExistsException();
        }
        ingredientMap.put(id++, ingredient);
        saveToFileIngredient();
        return ingredient;
    }

    @Override
    public Ingredient getIngredient(Integer id) {
        if (!ingredientMap.containsKey(id)) {
            throw new NotFoundException("Ингредиент с заданным id не найден");
        }
        return ingredientMap.get(id);
    }

    @Override
    public Collection<Ingredient> getAll() {
        return ingredientMap.values();
    }

    @Override
    public Ingredient removeIngredient(int id) {
        if (!ingredientMap.containsKey(id)) {
            throw new NotFoundException("Ингредиент с заданным id не найден");
        }
        Ingredient removedIngredient = ingredientMap.remove(id);
        saveToFileIngredient();
        return removedIngredient;
    }

    @Override
    public Ingredient updateIngredient(int id, Ingredient ingredient) {
        if (!ingredientMap.containsKey(id)) {
            throw new NotFoundException("Ингредиент с заданным id не найден");
        }
        ingredientMap.put(id, ingredient);
        saveToFileIngredient();
        return ingredient;
    }

    @PostConstruct
    private void initIngredient() throws FileProcessingException {
        readFromFileIngredient();
    }

    private void readFromFileIngredient() throws FileProcessingException {
        try {
            String json = fileService.readFromFile();
            ingredientMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
                throw new FileProcessingException("файл не удалось прочитать");
            }
        }

            private void saveToFileIngredient() throws FileProcessingException {
                try {
                    String json = new ObjectMapper().writeValueAsString(ingredientMap);
                    fileService.saveToFile(json);
                } catch (JsonProcessingException e) {
                    throw new FileProcessingException("файл не удалось сохранить");
                }
            }
        }
