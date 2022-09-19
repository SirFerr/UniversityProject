package com.example.universityproject;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClassForTests {
    public static void main(String[] args) throws IOException {
        ExcelParsing objForExcelParsing = new ExcelParsing();
        //get arrayList of Students and write file Path
        List<String> students = objForExcelParsing.pushToArrayList("src/main/resources/wordAndExcelTemplates/Пример таблицы.xlsx");
        //delete in future
        System.out.println(students);
        ArrayList<String> replaceableNames = new ArrayList<>();
        replaceableNames.add("${instituteName}");
        replaceableNames.add("${departmentName}");
        replaceableNames.add("${practiceName}");
        replaceableNames.add("${orderDate}");
        replaceableNames.add("${orderName}");
        replaceableNames.add("${sessionDate}");
        replaceableNames.add("${studentFN}");
        replaceableNames.add("${supervisorFN}");
        replaceableNames.add("${currentYear}");
        replaceableNames.add("${courseNum}");
        replaceableNames.add("${groupName}");
        replaceableNames.add("${studentFullName}");
        replaceableNames.add("${practicePlaceAndTime}");
        replaceableNames.add("${position}");
        replaceableNames.add("${currentDate}");
        replaceableNames.add("${headOfDFN}");
        replaceableNames.add("${directionNum}");
        replaceableNames.add("${directionName}");
        replaceableNames.add("${profileName}");
        UpdateDocument objUpdateWord = new UpdateDocument();
        String inputPath = "D:\\wrdfiles\\Титулы практики.docx";
        String outputPath = "D:\\wrdfiles\\projecttest.docx";
        for(int i = 0; i < replaceableNames.size(); i++){
            switch (replaceableNames.get(i)){
                case "${instituteName}":
                    objUpdateWord.updateDocument(inputPath, outputPath, "${instituteName}", instituteName);
                    break;
                case "${departmentName}":
                    objUpdateWord.updateDocument(inputPath, outputPath, "${departmentName}", departmentName);
                    break;
                case "${practiceName}":
                    objUpdateWord.updateDocument(inputPath, outputPath, "${practiceName}", practiceName);
                    break;
                case "${orderDate}":
                    objUpdateWord.updateDocument(inputPath, outputPath, "${orderDate}", orderDate);
                    break;
                case "${orderName}":
                    objUpdateWord.updateDocument(inputPath, outputPath, "${orderName}", orderName);
                    break;
                case "${sessionDate}":
                    objUpdateWord.updateDocument(inputPath, outputPath, "${sessionDate}", sessionDate);
                    break;
                case "${studentFN}":
                    objUpdateWord.updateDocument(inputPath, outputPath, "${studentFN}", studentFN);
                    break;
                case "${supervisorFN}":
                    objUpdateWord.updateDocument(inputPath, outputPath, "${supervisorFN}", supervisorFN);
                    break;
                case "${currentYear}":
                    objUpdateWord.updateDocument(inputPath, outputPath, "${currentYear}", currentYear);
                    break;
                case "${courseNum}":
                    objUpdateWord.updateDocument(inputPath, outputPath, "${courseNum}", courseNum);
                    break;
                case "${groupName}":
                    objUpdateWord.updateDocument(inputPath, outputPath, "${groupName}", groupName);
                    break;
                case "${studentFullName}":
                    objUpdateWord.updateDocument(inputPath, outputPath, "${studentFullName}", studentFullName);
                    break;
                case "${practicePlaceAndTime}":
                    objUpdateWord.updateDocument(inputPath, outputPath, "${practicePlaceAndTime}", practicePlaceAndTime);
                    break;
                case "${position}":
                    objUpdateWord.updateDocument(inputPath, outputPath, "${position}", position);
                    break;
                case "${currentDate}":
                    objUpdateWord.updateDocument(inputPath, outputPath, "${currentDate}", currentDate);
                    break;
                case "${headOfDFN}":
                    objUpdateWord.updateDocument(inputPath, outputPath, "${headOfDFN}", headOfDFN);
                    break;
                case "${directionNum}":
                    objUpdateWord.updateDocument(inputPath, outputPath, "${directionNum}", directionNum);
                    break;
                case "${directionName}":
                    objUpdateWord.updateDocument(inputPath, outputPath, "${directionName}", directionName);
                    break;
                case "${profileName}":
                    objUpdateWord.updateDocument(inputPath, outputPath, "${profileName}", profileName);
                    break;
            }
        }
    }
}
