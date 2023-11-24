package team.elrant.audioplayer.views;

import team.elrant.audioplayer.song.SongLoader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class ButtonView extends JFrame {
    public ButtonView(SongLoader songLoader) {
        setTitle("Applicazione Grafica");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panel = getPanel(songLoader);
        add(panel);
        setVisible(true);
    }

    private static JPanel getPanel(SongLoader songLoader) {
        JPanel panel = new JPanel();
        for (int i = 0; i < songLoader.songs.size(); i++) {
            JButton button = new JButton(songLoader.songs.get(i).filePath);
            int finalI = i;
            button.addActionListener(e -> {
                try {
                    songLoader.songs.get(finalI).play();
                } catch (Exception exception) {
                    throw new RuntimeException(exception);
                }
            });
            panel.add(button);
        }
        return panel;
    }
}