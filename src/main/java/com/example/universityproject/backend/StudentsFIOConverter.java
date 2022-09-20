package com.example.universityproject.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class    StudentsFIOConverter {
    public static List<String> cutStud(List<String> students){
        List<String> newStudentsFormat = new ArrayList<>();
        for (String newFIOFormat:students) {
            StringBuilder latter = new StringBuilder();
            Matcher m = Pattern.compile("\\s\\W").matcher(newFIOFormat);
            while (m.find()) {
                latter.append(m.group() +".");
            }
           newStudentsFormat.add(newFIOFormat.replaceAll("\\s\\W+", String.valueOf(latter)));
        }
        return newStudentsFormat;
    }
}
