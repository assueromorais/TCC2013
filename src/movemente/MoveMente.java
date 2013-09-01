/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package movemente;

import controladorDispositivos.ControladorDispositivos;
import iGeradorComandos.enmTipoComando;

import iGeradorComandos.iGeradorComandos;
import iControladorDispositivos.iControladorDispositivos;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import obter_mindwave.ObterMindwave;

/**
 *
 * @author ASSUERO
 */
public class MoveMente {

    public static iGeradorComandos Comandos;
    public static FrmFalhaMindWave MindWaveDesconectado = null;
    private static FrmSplashScreen SplashScreen = null;
    public static iControladorDispositivos Controlador;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        SplashScreen = new FrmSplashScreen();
        SplashScreen.setVisible(true);
        Comandos = new ObterMindwave();
        try {
            Controlador = new ControladorDispositivos();
        } catch (IOException ex) {
            Logger.getLogger(MoveMente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Falha.");
        }
        MindWaveDesconectado = new FrmFalhaMindWave();
        MindWaveDesconectado.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        // Enquanto o usuário clicar em Tentar novamente e estiver ocorrendo falha 
                        // de conexão com o MindWave, o sistema irá tentar a conexão novamente
                        if (MindWaveDesconectado.TentarNovamente == true) {
                            SplashScreen.setVisible(true);
                            IniciarConexaoHeadset();
                        } else {
                            SplashScreen.setVisible(false);
                            SplashScreen = null;
                        }
                    }
                });


            }
        });
        IniciarConexaoHeadset();
    }

    public static void IniciarConexaoHeadset() {
        if (Comandos != null) {
            if (Comandos.Iniciar()) {
                //Conectou ao dispositivo corretamente;
                SplashScreen.setVisible(false);
                SplashScreen = null;
                new FrmInicio().setVisible(true);
            } else {
                MindWaveDesconectado.TentarNovamente = true;
                MindWaveDesconectado.ConfigurarMensagem(enmTipoComando.MindWaveNaoEncontrado, "");
                // Exibe a mensagem de falha na conexão com o Mind wave.
                MindWaveDesconectado.setVisible(true);
            }
        }
    }
}