module team.elrant.audioplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.apache.tika.core;

    opens team.elrant.audioplayer to javafx.fxml;
    exports team.elrant.audioplayer;
}