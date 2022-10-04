package com.example.universityproject.backend;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ResultPusher {
    static void pushFile(String path){
        Path file = Paths.get(path);

        try {
            Files.copy(file, Paths.get("C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\TitleList.docx"),REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
