/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Contém métodos úteis para que um formulário do tipo JFrame
 * manipule comandos advindos de um objeto que implemente a interface iGeradorComandos.
 * 
 * @author ASSUERO
 */
public class JFrameExtensaoComandos {
   /**
    * Chama o método doClick do botão que esteja atualmente com o foco.
    */ 
    public static void SelecionarBotaoFocado(JFrame frame) {
        Component focado = frame.getFocusOwner();
        if (focado.getClass().getName().compareToIgnoreCase("JButton") == 1) {
            JButton botaoFoco = (JButton) focado;
            botaoFoco.doClick();
        }
    }
}
