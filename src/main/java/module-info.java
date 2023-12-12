module com.example.semesterproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.datatransfer;


    opens com.example.semesterproject to javafx.fxml;
    exports com.example.semesterproject;
}