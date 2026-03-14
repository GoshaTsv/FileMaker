module org.example.txtfilemaker {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.txtfilemaker to javafx.fxml;
    exports org.example.txtfilemaker;
}