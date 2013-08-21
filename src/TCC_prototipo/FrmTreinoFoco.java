/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TCC_prototipo;

import iGeradorComandos.enmTipoComando;
import iGeradorComandos.iGeradorComandosOuvinte;
import java.awt.KeyboardFocusManager;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.UIManager;
import util.JFrameExtensaoComandos;

/**
 *
 * @author ASSUERO
 */
public class FrmTreinoFoco extends javax.swing.JFrame implements iGeradorComandosOuvinte {

    /**
     * Objeto responsável por alterar o foco para o próximo item da tela.
     */
    KeyboardFocusManager _gerenciadorFoco = KeyboardFocusManager.getCurrentKeyboardFocusManager();

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
        btnProsseguir.setVisible(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
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
        jButton1 = new javax.swing.JButton();
        btnFoco3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Treinamento");
        setResizable(false);

        btnFoco1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnFoco1.setText("Foco 1");
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

        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);

        btnFoco3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnFoco3.setText("Foco 3");
        btnFoco3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFoco3ActionPerformed(evt);
            }
        });

        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(btnFoco1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                .addComponent(btnFoco2)
                .addGap(116, 116, 116))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnProsseguir)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnFoco3)
                        .addGap(245, 245, 245))))
            .addComponent(lblExplicativo, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(293, 293, 293)
                    .addComponent(jButton1)
                    .addContainerGap(293, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addGap(29, 29, 29)
                .addComponent(lblExplicativo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFoco1)
                    .addComponent(btnFoco2))
                .addGap(44, 44, 44)
                .addComponent(btnFoco3)
                .addGap(31, 31, 31)
                .addComponent(btnProsseguir)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(224, 224, 224)
                    .addComponent(jButton1)
                    .addContainerGap(75, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFoco1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFoco1ActionPerformed
    }//GEN-LAST:event_btnFoco1ActionPerformed

    private void btnFoco2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFoco2ActionPerformed
    }//GEN-LAST:event_btnFoco2ActionPerformed

    private void btnProsseguirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProsseguirActionPerformed
        new FrmTreinoClique().setVisible(true);
        FecharFrame();
    }//GEN-LAST:event_btnProsseguirActionPerformed

    private void btnFoco3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFoco3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFoco3ActionPerformed

    private void FecharFrame() {
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
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
    private javax.swing.JButton btnProsseguir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel lblExplicativo;
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
}
