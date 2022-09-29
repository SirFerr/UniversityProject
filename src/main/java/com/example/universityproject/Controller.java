package com.example.universityproject;


import com.example.universityproject.backend.FileReplacerAndMerger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Controller  implements Initializable{

    public String pathForExcel;

    FileReplacerAndMerger objectForMerging = new FileReplacerAndMerger();

    @FXML
    protected ComboBox<String> instituteName;

    @FXML
    protected ComboBox<String> practiceName;

    @FXML
    protected ComboBox<String> departmentName;

    @FXML
    protected TextField position;

    @FXML
    protected TextField orderName;

    @FXML
    protected TextField supervisorFN;

    @FXML
    protected TextField headOfDFN;

    @FXML
    protected DatePicker orderDate;

    @FXML
    protected DatePicker currentDate;

    @FXML
    protected TextField practicePlaceAndTime;

    @FXML
    protected TextField groupName;

    @FXML
    protected ChoiceBox<String> courseNum;

    @FXML
    protected TextField directionName;
    @FXML
    protected TextField profileName;
    @FXML
    protected Label errorLabel;

    @FXML
    protected Button btnFileChooser;

    @FXML
    void submit(ActionEvent event) throws IOException {
        if (instituteName.getValue().isEmpty()
                ||profileName.getText().isEmpty()
                ||practiceName.getValue().isEmpty()
                ||departmentName.getValue().isEmpty()
                ||pathForExcel.isEmpty()
                ||position.getText().isEmpty()
                ||orderName.getText().isEmpty()
                ||supervisorFN.getText().isEmpty()
                ||headOfDFN.getText().isEmpty()
                ||practicePlaceAndTime.getText().isEmpty()
                ||groupName.getText().isEmpty()
                ||courseNum.getValue().isEmpty()
                ||directionName.getText().isEmpty()
                ||profileName.getText().isEmpty()
                ||orderDate.getValue()==null
                ||currentDate.getValue()==null
        ){
            errorLabel.setText("Введите все данные");
        }else {
            errorLabel.setText("");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("subMenu.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        int currentYear = Integer.parseInt(currentDate.getValue().toString().split("/")[3]);
        String sessionDate = "2022";
        try {
            objectForMerging.fileReplacerAndMerger(
                    instituteName,
                    departmentName,
                    practiceName,
                    orderDate,
                    orderName,
                    sessionDate,
                    supervisorFN,
                    currentYear,
                    courseNum,
                    groupName,
                    practicePlaceAndTime,
                    position,
                    currentDate,
                    headOfDFN,
                    directionName,
                    profileName,
                    pathForExcel);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }}

    public void convertDate(){
        orderDate.setValue(LocalDate.now());
        orderDate.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate localDate) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String s) {
                return null;
            }
        });
        currentDate.setValue(LocalDate.now());
        currentDate.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate localDate) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String s) {
                return null;
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] instituteNameArr = {"Институт информационных технологий",
                "Институт искусственного технологий",
                "Институт кибербезопасности и цифровых технологий",
                "Институт перспективных технологий и индустриального программирования",
                "Институт технологий управления", "Институт тонких химических технологий им. М.В. Ломоносова"};

        String[] practiceNameArr = {"hui", "pizda", "chlen"};
        String[] departmentNameArr = {"hui", "pizda", "chlen"};
        String[] courseNumArr = {"1", "2", "3", "4", "5", "6"};
        instituteName.getItems().addAll(instituteNameArr);
        practiceName.getItems().addAll(practiceNameArr);
        departmentName.getItems().addAll(departmentNameArr);
        convertDate();
        courseNum.getItems().addAll(courseNumArr);
        btnFileChooser.setOnAction(e -> {//в file храниться полный путь к файлу
            FileChooser fileChooser = new FileChooser();
            pathForExcel = fileChooser.showOpenDialog(null).getPath();
            btnFileChooser.setText("Файл выбран");
        });

    }
}
