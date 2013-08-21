/*
 * Classe principal para tratamento dos sinais do Headset - Mindwave
 * Envio para aplicação especifica
 */
// Nome do projeto
package obter_mindwave;

// Importacação de pacotes e de bibliotecas
import com.neurosky.thinkgear.ThinkGear;
import iGeradorComandos.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.event.EventListenerList;

/**
 *
 * @author MarcusVinicius
 */
public class Obter_mindwave implements iGeradorComandos, Runnable {

    protected EventListenerList eveEscutador = new EventListenerList(); // Lista de eventos
    private static String strCOM; // Porta COM
    private static Thread thrExecutar; // Thread que será executada para captura de sinas
    private static boolean booParar = true; // Boleano para decisão de parada de thread

    @Override
    public void run() {
        double douPiscarAntigo = 0.0; // Instancia variável para iniciar com 0.0

        // Objeto principal de execução da thread
        int intConexaoID; // Código da conexão
        intConexaoID = ThinkGear.GetNewConnectionId(); // Obtém uma noca conexão com o headset
        ThinkGear.Connect(intConexaoID, strCOM.toString(), ThinkGear.BAUD_57600, ThinkGear.STREAM_PACKETS); // Conexão com o headset
        ThinkGear.EnableAutoRead(intConexaoID, 1); // Habilitar a auto leitura
        ThinkGear.EnableBlinkDetection(intConexaoID, 1); // Habilitar o teste de piscar os olhos
        ThinkGear.ReadPackets(intConexaoID, -1); // Letirua de pacotes

        // Loop enquanto o boleano de parar for falso
        while (!booParar) {
            synchronized (this) { // Evita que outra thread misture com essa
                double douSinalFraco = ThinkGear.GetValue(intConexaoID, ThinkGear.DATA_POOR_SIGNAL); // Obtém sinais fracos do headset, geralmente ocorre quando o headset está fora da cabeça
                double douPiscar = ThinkGear.GetValue(intConexaoID, ThinkGear.TG_DATA_BLINK_STRENGTH); // Obtém os dados de piscar

                // Condicionais para teste
                if (douSinalFraco >= 100) {
                    DispararEvento(enmTipoComando.DesconectouCabeca, "Fora da cabeça"); // 
                    ThinkGear.Disconnect(intConexaoID);
                    ThinkGear.FreeConnection(intConexaoID);
                } else if (douPiscar != douPiscarAntigo) {
                    DispararEvento(enmTipoComando.MudarFoco, "Mudança de Foco");

                    douPiscarAntigo = douPiscar;
                }
            }
        }


    }

    @Override
    public boolean Iniciar() {
        Obter_porta_mindwave mind = new Obter_porta_mindwave();
        try {
            strCOM = mind.obterPorta();
        } catch (InterruptedException ex) {
            Logger.getLogger(Obter_mindwave.class.getName()).log(Level.SEVERE, null, ex);
        }

        if ("_".equals(strCOM) || "".equals(strCOM)) {

            booParar = true;
            return false;

        } else {
            booParar = false;
            thrExecutar = new Thread(this);
            thrExecutar.start();
            return true;

        }
    }

    @Override
    public void AdicionarOuvinte(iGeradorComandosOuvinte ouvinte) {
        eveEscutador.add(iGeradorComandosOuvinte.class, ouvinte);
    }

    @Override
    public void RemoverOuvinte(iGeradorComandosOuvinte ouvinte) {
        eveEscutador.remove(iGeradorComandosOuvinte.class, ouvinte);
    }

    @Override
    public void Parar() {
        booParar = true;
        Obter_mindwave.thrExecutar.interrupt();
    }

    private void DispararEvento(enmTipoComando evt, String strMensagem) {
        Object[] objOuvintes = eveEscutador.getListenerList();
        for (int i = 0; i < objOuvintes.length; i = i + 2) {
            if (objOuvintes[i] == iGeradorComandosOuvinte.class) {
                ((iGeradorComandosOuvinte) objOuvintes[i + 1]).ReceberComando(evt, strMensagem);
            }
        }
    }
}
