module team.elrant.audioplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.apache.tika.core;

    exports team.elrant.audioplayer.controllers;
    opens team.elrant.audioplayer.controllers to javafx.fxml;
    exports team.elrant.audioplayer.views;
    opens team.elrant.audioplayer.views to javafx.fxml;
    exports team.elrant.audioplayer.song;
}