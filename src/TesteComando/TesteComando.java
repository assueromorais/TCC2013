/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TesteComando;
import Cronometro.Cronometro;
import iGeradorComandos.enmTipoComando;
import iGeradorComandos.iGeradorComandos;
import iGeradorComandos.iGeradorComandosOuvinte;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.EventListenerList;

/**
 *
 * @author ASSUERO
 */
public class TesteComando implements iGeradorComandos,  Runnable {
    Thread trabalho = null;
    protected EventListenerList listenerList = new EventListenerList();
    boolean _parar = false;
    @Override
    public boolean Iniciar() {
        boolean booConectado = false;
        trabalho = new Thread(this);
        trabalho.start();
        return booConectado;
    }
    
    public void run(){
        while(!_parar){
            synchronized (this){
                try {
                    Thread.sleep(2000);
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(Cronometro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        _parar = false;
    }
    
    @Override
    public void Parar() {
        if(trabalho != null){
            _parar = true;
            trabalho.interrupt();
        }
    }

    @Override
    public void AdicionarOuvinte(iGeradorComandosOuvinte ouvinte) {
        this.listenerList.add(iGeradorComandosOuvinte.class, ouvinte);
    }

    @Override
    public void RemoverOuvinte(iGeradorComandosOuvinte ouvinte) {
        this.listenerList.remove(iGeradorComandosOuvinte.class, ouvinte);
    }
    private void DispararComando(enmTipoComando comando){
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i = i + 2) {
            if (listeners[i] == iGeradorComandosOuvinte.class) {
                ((iGeradorComandosOuvinte) listeners[i + 1]).ReceberComando(comando, null);
            }
        }
    }
    
}