/*
 * windowBalder.java
 *
 * Created on 23 mars 2007, 16:11
 *$Id$
 */

package Ihm;
import Main.*;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.JFileChooser;
import java.awt.*;
import Utils.Errors.*;
/**
 *
 * @author  Baldr Team
 */
public class windowBalder extends javax.swing.JFrame {
    int i=0;
    static java.awt.event.ActionEvent event;
    boolean[] listeOnglets=new boolean[Main.MAXONGLET];
    
    aPropos aProposBaldr;
    public windowBalder() {
        /** Look n feel du system*/
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { }
        initComponents();
        
        /* Centrer la fenêtre */
        Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle frame = getBounds();
        setBounds((screen.width - frame.width)/2,(screen.height - frame.height)/2,frame.width,frame.height);
        
        //javax.swing.ImageIcon iconBaldr= new javax.swing.ImageIcon("./ihmbalder/baldr.gif");
        java.awt.Image iconBaldr = java.awt.Toolkit.getDefaultToolkit().getImage("Images/baldr.gif");
        setIconImage(iconBaldr);
        
        for(i=0;i<Main.MAXONGLET;i++) listeOnglets[i]=false;
        
        ajouteOnglet();
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        aProposBaldr = new aPropos(this);
        
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jTabbedPane1 = new javax.swing.JTabbedPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        Preferences = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Baldr");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        fileMenu.setText("Fichier");
        jMenuItem1.setText("Nouvel onglet");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });

        fileMenu.add(jMenuItem1);

        fileMenu.add(jSeparator1);

        openMenuItem.setText("Ouvrir");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });

        fileMenu.add(openMenuItem);

        saveMenuItem.setText("Enregistrer");
        fileMenu.add(saveMenuItem);

        exitMenuItem.setText("Quitter");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });

        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setText("Edition");
        cutMenuItem.setText("Couper");
        editMenu.add(cutMenuItem);

        copyMenuItem.setText("Copier");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setText("Coller");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setText("Supprimer");
        editMenu.add(deleteMenuItem);

        Preferences.setText("Pr\u00e9f\u00e9rences");
        Preferences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Prefev(evt);
            }
        });

        editMenu.add(Preferences);

        menuBar.add(editMenu);

        helpMenu.setText("Aide");
        contentsMenuItem.setText("Rubriques d'aide");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setText("A propos de Baldr ...");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });

        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        aProposBaldr.setLocationRelativeTo(this);
        aProposBaldr.setVisible(true);
    }//GEN-LAST:event_aboutMenuItemActionPerformed
        private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
            ajouteOnglet();
    }//GEN-LAST:event_jMenuItem1ActionPerformed
            private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
                /* Migré dans le panelTab*/
        /*       JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
         
        int res = chooser.showOpenDialog(this);
        switch(res) {
            case JFileChooser.APPROVE_OPTION:
                for(int i=0;i < chooser.getSelectedFiles().length;i++){
                    System.out.println(chooser.getSelectedFiles()[i]);
                }
                break;
            case JFileChooser.CANCEL_OPTION:
                break;
            case JFileChooser.ERROR_OPTION:
                break;
        }*/
    }//GEN-LAST:event_openMenuItemActionPerformed
    private void Prefev(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Prefev
        prefwin test = new prefwin();
        test.setVisible(true);
    }//GEN-LAST:event_Prefev
        private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
            System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed
        public void fermerTab(panelTab pt,int numTab) {
            jTabbedPane1.remove(pt);
            listeOnglets[numTab]=false;
        }
        public void ajouteOnglet(){
            for(i=0;i<Main.MAXONGLET && listeOnglets[i]==true;i++); //détermine le 1er onglet non utilisé
            if (i==Main.MAXONGLET)
                Utils.Errors.Error.tropOnglets();
            else {
                int numeroDuTabLibre=i;
                int numOnglet=i+1;
                listeOnglets[numeroDuTabLibre]=true;
                final panelTab newtab=new panelTab(numeroDuTabLibre);
                jTabbedPane1.addTab("Onglet "+numOnglet ,newtab);
                jTabbedPane1.setSelectedComponent(newtab);
            }
        }
        
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenuItem Preferences;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    // End of variables declaration//GEN-END:variables
    
}
