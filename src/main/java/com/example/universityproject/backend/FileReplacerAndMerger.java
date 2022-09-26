package com.example.universityproject.backend;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface FileReplacerAndMerger {
    String pathToTitleList = "src/main/resources/wordAndExcelTemplates/TitleLists.docx";
    default void fileReplacerAndMerger(ComboBox instituteName, ComboBox departmentName,
                                              ComboBox practiceName, DatePicker orderDate,
                                              TextField orderName, DatePicker sessionDate,
                                              TextField supervisorFN, int currentYear,
                                              ChoiceBox courseNum, TextField groupName,
                                              TextField practicePlaceAndTime, TextField position,
                                              DatePicker currentDate, TextField headOfDFN,TextField directionName,
                                              TextField profileName) throws IOException {
        ExcelParsing objForExcelParsing = new ExcelParsing();
        //get arrayList of Students and write file Path
        List<String> students = objForExcelParsing.pushToArrayList("src/main/resources/wordAndExcelTemplates/Пример таблицы.xlsx");
        List<String> studentInShortForm = StudentsFIOConverter.cutStud(students);
        //delete in future
        //System.out.println(studentInShortForm.get(5));
        //System.out.println(students);
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
        for (String replaceableName : replaceableNames) {
            //switch-case to work with key-words
            switch (replaceableName) {
                case "${instituteName}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${instituteName}", (String) instituteName.getValue());
                case "${departmentName}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${departmentName}", (String) departmentName.getValue());
                case "${practiceName}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${practiceName}", (String) practiceName.getValue());
                case "${orderDate}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${orderDate}", String.valueOf(orderDate.getValue()));
                case "${orderName}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${orderName}", orderName.getText());
                case "${sessionDate}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${sessionDate}", String.valueOf(sessionDate.getValue()));
                case "${supervisorFN}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${supervisorFN}", supervisorFN.getText());
                case "${currentYear}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${currentYear}", String.valueOf(currentYear));
                case "${courseNum}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${courseNum}", (String) courseNum.getValue());
                case "${groupName}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${groupName}", groupName.getText());
                case "${practicePlaceAndTime}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${practicePlaceAndTime}", practicePlaceAndTime.getText());
                case "${position}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${position}", position.getText());
                case "${currentDate}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${currentDate}", String.valueOf(currentDate.getValue()));
                case "${headOfDFN}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${headOfDFN}", headOfDFN.getText());
                case "${directionNum}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${directionNum}", directionName.getText());
                case "${directionName}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${directionName}", directionName.getText());
                case "${profileName}" -> objUpdateWord.updateDocument(inputPath, outputPath, "${profileName}", profileName.getText());
                default -> {
                }
            }
        }
        for(int i = 0; i < students.size(); i++){
            try(XWPFDocument doc = new XWPFDocument(); FileOutputStream out = new FileOutputStream(pathToTitleList)){
                if (i == 0) {
                    objUpdateWord.updateDocument(outputPath, pathToTitleList, "${studentFN}", students.get(i));
                    objUpdateWord.updateDocument(outputPath, pathToTitleList, "${studentFullName}", studentInShortForm.get(i));
                    doc.write(out);
                }else{
                    objUpdateWord.updateDocument(pathToTitleList, pathToTitleList, "${studentFN}", students.get(i));
                    objUpdateWord.updateDocument(pathToTitleList, pathToTitleList, "${studentFullName}", studentInShortForm.get(i));
                    doc.write(out);
                }
                //Load the first document
                Document document = new Document(pathToTitleList);
                //Insert content of the second document into the first document
                document.insertTextFromFile(outputPath, FileFormat.Docx_2013);
                objUpdateWord.updateDocument(pathToTitleList, pathToTitleList, "Evaluation Warning: The document was created with Spire.Doc for JAVA.", "");
                //Save the resultant document
                document.saveToFile(pathToTitleList, FileFormat.Docx_2013);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
