package com.example.universityproject.backend;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ResultPusher {
    static void pushFile(String path){
        Path file = Paths.get(path);
        String pathToDesktop = System.getProperty(("user.home"))  + "\\OneDrive\\Desktop";
        if(!isCorrectPath(pathToDesktop)){
            pathToDesktop = pathToDesktop.replace("Desktop", "Рабочий Стол");
            if(!isCorrectPath(pathToDesktop))
                pathToDesktop = pathToDesktop.replace("\\OneDrive","");
            if(!isCorrectPath(pathToDesktop))
                pathToDesktop = pathToDesktop.replace("Рабочий Стол","Desktop");
        }
        try {
            Files.copy(file, Paths.get(pathToDesktop+"\\TitleList.docx"),REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    static boolean isCorrectPath(String pathToDesktop){
        return Files.exists(Paths.get(pathToDesktop));
    }
}
