package team.elrant.audioplayer.song;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SongLoader {
    public ArrayList<Song> songs = new ArrayList<>();
    private final String folderPath;
    public SongLoader(String folderPath) {
        this.folderPath = folderPath;
    }
    public void loadSongs() throws IOException, LineUnavailableException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(folderPath))) {
            for (Path path : stream) {
                if (!Files.isDirectory(path)) {
                    if (path.getFileName().toString().endsWith(".wav")) {
                        System.out.println("Added file: " + path.getFileName()); // Debug print for added file
                        try {
                            Song newSong = new Song(path.toFile().getAbsolutePath());
                            System.out.println("Loaded song: " + path.getFileName());
                            songs.add(newSong);
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    else {
                        System.out.println("Skipped file: " + path.getFileName()); // Debug print for skipped file
                    }
                }
            }
        }
        for (Song song : songs) {
            System.out.println("Song #" + songs.indexOf(song) + ": " + song.filePath);
            System.out.println(song.status);
        }
    }
}
