package team.elrant.audioplayer;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import team.elrant.audioplayer.song.SongLoader;

public class HelloController {
    @FXML
    private TextField pathField;

    @FXML
    protected void onHelloButtonClick() {
        String textValue = pathField.getText();
        System.out.println("Text entered: " + textValue);
        try {
            SongLoader songLoader = new SongLoader(textValue);
            songLoader.loadSongs();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}