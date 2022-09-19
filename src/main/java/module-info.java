module com.example.universityproject {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                requires validatorfx;
                requires org.kordamp.bootstrapfx.core;
            
    opens com.example.universityproject to javafx.fxml;
    exports com.example.universityproject;
}