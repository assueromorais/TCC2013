/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorDispositivos;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASSUERO
 */
public class DisparadorComando implements Runnable {

    private String _strComando;
    private OutputStream _output;
    
    /**
     *
     */
    public DisparadorComando(OutputStream output, String strComando) {
        _strComando = strComando;
        _output = output;
    }

    /**
     * Executa a thread principal da classe.
     */
    public void Iniciar() {
        new Thread(this).start();
    }

    /**
     * 
     */
    @Override
    public void run() {
        try {
           _output.write(_strComando.getBytes());
           Thread.sleep(10);
        } catch (IOException ex) {
            Logger.getLogger(ControladorDispositivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(DisparadorComando.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
