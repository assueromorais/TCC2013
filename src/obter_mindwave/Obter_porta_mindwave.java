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

        int intContador = 0; // Contador do vetor

        while (enmPortaCOM.hasMoreElements()) {
            // Lista todas portas COM do computador para inverter a posição da listagem do último para o primeiro
            CommPortIdentifier comPortaDetalhes = (CommPortIdentifier) enmPortaCOM.nextElement(); // Insere objetos em comPortaDetalhes
            if (comPortaDetalhes.getPortType() == 1) { // Verifica se a porta COM é serial
                mstrInversaoCOM.add(intContador, comPortaDetalhes.getName()); // Insere em um vetor o nome da porta
                intContador++; // Incrementa mais um no contador
            }
        }

        // Loop para listar o vetor em ordem inversa
        for (int intContador2 = mstrInversaoCOM.size() - 1; intContador2 >= 0; intContador2--) {

            int intIdConexao = ThinkGear.GetNewConnectionId();  // Obtém um Código da conexão aleatório

            // Connect(Código da conexão,Porta COM,Bits,Pacote do mindwave)
            ThinkGear.Connect(intIdConexao, mstrInversaoCOM.get(intContador2).toString(), ThinkGear.BAUD_57600, ThinkGear.STREAM_PACKETS); // Efetua uma conexão com o mindwave

            // EnableAutoRead(Código da conexão,1 = habilitado ou 0 = Desabilitado)
            ThinkGear.EnableAutoRead(intIdConexao, 1); // Efetua leitura contínua

            for (int intContador3 = 0; intContador3 < 5; intContador3++) { // Loop para efetuar tentativas de conexão, 5 passos
                // GetValue(Código da conexão, Dado desejado)
                douSinal = ThinkGear.GetValue(intIdConexao, ThinkGear.DATA_RAW); // Obtém o valor do mindwave
                // Sleep(Tempo de espera em milisegundo)
                Thread.sleep(1000); // Aguarda processar sinal no headset

                if (douSinal > 0) { // Se sinal for maior que 0 é considerado que obteve sucesso ao tentar conectar
                    break; // Sai do loop
                }
            }

            if (douSinal > 0) { // Verifica se sinal do mindwave
                booAtivo = true; // Informa que foi encontrada porta
                strPortaUsada = mstrInversaoCOM.get(intContador2); // Informa a porta que está ativa

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
