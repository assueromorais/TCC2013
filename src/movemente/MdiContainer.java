/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package movemente;

import iGeradorComandos.enmTipoComando;
import iGeradorComandos.iGeradorComandosOuvinte;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author ASSUERO
 */
public class MdiContainer extends javax.swing.JFrame implements iGeradorComandosOuvinte {

    /**
     * Creates new form MdiContainer
     */
    public MdiContainer() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.lblMensagemErro.setVisible(false);
        MoveMente.Comandos.AdicionarOuvinte(this);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.pgbNivelPiscada.setValue(0);
    }

    /**
     * Adiciona um frame internamente ao container.
     *
     * @param frame
     */
    public void AdicionarFrame(JInternalFrame frame) {
        desktopPane.add(frame);
        frame.setVisible(true);
        frame.setMaximizable(true);
        frame.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        frame.setMaximizable(false);
        try {
            frame.setMaximum(true);
        } catch (PropertyVetoException e) {
        }
        util.JFrameExtensaoComandos.ConfigurarBordaBotoes(this);
        javax.swing.plaf.InternalFrameUI ifu = frame.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ifu).setNorthPane(null);
        EmptyBorder bordaVazia = new EmptyBorder(1, 1, 1, 1);
        frame.setBorder(bordaVazia);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        lblNivelUltimaPiscada = new javax.swing.JLabel();
        lblMensagemErro = new javax.swing.JLabel();
        btnFechar = new javax.swing.JButton();
        pgbNivelPiscada = new javax.swing.JProgressBar();
        lblIntensidadePiscada = new javax.swing.JLabel();
        mnbMenuSuperior = new javax.swing.JMenuBar();
        menAjuda = new javax.swing.JMenu();
        mniConteudoAjuda = new javax.swing.JMenuItem();
        mniSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(658, 571));
        setPreferredSize(new java.awt.Dimension(658, 571));

        desktopPane.setBackground(new java.awt.Color(255, 255, 255));
        desktopPane.setAutoscrolls(true);
        desktopPane.setMinimumSize(new java.awt.Dimension(658, 452));
        desktopPane.setName(""); // NOI18N

        lblNivelUltimaPiscada.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblNivelUltimaPiscada.setText("Nível da piscada:");

        lblMensagemErro.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblMensagemErro.setForeground(new java.awt.Color(255, 0, 0));
        lblMensagemErro.setText("Falha no MindWave");

        btnFechar.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        pgbNivelPiscada.setMaximum(254);
        pgbNivelPiscada.setToolTipText("");
        pgbNivelPiscada.setValue(110);

        lblIntensidadePiscada.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblIntensidadePiscada.setText("<html><body>&Darr;</body></html>");

        menAjuda.setText("Ajuda");
        menAjuda.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        mniConteudoAjuda.setText("Conteúdo da ajuda");
        mniConteudoAjuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniConteudoAjudaActionPerformed(evt);
            }
        });
        menAjuda.add(mniConteudoAjuda);

        mniSobre.setText("Sobre");
        mniSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniSobreActionPerformed(evt);
            }
        });
        menAjuda.add(mniSobre);

        mnbMenuSuperior.add(menAjuda);

        setJMenuBar(mnbMenuSuperior);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMensagemErro)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNivelUltimaPiscada)
                                .addGap(18, 18, 18)
                                .addComponent(lblIntensidadePiscada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pgbNivelPiscada, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMensagemErro)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNivelUltimaPiscada)
                    .addComponent(lblIntensidadePiscada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pgbNivelPiscada, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFechar))
                .addGap(17, 17, 17))
        );

        getAccessibleContext().setAccessibleName("MoveMente");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        // TODO add your handling code here:
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.exit(1);
    }//GEN-LAST:event_btnFecharActionPerformed

    private void mniConteudoAjudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniConteudoAjudaActionPerformed
        // TODO add your handling code here:
        new FrmAjuda().setVisible(true);
    }//GEN-LAST:event_mniConteudoAjudaActionPerformed

    private void mniSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSobreActionPerformed
        // TODO add your handling code here:
        new FrmSobre().setVisible(true);
    }//GEN-LAST:event_mniSobreActionPerformed

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
            java.util.logging.Logger.getLogger(MdiContainer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MdiContainer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MdiContainer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MdiContainer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MdiContainer().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFechar;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JLabel lblIntensidadePiscada;
    private javax.swing.JLabel lblMensagemErro;
    private javax.swing.JLabel lblNivelUltimaPiscada;
    private javax.swing.JMenu menAjuda;
    private javax.swing.JMenuBar mnbMenuSuperior;
    private javax.swing.JMenuItem mniConteudoAjuda;
    private javax.swing.JMenuItem mniSobre;
    private javax.swing.JProgressBar pgbNivelPiscada;
    // End of variables declaration//GEN-END:variables

    /**
     * Trata os comados recebidos pelo headset.
     *
     * @param Comando
     * @param mensagem
     */
    @Override
    public void ReceberComando(enmTipoComando Comando, String mensagem) {
        switch (Comando) {
            case MudarFoco:
                break;
            case SelecionarItem:
                break;
            case Piscou:
                // Carrega o nível da piscada na progress bar
                pgbNivelPiscada.setValue((int) Double.parseDouble(mensagem));
                lblMensagemErro.setVisible(false);
                break;
            case PiscouForte:
                break;
            case DesconectouCabeca:
                break;
            case BateriaCritica:
                break;
            case PortaDesconectada:
                break;
            case MindWaveNaoEncontrado:
                break;
            case SinalPobre:
                lblMensagemErro.setText("O sinal está fraco, verifique se o headset está corretamente posicionado na cabeça.");
                lblMensagemErro.setVisible(true);
                break;
            default:
                break;
        }
    }

    private void FecharFrame() {
        MoveMente.Comandos.RemoverOuvinte(this);
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
