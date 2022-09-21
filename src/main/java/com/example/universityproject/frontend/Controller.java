package com.example.universityproject.frontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
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
    private ComboBox<String> directionName;

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
        String[] instituteNameArr = {"hui", "pizda", "chlen"};
        String[] practiceNameArr = {"hui", "pizda", "chlen"};
        String[] departmentNameArr = {"hui", "pizda", "chlen"};
        Integer[] courseNumArr = {1, 2, 3, 4, 5, 6};
        instituteName.getItems().addAll(instituteNameArr);
        practiceName.getItems().addAll(practiceNameArr);
        departmentName.getItems().addAll(departmentNameArr);
        courseNum.getItems().addAll(courseNumArr);

        btnSubmit.setOnAction(e -> {
            submitText.setText("Запущен процесс создания файлов");
            instituteName.getValue();//получение текущего значения из ComboBox
            position.getText();//получение текущего значения из TextField
            currentDate.getValue();//получение текущего значения из DatePicker
            submitText.setText("Файлы созданы");
        });

    }
}
