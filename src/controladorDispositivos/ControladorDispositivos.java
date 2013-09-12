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
import iControladorDispositivos.iControladorDispositivos;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASSUERO
 */
public final class ControladorDispositivos implements iControladorDispositivos {

    /**
     * Porta atualmente utilizada para conectar ao dispositivo.
     */
    private SerialPort serialPort;
    private String PortaCom = "COM7";
    /**
     * Strem para envio de dados para o controlador.
     */
    private OutputStream output;
    /**
     * Strem para recebimento de dados vindos do controlador.
     */
    private InputStream input;

    /**
     * Conecta no controlador de dispositivos, uma placa Arduino. Mantém a
     * conexão com a porta serial até solicitar o fechamento ou encerrar o
     * aplicativo.
     *
     * @throws IOException
     */
    public ControladorDispositivos() {
    }

    /**
     * Conecta na porta serial do Arduino. Irá procurar dente as portas COM
     * atualmente ativas, uma porta que responda ao comando 1, deverá retornar
     * 0(zero).
     */
    @Override
    public boolean Conectar() {
        PortaCom = ObterPortaArduino.ObterPorta();
        if (!PortaCom.equals("")) {
            CommPortIdentifier portIdentifier = null;
            try {
                portIdentifier = CommPortIdentifier.getPortIdentifier(PortaCom);
            } catch (NoSuchPortException ex) {
                Logger.getLogger(ControladorDispositivos.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (portIdentifier.isCurrentlyOwned()) {
                System.out.println("Erro: A porta está atualmente em uso.");
            } else {
                CommPort commPort;
                try {
                    commPort = portIdentifier.open(this.getClass().getName(), 2000);
                    if (commPort instanceof SerialPort) {
                        serialPort = (SerialPort) commPort;
                        serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                        input = serialPort.getInputStream();
                        output = serialPort.getOutputStream();
                        return true;
                    } else {
                        System.out.println("Erro: Somente portas seriais podem ser utilizadas para esta conexão.");
                    }
                } catch (PortInUseException | UnsupportedCommOperationException ex) {
                    Logger.getLogger("Falha");
                    Logger.getLogger(ControladorDispositivos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ControladorDispositivos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return false;
        } else {
            return false;
        }
    }

    /**
     * Fecha a conexão com a porta serial do controlador de dispositivos .
     */
    @Override
    public void Desconectar() {
        if (serialPort != null) {
            serialPort.close();
            serialPort = null;
        }
    }

    @Override
    public void EnviarComando(String strComando) {
        DisparadorComando disparador = new DisparadorComando(output, strComando);
        disparador.Iniciar();
    }

    @Override
    public boolean ControladorConectado() {
        return (serialPort != null);
    }
}
