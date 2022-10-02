package com.example.universityproject.backend;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReplacerAndMerger{
    String pathToTitleList = "src/main/resources/wordAndExcelTemplates/TitleLists.docx";

    public void fileReplacerAndMerger(ComboBox instituteName,
                                      TextField departmentName,
                                      TextField practiceName,
                                      DatePicker orderDate,
                                      TextField orderName,
                                      String sessionDate,
                                      TextField supervisorFN,
                                      int currentYear,
                                      ChoiceBox courseNum,
                                      TextField groupName,
                                      TextField practicePlaceAndTime,
                                      TextField position,
                                      DatePicker currentDate,
                                      TextField headOfDFN,
                                      TextField directionName,
                                      TextField profileName,
                                      String pathForExcel) throws IOException {
        List<String> students = ExcelParsing.pushToArrayList(pathForExcel);
        ArrayList<String> replaceableNames = new ArrayList<>(){{
            add("studentFullName");
            add("studentFN");
            add("instituteName");
            add("departmentName");
            add("practiceName");
            add("orderDate");
            add("orderName");
            add("sessionDate");
            add("supervisorFN");
            add("currentYear");
            add("courseNum");
            add("groupName");
            add("practicePlaceAndTime");
            add("position");
            add("currentDate");
            add("headOfDFN");
            //add("directionNum");
            add("directionName");
            add("profileName");
        }};
        String inputPath = "src/main/resources/wordAndExcelTemplates/mainWordFile.docx";
        String outputPath = "src/main/resources/wordAndExcelTemplates/fileForTesting.docx";
        DocumentBuilder.clearDoc(pathToTitleList);
        for (String studentName: students) {
            Template template = new Template(replaceableNames, studentName);
            for (String replaceableName : replaceableNames) {
                switch (replaceableName) {
                    case "instituteName" -> template.setField("instituteName",(String) instituteName.getValue());//objUpdateWord.updateDocument(inputPath, outputPath, "${instituteName}", (String) instituteName.getValue());
                    case "departmentName" -> template.setField("departmentName",departmentName.getText());//objUpdateWord.updateDocument(inputPath, outputPath, "${departmentName}", (String) departmentName.getValue());
                    case "practiceName" -> template.setField("practiceName",practiceName.getText());//objUpdateWord.updateDocument(inputPath, outputPath, "${practiceName}", (String) practiceName.getValue());
                    case "orderDate" -> template.setField("orderDate",String.valueOf(orderDate.getValue()));//objUpdateWord.updateDocument(inputPath, outputPath, "${orderDate}", String.valueOf(orderDate.getValue()));
                    case "orderName" -> template.setField("orderName", orderName.getText());//objUpdateWord.updateDocument(inputPath, outputPath, "${orderName}", orderName.getText());
                    case "sessionDate" -> template.setField("sessionDate",sessionDate);//objUpdateWord.updateDocument(inputPath, outputPath, "${sessionDate}", String.valueOf(sessionDate.getValue()));
                    case "supervisorFN" -> template.setField("supervisorFN",supervisorFN.getText());//objUpdateWord.updateDocument(inputPath, outputPath, "${supervisorFN}", supervisorFN.getText());
                    case "currentYear" -> template.setField("currentYear",String.valueOf(currentYear));//objUpdateWord.updateDocument(inputPath, outputPath, "${currentYear}", String.valueOf(currentYear));
                    case "courseNum" -> template.setField("courseNum",(String) courseNum.getValue());//objUpdateWord.updateDocument(inputPath, outputPath, "${courseNum}", (String) courseNum.getValue());
                    case "groupName" -> template.setField("groupName", groupName.getText());//objUpdateWord.updateDocument(inputPath, outputPath, "${groupName}", groupName.getText());
                    case "practicePlaceAndTime" -> template.setField("practicePlaceAndTime",practicePlaceAndTime.getText());//objUpdateWord.updateDocument(inputPath, outputPath, "${practicePlaceAndTime}", practicePlaceAndTime.getText());
                    case "position" -> template.setField("position",position.getText());//objUpdateWord.updateDocument(inputPath, outputPath, "${position}", position.getText());
                    case "currentDate" -> template.setField("currentDate",String.valueOf(currentDate.getValue()));//objUpdateWord.updateDocument(inputPath, outputPath, "${currentDate}", String.valueOf(currentDate.getValue()));
                    case "headOfDFN" -> template.setField("headOfDFN",headOfDFN.getText()); //objUpdateWord.updateDocument(inputPath, outputPath, "${headOfDFN}", headOfDFN.getText());
                    //case "${directionNum}" -> template.setField("directionNum",(String) directionNum.getValue());//objUpdateWord.updateDocument(inputPath, outputPath, "${directionNum}", (String) directionNum.getValue());
                    case "directionName" -> template.setField("directionName", directionName.getText());//objUpdateWord.updateDocument(inputPath, outputPath, "${directionName}", (String) directionName.getValue());
                    case "profileName" -> template.setField("profileName",profileName.getText());//objUpdateWord.updateDocument(inputPath, outputPath, "${profileName}", profileName.getText());
                    default -> {
                    }
                }
            }
            template.createMap();

            DocumentBuilder documentBuilder = new DocumentBuilder(template, outputPath, inputPath);
            documentBuilder.buildDoc();
            documentBuilder.saveDoc();
            Document document = new Document(pathToTitleList);
            document.insertTextFromFile(outputPath, FileFormat.Docx_2013);
            document.saveToFile(pathToTitleList, FileFormat.Docx_2013);
            DocumentBuilder.clearDoc(outputPath);
        }
        DocumentBuilder.deleteWM(pathToTitleList,"Evaluation Warning: The document was created with Spire.Doc for JAVA.");
    }
}
