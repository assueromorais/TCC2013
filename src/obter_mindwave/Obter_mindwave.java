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
public class Obter_mindwave implements iGeradorComandos, Runnable {

    protected EventListenerList eveEscutador = new EventListenerList(); // Lista de eventos
    private static String strCOM; // Porta COM
    private static Thread thrExecutar; // Thread que será executada para captura de sinas
    private static boolean booParar = true; // Boleano para decisão de parada de thread

    @Override
    public void run() {
        // Objeto principal de execução da thread
        int intConexaoID; // Código da conexão

        intConexaoID = ThinkGear.GetNewConnectionId(); // Obtém uma noca conexão com o headset
        while (intConexaoID == 0) {
            intConexaoID = ThinkGear.GetNewConnectionId(); // Obtém uma noca conexão com o headset
        }
        ThinkGear.Connect(intConexaoID, strCOM.toString(), ThinkGear.BAUD_57600, ThinkGear.STREAM_PACKETS); // Conexão com o headset
        ThinkGear.EnableAutoRead(intConexaoID, 1); // Habilitar a auto leitura
        /* Inicio de lixo
         FileWriter arq = null;
         try {
         arq = new FileWriter("C:\\tcc_testes\\piscada_altera\\piscada_2seg.txt");
         } catch (IOException ex) {
         Logger.getLogger(Obter_mindwave.class.getName()).log(Level.SEVERE, null, ex);
         }
         PrintWriter gravarArq = new PrintWriter(arq);
         gravarArq.println("Sinal Fraco|Atenção|Piscar|Meditacao|Alpha1|Alpha2|Beta1|Beta2|Delta|Gamma1|Gamma2|Theta");
         String strArquivoOLD = "";
         int intTeste = 0;
         // Fim de lixo */
        boolean booFoco = false;
        int intContaFoco = 0;
        // Loop enquanto o boleano de parar for falso
        //System.out.println("Sinal Fraco|Atenção|Piscar|Meditacao|Alpha1|Alpha2|Beta1|Beta2|Delta|Gamma1|Gamma2|Theta");
        System.out.println("Sinal Fraco|Atenção|Piscar|Beta1|Beta2|Gamma1|Gamma2");
        String oldSoma = "";
        ThinkGear.EnableBlinkDetection(intConexaoID, 1); // Habilitar o teste de piscar os olhos
        ThinkGear.ReadPackets(intConexaoID, 1); // Leitura de pacotes
        double ultimoPiscar = 0.0;
        Date dtUltimoPiscar = null;
        while (!booParar) {
            synchronized (this) { // Evita que outra thread misture com essa
                //System.out.print(ThinkGear.GetValueStatus(intConexaoID, ThinkGear.TG_DATA_BLINK_STRENGTH) + " - ");
                double douPiscar = ThinkGear.GetValue(intConexaoID, ThinkGear.TG_DATA_BLINK_STRENGTH); // Obtém os dados de piscar
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
                 * /
                 * 
                 //double douSomaSinais = 0.0;
                 //float floDivisao = 0;

                 String douSomaSinais = (douPiscar) + "|";//(douAtencao) + "|" + (douPiscar) + "|" + (douMeditacao) + "|" + (douAlpha1) + "|" + (douAlpha2) + "|" + (douBeta1) + "|" + (douBeta2) + "|" + (douDelta) + "|" + (douGamma1) + "|" + (douGamma2) + "|" + (douTheta) + "|" + (douBateria);
                 if (douSomaSinais != oldSoma) {
                 oldSoma = douSomaSinais;
                 System.out.println(douSomaSinais);
                 }
                
                 //double douSomaSinais = ValidacaoLogDouble(douSinalFraco) + ValidacaoLogDouble(douAtencao) + ValidacaoLogDouble(douPiscar) + ValidacaoLogDouble(douMeditacao) + ValidacaoLogDouble(douAlpha1) + ValidacaoLogDouble(douAlpha2) + ValidacaoLogDouble(douBeta1) + ValidacaoLogDouble(douBeta2) + ValidacaoLogDouble(douDelta) + ValidacaoLogDouble(douGamma1) + ValidacaoLogDouble(douGamma2) + ValidacaoLogDouble(douTheta);
                 //floDivisao = (float) (douSomaSinais / 12);

                 //String strArquivo = douSinalFraco + "|" + douAtencao + "|" + douPiscar + "|" + douBeta1 + "|" + douBeta2 + "|" + douGamma1 + "|" + douGamma2;

                 /* Inicio de lixo
                 String strArquivo = douSinalFraco + "|" + douAtencao + "|" + douPiscar + "|" + douMeditacao + "|" + douAlpha1 + "|" + douAlpha2;
                 strArquivo += "|" + douBeta1 + "|" + douBeta2 + "|" + douDelta + "|" + douGamma1 + "|" + douGamma2 + "|" + douTheta;

                 if (strArquivo == null ? strArquivoOLD != null : !strArquivo.equals(strArquivoOLD)) {
                 System.out.println(strArquivo + " - " + intTeste);
                 strArquivoOLD = strArquivo;
                 gravarArq.println(strArquivo);
                 intTeste++;
                 }

                 if (intTeste == 104) {
                 booParar = true;
                 }

                 // Fim de lixo */

                // Condicionais para teste
                if (ultimoPiscar > 130 && util.Data.DiferencaEmSegundos(dtUltimoPiscar, new Date()) > 1) {
                    ultimoPiscar = 0;
                    DispararEvento(enmTipoComando.MudarFoco, "Mudar foco");
                } else {
                    if (douPiscar > 130 && douPiscar != ultimoPiscar) {
                        if (ultimoPiscar > 0) {
                            ultimoPiscar = 0;
                            DispararEvento(enmTipoComando.SelecionarItem, "Selecionar item");
                        } else {
                            ultimoPiscar = douPiscar;
                            dtUltimoPiscar = new Date();
                        }
                    }
                }
                //float floDivisao = ValidacaoLogDouble(douPiscar);

                //floDivisao = (float) douPiscar;
                //if (douPiscar != 0.0) {
                  System.out.println(douPiscar + "");
                //}

                /*if (douSinalFraco >= 100) {
                 DispararEvento(enmTipoComando.DesconectouCabeca, "Fora da cabeça"); // 
                 //ThinkGear.Disconnect(intConexaoID);
                 //ThinkGear.FreeConnection(intConexaoID);
                 } else if (douPiscar > 30.0) {
                 int intTentativa = 0;
                 douBeta1 = 0.0;
                 for (int intContador = 1; intContador <= 1000; intContador++) {
                 douAtencao = ThinkGear.GetValue(intConexaoID, ThinkGear.DATA_ATTENTION); // Obtém os dados de atenção
                 douBeta1 = ThinkGear.GetValue(intConexaoID, ThinkGear.DATA_BETA1); // Obtém os dados de Beta1 (Foco e Atenção)
                 douBeta2 = ThinkGear.GetValue(intConexaoID, ThinkGear.DATA_BETA2); // Obtém os dados de Beta2 (Foco e Atenção)
                 if (douBeta1 > 16000.0 && douBeta2 > 2000) {
                 intTentativa++;
                 }

                 System.out.println(intTentativa + " | " + douBeta1 + " | " + douBeta2);

                 try {
                 Thread.sleep(1);
                 } catch (InterruptedException ex) {
                 Logger.getLogger(Obter_mindwave.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 }

                 if (intTentativa >= 450) {
                 intTentativa = 0;
                 DispararEvento(enmTipoComando.SelecionarItem, "Selecionado");
                 } else {
                 intTentativa = 0;
                 DispararEvento(enmTipoComando.MudarFoco, "Mudança de Foco");
                 }
                 }*/
                /*
                 for (int intContador = 1; intContador <= 5000; intContador++) {
                 if (ThinkGear.GetValueStatus(intConexaoID, ThinkGear.TG_DATA_BLINK_STRENGTH) != 0) {
                 DispararEvento(enmTipoComando.SelecionarItem, "Selecionado");
                 } else {
                 DispararEvento(enmTipoComando.MudarFoco, "Mudança de Foco");
                 }
                 }
                 }*/
            }
        }

        /* Inicio de lixo
         try {
         arq.close();
         } catch (IOException ex) {
         Logger.getLogger(Obter_mindwave.class.getName()).log(Level.SEVERE, null, ex);
         }
         // Fim de lixo */

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
        // Se o ouvinte já estiver na lista não irá adicioná-lo.
        Object[] objOuvintes = eveEscutador.getListenerList();
        for (int i = 0; i <= objOuvintes.length - 1; i++) {
            if (objOuvintes[i] == ouvinte) {
                return;
            }
        }
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
        new DisparadorComando(eveEscutador, evt, strMensagem).Iniciar();
    }

    private float ValidacaoLogDouble(double douNumero) {
        return (douNumero <= 0) ? 0 : (float) Math.log10(douNumero);
    }

    private double ValidacaoAlteracaoDataMindwave(int intConexaoID, int intDado) {
        return (ThinkGear.GetValueStatus(intConexaoID, intDado) != 0) ? ThinkGear.GetValue(intConexaoID, intDado) : 0.0;
    }
}
