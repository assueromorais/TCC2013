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

    /**
     * Lista com os ouvintes que irão receber os comandos da classe.
     */
    protected EventListenerList eveEscutadores = new EventListenerList();
    /**
     * Porta COM onde o headset está conectado.
     */
    private String strCOM;
    /**
     * Thread principal da classe, executa a captura de sinais em um loop.
     */
    private Thread thrExecutar;
    /**
     * Identifica que deve parar de analisar os dados do headset
     */
    private boolean booParar = true;
    /**
     * Define a intensidade da piscada para que ela será considerada como um
     * comando. O valor varia de 0 á 255.
     */
    public int IntensidadePiscada = 105;
    /**
     * Define o intervalo em que deverá ocorrer duas piscadas para que o comando
     * SelecionarItem será disparado. Caso o intervalo seja ultrapassado, o
     * comando MudarFoco será disparado.
     */
    private double IntervaloPiscadasSelecionarItem = 1.4;
    /**
     * ID da conexão atual com o Headset.
     */
    private int intIDConexao = 0;

    @Override
    public boolean Conectar() {
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
        intIDConexao = ThinkGear.GetNewConnectionId();  // Obtém um Código da conexão aleatório
        while (intIDConexao == 0) {
            intIDConexao = ThinkGear.GetNewConnectionId(); // Obtém uma noca conexão com o headset
        }
        ThinkGear.Connect(intIDConexao, strCOM.toString(), ThinkGear.BAUD_9600, ThinkGear.STREAM_PACKETS); // Conexão com o headset
        // Loop enquanto o boleano de parar for falso
        //System.out.println("Sinal Fraco|Atenção|Piscar|Meditacao|Alpha1|Alpha2|Beta1|Beta2|Delta|Gamma1|Gamma2|Theta");
        System.out.println("Sinal Fraco|Atenção|Piscar|Beta1|Beta2|Gamma1|Gamma2");
        ThinkGear.EnableBlinkDetection(intIDConexao, 1); // Habilitar o teste de piscar os olhos
        Date dtUltimoPiscar = null;
        double douUltimoNivelPiscar = 0.0;
        double douNivelPiscar; // Obtém os dados de piscar
        boolean booSinalPiscarMudou; // indica que o último valor capturado da piscada foi atualizado pelo Mind Wave.
        float difSegundos = 0;
        while (!booParar) {
            synchronized (this) { // Evita que outra thread misture com essa
                ThinkGear.ReadPackets(intIDConexao, 1); // Leitura de pacotes
                douNivelPiscar = ThinkGear.GetValue(intIDConexao, ThinkGear.TG_DATA_BLINK_STRENGTH); // Obtém os dados de piscar
                booSinalPiscarMudou = (ThinkGear.GetValueStatus(intIDConexao, ThinkGear.TG_DATA_BLINK_STRENGTH) != 0); // Obtém os dados de piscar
                /*double douBateria = ThinkGear.GetValue(intIDConexao, ThinkGear.DATA_BATTERY);
                 double douSinalFraco = ThinkGear.GetValue(intIDConexao, ThinkGear.DATA_POOR_SIGNAL); // Obtém sinais fracos do headset, geralmente ocorre quando o headset está fora da cabeça
                 double douAtencao = ThinkGear.GetValue(intIDConexao, ThinkGear.DATA_ATTENTION); // Obtém os dados de atenção
                 double douMeditacao = ThinkGear.GetValue(intIDConexao, ThinkGear.DATA_MEDITATION); // Obtém os dados de meditação (Meditação)
                 double douAlpha1 = ThinkGear.GetValue(intIDConexao, ThinkGear.DATA_ALPHA1); // Obtém os dados de Alpha1 (Relaxamento e meditação)
                 double douAlpha2 = ThinkGear.GetValue(intIDConexao, ThinkGear.DATA_ALPHA2); // Obtém os dados de Alpha2 (Relaxamento e meditação)
                 double douBeta1 = ThinkGear.GetValue(intIDConexao, ThinkGear.DATA_BETA1); // Obtém os dados de Beta1 (Foco e Atenção)
                 double douBeta2 = ThinkGear.GetValue(intIDConexao, ThinkGear.DATA_BETA2); // Obtém os dados de Beta2 (Foco e Atenção)
                 double douDelta = ThinkGear.GetValue(intIDConexao, ThinkGear.DATA_DELTA); // Obtém os dados de Delta (Sono profundo ou inconsciente)
                 double douGamma1 = ThinkGear.GetValue(intIDConexao, ThinkGear.DATA_GAMMA1); // Obtém os dados de Gamma1 (Relacionada com alguns sentidos e memória)
                 double douGamma2 = ThinkGear.GetValue(intIDConexao, ThinkGear.DATA_GAMMA2); // Obtém os dados de Gamma2 (Relacionada com alguns sentidos e memória)
                 double douTheta = ThinkGear.GetValue(intIDConexao, ThinkGear.DATA_THETA); // Obtém os dados de Theta (Sonolência e relaxamento profundo)
                 */
                //double douSomaSinais = 0.0;
                if (booSinalPiscarMudou) {
                    // Piscou uma vez
                    String douSomaSinais = (douNivelPiscar) + " : " + difSegundos + " segundos";//(douAtencao) + "|" + (douNivelPiscar) + "|" + (douMeditacao) + "|" + (douAlpha1) + "|" + (douAlpha2) + "|" + (douBeta1) + "|" + (douBeta2) + "|" + (douDelta) + "|" + (douGamma1) + "|" + (douGamma2) + "|" + (douTheta) + "|" + (douBateria);
                    System.out.println("Piscou");
                    System.out.println(douSomaSinais);
                    DispararEvento(enmTipoComando.Piscou, String.valueOf(douNivelPiscar));
                }
                if (dtUltimoPiscar != null) {
                    difSegundos = util.Data.DiferencaEmSegundos(dtUltimoPiscar, new Date());
                }
                // Analisa se deve enviar os comandos
                if (douUltimoNivelPiscar >= IntensidadePiscada && difSegundos >= IntervaloPiscadasSelecionarItem && (douNivelPiscar < IntensidadePiscada || !booSinalPiscarMudou)) {
                    // Mesmo o sinal não tendo mudado deverá verificar se houve uma piscada para mudança de foco
                    // Se a última piscada foi maior do que o definido e o intervalo da última piscada for maior do que 1 segundo
                    douUltimoNivelPiscar = 0;
                    difSegundos = 0;
                    dtUltimoPiscar = null;
                    DispararEvento(enmTipoComando.MudarFoco, "Mudar foco");
                    System.out.println("Mudou foco");
                } else {
                    if (douNivelPiscar >= IntensidadePiscada && booSinalPiscarMudou) {
                        // O comando SelecionarItem só é disparada se houver duas piscadas em menos de 1 segundo e
                        // se a piscada atual é um novo sinal do headset
                        if (douUltimoNivelPiscar >= IntensidadePiscada) {
                            douUltimoNivelPiscar = 0;
                            difSegundos = 0;
                            dtUltimoPiscar = null;
                            DispararEvento(enmTipoComando.SelecionarItem, "Selecionar item");
                            System.out.println("Selecionou");
                        } else {
                            douUltimoNivelPiscar = douNivelPiscar;
                            dtUltimoPiscar = new Date();
                        }
                        // O usuário piscou na intensidade que deve ser considerada 
                        // Dispara o evento para os observadores
                        DispararEvento(enmTipoComando.PiscouForte, String.valueOf(douUltimoNivelPiscar));
                    }
                }
                if (ThinkGear.GetValue(intIDConexao, ThinkGear.DATA_POOR_SIGNAL) > 150) {
                    // O sinal está fraco, isto pode indicar que o headset está mau posicionado ou está foca da cabeça
                    DispararEvento(enmTipoComando.SinalPobre, String.valueOf(ThinkGear.GetValue(intIDConexao, ThinkGear.DATA_POOR_SIGNAL)));
                    System.out.println("Sinal probre.");
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

    private double ValidacaoAlteracaoDataMindwave(int intIDConexao, int intDado) {
        return (ThinkGear.GetValueStatus(intIDConexao, intDado) != 0) ? ThinkGear.GetValue(intIDConexao, intDado) : 0.0;
    }

    @Override
    public double NivelDaBateria() {
        return ThinkGear.GetValue(intIDConexao, ThinkGear.DATA_BATTERY);
    }

    @Override
    public boolean ConectadoNaCabeca() {
        return ThinkGear.GetValue(intIDConexao, ThinkGear.DATA_POOR_SIGNAL) <= 100;
    }

    @Override
    public boolean DispositivoConectado() {
        return false;
    }

    @Override
    public double getIntervaloFocar() {
        return IntervaloPiscadasSelecionarItem;
    }
;
    
}