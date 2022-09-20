package com.example.universityproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class Controller {
    ObservableList<String> items;
    @FXML
    private ComboBox<?> instituteName;


    @FXML
    private ComboBox<?> practiceName;

    @FXML
    private ComboBox<?> departmentName;

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
    private ChoiceBox<?> courseNum;

    @FXML
    private ComboBox<?> directionName;

    @FXML
    private TextField profileName;

    @FXML
    private Button btnFileChooser;

    @FXML
    private Button btnSubmit;

}
