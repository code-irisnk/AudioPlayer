package team.elrant.audioplayer.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import team.elrant.audioplayer.song.SongLoader;
import team.elrant.audioplayer.views.ButtonView;

public class HelloController {
    @FXML
    private TextField pathField;

    @FXML
    protected void onHelloButtonClick() {
        switchView();
    }
    @FXML
    protected void onPathFieldEnter() {
        switchView();
    }

    protected void switchView() {
        String textValue = pathField.getText();
        System.out.println("Text entered: " + textValue);
        try {
            SongLoader songLoader = new SongLoader(textValue);
            songLoader.loadSongs();
            // open button view here
            new ButtonView(songLoader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}