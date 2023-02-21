package com.skypro.connecting_libraries_6.controller;

import com.skypro.connecting_libraries_6.model.Recipe;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
@Tag(name = "Рецепты", description = "CRUD-операции для работы с рецептами")

public class RecipeController {
    private final com.skypro.connecting_libraries_6.service.RecipeService RecipeService;

    @Operation(summary = "Поиск рецепта по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт был найден")})
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    @GetMapping("/{id}")
    ResponseEntity<Recipe> getRecipe(@PathVariable Integer id) {
        return ResponseEntity.ok(recipeService.getRecipe(id));
    }

    @GetMapping("/all")
    @Operation(summary = "Получение всех рецептов", description = "Поиск производится без параментров")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Рецепты получены" )})
    ResponseEntity<Collection<Recipe>> getRecipe() {
        return ResponseEntity.ok(recipeService.getAll());
    }

    @PostMapping
    @Operation(summary = "Добавление рецепта")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Рецепт добавлен")})
    ResponseEntity<Recipe> addRecipe(@Valid @RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.addRecipe(recipe));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение рецептов по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт изменен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Recipe.class)
                            )
                    }
            )

    })
    @Parameters(value = {
            @Parameter(name = "id",
                    example = "1")
    })
    ResponseEntity<Recipe> updateRecipe(@PathVariable Integer id, @Valid @RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.updateRecipe(id, recipe));
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление рецептом по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт удален"
            )
    })
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    ResponseEntity<Recipe> removeRecipe(@PathVariable Integer id) {
        return ResponseEntity.ok(recipeService.removeRecipe(id));
    }
    @GetMapping
    @Operation(summary = "Получение всех рецептов", description = "поиск производится без параментов")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Рецепты получены")})
    ResponseEntity<Collection<Recipe>> getRecipesIngredientId() {
        return ResponseEntity.ok(recipeService.getAll());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(NotFoundException notFoundException) {
        return notFoundException.getMessage();
    }
}