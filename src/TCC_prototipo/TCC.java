/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TCC_prototipo;

import iComando.iGeradorComandos;

/**
 * Classe principal do projeto.
 * @author ASSUERO
 */
public class TCC {

    public static iGeradorComandos Comandos ;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Comandos = new TesteComando.TesteComando();
        if (Comandos.Iniciar()){
            //Conectou ao dispositivo corretamente;
            new FrmInicio().setVisible(true);
        }
        else{
            //Exibe a mensagem de falha na conexão com o Mind wave.
            new FrmMindWaveDesconectado().setVisible(true);
        }
    }
}
