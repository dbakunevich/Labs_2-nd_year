package personal.bakunevich.utils;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Objects;

public class Sounds {
    private final String track;
    private FloatControl volume;
    private double wt;
    private Clip clip;

    public Sounds(String track, double wt) {
        this.track = track;
        this.wt = wt;
        volume = null;
    }

    public void sound(){
        AudioInputStream audioStream = null;
        try {
            audioStream = AudioSystem.getAudioInputStream(
                    Objects.requireNonNull(Sounds.class.getResourceAsStream(track)));
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        try {
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            clip.setFramePosition(0);
            clip.start();
        } catch (LineUnavailableException | IOException e){
            e.printStackTrace();
        }
    }

    public void stop(){
        clip.stop();
    }

    public void setVolume() {
        if (wt < 0) wt = 0;
        if (wt > 1) wt = 1;
        float min = volume.getMinimum();
        float max = volume.getMaximum();
        volume.setValue((max - min) * (float) wt + min);
    }

}