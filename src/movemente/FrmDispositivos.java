/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package movemente;

import Cronometro.Cronometro;
import Cronometro.CronometroEvento;
import Cronometro.CronometroOuvinte;
import iGeradorComandos.iGeradorComandosOuvinte;
import java.awt.KeyboardFocusManager;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import util.JFrameExtensaoComandos;

/**
 *
 * @author ASSUERO
 */
public class FrmDispositivos extends javax.swing.JInternalFrame implements iGeradorComandosOuvinte, CronometroOuvinte {
// Variáveis que mantém o estado dos dispositivos

    private static boolean booLampadaLigada = false;
    private static boolean booCampainhaLigada = false;
    private static boolean booMotorLigado = false;
    /**
     * Objeto responsável por alterar o foco para o próximo item da tela.
     */
    KeyboardFocusManager _gerenciadorFoco = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    /**
     * Cronometro utilizado para voltar os botões ao estado inicial após o tempo
     * de execução das funções.
     */
    private Cronometro crnBotao = null;

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
            public void run() {
                FrmDispositivos dispositivos = new FrmDispositivos();
                dispositivos.setVisible(true);
            }
        });
    }

    /**
     * Creates new form FrmDispositivos
     */
    public FrmDispositivos() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {/* If Nimbus is not available, you can set the GUI to another look and feel.*/

        }
        initComponents();
        // Ao receber um comando irá processá-lo
        MoveMente.Comandos.AdicionarOuvinte(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        util.JFrameExtensaoComandos.ConfigurarBordaBotoes(this);
        crnBotao = new Cronometro(2000);
        crnBotao.adicionarOuvinte(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnLampada = new javax.swing.JButton();
        btnLeito = new javax.swing.JButton();
        btnLeitoSubir = new javax.swing.JButton();
        btnLeitoDescer = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnSim = new javax.swing.JButton();
        btnFome = new javax.swing.JButton();
        btnDor = new javax.swing.JButton();
        btnBanheiro = new javax.swing.JButton();
        btnSede = new javax.swing.JButton();
        btnNao = new javax.swing.JButton();
        btnFala = new javax.swing.JButton();
        btnCampainha = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setForeground(new java.awt.Color(255, 255, 255));
        setTitle("Controle de dispositivos");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(null);
        setPreferredSize(new java.awt.Dimension(1300, 651));

        btnLampada.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        btnLampada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/movemente/Imagens/lampada_desl128.png"))); // NOI18N
        btnLampada.setText("Lâmpada");
        btnLampada.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLampada.setMaximumSize(new java.awt.Dimension(170, 88));
        btnLampada.setMinimumSize(new java.awt.Dimension(170, 88));
        btnLampada.setName(""); // NOI18N
        btnLampada.setNextFocusableComponent(btnCampainha);
        btnLampada.setPreferredSize(new java.awt.Dimension(170, 88));
        btnLampada.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLampada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLampadaActionPerformed(evt);
            }
        });

        btnLeito.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        btnLeito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/movemente/Imagens/leito_desl128.png"))); // NOI18N
        btnLeito.setText("Leito");
        btnLeito.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLeito.setMaximumSize(new java.awt.Dimension(170, 88));
        btnLeito.setMinimumSize(new java.awt.Dimension(170, 88));
        btnLeito.setNextFocusableComponent(btnFala);
        btnLeito.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLeito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeitoActionPerformed(evt);
            }
        });

        btnLeitoSubir.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnLeitoSubir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/movemente/Imagens/subir_des.png"))); // NOI18N
        btnLeitoSubir.setText("Subir");
        btnLeitoSubir.setEnabled(false);
        btnLeitoSubir.setMaximumSize(new java.awt.Dimension(170, 88));
        btnLeitoSubir.setMinimumSize(new java.awt.Dimension(170, 88));
        btnLeitoSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeitoSubirActionPerformed(evt);
            }
        });

        btnLeitoDescer.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnLeitoDescer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/movemente/Imagens/descer_des.png"))); // NOI18N
        btnLeitoDescer.setText("Descer");
        btnLeitoDescer.setEnabled(false);
        btnLeitoDescer.setMaximumSize(new java.awt.Dimension(170, 88));
        btnLeitoDescer.setMinimumSize(new java.awt.Dimension(170, 88));
        btnLeitoDescer.setNextFocusableComponent(btnLeito);
        btnLeitoDescer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeitoDescerActionPerformed(evt);
            }
        });

        btnSim.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnSim.setText("Sim");
        btnSim.setEnabled(false);
        btnSim.setMaximumSize(new java.awt.Dimension(170, 88));
        btnSim.setMinimumSize(new java.awt.Dimension(170, 88));
        btnSim.setNextFocusableComponent(btnNao);
        btnSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimActionPerformed(evt);
            }
        });

        btnFome.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnFome.setText("Fome");
        btnFome.setEnabled(false);
        btnFome.setMaximumSize(new java.awt.Dimension(170, 88));
        btnFome.setMinimumSize(new java.awt.Dimension(170, 88));
        btnFome.setNextFocusableComponent(btnSede);
        btnFome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFomeActionPerformed(evt);
            }
        });

        btnDor.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnDor.setText("Dor");
        btnDor.setEnabled(false);
        btnDor.setMaximumSize(new java.awt.Dimension(170, 88));
        btnDor.setMinimumSize(new java.awt.Dimension(170, 88));
        btnDor.setNextFocusableComponent(btnBanheiro);
        btnDor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDorActionPerformed(evt);
            }
        });

        btnBanheiro.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnBanheiro.setText("Banheiro");
        btnBanheiro.setEnabled(false);
        btnBanheiro.setMaximumSize(new java.awt.Dimension(170, 88));
        btnBanheiro.setMinimumSize(new java.awt.Dimension(170, 88));
        btnBanheiro.setNextFocusableComponent(btnFala);
        btnBanheiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanheiroActionPerformed(evt);
            }
        });

        btnSede.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnSede.setText("Sede");
        btnSede.setEnabled(false);
        btnSede.setMaximumSize(new java.awt.Dimension(170, 88));
        btnSede.setMinimumSize(new java.awt.Dimension(170, 88));
        btnSede.setNextFocusableComponent(btnDor);
        btnSede.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSedeActionPerformed(evt);
            }
        });

        btnNao.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnNao.setText("Não");
        btnNao.setEnabled(false);
        btnNao.setMaximumSize(new java.awt.Dimension(170, 88));
        btnNao.setMinimumSize(new java.awt.Dimension(170, 88));
        btnNao.setNextFocusableComponent(btnFome);
        btnNao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNaoActionPerformed(evt);
            }
        });

        btnFala.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        btnFala.setIcon(new javax.swing.ImageIcon(getClass().getResource("/movemente/Imagens/fala_desl128.png"))); // NOI18N
        btnFala.setText("Fala");
        btnFala.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFala.setMaximumSize(new java.awt.Dimension(170, 88));
        btnFala.setMinimumSize(new java.awt.Dimension(170, 88));
        btnFala.setNextFocusableComponent(btnLampada);
        btnFala.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFalaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnNao, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnSim, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnSede, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnBanheiro, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnFome, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnDor, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(btnFala, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnFala, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSim, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFome, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDor, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSede, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNao, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBanheiro, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnCampainha.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        btnCampainha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/movemente/Imagens/campainha_desl.png"))); // NOI18N
        btnCampainha.setText("Campainha");
        btnCampainha.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCampainha.setMaximumSize(new java.awt.Dimension(170, 88));
        btnCampainha.setMinimumSize(new java.awt.Dimension(170, 88));
        btnCampainha.setNextFocusableComponent(btnLeito);
        btnCampainha.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCampainha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCampainhaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLampada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCampainha, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(btnLeito, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLeitoDescer, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLeitoSubir, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLeito, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLampada, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnLeitoSubir, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLeitoDescer, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnCampainha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(282, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLampadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLampadaActionPerformed
        // TODO add your handling code here:
        booLampadaLigada = !booLampadaLigada;
        if (booLampadaLigada) {
            EnviarComando("L");
            System.out.println("lampada:ligar");
        } else {
            EnviarComando("D");
            System.out.println("lampada:desligar");
        }
        btnLampada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/movemente/Imagens/lampada_" + ((booLampadaLigada) ? "lig" : "desl") + "128.png")));
    }//GEN-LAST:event_btnLampadaActionPerformed

    private void btnCampainhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCampainhaActionPerformed
        // TODO add your handling code here:
        booCampainhaLigada = !booCampainhaLigada;
        if (booCampainhaLigada) {
            EnviarComando("S");
            System.out.println("campainha:ligar");
        } else {
            EnviarComando("S");
            System.out.println("campainha:desligar");
        }
        btnCampainha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/movemente/Imagens/campainha_" + ((booCampainhaLigada) ? "lig" : "desl") + ".png")));
        crnBotao.Iniciar();
    }//GEN-LAST:event_btnCampainhaActionPerformed

    private void btnLeitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeitoActionPerformed
        // TODO add your handling code here:
        btnLeitoDescer.setEnabled(!btnLeitoDescer.isEnabled());
        btnLeitoSubir.setEnabled(!btnLeitoSubir.isEnabled());
        if (!btnLeitoSubir.isEnabled()) {
            btnLeito.requestFocus();
            btnLeito.setNextFocusableComponent(btnFala);
        } else {
            btnLeito.setNextFocusableComponent(btnLeitoSubir);
        }
        btnLeito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/movemente/Imagens/leito_" + ((btnLeitoSubir.isEnabled()) ? "lig" : "desl") + "128.png")));
    }//GEN-LAST:event_btnLeitoActionPerformed

    private void btnLeitoDescerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeitoDescerActionPerformed
        // TODO add your handling code here:
        booMotorLigado = !booMotorLigado;
        btnLeitoDescer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/movemente/Imagens/descer.png")));
        if (booMotorLigado) {
            //EnviarComando("motor:desligar");
            EnviarComando("A");
            System.out.println("motor:desligar");
        } else {
            //EnviarComando("motor:ligar");
            EnviarComando("P");
            System.out.println("motor:ligar");
        }
    }//GEN-LAST:event_btnLeitoDescerActionPerformed

    private void btnLeitoSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeitoSubirActionPerformed
        // TODO add your handling code here:
        booMotorLigado = !booMotorLigado;
        btnLeitoSubir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/movemente/Imagens/subir.png")));
        if (booMotorLigado) {
            //EnviarComando("motor:desligar");
            EnviarComando("A");
            System.out.println("motor:desligar");
        } else {
            //EnviarComando("motor:ligar");
            EnviarComando("P");
            System.out.println("motor:ligar");
        }

    }//GEN-LAST:event_btnLeitoSubirActionPerformed

    private void btnFalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFalaActionPerformed
        // TODO add your handling code here:
        btnNao.setEnabled(!btnSim.isEnabled());
        if (btnNao.isEnabled()) {
            btnFala.setNextFocusableComponent(btnSim);
        } else {
            btnFala.setNextFocusableComponent(btnLampada);
        }
        btnFome.setEnabled(!btnSim.isEnabled());
        btnDor.setEnabled(!btnSim.isEnabled());
        btnSede.setEnabled(!btnSim.isEnabled());
        btnBanheiro.setEnabled(!btnSim.isEnabled());
        btnSim.setEnabled(!btnSim.isEnabled());
        btnFala.setIcon(new javax.swing.ImageIcon(getClass().getResource("/movemente/Imagens/fala_" + ((btnSim.isEnabled()) ? "lig" : "desl") + "128.png")));
    }//GEN-LAST:event_btnFalaActionPerformed

    private void btnFomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFomeActionPerformed
        // TODO add your handling code here:
        controladorFala.ControladorFala.Fome();
    }//GEN-LAST:event_btnFomeActionPerformed

    private void btnSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimActionPerformed
        // TODO add your handling code here:
        controladorFala.ControladorFala.Sim();
    }//GEN-LAST:event_btnSimActionPerformed

    private void btnDorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDorActionPerformed
        // TODO add your handling code here
        controladorFala.ControladorFala.SentindoDor();
    }//GEN-LAST:event_btnDorActionPerformed

    private void btnSedeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSedeActionPerformed
        // TODO add your handling code here:
        controladorFala.ControladorFala.Sede();
    }//GEN-LAST:event_btnSedeActionPerformed

    private void btnNaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNaoActionPerformed
        // TODO add your handling code here:
        controladorFala.ControladorFala.Nao();
    }//GEN-LAST:event_btnNaoActionPerformed

    private void btnBanheiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanheiroActionPerformed
        // TODO add your handling code here:
        controladorFala.ControladorFala.Banheiro();
    }//GEN-LAST:event_btnBanheiroActionPerformed

    private void FecharFrame() {
        MoveMente.Comandos.RemoverOuvinte(this);
        this.hide();
        this.dispose();
    }

    /**
     * Altera o foco dos itens da tela, normalmente botões. Muda o foco do item
     * que está atualmente com o foco para o próximo item da direita.
     *
     * @param args the command line arguments
     */
    private void MudarFoco() {
        JFrameExtensaoComandos.MudarFoco();
    }

    private void EnviarComando(String strComando) {
        MoveMente.Controlador.EnviarComando(strComando);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBanheiro;
    private javax.swing.JButton btnCampainha;
    private javax.swing.JButton btnDor;
    private javax.swing.JButton btnFala;
    private javax.swing.JButton btnFome;
    private javax.swing.JButton btnLampada;
    private javax.swing.JButton btnLeito;
    private javax.swing.JButton btnLeitoDescer;
    private javax.swing.JButton btnLeitoSubir;
    private javax.swing.JButton btnNao;
    private javax.swing.JButton btnSede;
    private javax.swing.JButton btnSim;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void ReceberComando(iGeradorComandos.enmTipoComando tipoComando, String mensagem) {
        switch (tipoComando) {
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
    public void IntervaloOcorreu(CronometroEvento evt) {
        booCampainhaLigada = !booCampainhaLigada;
        btnCampainha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/movemente/Imagens/campainha_desl.png")));
        crnBotao.Parar();
    }
}
