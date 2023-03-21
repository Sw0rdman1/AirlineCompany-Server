/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.forme;

import java.awt.Color;
import java.awt.Font;
import java.awt.color.ColorSpace;
import java.io.IOException;
import javax.swing.JOptionPane;
import server.kontroler.Kontroler;
import server.niti.ServerskaNit;

/**
 *
 * @author Božidar
 */
public class ServerskaForma extends javax.swing.JFrame {

    Color whiteColor = Color.WHITE;
    Color blueColor = new Color(0, 94, 145);
    Color lightBlueColor = new Color(0, 104, 145);
    Color succesColor = new Color(75, 181, 67);
    Color errorColor = new Color(219, 31, 31);

    private boolean serverStatus = false;
    ServerskaNit serverskaNit;

    /**
     * Creates new form ServerskaForma
     */
    public ServerskaForma() {
        initComponents();
        prepareForm();
        Kontroler.getInstanca().setServerForm(this);
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
        jPanelServisStatus = new javax.swing.JPanel();
        lblStatus = new javax.swing.JLabel();
        lblStatusChanging = new javax.swing.JLabel();
        btnStartStopServer = new javax.swing.JButton();
        jPanelConfiguration2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnDatabase = new javax.swing.JButton();
        btnServer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server");
        setBackground(new java.awt.Color(204, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Server Settings", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 20), new java.awt.Color(0, 104, 145))); // NOI18N

        lblStatus.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblStatus.setText("Server Status:");

        lblStatusChanging.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        lblStatusChanging.setText("Offline");

        javax.swing.GroupLayout jPanelServisStatusLayout = new javax.swing.GroupLayout(jPanelServisStatus);
        jPanelServisStatus.setLayout(jPanelServisStatusLayout);
        jPanelServisStatusLayout.setHorizontalGroup(
            jPanelServisStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelServisStatusLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(lblStatusChanging, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelServisStatusLayout.setVerticalGroup(
            jPanelServisStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelServisStatusLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelServisStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStatus)
                    .addComponent(lblStatusChanging))
                .addContainerGap())
        );

        btnStartStopServer.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        btnStartStopServer.setText("Start server");
        btnStartStopServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartStopServerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelServisStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(btnStartStopServer)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelServisStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnStartStopServer)
                .addGap(401, 401, 401))
        );

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Configuration");

        btnDatabase.setText("Database");
        btnDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatabaseActionPerformed(evt);
            }
        });

        btnServer.setText("Server");
        btnServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelConfiguration2Layout = new javax.swing.GroupLayout(jPanelConfiguration2);
        jPanelConfiguration2.setLayout(jPanelConfiguration2Layout);
        jPanelConfiguration2Layout.setHorizontalGroup(
            jPanelConfiguration2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConfiguration2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(btnDatabase)
                .addGap(18, 18, 18)
                .addComponent(btnServer)
                .addGap(24, 24, 24))
        );
        jPanelConfiguration2Layout.setVerticalGroup(
            jPanelConfiguration2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConfiguration2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanelConfiguration2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnDatabase)
                    .addComponent(btnServer))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelConfiguration2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jPanelConfiguration2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartStopServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartStopServerActionPerformed

        if (serverStatus) {
            stopServer();
        } else {
            startServer();
        }

    }//GEN-LAST:event_btnStartStopServerActionPerformed

    private void btnDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatabaseActionPerformed

        new KonfiguracijaBazeForm().setVisible(true);

    }//GEN-LAST:event_btnDatabaseActionPerformed

    private void btnServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServerActionPerformed

        new KonfiguracijaServeraForm().setVisible(true);
    }//GEN-LAST:event_btnServerActionPerformed

    private void prepareForm() {

        getContentPane().setBackground(whiteColor);
        jPanel1.setBackground(whiteColor);
        jPanelServisStatus.setBackground(errorColor);
        lblStatus.setForeground(whiteColor);
        lblStatusChanging.setForeground(whiteColor);
        jPanelConfiguration2.setBackground(lightBlueColor);

        prepareButton();

    }

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDatabase;
    private javax.swing.JButton btnServer;
    private javax.swing.JButton btnStartStopServer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelConfiguration;
    private javax.swing.JPanel jPanelConfiguration1;
    private javax.swing.JPanel jPanelConfiguration2;
    private javax.swing.JPanel jPanelServisStatus;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblStatusChanging;
    // End of variables declaration//GEN-END:variables

    private void prepareButton() {
        btnStartStopServer.setBackground(whiteColor);
        btnStartStopServer.setForeground(blueColor);
        btnStartStopServer.setFocusPainted(false);
        btnStartStopServer.setFont(new Font("Sans Serif", Font.BOLD, 16));

        btnDatabase.setBackground(whiteColor);
        btnDatabase.setForeground(blueColor);
        btnDatabase.setFocusPainted(false);
        btnDatabase.setFont(new Font("Sans Serif", Font.BOLD, 14));

        btnServer.setBackground(whiteColor);
        btnServer.setForeground(blueColor);
        btnServer.setFocusPainted(false);
        btnServer.setFont(new Font("Sans Serif", Font.BOLD, 14));
    }

    private void startServer() {

        try {
            Kontroler.getInstanca().pokreniServer(this);
            serverStatus = true;
            changeForm();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Neuspesno pokretanje servera", "Greska", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void stopServer() {

        try {
            Kontroler.getInstanca().zaustaviServer(this);
            serverStatus = false;
            changeForm();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Neuspesno zaustavljanje servera", "Greska", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void changeForm() {

        if (serverStatus) {
            btnStartStopServer.setText("Stop server");
            jPanelServisStatus.setBackground(succesColor);
            lblStatusChanging.setText("Online");
        } else {
            btnStartStopServer.setText("Start server");
            jPanelServisStatus.setBackground(errorColor);
            lblStatusChanging.setText("Offline");
        }

    }

}
