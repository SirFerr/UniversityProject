package com.example.universityproject.backend;
import com.example.universityproject.backend.ExcelParsing;
import com.example.universityproject.backend.UpdateDocument;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClassForTests {
    public static void main(String[] args) throws IOException {
        ExcelParsing objForExcelParsing = new ExcelParsing();
        //get arrayList of Students and write file Path
        List<String> students = objForExcelParsing.pushToArrayList("src/main/resources/wordAndExcelTemplates/Пример таблицы.xlsx");
        List<String> studentInShortForm = StudentsFIOConverter.cutStud(students);
        //delete in future
        System.out.println(studentInShortForm.get(5));
        System.out.println(students);
        // initialise arraylist witch contains key-words(like ${id}, &{name} etc.)
        ArrayList<String> replaceableNames = new ArrayList<>(){{
            add("${instituteName}");
            add("${departmentName}");
            add("${practiceName}");
            add("${orderDate}");
            add("${orderName}");
            add("${sessionDate}");
            add("${studentFN}");
            add("${supervisorFN}");
            add("${currentYear}");
            add("${courseNum}");
            add("${groupName}");
            add("${studentFullName}");
            add("${practicePlaceAndTime}");
            add("${position}");
            add("${currentDate}");
            add("${headOfDFN}");
            add("${directionNum}");
            add("${directionName}");
            add("${profileName}");
        }};

        //initialise object to use methode updateDocument
        UpdateDocument objUpdateWord = new UpdateDocument();

        //creating variables for path to files
        String inputPath = "src/main/resources/wordAndExcelTemplates/mainWordFile.docx";
        String outputPath = "src/main/resources/wordAndExcelTemplates/fileForTesting.docx";

        // for statement to replace everything in the project
        for(int i = 0; i < replaceableNames.size(); i++){
            //switch-case to work with key-words
            switch (replaceableNames.get(i)) {
                case "${instituteName}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${instituteName}", getIn);
                case "${departmentName}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${departmentName}", departmentName);
                case "${practiceName}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${practiceName}", practiceName);
                case "${orderDate}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${orderDate}", orderDate);
                case "${orderName}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${orderName}", orderName);
                case "${sessionDate}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${sessionDate}", sessionDate);
                case "${supervisorFN}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${supervisorFN}", supervisorFN);
                case "${currentYear}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${currentYear}", currentYear);
                case "${courseNum}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${courseNum}", courseNum);
                case "${groupName}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${groupName}", groupName);
                case "${practicePlaceAndTime}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${practicePlaceAndTime}", practicePlaceAndTime);
                case "${position}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${position}", position);
                case "${currentDate}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${currentDate}", currentDate);
                case "${headOfDFN}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${headOfDFN}", headOfDFN);
                case "${directionNum}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${directionNum}", directionNum);
                case "${directionName}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${directionName}", directionName);
                case "${profileName}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${profileName}", profileName);
                default -> {
                }
            }
        }
        for(int i = 0; i < students.size(); i++){
            XWPFDocument doc = new XWPFDocument();
            FileOutputStream out = new FileOutputStream("src/main/resources/wordAndExcelTemplates/TitleLists.docx");
            objUpdateWord.updateDocument(outputPath, "src/main/resources/wordAndExcelTemplates/TitleLists.docx", "${studentFN}", students.get(i));
            objUpdateWord.updateDocument(outputPath, "src/main/resources/wordAndExcelTemplates/TitleLists.docx", "${studentFullName}", studentInShortForm.get(i));
            doc.write(out);
            String filePath1 = "src/main/resources/wordAndExcelTemplates/TitleLists.docx";
            String filePath2 = "src/main/resources/wordAndExcelTemplates/fileForTesting.docx";
            //Load the first document
            Document document = new Document(filePath1);
            //Insert content of the second document into the first document
            document.insertTextFromFile(filePath2, FileFormat.Docx_2013);
            objUpdateWord.updateDocument("src/main/resources/wordAndExcelTemplates/TitleLists.docx", "src/main/resources/wordAndExcelTemplates/TitleLists.docx", "Evaluation Warning: The document was created with Spire.Doc for JAVA.", "");
            //Save the resultant document
            document.saveToFile("src/main/resources/wordAndExcelTemplates/TitleLists.docx", FileFormat.Docx_2013);
        }
    }
}