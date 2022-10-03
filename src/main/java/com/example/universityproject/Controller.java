package com.example.universityproject;


import com.example.universityproject.backend.FileReplacerAndMerger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
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
    private ComboBox<String> instituteName;

    @FXML
    private TextField practiceName;

    @FXML
    private TextField departmentName;

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
    private ChoiceBox<String> courseNum;

    @FXML
    private TextField directionName;
    @FXML
    private TextField profileName;

    @FXML
    private Button btnFileChooser;

    @FXML
    private Button btnSubmit;

    @FXML
    private ProgressBar submitPB;

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

        String[] courseNumArr = {"1", "2", "3", "4", "5", "6"};
        instituteName.getItems().addAll(instituteNameArr);;
         convertDate();
        courseNum.getItems().addAll(courseNumArr);
        btnFileChooser.setOnAction(e -> {//в file храниться полный путь к файлу
            FileChooser fileChooser = new FileChooser();
            pathForExcel = fileChooser.showOpenDialog(null).getPath();
            btnFileChooser.setText("Файл выбран");
        });
        btnSubmit.setOnAction(e -> {
            submitPB.setProgress(0.1);
            int currentYear = 2022;
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
            submitPB.setProgress(1);


        });

    }
}
