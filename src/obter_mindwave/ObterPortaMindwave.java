/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package obter_mindwave;

import com.neurosky.thinkgear.ThinkGear;
import java.util.*;
import javax.comm.CommPortIdentifier;

/**
 *
 * @author MarcusVinicius
 */
public class ObterPortaMindwave {

    public static String ObterPorta() throws InterruptedException {

        boolean booAtivo = false; // Booleano que identificará se foi identificado a porta COM
        String strPortaUsada = ""; // String que conterá a porta COM de comunicação do Headset
        ArrayList<String> mstrInversaoCOM = obterListaPortas(); // Vetor para portas COM
        int intIdConexao = ThinkGear.GetNewConnectionId();  // Obtém um Código da conexão aleatório
        while (intIdConexao < 0) {
            ThinkGear.Disconnect(intIdConexao);
            ThinkGear.FreeConnection(intIdConexao);
            intIdConexao = ThinkGear.GetNewConnectionId();  // Obtém um Código da conexão aleatório
        }
        for (int intIndicePorcaCom = mstrInversaoCOM.size() - 1; intIndicePorcaCom >= 0; intIndicePorcaCom--) {
            System.out.println(mstrInversaoCOM.get(intIndicePorcaCom).toString());
            // Informa que foi encontrada porta
            booAtivo = TestarSinais(intIdConexao, mstrInversaoCOM.get(intIndicePorcaCom).toString());
            if (booAtivo) { // Verifica se sinal do mindwave                
                strPortaUsada = mstrInversaoCOM.get(intIndicePorcaCom); // Informa a porta que está ativa
                break; // Sai do loop
            }
        }
        if (booAtivo) { // Se existe porta disponível
            return strPortaUsada; // Nome da porta
        } else { // Senão retorna vazio
            return "_"; // Vazio
        }
    }

    private static ArrayList<String> obterListaPortas() {
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

    private static boolean TestarSinais(int intIdConexao, String strPortaCOM) throws InterruptedException {
        double douSinal = 0.0;
        ThinkGear.Connect(intIdConexao, strPortaCOM, ThinkGear.BAUD_57600, ThinkGear.STREAM_PACKETS); // Efetua uma conexão com o mindwave
        ThinkGear.EnableAutoRead(intIdConexao, 1); // Efetua leitura contínua
        int intTempoEspera = 10000;
        for (int intTentativasConexao = 0; intTentativasConexao < 5; intTentativasConexao++) { // Loop para efetuar tentativas de conexão, 5 passos
            // Sleep(Tempo de espera em milisegundo)
            Thread.sleep(intTempoEspera); // Aguarda processar sinal no headset
            // GetValue(Código da conexão, Dado desejado)
            douSinal = ThinkGear.GetValue(intIdConexao, ThinkGear.DATA_RAW); // Obtém o valor do mindwave
            if (douSinal > 0.0) { // Se sinal for maior que 0 é considerado que obteve sucesso ao tentar conectar
                System.out.println("Entrou");
                break; // Sai do loop
            }
            System.out.println(intTempoEspera);
            intTempoEspera = intTempoEspera / 2;
        }
        ThinkGear.Disconnect(intIdConexao); // Desconecta para próxima conexão
        ThinkGear.FreeConnection(intIdConexao); // Limpa espaço de conexão
        return (douSinal > 0.0);
    }
}
