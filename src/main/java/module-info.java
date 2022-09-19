module com.example.universityproject {
    requires javafx.controls;
    requires javafx.fxml;
    //Find how to fix lombok if it's necessary
    //requires lombok;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires org.apache.poi.ooxml;
    requires org.apache.poi.ooxml.schemas;
    requires org.apache.poi.poi;


            
        requires org.controlsfx.controls;
                requires validatorfx;
                requires org.kordamp.bootstrapfx.core;

            
    opens com.example.universityproject to javafx.fxml;
    exports com.example.universityproject;
}