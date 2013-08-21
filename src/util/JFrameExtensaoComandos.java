/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.KeyboardFocusManager;

/**
 * Contém métodos úteis para que um formulário do tipo JFrame manipule comandos
 * advindos de um objeto que implemente a interface iGeradorComandos.
 *
 * @author ASSUERO
 */
public class JFrameExtensaoComandos {

    private static JFrame _frame = null;

    /**
     * Chama o método doClick do botão que esteja atualmente com o foco.
     */
    public static void SelecionarBotaoFocado(JFrame frame) {
        _frame = frame;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Component focado = _frame.getFocusOwner();
                if (focado.getClass().getName().toLowerCase().contains("jbutton")) {
                    JButton botaoFoco = (JButton) focado;
                    botaoFoco.doClick();
                }
            }
        });
    }
    
    /**
     * Muda o foco entre os componentes do formulário atualmente ativo.
     * @autor Assuéro Araújo Morais
     */
    public static void MudarFoco() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();}
        });
    }
    
}
