package com.skypro.connecting_libraries_6.service;

import com.skypro.connecting_libraries_6.model.Recipe;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;


public interface FileService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();

    File getDataFile();

    InputStreamResource exportFile(Map<Integer, Recipe> recipeMap) throws FileNotFoundException;

    InputStreamResource exportTxtFile(Map<Integer, Recipe> recipeMap) throws IOException;

    void importFile(MultipartFile file) throws FileNotFoundException;

    public Path getPath();

}
