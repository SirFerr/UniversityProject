package com.example.universityproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class subMenuController extends Controller implements Initializable {

    @FXML
    private ProgressBar submitPB;

    @FXML
    private Label submitText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        submitPB.setProgress(0.1);
        submitText.setText("Создание файлов началось");
        submitPB.setProgress(1);
    }
}