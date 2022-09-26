package com.example.universityproject;


import com.example.universityproject.backend.FileReplacerAndMerger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable,FileReplacerAndMerger {
    @FXML
    private ComboBox<String> instituteName;

    @FXML
    private ComboBox<String> practiceName;

    @FXML
    private ComboBox<String> departmentName;

    @FXML
    private TextField position;

    @FXML
    private TextField orderName;

    @FXML
    private TextField supervisorFN;

    @FXML
    private TextField headOfDFN;

    @FXML
    private DatePicker orderDate;

    @FXML
    private DatePicker currentDate;

    @FXML
    private TextField practicePlaceAndTime;

    @FXML
    private TextField groupName;

    @FXML
    private ChoiceBox<Integer> courseNum;

    @FXML
    private TextField directionName;
    @FXML
    private TextField profileName;

    @FXML
    private Button btnFileChooser;

    @FXML
    private Button btnSubmit;

    @FXML
    private Label submitText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] instituteNameArr = {"Институт информационных технологий",
                "Институт искусственного технологий",
                "Институт кибербезопасности и цифровых технологий",
                "Институт перспективных технологий и индустриального программирования",
                "Институт технологий управления", "Институт тонких химических технологий им. М.В. Ломоносова"};

        String[] practiceNameArr = {"hui", "pizda", "chlen"};
        String[] departmentNameArr = {"hui", "pizda", "chlen"};
        Integer[] courseNumArr = {1, 2, 3, 4, 5, 6};
        instituteName.getItems().addAll(instituteNameArr);
        practiceName.getItems().addAll(practiceNameArr);
        departmentName.getItems().addAll(departmentNameArr);
        courseNum.getItems().addAll(courseNumArr);
        btnFileChooser.setOnAction(e -> {//в file храниться полный путь к файлу
            FileChooser fileChooser = new FileChooser();
            String file = fileChooser.showOpenDialog(null).getPath();
            btnFileChooser.setText("Файл выбран");
        });
        btnSubmit.setOnAction(e -> {
            submitText.setText("Запущен процесс создания файлов");

            fileReplacerAndMerger(instituteName, departmentName, practiceName,
                    orderDate, orderName, currentDate, supervisorFN,
                    2022, courseNum, groupName, practicePlaceAndTime,
                    position, currentDate, headOfDFN, directionName,profileName);
            submitText.setText("Файлы созданы");
        });

    }

    private void fileReplacerAndMerger(ComboBox<String> instituteName, ComboBox<String> departmentName, ComboBox<String> practiceName, DatePicker orderDate, TextField orderName, DatePicker currentDate, TextField supervisorFN, int i, ChoiceBox<Integer> courseNum, TextField groupName, TextField practicePlaceAndTime, TextField position, DatePicker currentDate1, TextField headOfDFN, TextField directionName, TextField profileName) {
    }

}
