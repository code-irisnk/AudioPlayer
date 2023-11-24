package team.elrant.audioplayer.song;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Song {
    // to store current position
    Long currentFrame;
    Clip clip;

    // current status of clip
    String status = "paused";

    AudioInputStream audioInputStream;
    public String filePath;

    public Song(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.filePath = filePath;
        // create AudioInputStream object
        this.audioInputStream =
                AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        // create clip reference
        this.clip = AudioSystem.getClip();
    }


    // Method to play the audio
    public void play() throws LineUnavailableException, IOException {
        clip.open(audioInputStream);
        clip.start();
        status = "playing";
    }

    // Method to pause the audio
    public void pause()
    {
        if (status.equals("paused"))
        {
            System.out.println("audio is already paused");
            return;
        }
        this.currentFrame =
                this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }

    // Method to resume the audio
    public void resumeAudio() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        if (status.equals("playing"))
        {
            System.out.println("Audio is already "+
                    "being played");
            return;
        }
        resetAudioStream();

        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }

    // Method to restart the audio
    public void restart() throws IOException, LineUnavailableException,
            UnsupportedAudioFileException
    {
        this.pause();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }

    // Method to stop the audio
    public void stop()
    {
        currentFrame = 0L;
        clip.stop();
        clip.close();
        status = "stopped";
    }

    // Method to jump to a specific part
    public void jump(long c) throws UnsupportedAudioFileException, IOException,
            LineUnavailableException
    {
        if (c > 0 && c < clip.getMicrosecondLength())
        {
            clip.stop();
            clip.close();
            resetAudioStream();
            clip.open(audioInputStream);
            currentFrame = c;
            clip.setMicrosecondPosition(c);
            this.play();
            status = "playing";
        }
    }

    // Method to reset audio stream
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException
    {
        audioInputStream = AudioSystem.getAudioInputStream(
                new File(filePath).getAbsoluteFile());
        status = "stopped";
    }

}
