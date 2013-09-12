/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.Enumeration;
import gnu.io.*;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author ASSUERO
 */
public class PortaSerial {

    /**
     * Retorna uma lista contendo as portas seriais atualmente disponíveis no SO
     *
     * @return
     */
    public static ArrayList<String> ObterListaPortasAtivas() {
        ArrayList<String> mstrInversaoCOM = new ArrayList(); // Vetor para portas COM
        Enumeration enmPortaCOM = CommPortIdentifier.getPortIdentifiers(); // Obtém uma lista de portas COM
        int intPortasCom = 0; // Contador do vetor
        while (enmPortaCOM.hasMoreElements()) {
            // Lista todas portas COM do computador para inverter a posição da listagem do último para o primeiro
            CommPortIdentifier comPortaDetalhes = (CommPortIdentifier) enmPortaCOM.nextElement(); // Insere objetos em comPortaDetalhes
            if (comPortaDetalhes.getPortType() == 1) { // Verifica se a porta COM é serial
                mstrInversaoCOM.add(intPortasCom, "\\\\.\\COM" + comPortaDetalhes.getName().toString().replace("COM", "")); // Insere em um vetor o nome da porta
                intPortasCom++; // Incrementa mais um no contador
            }
        }
        return mstrInversaoCOM;
    }

    public static String LerPortaSerial(InputStream _input) {
        byte[] readBuffer = new byte[1024];
        int len = -1;
        String result = "";
        try {
            /**
             * if (_input.available() > 0) { while ((len =
             * _input.read(readBuffer)) > -1) { result += new String(readBuffer,
             * 0, len); } }
            *
             */
            int availableBytes = _input.available();
            if (availableBytes > 0) { // Read the serial port 
                _input.read(readBuffer, 0, availableBytes);
               // Print it out return 
                result += new String(readBuffer, 0, availableBytes);
             }
            return result;
        } catch (IOException e) {
        }
        return null;
    }
}
