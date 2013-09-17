/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorFala;

import javazoom.jl.player.advanced.*;

/**
 *
 * @author ASSUERO
 */
public class ControladorFala {

    public static void EmitirSom() {
        SoundJLayer soundToPlay = new SoundJLayer("test.mp3");
        soundToPlay.play();
    }

    public static void Ola() {
        SoundJLayer soundToPlay = new SoundJLayer("ola.mp3");
        soundToPlay.play();
    }

    public static void Despedir() {
        SoundJLayer soundToPlay = new SoundJLayer("atemais.mp3");
        soundToPlay.play();

    }

    public static void Sim() {
        SoundJLayer soundToPlay = new SoundJLayer("sim.mp3");
        soundToPlay.play();
    }

    public static void Nao() {
        SoundJLayer soundToPlay = new SoundJLayer("nao.mp3");
        soundToPlay.play();
    }

    public static void SentindoDor() {
        SoundJLayer soundToPlay = new SoundJLayer("comdor.mp3");
        soundToPlay.play();
    }
    
}

class SoundJLayer extends PlaybackListener implements Runnable {

    private String filePath;
    private AdvancedPlayer player;
    private Thread playerThread;

    public SoundJLayer(String filePath) {
        this.filePath = filePath;
    }

    public void play() {
        try {
            String urlAsString =
                    "file:///"
                    + new java.io.File(".").getCanonicalPath()
                    + "/"
                    + this.filePath;

            this.player = new AdvancedPlayer(
                    new java.net.URL(urlAsString).openStream(),
                    javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice());
            this.player.setPlayBackListener(this);
            this.playerThread = new Thread(this, "AudioPlayerThread");
            this.playerThread.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // PlaybackListener members
    public void playbackStarted(PlaybackEvent playbackEvent) {
        System.out.println("playbackStarted()");
    }

    public void playbackFinished(PlaybackEvent playbackEvent) {
        System.out.println("playbackEnded()");
    }

    // Runnable members
    public void run() {
        try {
            this.player.play();
        } catch (javazoom.jl.decoder.JavaLayerException ex) {
            ex.printStackTrace();
        }

    }
}