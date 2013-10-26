/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package movemente;

import controladorDispositivos.ControladorDispositivos;

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

    /**
     * Objeto responsável por gerar os na tela.
     */
    public static iGeradorComandos Comandos;
    
    /**
     * Formulário responsável por exibir uma mensagem de falha na conexão com o MindWave.
     */
    public static FrmFalhaMindWave MindWaveDesconectado = null;
    
    /**
     * Formulário responsável por exibir uma mensagem de falha na conexão com o Arduino.
     */
    public static FrmFalhaConexaoArduino ArduinoDesconectado = null;
    
    /**
     * Formulário exibido no início da execução do aplicativo enquanto está tentando se conectar aos controladores.
     */
    private static FrmSplashScreen SplashScreen = null;
    
    /**
     * Objeto responsável por controlar os dispostivos externos.
     */
    public static iControladorDispositivos Controlador;
    
    /**
     * Borda utilizada quando um botão é focado.
     */
    public static Border bordaBotaoFocado = null;
    
    /**
     * Borda utilizada quando um botão perde o foco.
     */
    public static Border bordaBotaoPadrao = null;
    
    /**
     * Formulário principal o qual irá "Conter" os demais formulários do aplicativo, exceto as telas de falha.
     */
    public static MdiContainer Container = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        ConfigurarLookUIManager();
        ConfigurarFormularios();
        IniciarConexaoHeadset();
    }

    /**
     * Tenta se conectar ao headset.
     */
    public static void IniciarConexaoHeadset() {
        if (Comandos != null) {
            //  if (Comandos.Conectar()) {
            if (true) {
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
                // MindWaveDesconectado.ConfigurarMensagem(enmTipoComando.MindWaveNaoEncontrado, "");
                // Exibe a mensagem de falha na conexão com o Mind wave.
                MindWaveDesconectado.setVisible(true);
            }
        }
    }

    /**
     * Tenta se conectar ao controlador arduino.
     */
    public static void IniciarConexaoArduino() {
        if (Controlador != null) {
            if (Controlador.Conectar()) {
           // if (true) {
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
        Color botoes = new java.awt.Color(188, 188, 188);
        javax.swing.UIManager.put("nimbusBase", botoes);
        javax.swing.UIManager.put("nimbusBlueGrey", botoes);
        javax.swing.UIManager.put("control", java.awt.Color.white);//new java.awt.Color(240,245,255));//new java.awt.Color(255, 247, 235));
    }

    /**
     * Configura os formulários principais do aplicativo.
     */
    private static void ConfigurarFormularios() throws IOException {
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