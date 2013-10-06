/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cronometro;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.EventListenerList;

/**
 *
 * @author ASSUERO
 */
public class Cronometro implements Runnable {

    public int Intervalo = 0;
    private boolean _parar = false;
    protected EventListenerList listenerList = new EventListenerList();

    private Thread t = null;
    
    public Cronometro(int _intervalo) {
        Intervalo = _intervalo;
    }
    
    public void Iniciar(){
        Parar();
        t = new Thread(this);
        t.start();
        _parar = false;
    }
    
    public void run() {
        while (!_parar) {
            synchronized (this) {
                try {
                    Thread.sleep(Intervalo);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Cronometro.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (!_parar){
                CronometroEvento te = new CronometroEvento(this);
                // Dispara o evento para os "ouvintes"
                dispararEvento(te);}
            }
        }
        // Só sai do loop se tiver setado a variável _parar como true, então saí do loop e retorna a variável para false
        _parar = false;
    }
    
    /**
     *
     * @author ASSUERO
     * Interrompe a thread responsável pelo timer.
     */
    public void Parar() {
        if(t != null){
                _parar = true;
        }
    }
    
    /**
     *
     * @author ASSUERO
     * Adiciona um ouvinte ao timer, permite que mais de um objeto espere pela notificação do timer.
     */
    public void adicionarOuvinte(CronometroOuvinte listener) {
        listenerList.add(CronometroOuvinte.class, listener);
    }
    
    /**
     *
     * @author ASSUERO
     * Remove um ouvinte anteriormente inserido.
     */
    public void removerOuvinte(CronometroOuvinte listener) {
        listenerList.remove(CronometroOuvinte.class, listener);
    }
        
    /**
     *
     * @author ASSUERO
     * Dispara o evento
     */
    private void dispararEvento(CronometroEvento evt) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i = i + 2) {
            if (listeners[i] == CronometroOuvinte.class) {
                ((CronometroOuvinte) listeners[i + 1]).IntervaloOcorreu(evt);
            }
        }
    }
}
