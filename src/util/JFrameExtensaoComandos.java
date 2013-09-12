/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.KeyboardFocusManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JInternalFrame;
import movemente.MoveMente;

/**
 * Contém métodos úteis para que um formulário do tipo JFrame manipule comandos
 * advindos de um objeto que implemente a interface iGeradorComandos.
 *
 * @author ASSUERO
 */
public class JFrameExtensaoComandos {

    private static JFrame _frame = null;
    private static JInternalFrame _iFrame = null;

    /**
     * Chama o método doClick do botão que esteja atualmente com o foco.
     */
    public static void SelecionarBotaoFocado(JFrame frame) {
        _frame = frame;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Component focado = _frame.getFocusOwner();
                if (focado != null && focado.getClass().getName().toLowerCase().contains("jbutton")) {
                    JButton botaoFoco = (JButton) focado;
                    botaoFoco.doClick();
                }
            }
        });
    }

    /**
     * Chama o método doClick do botão que esteja atualmente com o foco.
     */
    public static void SelecionarBotaoFocado(JInternalFrame frame) {
        _iFrame = frame;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                List<JButton> botoes = RetornarBotoesJFrame(_iFrame);
                for (int i = 0; i < botoes.size(); i++) {
                    if (botoes.get(i).hasFocus()) {
                        botoes.get(i).doClick();
                    }
                }
            }
        });
    }

    /**
     * Muda o foco entre os componentes do formulário atualmente ativo.
     *
     * @autor Assuéro Araújo Morais
     */
    public static void MudarFoco() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
            }
        });
    }

    /**
     * Configura a borda de todos os botões do formulário.
     *
     * @param frame
     */
    public static void ConfigurarBordaBotoes(JFrame frame) {
        // Percorre todo o formulário buscando pelos botões disponíveis
        List<JButton> botoes = RetornarBotoesJFrame(frame.getContentPane());
        for (int i = 0; i < botoes.size(); i++) {
            ConfigurarDimensaoMinima(botoes.get(i));
            ConfigurarBordaBotao(botoes.get(i));
        }
    }

    /**
     * Configura a borda de todos os botões do formulário.
     *
     * @param frame
     */
    public static void ConfigurarBordaBotoes(JInternalFrame frame) {
        // Percorre todo o formulário buscando pelos botões disponíveis
        List<JButton> botoes = RetornarBotoesJFrame(frame.getContentPane());
        for (int i = 0; i < botoes.size(); i++) {
            ConfigurarDimensaoMinima(botoes.get(i));
            ConfigurarBordaBotao(botoes.get(i));
        }
    }

    /**
     * Configura o botão para que, quando ele receber o foco fique com uma
     * borda, e quando perder o foco fique sem borda.
     *
     * @param botao
     */
    private static void ConfigurarBordaBotao(JButton botao) {
        botao.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                JButton botao = (JButton) fe.getComponent();
                botao.setBorder(MoveMente.bordaBotaoFocado);
            }

            @Override
            public void focusLost(FocusEvent fe) {
                JButton botao = (JButton) fe.getComponent();
                botao.setBorder(MoveMente.bordaBotaoPadrao);
            }
        });
    }

    /**
     * Retorna todos os botões de um formulário, ou container.
     *
     * @param c
     * @return
     */
    public static List<JButton> RetornarBotoesJFrame(Container container) {
        Component[] componentes = container.getComponents();
        List<JButton> compList = new ArrayList<>();
        // Percorre os componentes do container 
        for (Component comp : componentes) {
            if (comp.getClass().getName() != null && comp.getClass().getName().toLowerCase().contains("jbutton")) {
                // Se o componente for um JButton, ele será acrescentado na lista de retorno
                compList.add((JButton) comp);
            }
            if (comp instanceof Container) {
                // Se for um container, deverá percorrer sua lista de componentes também, pois, ele pode possuir botões internamente
                compList.addAll(RetornarBotoesJFrame((Container) comp));
            }
        }
        return compList;

    }

    private static void ConfigurarDimensaoMinima(JButton botao) {
        if (botao.getMinimumSize().height > 1 && botao.getMinimumSize().width > 1) {
            botao.setMinimumSize(new Dimension(75, 25));
        }
    }
}
