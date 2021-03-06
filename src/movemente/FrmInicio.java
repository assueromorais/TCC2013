/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package movemente;

import Cronometro.Cronometro;
import Cronometro.CronometroOuvinte;
import Cronometro.CronometroEvento;
import iGeradorComandos.enmTipoComando;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UIManager;
import util.JFrameExtensaoComandos;

/**
 * Primeiro formulário exibido pelo sistema.
 *
 * @author ASSUERO
 */
public class FrmInicio extends javax.swing.JInternalFrame implements iGeradorComandos.iGeradorComandosOuvinte, CronometroOuvinte {

    /**
     * Objeto responsável por alterar o foco para o próximo item da tela.
     */
    KeyboardFocusManager _gerenciadorFoco = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    /**
     * Cronometro responsável por exibir a janela de treinamento. Será atribuído
     * um intervalo em que o usuário deverá realizar alguma ação na tela caso
     * contrário a janela de treinamento será exibida
     */
    private Cronometro _crnTreinoIniciar = null;
    private Date _dtInicioFormulario = null;

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
            java.util.logging.Logger.getLogger(FrmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FrmInicio().setVisible(true);
            }
        });
    }

    /**
     * Creates new form FrmInicio
     */
    public FrmInicio() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        initComponents();
        MoveMente.Comandos.AdicionarOuvinte(this);
        _dtInicioFormulario = new Date();
        //Se passar 30 segundos sem que o usuário faça nada, o sistema irá selecionar automaticamente o formulário de treino.
        _crnTreinoIniciar = new Cronometro(1000);
        _crnTreinoIniciar.adicionarOuvinte(this);
        _crnTreinoIniciar.Iniciar();

    }

    @Override
    public void IntervaloOcorreu(CronometroEvento evt) {
        double lnDif = util.Data.DiferencaEmSegundos(_dtInicioFormulario, new Date());
        if (lnDif >= 30) {
            btnTreinar.doClick();
            _crnTreinoIniciar.Parar();
        } else {
            lblMsgTempoTreino.setText("Em " + (int) (30 - lnDif) + " segundos o treino será aberto automaticamente.");
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

        lblMsgTempoTreino = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnIniciar = new javax.swing.JButton();
        btnTreinar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 0, 1));
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela inicial");
        setVisible(true);

        lblMsgTempoTreino.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        lblMsgTempoTreino.setText("Em 30 segundos o treino será aberto automaticamente.");

        btnIniciar.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        btnIniciar.setText("Iniciar");
        btnIniciar.setToolTipText("Abre a janela com os dispositivos.");
        btnIniciar.setDoubleBuffered(true);
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        btnTreinar.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        btnTreinar.setText("Treinar");
        btnTreinar.setToolTipText("Abre a área para treinamento.");
        btnTreinar.setDoubleBuffered(true);
        btnTreinar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTreinarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
                .addComponent(btnTreinar, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTreinar, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMsgTempoTreino)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146)
                .addComponent(lblMsgTempoTreino)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        // TODO add your handling code here:
        this._crnTreinoIniciar.Parar();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        MoveMente.Container.AdicionarFrame(new FrmDispositivos());
        FecharFrame();
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnTreinarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTreinarActionPerformed
        // TODO add your handling code here:
        this._crnTreinoIniciar.Parar();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        MoveMente.Container.AdicionarFrame(new FrmTreino());
        FecharFrame();
    }//GEN-LAST:event_btnTreinarActionPerformed

    private void FecharFrame() {
        MoveMente.Comandos.RemoverOuvinte(this);
        this.hide();
        this.dispose();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnTreinar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblMsgTempoTreino;
    // End of variables declaration//GEN-END:variables

    private void MudarFoco() {
        _crnTreinoIniciar.Parar();
        JFrameExtensaoComandos.MudarFoco();
    }

    @Override
    public void ReceberComando(enmTipoComando Comando, String mensagem) {
        switch (Comando) {
            case MudarFoco:
                MudarFoco();
                break;
            case SelecionarItem:
                Component teste = this.getFocusOwner();
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
