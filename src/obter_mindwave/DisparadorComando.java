/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package obter_mindwave;

import iGeradorComandos.enmTipoComando;
import iGeradorComandos.iGeradorComandosOuvinte;
import javax.swing.event.EventListenerList;

/**
 * Classe responsável por disparar os comandos gerados pelo MindWave para serem
 * recebidos pelos formulários. A classe dispara o comando em uma thread
 * separada, para evitar que o gerador de comandos fique preso no fluxo do
 * código do formulário.
 *
 * @author ASSUERO
 */
public class DisparadorComando implements Runnable {

    private EventListenerList _ouvintes = new EventListenerList(); // Lista de eventos
    private enmTipoComando _comando;
    private String _mensagem;

    /**
     * 
     * @param ellOuvintes : Lista de ouvintes que receberão o comando.
     * @param tpcComando : Comando à ser disparado.
     * @param strMensagem : Mensagem opcional gerada pelo comando.
     */
    public DisparadorComando(EventListenerList ellOuvintes, enmTipoComando tpcComando, String strMensagem) {
        _comando = tpcComando;
        _mensagem = strMensagem;
        _ouvintes = ellOuvintes;
    }

    /**
     * Executa a thread principal da classe.
     */
    public void Iniciar() {
        new Thread(this).start();
    }

    /**
     * Percorre a lista de ouvintes e dispara o comando para todos eles.
     */
    @Override
    public void run() {
        Object[] objOuvintes = _ouvintes.getListenerList();
        for (int i = 0; i < objOuvintes.length; i = i + 2) {
            if (objOuvintes[i] == iGeradorComandosOuvinte.class) {
                ((iGeradorComandosOuvinte) objOuvintes[i + 1]).ReceberComando(_comando, _mensagem);
            }
        }
    }
}
