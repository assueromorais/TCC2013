/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorDispositivos;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASSUERO
 */
public class ObterPortaArduino {

    public static String ObterPorta() {
        ArrayList<String> portas = util.PortaSerial.ObterListaPortasAtivas();
        for (int i = 0; i < portas.size(); i++) {
            if (TestarPorta(portas.get(i))) {
                return portas.get(i).replace("\\\\.\\", "");
            }
        }
        return "";
    }

    private static boolean TestarPorta(String strPortaCom) {
        CommPortIdentifier portIdentifier = null;
        SerialPort serialPort = null;
        InputStream input = null;
        OutputStream output = null;
        try {
            portIdentifier = CommPortIdentifier.getPortIdentifier(strPortaCom.replace("\\\\.\\", ""));
        } catch (NoSuchPortException ex) {
            return false;
        }
        if (portIdentifier.isCurrentlyOwned()) {
            System.out.println("Erro: A porta está atualmente em uso.");
        } else {
            CommPort commPort;
            try {
                commPort = portIdentifier.open("Testando porta arduino", 2000);
                if (commPort instanceof SerialPort) {
                    serialPort = (SerialPort) commPort;
                    serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                    input = serialPort.getInputStream();
                    output = serialPort.getOutputStream();

                } else {
                    System.out.println("Erro: Somente portas seriais podem ser utilizadas para esta conexão.");
                }
            } catch (PortInUseException | UnsupportedCommOperationException ex) {
                Logger.getLogger("Falha");
                Logger.getLogger(ControladorDispositivos.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } catch (IOException ex) {
                Logger.getLogger(ControladorDispositivos.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        if (output != null) {
            try {
                // Envia o comando para o dispositivo, para confirmar se é um Arduino.
                // Define um tempo limite para esperar a resposta do dispositivo.
                // o tempo limite é necessário para evitar que o aplicativo espere indefinidamente pela resposta
                String resposta;
                Thread.sleep(1500);
                //while (dtTimeout.getTime() < (new Date().getTime())) {
                System.out.println("Aguardando resposta na porta " + strPortaCom + ".");
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.SECOND, 5);
                Date dataInicio = calendar.getTime();
                do {
                    output.write("0".getBytes());
                    output.flush();
                    output.close();
                    resposta = util.PortaSerial.LerPortaSerial(input);
                    if ((resposta != null && !resposta.equals("") && resposta.contains("1"))) {
                        // O dispositivo respondeu ao comando
                        System.out.println("Resposta: " + resposta);
                        System.out.println("Encontrado na porta " + strPortaCom);
                        if (serialPort != null) {
                            serialPort.close();
                        }
                        return true;
                    };
                } while (util.Data.DiferencaEmSegundos(new Date(), dataInicio) > 0);
                output.write("A".getBytes());
                output.close();
                Thread.sleep(2000);
                serialPort.close();
            } catch (IOException ex) {
                Logger.getLogger(ControladorDispositivos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ControladorDispositivos.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("A porta não respondeu.");
            return false;
        } else {
            if (serialPort != null) {
                serialPort.close();
            }
            return false;
        }
    }
}
