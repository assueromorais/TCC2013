/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package movemente;

import Cronometro.Cronometro;
import Cronometro.CronometroEvento;
import Cronometro.CronometroOuvinte;
import iGeradorComandos.enmTipoComando;
import iGeradorComandos.iGeradorComandosOuvinte;
import java.awt.KeyboardFocusManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.UIManager;
import util.JFrameExtensaoComandos;

/**
 *
 * @author ASSUERO
 */
public class FrmTreinoFoco extends javax.swing.JInternalFrame implements iGeradorComandosOuvinte, FocusListener, CronometroOuvinte {

    /**
     * Objeto responsável por alterar o foco para o próximo item da tela.
     */
    KeyboardFocusManager _gerenciadorFoco = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    /**
     * Data e hora em que o formulário irá iniciar.
     *
     */
    Date _dtInicioProsseguir;
    /**
     * Cronometro responsável por abrir novamente o treino de foco. O cronômetro
     * irá iniciar quando o último botão for exibido.
     */
    private Cronometro _crnEtapaReiniciar = null;

    /**
     * Creates new form FrmTreinoFoco
     */
    public FrmTreinoFoco() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        initComponents();
        btnFoco2.setEnabled(false);
        btnFoco3.setEnabled(false);
        btnFoco1.addFocusListener(this);
        btnFoco2.addFocusListener(this);
        btnFoco3.addFocusListener(this);
        btnProsseguir.addFocusListener(this);
        btnProsseguir.setVisible(false);
        lblReiniciarTreino.setVisible(false);
        MoveMente.Comandos.AdicionarOuvinte(this);
        _crnEtapaReiniciar = new Cronometro(1000);
        _crnEtapaReiniciar.adicionarOuvinte(this);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        util.JFrameExtensaoComandos.ConfigurarBordaBotoes(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnFoco1 = new javax.swing.JButton();
        lblExplicativo = new javax.swing.JLabel();
        btnFoco2 = new javax.swing.JButton();
        btnProsseguir = new javax.swing.JButton();
        btnInicioDoFoco = new javax.swing.JButton();
        btnFoco3 = new javax.swing.JButton();
        lblTituloTreino = new javax.swing.JLabel();
        lblReiniciarTreino = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Treinamento");
        setMaximumSize(new java.awt.Dimension(587, 336));
        setMinimumSize(new java.awt.Dimension(587, 336));

        btnFoco1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnFoco1.setText("Foco 1");
        btnFoco1.setNextFocusableComponent(btnFoco2);
        btnFoco1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFoco1ActionPerformed(evt);
            }
        });

        lblExplicativo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblExplicativo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExplicativo.setText("<html>Para mudar o foco de um botão para outro, <br/>basta piscar o olho com força uma vez em um intervalo de 3 segundos.</html>");

        btnFoco2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnFoco2.setText("Foco 2");
        btnFoco2.setNextFocusableComponent(btnFoco3);
        btnFoco2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFoco2ActionPerformed(evt);
            }
        });

        btnProsseguir.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnProsseguir.setText("Prosseguir");
        btnProsseguir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProsseguirActionPerformed(evt);
            }
        });

        btnInicioDoFoco.setBorder(null);
        btnInicioDoFoco.setBorderPainted(false);
        btnInicioDoFoco.setNextFocusableComponent(btnFoco1);

        btnFoco3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnFoco3.setText("Foco 3");
        btnFoco3.setNextFocusableComponent(btnProsseguir);
        btnFoco3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFoco3ActionPerformed(evt);
            }
        });

        lblTituloTreino.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        lblTituloTreino.setText("ETAPA 1 - FOCANDO NOS BOTÕES");

        lblReiniciarTreino.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblReiniciarTreino.setText("Aguarde 25 segundos para esta etapa ser reiniciada.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblReiniciarTreino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnProsseguir))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnInicioDoFoco, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTituloTreino))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblExplicativo, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnFoco1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFoco2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFoco3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(250, 250, 250))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloTreino)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblExplicativo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInicioDoFoco, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFoco1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFoco2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnFoco3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProsseguir)
                    .addComponent(lblReiniciarTreino))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFoco1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFoco1ActionPerformed
    }//GEN-LAST:event_btnFoco1ActionPerformed

    private void btnFoco2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFoco2ActionPerformed
    }//GEN-LAST:event_btnFoco2ActionPerformed

    private void btnProsseguirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProsseguirActionPerformed
        MoveMente.Container.AdicionarFrame(new FrmTreinoClique());
        FecharFrame();
    }//GEN-LAST:event_btnProsseguirActionPerformed

    private void btnFoco3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFoco3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFoco3ActionPerformed

    private void FecharFrame() {
        _crnEtapaReiniciar.Parar();
        _crnEtapaReiniciar.removerOuvinte(this);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        MoveMente.Comandos.RemoverOuvinte(this);
        this.hide();
        this.dispose();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmTreinoFoco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmTreinoFoco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmTreinoFoco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmTreinoFoco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmTreinoFoco treinar = new FrmTreinoFoco();
                treinar.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFoco1;
    private javax.swing.JButton btnFoco2;
    private javax.swing.JButton btnFoco3;
    private javax.swing.JButton btnInicioDoFoco;
    private javax.swing.JButton btnProsseguir;
    private javax.swing.JLabel lblExplicativo;
    private javax.swing.JLabel lblReiniciarTreino;
    private javax.swing.JLabel lblTituloTreino;
    // End of variables declaration//GEN-END:variables

    /**
     * Altera o foco dos itens da tela, normalmente botões. Muda o foco do item
     * que está atualmente com o foco para o próximo item da direita.
     *
     * @param args the command line arguments
     */
    private void MudarFoco() {
        JFrameExtensaoComandos.MudarFoco();
    }

    @Override
    public void ReceberComando(enmTipoComando Comando, String mensagem) {
        switch (Comando) {
            case MudarFoco:
                MudarFoco();
                break;
            case SelecionarItem:
                JFrameExtensaoComandos.SelecionarBotaoFocado(this);
                break;
            case DesconectouCabeca:
                break;
            case BateriaCritica:
                break;
            case PortaDesconectada:
                break;
        }
    }

    @Override
    public void focusGained(FocusEvent fe) {
        if (fe.getComponent().getClass() != null && fe.getComponent().getClass().getName().contains("JButton")) {
            if (fe.getComponent() == btnFoco1) {
                btnFoco2.setEnabled(true);
                btnInicioDoFoco.setVisible(false);
            } else {
                if (fe.getComponent() == btnFoco2) {
                    btnFoco3.setEnabled(true);
                } else {
                    if (fe.getComponent() == btnFoco3) {
                        btnProsseguir.setVisible(true);
                        this.lblReiniciarTreino.setVisible(true);
                        _crnEtapaReiniciar.Iniciar();
                        _dtInicioProsseguir = new Date();
                    } else {
                        if (btnProsseguir.hasFocus()) {
                            btnProsseguir.doClick();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void focusLost(FocusEvent fe) {
    }

    @Override
    public void IntervaloOcorreu(CronometroEvento evt) {
        double lnDif = util.Data.DiferencaEmSegundos(_dtInicioProsseguir, new Date());
        if (lnDif >= 25) {
            new FrmTreinoFoco().setVisible(true);
            FecharFrame();
        } else {
            lblReiniciarTreino.setText("Em " + (int) (25 - lnDif) + " segundos esta etapa será reiniciada.");
        }
    }
}
