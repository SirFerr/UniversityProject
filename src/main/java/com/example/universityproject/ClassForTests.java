package com.example.universityproject;


import java.io.IOException;
import java.util.List;

public class ClassForTests {
    public static void main(String[] args) throws IOException {
        ExcelParsing objForExcelParsing = new ExcelParsing();
        //get arrayList of Students and write file Path
        List<String> students = objForExcelParsing.pushToArrayList("src/main/resources/wordAndExcelTemplates/Пример таблицы.xlsx");
        //delete in future
        System.out.println(students);
    }
}
