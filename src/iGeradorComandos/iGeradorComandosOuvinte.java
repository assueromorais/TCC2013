/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iGeradorComandos;
/**
 * Interface que define as funções necessárias para um objeto que deseja aguardar comandos de um objeto que implemente a interface iComandos.
 * @author ASSUERO
 */
public interface iGeradorComandosOuvinte extends java.util.EventListener {
    
    /**
    * Método pelo qual o comando será enviado de um objeto que implementa iComandos para um objeto que esteja aguardando os comandos.
    * @author ASSUERO
    * @param Comando: Comando que será enviado para os ouvintes.
    * @param mensagem: Mensagem que poderá ser enviada juntamente com o comando.
    */
    public void ReceberComando(enmTipoDeComando Comando, String mensagem);
    
}
