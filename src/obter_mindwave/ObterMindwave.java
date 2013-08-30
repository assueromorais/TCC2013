/*
 * Classe principal para tratamento dos sinais do Headset - Mindwave
 * Envio para aplicação especifica
 */
// Nome do projeto
package obter_mindwave;

// Importacação de pacotes e de bibliotecas
import com.neurosky.thinkgear.ThinkGear;
import iGeradorComandos.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.EventListenerList;

/**
 *
 * @author MarcusVinicius
 */
public class ObterMindwave implements iGeradorComandos, Runnable {

    protected EventListenerList eveEscutadores = new EventListenerList(); // Lista de eventos
    private String strCOM; // Porta COM
    private Thread thrExecutar; // Thread que será executada para captura de sinas
    private boolean booParar = true; // Boleano para decisão de parada de thread
    /**
     * Define a intensidade da piscada para que ela será considerada como um
     * comando. O valor varia de 0 á 255.
     */
    public int IntensidadePiscada = 110;
    /**
     * Define o intervalo em que deverá ocorrer duas piscadas para que o comando
     * SelecionarItem será disparado. Caso o intervalo seja ultrapassado, o
     * comando MudarFoco será disparado.
     */
    public int IntervaloPiscadasSelecionarItem = 2;

    @Override
    public boolean Iniciar() {
        try {
            strCOM = ObterPortaMindwave.ObterPorta();
        } catch (InterruptedException ex) {
            Logger.getLogger(ObterMindwave.class.getName()).log(Level.SEVERE, null, ex);
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

    /**
     * Objeto principal de execução da thread
     */
    @Override
    public void run() {
        int intConexaoID = ThinkGear.GetNewConnectionId();  // Obtém um Código da conexão aleatório
        while (intConexaoID == 0) {
            intConexaoID = ThinkGear.GetNewConnectionId(); // Obtém uma noca conexão com o headset
        }
        ThinkGear.Connect(intConexaoID, strCOM.toString(), ThinkGear.BAUD_9600, ThinkGear.STREAM_PACKETS); // Conexão com o headset
        // Loop enquanto o boleano de parar for falso
        //System.out.println("Sinal Fraco|Atenção|Piscar|Meditacao|Alpha1|Alpha2|Beta1|Beta2|Delta|Gamma1|Gamma2|Theta");
        System.out.println("Sinal Fraco|Atenção|Piscar|Beta1|Beta2|Gamma1|Gamma2");
        String oldSoma = "";
        ThinkGear.EnableBlinkDetection(intConexaoID, 1); // Habilitar o teste de piscar os olhos
        Date dtUltimoPiscar = null;
        double ultimoPiscar = 0.0;
        double douPiscar = 0.0; // Obtém os dados de piscar
        boolean booSinalPiscarMudou = false; //
        double difSegundos = 0.0;
        while (!booParar) {
            synchronized (this) { // Evita que outra thread misture com essa
                ThinkGear.ReadPackets(intConexaoID, 1); // Leitura de pacotes
                douPiscar = ThinkGear.GetValue(intConexaoID, ThinkGear.TG_DATA_BLINK_STRENGTH); // Obtém os dados de piscar
                booSinalPiscarMudou = (ThinkGear.GetValueStatus(intConexaoID, ThinkGear.TG_DATA_BLINK_STRENGTH) != 0); // Obtém os dados de piscar
                /*double douBateria = ThinkGear.GetValue(intConexaoID, ThinkGear.DATA_BATTERY);
                 double douSinalFraco = ThinkGear.GetValue(intConexaoID, ThinkGear.DATA_POOR_SIGNAL); // Obtém sinais fracos do headset, geralmente ocorre quando o headset está fora da cabeça
                 double douAtencao = ThinkGear.GetValue(intConexaoID, ThinkGear.DATA_ATTENTION); // Obtém os dados de atenção
                 double douMeditacao = ThinkGear.GetValue(intConexaoID, ThinkGear.DATA_MEDITATION); // Obtém os dados de meditação (Meditação)
                 double douAlpha1 = ThinkGear.GetValue(intConexaoID, ThinkGear.DATA_ALPHA1); // Obtém os dados de Alpha1 (Relaxamento e meditação)
                 double douAlpha2 = ThinkGear.GetValue(intConexaoID, ThinkGear.DATA_ALPHA2); // Obtém os dados de Alpha2 (Relaxamento e meditação)
                 double douBeta1 = ThinkGear.GetValue(intConexaoID, ThinkGear.DATA_BETA1); // Obtém os dados de Beta1 (Foco e Atenção)
                 double douBeta2 = ThinkGear.GetValue(intConexaoID, ThinkGear.DATA_BETA2); // Obtém os dados de Beta2 (Foco e Atenção)
                 double douDelta = ThinkGear.GetValue(intConexaoID, ThinkGear.DATA_DELTA); // Obtém os dados de Delta (Sono profundo ou inconsciente)
                 double douGamma1 = ThinkGear.GetValue(intConexaoID, ThinkGear.DATA_GAMMA1); // Obtém os dados de Gamma1 (Relacionada com alguns sentidos e memória)
                 double douGamma2 = ThinkGear.GetValue(intConexaoID, ThinkGear.DATA_GAMMA2); // Obtém os dados de Gamma2 (Relacionada com alguns sentidos e memória)
                 double douTheta = ThinkGear.GetValue(intConexaoID, ThinkGear.DATA_THETA); // Obtém os dados de Theta (Sonolência e relaxamento profundo)
                 */
                //double douSomaSinais = 0.0;
                if (booSinalPiscarMudou) {
                    // Piscou uma vez 
                    if (dtUltimoPiscar != null) {
                        difSegundos = util.Data.DiferencaEmSegundos(dtUltimoPiscar, new Date());
                    }
                    String douSomaSinais = (douPiscar) + " : " + difSegundos + " segundos";//(douAtencao) + "|" + (douPiscar) + "|" + (douMeditacao) + "|" + (douAlpha1) + "|" + (douAlpha2) + "|" + (douBeta1) + "|" + (douBeta2) + "|" + (douDelta) + "|" + (douGamma1) + "|" + (douGamma2) + "|" + (douTheta) + "|" + (douBateria);
                    if (douSomaSinais != oldSoma) {
                        oldSoma = douSomaSinais;
                        System.out.println(douSomaSinais);
                    }
                }

                // Analisa se deve enviar os comandos
                if (ultimoPiscar >= IntensidadePiscada && difSegundos > IntervaloPiscadasSelecionarItem) {
                    // Mesmo o sinal não tendo mudado deverá verificar se houve uma piscada para mudança de foco
                    // Se a última piscada foi maior do que o definido e o intervalo da última piscada for maior do que 1 segundo
                    ultimoPiscar = 0;
                    difSegundos = 0;
                    DispararEvento(enmTipoComando.MudarFoco, "Mudar foco");
                } else {
                    if (douPiscar >= IntensidadePiscada && booSinalPiscarMudou) {
                        // O comando SelecionarItem só é disparada se houver duas piscadas em menos de 1 segundo e
                        // se a piscada atual é um novo sinal do headset
                        if (ultimoPiscar > IntensidadePiscada) {
                            ultimoPiscar = 0;
                            difSegundos = 0;
                            dtUltimoPiscar = null;
                            DispararEvento(enmTipoComando.SelecionarItem, "Selecionar item");
                        } else {
                            if(ultimoPiscar < IntensidadePiscada){
                                ultimoPiscar = douPiscar;
                                dtUltimoPiscar = new Date();
                            }
                        }
                    }
                }

            }
        }
    }

    @Override
    public void AdicionarOuvinte(iGeradorComandosOuvinte ouvinte) {
        // Se o ouvinte já estiver na lista não irá adicioná-lo.
        Object[] objOuvintes = eveEscutadores.getListenerList();
        for (int i = 0; i <= objOuvintes.length - 1; i++) {
            if (objOuvintes[i] == ouvinte) {
                return;
            }
        }
        eveEscutadores.add(iGeradorComandosOuvinte.class, ouvinte);
    }

    @Override
    public void RemoverOuvinte(iGeradorComandosOuvinte ouvinte) {
        eveEscutadores.remove(iGeradorComandosOuvinte.class, ouvinte);
    }

    @Override
    public void Parar() {
        booParar = true;
        this.thrExecutar.interrupt();
    }

    private void DispararEvento(enmTipoComando evt, String strMensagem) {
        new DisparadorComando(eveEscutadores, evt, strMensagem).Iniciar();
    }

    private float ValidacaoLogDouble(double douNumero) {
        return (douNumero <= 0) ? 0 : (float) Math.log10(douNumero);
    }

    private double ValidacaoAlteracaoDataMindwave(int intConexaoID, int intDado) {
        return (ThinkGear.GetValueStatus(intConexaoID, intDado) != 0) ? ThinkGear.GetValue(intConexaoID, intDado) : 0.0;
    }
}
