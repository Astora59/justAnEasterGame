package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {

        soundURL[0] = getClass().getResource("/assets/music/theme.wav");
        soundURL[1] = getClass().getResource("/assets/music/andsuch.wav");
        soundURL[2] = getClass().getResource("/assets/music/item_pickup.wav");
        soundURL[3] = getClass().getResource("/assets/music/Distorted.wav");
    }


    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch(Exception e) {

        }
    }




    public void setVolume(float volume) {
        // volume : 0.0f = muet, 1.0f = volume max
        FloatControl fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
        fc.setValue(dB);
    }


    public void play() {
        clip.start();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        if(clip != null) {
            clip.stop();
            clip.close();
        }

    }

}
