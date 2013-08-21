/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TCC_prototipo;

import iGeradorComandos.iGeradorComandos;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import obter_mindwave.Obter_mindwave;

/**
 * Classe principal do projeto.
 *
 * @author ASSUERO
 */
public class TCC {

    public static iGeradorComandos Comandos;
    static FrmMindWaveDesconectado MindWaveDesconectado = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        Comandos = new Obter_mindwave();
        IniciarConexaoHeadset();
    }

    public static void IniciarConexaoHeadset() {
        if (Comandos != null) {
            //if (Comandos.Iniciar()) {
            if (true) {
                //Conectou ao dispositivo corretamente;
                new FrmInicio().setVisible(true);
            } else {
                MindWaveDesconectado = new FrmMindWaveDesconectado();
                MindWaveDesconectado.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent we) {
                        // Enquanto o usuário clicar em Tentar novamente e estiver ocorrendo falha 
                        // de conexão com o MindWave, o sistema irá tentar a conexão novamente
                        if (TCC.MindWaveDesconectado.TentarNovamente) {
                            TCC.IniciarConexaoHeadset();
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
