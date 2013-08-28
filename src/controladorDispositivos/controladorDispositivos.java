
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorDispositivos;

import iControladorDispositivos.iControladorDispositivos;
import java.util.*;
import java.io.*;
import javax.comm.*;

/**
 *
 * @author ASSUERO
 */
public class controladorDispositivos implements iControladorDispositivos {

    static Enumeration portList;
    static CommPortIdentifier portId;
    static SerialPort serialPort;
    static OutputStream outputStream;

    @Override
    public void EnviarComando(String strComando) {
        portList = CommPortIdentifier.getPortIdentifiers();

        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if (portId.getName().equals("COM1")) {
                    //if (portId.getName().equals("/dev/term/a")) {
                    try {
                        serialPort = (SerialPort) portId.open("SimpleWriteApp", 2000);
                    } catch (PortInUseException e) {
                    }
                    try {
                        outputStream = serialPort.getOutputStream();
                    } catch (IOException e) {
                    }
                    try {
                        serialPort.setSerialPortParams(9600,
                                SerialPort.DATABITS_8,
                                SerialPort.STOPBITS_1,
                                SerialPort.PARITY_NONE);
                    } catch (UnsupportedCommOperationException e) {
                    }
                    try {
                        outputStream.write(strComando.getBytes());
                    } catch (IOException e) {
                    }
                }
            }
        }
    }

    @Override
    public boolean ControladorConectado() {
        boolean booConectado = false;
        return booConectado;
    }
}
