/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TCC_prototipo;

import iGeradorComandos.iGeradorComandos;
import javax.swing.JFrame;

/**
 * Classe principal do projeto.
 *
 * @author ASSUERO
 */
public class TCC {

    public static iGeradorComandos Comandos;
    /**
     * O formulário frmInicio é o primeiro formulário.
     *
     * @param args the command line arguments
     */
    static JFrame Dispositivos = null;
    static FrmMindWaveDesconectado MindWaveDesconectado = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        Comandos = new TesteComando.TesteComando();
        Dispositivos = new FrmDispositivos();
        IniciarConexaoHeadset();
    }

    public static void IniciarConexaoHeadset() {
        if (Comandos != null) {
            if (Comandos.Iniciar()) {
                //Conectou ao dispositivo corretamente;
                new FrmInicio().setVisible(true);
            } else {
                /*
                MindWaveDesconectado = new FrmMindWaveDesconectado();
                MindWaveDesconectado.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent we) {
                        if (TCC.MindWaveDesconectado.TentarNovamente) {
                            TCC.IniciarConexaoHeadset();
                        }
                    }
                });
                //Exibe a mensagem de falha na conexão com o Mind wave.
                MindWaveDesconectado.setVisible(true);*/
                new FrmInicio().setVisible(true);
            }
        }
    }
}
