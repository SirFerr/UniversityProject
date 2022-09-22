//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

module com.example.universityproject {
    requires javafx.controls;
    requires org.slf4j;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires org.apache.poi.ooxml;
    requires org.apache.poi.ooxml.schemas;
    requires org.apache.poi.poi;
    requires spire.doc.free;
    requires org.controlsfx.controls;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;



    exports com.example.universityproject;

    opens com.example.universityproject to
            javafx.fxml;
    opens com.example.universityproject.backend to
            javafx.fxml;

}
