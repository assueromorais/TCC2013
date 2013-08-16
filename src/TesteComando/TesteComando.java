/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TesteComando;
import iGeradorComandos.iGeradorComandos;
import iGeradorComandos.iGeradorComandosOuvinte;
import javax.swing.event.EventListenerList;

/**
 *
 * @author ASSUERO
 */
public class TesteComando implements iGeradorComandos,  Runnable {
    Thread trabalho = null;
    protected EventListenerList listenerList = new EventListenerList();
    
    @Override
    public boolean Iniciar() {
        boolean booConectado = false;
        trabalho = new Thread(this);
        trabalho.start();
        return booConectado;
    }

    public void run(){
        
    }
    
    @Override
    public void Parar() {
        if(trabalho != null){
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
}