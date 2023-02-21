package com.skypro.connecting_libraries_6.service;

import java.io.File;

public interface FileService {

    boolean saveToFile(String json);


    String readFromFile();

    boolean cleanDataFile();

    File getDataFileTxt();


}
