/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorDispositivos;

import iControladorDispositivos.iControladorDispositivos;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import javax.comm.CommPortIdentifier;
import util.TwoWaySerialComm.*;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ASSUERO
 */
public class ControladorDispositivos implements iControladorDispositivos{

    static Enumeration portList;
    static CommPortIdentifier portId;
    static OutputStream outputStream;
    
    @Override
    public void EnviarComando(String strComando) {
        ByteArrayOutputStream baos = null;
        try {
            byte[] stringByte = strComando.getBytes();
            baos = new ByteArrayOutputStream(strComando.length());
            baos.write(stringByte);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ControladorDispositivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControladorDispositivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        util.TwoWaySerialComm.SerialWriter escritor = null;
        escritor = new util.TwoWaySerialComm.SerialWriter(baos);
        Thread t = new Thread(escritor);
        t.start();
    }

    @Override
    public boolean ControladorConectado() {
        boolean booConectado = false;

        try {
            //Informe a sua porta serial no metódo "connect()"
            //Por padrão ele traz a porta "COM3" que é utilizada no Windows.
            //É necessário substituir pelo nome da porta no Mac, conforme o exemplo abaixo.
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return booConectado;
    }

}
