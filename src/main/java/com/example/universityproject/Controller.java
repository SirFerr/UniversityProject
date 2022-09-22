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
        btnFileChooser.setOnAction(e ->{//в file храниться полный путь к файлу
            FileChooser fileChooser = new FileChooser();
            String file = fileChooser.showOpenDialog(null).getPath();
            btnFileChooser.setText("Файл выбран");
        });
        btnSubmit.setOnAction(e -> {
            submitText.setText("Запущен процесс создания файлов");
            try {
                fileReplacerAndMerger(instituteName,departmentName,practiceName,
                        orderDate,orderName, currentDate,supervisorFN,
                        2022,courseNum,groupName,practicePlaceAndTime,
                        position,currentDate,headOfDFN,directionName,
                        directionName,profileName);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            instituteName.getValue();//получение текущего значения из ComboBox
            position.getText();//получение текущего значения из TextField
            currentDate.getValue();//получение текущего значения из DatePicker
            submitText.setText("Файлы созданы");
        });

    }

    public ComboBox<String> getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(ComboBox<String> instituteName) {
        this.instituteName = instituteName;
    }

    public ComboBox<String> getPracticeName() {
        return practiceName;
    }

    public void setPracticeName(ComboBox<String> practiceName) {
        this.practiceName = practiceName;
    }

    public ComboBox<String> getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(ComboBox<String> departmentName) {
        this.departmentName = departmentName;
    }

    public TextField getPosition() {
        return position;
    }

    public void setPosition(TextField position) {
        this.position = position;
    }

    public TextField getOrderName() {
        return orderName;
    }

    public void setOrderName(TextField orderName) {
        this.orderName = orderName;
    }

    public TextField getSupervisorFN() {
        return supervisorFN;
    }

    public void setSupervisorFN(TextField supervisorFN) {
        this.supervisorFN = supervisorFN;
    }

    public TextField getHeadOfDFN() {
        return headOfDFN;
    }

    public void setHeadOfDFN(TextField headOfDFN) {
        this.headOfDFN = headOfDFN;
    }

    public DatePicker getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(DatePicker orderDate) {
        this.orderDate = orderDate;
    }

    public DatePicker getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(DatePicker currentDate) {
        this.currentDate = currentDate;
    }

    public TextField getPracticePlaceAndTime() {
        return practicePlaceAndTime;
    }

    public void setPracticePlaceAndTime(TextField practicePlaceAndTime) {
        this.practicePlaceAndTime = practicePlaceAndTime;
    }

    public TextField getGroupName() {
        return groupName;
    }

    public void setGroupName(TextField groupName) {
        this.groupName = groupName;
    }

    public ChoiceBox<Integer> getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(ChoiceBox<Integer> courseNum) {
        this.courseNum = courseNum;
    }

    public ComboBox<String> getDirectionName() {
        return directionName;
    }

    public void setDirectionName(ComboBox<String> directionName) {
        this.directionName = directionName;
    }

    public TextField getProfileName() {
        return profileName;
    }

    public void setProfileName(TextField profileName) {
        this.profileName = profileName;
    }

    public Button getBtnFileChooser() {
        return btnFileChooser;
    }

    public void setBtnFileChooser(Button btnFileChooser) {
        this.btnFileChooser = btnFileChooser;
    }

    public Button getBtnSubmit() {
        return btnSubmit;
    }

    public void setBtnSubmit(Button btnSubmit) {
        this.btnSubmit = btnSubmit;
    }

    public Label getSubmitText() {
        return submitText;
    }

    public void setSubmitText(Label submitText) {
        this.submitText = submitText;
    }
}
