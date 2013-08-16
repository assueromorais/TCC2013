/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iGeradorComandos;

/**
 * Interface que define o funcionamento de uma classe responsável por emitir comandos para formulários.
 * @author ASSUERO
 */
public interface iGeradorComandos {
    
    /**
     * Inicia a função que verifica a geração de comandos.
     * @author ASSUERO
     */
    public boolean Iniciar();

    /**
     * Para a função de geração de comandos.
     * @author ASSUERO
     */
    public void Parar();
    
    /**
     * Estado da bateria do dispositivo I/O.
     * @author ASSUERO
     */
    public double Bateria = 0.0;
    
    /**
     * Estado da bateria do dispositivo I/O.
     * @author ASSUERO
     */
    public boolean ConectadoNaCabeca = false;
    
    /**
     * Retorna verdadeiro se a conexão com o dispositivo de I/O estiver ativa.
     * @author ASSUERO
     */
    public boolean DispositivoConectado = false;
    
    /**
     * Permite adicionar um ouvinte à uma lista interna de ouvintes do objeto
     * @author ASSUERO
     */
    public void AdicionarOuvinte(iGeradorComandosOuvinte ouvinte);
    
    /**
     * Remove um ouvinte da lista interna de ouvintes do objeto
     * @author ASSUERO
     */
    public void RemoverOuvinte(iGeradorComandosOuvinte ouvinte);

}
