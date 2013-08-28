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
public class Obter_porta_mindwave {

    public String obterPorta() throws InterruptedException {

        boolean booAtivo = false; // Booleano que identificará se foi identificado a porta COM
        String strPortaUsada = ""; // String que conterá a porta COM de comunicação do Headset
        ArrayList<String> mstrInversaoCOM = new ArrayList(); // Vetor para portas COM
        double douSinal = 0.0;

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

        // Loop para listar o vetor em ordem inversa
        int intIdConexao = ThinkGear.GetNewConnectionId();  // Obtém um Código da conexão aleatório

        while (intIdConexao < 0) {
            ThinkGear.Disconnect(intIdConexao);
            ThinkGear.FreeConnection(intIdConexao);
            intIdConexao = ThinkGear.GetNewConnectionId();  // Obtém um Código da conexão aleatório
        }

        for (int intIndicePorcaCom = mstrInversaoCOM.size() - 1; intIndicePorcaCom >= 0; intIndicePorcaCom--) {
            // Connect(Código da conexão,Porta COM,Bits,Pacote do mindwave)
            System.out.println(mstrInversaoCOM.get(intIndicePorcaCom).toString());
            intIdConexao = ThinkGear.GetNewConnectionId();  // Obtém um Código da conexão aleatório

            ThinkGear.Connect(intIdConexao, mstrInversaoCOM.get(intIndicePorcaCom).toString(), ThinkGear.BAUD_57600, ThinkGear.STREAM_PACKETS); // Efetua uma conexão com o mindwave
            //Thread.sleep(3000);
            // EnableAutoRead(Código da conexão,1 = habilitado ou 0 = Desabilitado)
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

            if (douSinal > 0) { // Verifica se sinal do mindwave
                booAtivo = true; // Informa que foi encontrada porta
                strPortaUsada = mstrInversaoCOM.get(intIndicePorcaCom); // Informa a porta que está ativa
                ThinkGear.Disconnect(intIdConexao); // Desconecta para próxima conexão
                ThinkGear.FreeConnection(intIdConexao); // Limpa espaço de conexão
                break; // Sai do loop
            }

            ThinkGear.Disconnect(intIdConexao); // Desconecta para próxima conexão
            ThinkGear.FreeConnection(intIdConexao); // Limpa espaço de conexão para efetuar nova conexão

        }

        if (booAtivo == true) { // Se existe porta disponível
            Thread.sleep(1000); // Aguarda processar sinal no headset
            return strPortaUsada; // Nome da porta
        } else { // Senão retorna vazio
            return "_"; // Vazio
        }
    }
}
