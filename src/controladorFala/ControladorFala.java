/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorFala;

import javazoom.jl.player.advanced.*;

/**
 * Classe que disponibiliza as opções de fala.
 * @author ASSUERO
 */
public class ControladorFala {

    /**
     * Emite um som de teste.
     */
    public static void EmitirSom() {
        SoundJLayer soundToPlay = new SoundJLayer("test.mp3");
        soundToPlay.play();
    }

    /**
     * Emite um som que diz "Quero ir ao banheiro".
     */
    public static void Banheiro() {
        SoundJLayer soundToPlay = new SoundJLayer("banheiro.mp3");
        soundToPlay.play();
    }

    /**
     * Emite um som que diz "Estou com fome".
     */
    public static void Fome() {
        SoundJLayer soundToPlay = new SoundJLayer("fome.mp3");
        soundToPlay.play();

    }

    /**
     * Emite um som que diz "Estou com sede".
     */
    public static void Sede() {
        SoundJLayer soundToPlay = new SoundJLayer("sede.mp3");
        soundToPlay.play();
    }
    
    /**
     * Emite um som que diz "Sim" como resposta à alguma pergunta.
     */
    public static void Sim() {
        SoundJLayer soundToPlay = new SoundJLayer("sim.mp3");
        soundToPlay.play();
    }

    /**
     * Emite um som que diz "Não" como resposta à alguma pergunta.
     */
    public static void Nao() {
        SoundJLayer soundToPlay = new SoundJLayer("nao.mp3");
        soundToPlay.play();
    }

    /**
     * Emite um som que diz "Estou com dor".
     */
    public static void SentindoDor() {
        SoundJLayer soundToPlay = new SoundJLayer("comdor.mp3");
        soundToPlay.play();
    }
}

/**
 * Classe responsável por executar os sons do controlador de fala.
 * @author ASSUERO
 */
class SoundJLayer extends PlaybackListener implements Runnable {

    private String filePath;
    private AdvancedPlayer player;
    private Thread playerThread;

    public SoundJLayer(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Executa o som definido na inicialização do objeto em um novo processo (thread).
     */
    public void play() {
        try {
            String urlAsString = "file:///" + new java.io.File(".").getCanonicalPath() + "/sons/" + this.filePath;
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

    // Runnable members
    @Override
    public void run() {
        try {
            this.player.play();
        } catch (javazoom.jl.decoder.JavaLayerException ex) {
            ex.printStackTrace();
        }

    }
}