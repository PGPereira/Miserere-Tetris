/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;


import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author aluno
 */
public class Musiquinha {

    private Clip clip;

    public Musiquinha(String s) {
        try {
            AudioInputStream a = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(s));
            AudioFormat baseFormat = a.getFormat();
            AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
            AudioInputStream ai = AudioSystem.getAudioInputStream(decodeFormat, a);
            clip = AudioSystem.getClip();
            clip.open(ai);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip == null) {
            return;    
        }
        stop();
        clip.setFramePosition(0);
        clip.setMicrosecondPosition(1000);
        clip.loop(100000000);
        clip.start();
    }
    
    public void stop(){
        if(clip.isRunning()){
            clip.stop();
        }
    }
    
    public void close(){
        stop();
        clip.close();
    }
}