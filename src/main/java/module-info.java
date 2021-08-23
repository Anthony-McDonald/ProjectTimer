module com.example.projecttimer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;


    opens ProjectTimer to javafx.fxml;
    exports ProjectTimer;
}