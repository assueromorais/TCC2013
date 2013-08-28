package iControladorDispositivos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASSUERO
 */
public interface iControladorDispositivos {
    /**
     * Rotina responsável por enviar o comando para o controlador de dispositivos.
     * @param strComando 
     */
    public void EnviarComando(String strComando);
    
    /**
     * Verifica se o controlador de dispositivos foi encontrado.
     * 
     */
    public boolean ControladorConectado();
            
}
