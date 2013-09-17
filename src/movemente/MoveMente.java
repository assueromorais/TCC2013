/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package movemente;

import controladorDispositivos.ControladorDispositivos;
import iGeradorComandos.enmTipoComando;

import iGeradorComandos.iGeradorComandos;
import iControladorDispositivos.iControladorDispositivos;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.border.Border;
import obter_mindwave.ObterMindwave;

/**
 *
 * @author ASSUERO
 */
public class MoveMente {

    public static iGeradorComandos Comandos;
    public static FrmFalhaMindWave MindWaveDesconectado = null;
    public static FrmFalhaConexaoArduino ArduinoDesconectado = null;
    private static FrmSplashScreen SplashScreen = null;
    public static iControladorDispositivos Controlador;
    public static Border bordaBotaoFocado = null;
    public static Border bordaBotaoPadrao = null;
    public static MdiContainer Container = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        ConfigurarLookUIManager();
        ConfigurarFormularios();
        IniciarConexaoHeadset();
    }

    public static void IniciarConexaoHeadset() {
        if (Comandos != null) {
            if (Comandos.Conectar()) {
//            if (true) {
                //Conectou ao dispositivo corretamente;
                // Tenta a conexão com o controlador arduino
                IniciarConexaoArduino();
                //Conectou ao dispositivo corretamente;
                /**
                 * SplashScreen.setVisible(false); SplashScreen = null;
                 * Container.AdicionarFrame(new FrmInicio());
                 * Container.setVisible(true);*
                 */
            } else {
                MindWaveDesconectado.ConfigurarMensagem(enmTipoComando.MindWaveNaoEncontrado, "");
                // Exibe a mensagem de falha na conexão com o Mind wave.
                MindWaveDesconectado.setVisible(true);
            }
        }
    }

    public static void IniciarConexaoArduino() {
        if (Controlador != null) {
            if (Controlador.Conectar()) {
                //if (true) {
                //Conectou ao dispositivo corretamente;
                SplashScreen.setVisible(false);
                SplashScreen = null;
                Container.AdicionarFrame(new FrmInicio());
                Container.setVisible(true);
            } else {
                ArduinoDesconectado.ConfigurarMensagem("");
                // Exibe a mensagem de falha na conexão com o Mind wave.
                ArduinoDesconectado.setVisible(true);
            }
        }
    }

    /**
     * Configura a interface do usuário.
     */
    private static void ConfigurarLookUIManager() {
        Color orange = new java.awt.Color(255, 225, 195);
        javax.swing.UIManager.put("nimbusBase", orange);
        javax.swing.UIManager.put("nimbusBlueGrey", orange);
        javax.swing.UIManager.put("control", new java.awt.Color(255, 247, 235));
    }

    private static void ConfigurarFormularios() {
        SplashScreen = new FrmSplashScreen();
        // As bordas abaixo são utilizadas como padrão para alterar o layout dos botões do formulário,
        // permitindo que, ao receber o foco o botão receba uma borda, e ao perder o foco ele volte ao normla.
        bordaBotaoPadrao = SplashScreen.btnPadrao.getBorder();
        bordaBotaoFocado = SplashScreen.btnBorda.getBorder();
        SplashScreen.setVisible(true);
        Comandos = new ObterMindwave();
        Controlador = new ControladorDispositivos();
        MindWaveDesconectado = new FrmFalhaMindWave();
        ArduinoDesconectado = new FrmFalhaConexaoArduino();
        ArduinoDesconectado.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        // Enquanto o usuário clicar em Tentar novamente e estiver ocorrendo falha 
                        // de conexão com o Arduino, o sistema irá tentar a conexão novamente
                        if (ArduinoDesconectado.TentarNovamente == true) {
                            ArduinoDesconectado.TentarNovamente = false;
                            SplashScreen.setVisible(true);
                            IniciarConexaoArduino();
                        } else {
                            SplashScreen.setVisible(false);
                            SplashScreen = null;
                            System.exit(0);
                        }
                    }
                });
            }
        });
        MindWaveDesconectado.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        // Enquanto o usuário clicar em Tentar novamente e estiver ocorrendo falha 
                        // de conexão com o Headset, o sistema irá tentar a conexão novamente
                        if (MindWaveDesconectado.TentarNovamente == true) {
                            MindWaveDesconectado.TentarNovamente = false;
                            SplashScreen.setVisible(true);
                            IniciarConexaoHeadset();
                        } else {
                            SplashScreen.setVisible(false);
                            SplashScreen = null;
                            System.exit(0);
                        }
                    }
                });
            }
        });
        Container = new MdiContainer();
    }
}