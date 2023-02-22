package com.skypro.connecting_libraries_6.controller;

import com.skypro.connecting_libraries_6.service.FileService;
import com.skypro.connecting_libraries_6.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/files")
@Tag(name = "Files", description = "CRUD-операции для работы с файлами")

public class FilesController {
    private final FileService recipeFileService;
    private final FileService ingredientFileService;

    private final RecipeService recipeService;

    public FilesController(@Qualifier("ingredientFileService") FileService ingredientFileService,
                           @Qualifier("recipeFileService") FileService recipeFileService,
                           RecipeService recipeService) {
        this.recipeFileService = recipeFileService;
        this.ingredientFileService = ingredientFileService;
        this.recipeService = recipeService;
    }

    @GetMapping("ingredient/export")
    @Operation(description = "Экспорт файла ингредиентов)")
    public ResponseEntity<InputStreamResource> downloadIngredientFile() throws IOException {
        InputStreamResource inputStreamResource = ingredientFileService.exportFile(recipeService.getRecipeMap());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(Files.size(ingredientFileService.getPath()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = \"Ingredients.json\"")
                .body(inputStreamResource);
    }

    @GetMapping("/recipe/exportTxt")
    @Operation(description = "Экспорт файла рецептов")
    public ResponseEntity<InputStreamResource> downloadTxtRecipeFile() throws IOException {
        InputStreamResource inputStreamResource = recipeFileService.exportFile(recipeService.getRecipeMap());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(Files.size(ingredientFileService.getPath()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = \"Ingredients.json\"")
                .body(inputStreamResource);
    }

    @PostMapping(value = "/ingredient/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(description = "Импорт файла ингредиентов")
    public ResponseEntity<Void> uploadDataFileIngredient(@RequestParam MultipartFile file) throws FileNotFoundException {
        ingredientFileService.importFile(file);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/recipe/export")
    @Operation(description = "Экспорт файла рецептов")
    public ResponseEntity<InputStreamResource> downloadRecipeFile() throws IOException {
        InputStreamResource inputStreamResource = recipeFileService.exportTxtFile(recipeService.getRecipeMap());
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(Files.size(recipeFileService.getPath()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = \"AllRecipes.txt\"")
                .body(inputStreamResource);
    }
    @GetMapping(value = "/recipe/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(description = "Импорт файла рецептов")
    public ResponseEntity<Void> uploadDataFileRecipe(@RequestParam MultipartFile file) throws FileNotFoundException {
        return ResponseEntity.ok().build();
    }
}


