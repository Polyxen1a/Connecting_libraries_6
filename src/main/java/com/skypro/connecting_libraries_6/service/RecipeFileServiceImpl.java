package com.skypro.connecting_libraries_6.service;

import com.skypro.connecting_libraries_6.model.Recipe;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import com.skypro.connecting_libraries_6.Exceptions.FileProcessingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;


@Service("recipeFileService")

public class RecipeFileServiceImpl implements FileService {

    @Value("${scr/main/resources}")
    private String dataFilePathIngredient;
    @Value("${recipe.json}")
    private String dataFileNameRecipe;
    private Path path;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePathIngredient, dataFileNameRecipe), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readFromFile() {
        if (Files.exists(path)) {
            try {
                return Files.readString(path);
            } catch (IOException e) {
                throw new FileProcessingException("не удалось прочиать файл");
            }
        } else {
            return "{ }";
        }
    }

    @Override
    public boolean cleanDataFile() {
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public File getDataFile() {
        return new File(dataFilePathIngredient + "/" + dataFileNameRecipe);
    }

    @Override
    public InputStreamResource exportFile(Map<Integer, Recipe> recipeMap) throws FileNotFoundException {
        File file = getDataFile();
        return new InputStreamResource(new FileInputStream(file));
    }


    @Override
    public InputStreamResource exportTxtFile(Map<Integer, Recipe> recipeMap) throws IOException {
        Path path = this.createAllRecipesFile("allRecipes");
        for (Recipe recipe : recipeMap.values()) {
            try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                writer.append("Название рецепта: ");
                writer.append(recipe.getName());
                writer.append("/n Время приготовления: ");
                writer.append(String.valueOf(recipe.getCookingTime()));
                writer.append(" ");
                writer.append("/n Ингредиенты: ");
                writer.append(String.valueOf(recipe.getIngredients()));
                writer.append("/n Шаги приготовления: ");
                writer.append(String.valueOf(recipe.getSteps()));
            }
        }
        File file = path.toFile();
        return new InputStreamResource(new FileInputStream(file));
    }

    private Path createAllRecipesFile(String suffix) throws IOException {
        if (Files.exists(Path.of(dataFilePathIngredient, suffix))) {
            Files.delete(Path.of(dataFilePathIngredient, suffix));
            Files.createFile(Path.of(dataFilePathIngredient, suffix));
            return Path.of(dataFilePathIngredient, suffix);
        }
        return Files.createFile(Path.of(dataFilePathIngredient, suffix));
    }

    @Override
    public void importFile(MultipartFile file) throws FileNotFoundException {
        cleanDataFile();
        FileOutputStream fos = new FileOutputStream(getDataFile());
        try {
            IOUtils.copy(file.getInputStream(), fos);
        } catch (IOException e) {
            throw new FileProcessingException("Проблема сохранения файла");
        }
    }

    @Override
    public Path getPath() {
        return path;
    }
}
