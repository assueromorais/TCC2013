/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package movemente;

import com.neurosky.thinkgear.ThinkGear;
import iGeradorComandos.enmTipoComando;

import iGeradorComandos.iGeradorComandos;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import obter_mindwave.Obter_mindwave;

/**
 *
 * @author ASSUERO
 */
public class MoveMente {

    public static iGeradorComandos Comandos;
    public static FrmFalhaMindWave MindWaveDesconectado = null;
    private static FrmSplashScreen SplashScreen = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        SplashScreen = new FrmSplashScreen();
        SplashScreen.setVisible(true);
        Comandos = new Obter_mindwave();
        IniciarConexaoHeadset();
        SplashScreen.setVisible(false);
        SplashScreen = null;
    }

    public static void IniciarConexaoHeadset() {
        if (Comandos != null) {
            if (Comandos.Iniciar()) {
                //if (true) {
                //Conectou ao dispositivo corretamente;
                new FrmInicio().setVisible(true);
            } else {
                MindWaveDesconectado = new FrmFalhaMindWave();
                MindWaveDesconectado.TentarNovamente = true;
                MindWaveDesconectado.ConfigurarMensagem( enmTipoComando.MindWaveNaoEncontrado, "");
                MindWaveDesconectado.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent we) {
                        // Enquanto o usuário clicar em Tentar novamente e estiver ocorrendo falha 
                        // de conexão com o MindWave, o sistema irá tentar a conexão novamente
                        if (MoveMente.MindWaveDesconectado.TentarNovamente) {
                            MoveMente.IniciarConexaoHeadset();
                        }
                    }
                });
                //Exibe a mensagem de falha na conexão com o Mind wave.
                MindWaveDesconectado.setVisible(true);
                //new FrmInicio().setVisible(false);
            }
        }
    }
}