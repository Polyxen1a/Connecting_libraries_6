package com.skypro.connecting_libraries_6.service;

import jakarta.annotation.PostConstruct;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import com.skypro.connecting_libraries_6.Exceptions.FileProcessingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
@Service("ingredientFileService")
public class IngredientFileServiceImpl implements FileService {
    @Value("${scr/main/resources}")
    private String dataFilePathIngredient;
    @Value("${ingredient.json}")
    private String dataFileNameIngredient;

    private Path path;

    @PostConstruct
    private void init() {
        path = Path.of(dataFilePathIngredient, dataFileNameIngredient);
    }
    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePathIngredient, dataFileNameIngredient), json);
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
        return new File(dataFilePathIngredient + "/" + dataFileNameIngredient);
    }

    @Override
    public InputStreamResource exportFile() throws FileNotFoundException {
        File file = getDataFile();
        return new InputStreamResource(new FileInputStream(file));
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