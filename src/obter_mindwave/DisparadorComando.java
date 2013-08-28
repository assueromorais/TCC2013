/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package obter_mindwave;

import iGeradorComandos.enmTipoComando;
import iGeradorComandos.iGeradorComandosOuvinte;
import javax.swing.event.EventListenerList;

/**
 *
 * @author ASSUERO
 */
public class DisparadorComando implements Runnable {

    private EventListenerList _ouvintes = new EventListenerList(); // Lista de eventos
    private enmTipoComando _comando;
    private String _mensagem;

    public DisparadorComando(EventListenerList ellOuvintes, enmTipoComando tpcComando, String strMensagem) {
        _comando = tpcComando;
        _mensagem = strMensagem;
        _ouvintes = ellOuvintes;
    }

    public void Iniciar(){
        new Thread(this).start();
    }
    
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
