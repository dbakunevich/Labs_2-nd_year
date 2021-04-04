package personal.bakunevich.utils;

import javax.sound.sampled.*;
import java.io.IOException;

public class Sounds {
    private final String track;

    public Sounds(String track) {
        this.track = track;
    }

    public synchronized void sound(){

        new Thread(() -> {
            AudioInputStream audioStream = null;
            try {
                audioStream = AudioSystem.getAudioInputStream(
                        Sounds.class.getResourceAsStream(track));
            } catch (UnsupportedAudioFileException | IOException e) {
                e.printStackTrace();
            }
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);

                clip.setFramePosition(0);
                clip.start();
            } catch (LineUnavailableException | IOException e){
                e.printStackTrace();
            }
        }).start();
    }
}