/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package movemente;

import iGeradorComandos.enmTipoComando;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author ASSUERO
 */
public class FrmFalhaMindWave extends javax.swing.JFrame {

    /**
     * Atributo que define que o usuário solicitou uma nova tentativa de conexão
     * ao MindWave Deverá ser acessado pela tela que abriu esta.
     *
     * @autor ASSUÉRO
     */
    public boolean TentarNovamente = false;

    /**
     * Creates new form FrmFalhaMindWave
     */
    public FrmFalhaMindWave() {
        initComponents();
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {/* If Nimbus is not available, you can set the GUI to another look and feel.*/

        }
        this.setSize(600, 400);
        // Centraliza o formulário
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        util.JFrameExtensaoComandos.ConfigurarBordaBotoes(this);
    }

    /**
     *
     */
    public void ConfigurarMensagem(enmTipoComando Comando, String strMensagem) {
        switch (Comando) {
            case BateriaCritica:

                break;
            case DesconectouCabeca:

                break;
            case PortaDesconectada:

                break;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEncerrarAplicativo = new javax.swing.JButton();
        btnTentarNovamente = new javax.swing.JButton();
        lblMensagem = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Falha na conexão");
        setBackground(new java.awt.Color(255, 255, 255));
        setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        setResizable(false);

        btnEncerrarAplicativo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnEncerrarAplicativo.setText("Encerrar aplicativo");
        btnEncerrarAplicativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncerrarAplicativoActionPerformed(evt);
            }
        });

        btnTentarNovamente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnTentarNovamente.setText("Tentar novamente");
        btnTentarNovamente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTentarNovamenteActionPerformed(evt);
            }
        });

        lblMensagem.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblMensagem.setText("<html>Verifique se o token USB está conectado ao computador e se o headset está ligado.<html/>");

        lblTitulo.setFont(new java.awt.Font("Arial", 2, 24)); // NOI18N
        lblTitulo.setText("Não foi possível conectar ao headset!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTentarNovamente)
                .addGap(40, 40, 40)
                .addComponent(btnEncerrarAplicativo)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitulo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(lblTitulo)
                .addGap(43, 43, 43)
                .addComponent(lblMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addGap(142, 142, 142)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEncerrarAplicativo)
                    .addComponent(btnTentarNovamente))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEncerrarAplicativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncerrarAplicativoActionPerformed
        // Encerra o aplicativo.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TentarNovamente = false;
        FecharFrame();
    }//GEN-LAST:event_btnEncerrarAplicativoActionPerformed

    private void btnTentarNovamenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTentarNovamenteActionPerformed
        // Fecha o formulário e seta que deve tentar novamente.
        TentarNovamente = true;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        FecharFrame();
    }//GEN-LAST:event_btnTentarNovamenteActionPerformed

    public void setMensagem(String strMensagem ){
        this.lblMensagem.setText(strMensagem);
    }
    private void FecharFrame() {
        this.setVisible(false);
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
            java.util.logging.Logger.getLogger(FrmFalhaMindWave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmFalhaMindWave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmFalhaMindWave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmFalhaMindWave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmFalhaMindWave().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEncerrarAplicativo;
    private javax.swing.JButton btnTentarNovamente;
    private javax.swing.JLabel lblMensagem;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
