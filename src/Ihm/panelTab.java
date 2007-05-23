/*
 * panelTab.java
 *
 * Created on 30 mars 2007, 19:03
 *$Id$
 */

package Ihm;

import Ihm.renderers.*;
import java.awt.Color;
import java.io.File;
import java.math.MathContext;
import java.util.Arrays;
import java.util.Enumeration;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.*;
import Main.*;
import Noyau.*;
import javax.swing.*;
import javax.swing.table.*;
import org.math.plot.Plot3DPanel;
import org.math.plot.plotObjects.Plotable;
import org.math.plot.plots.Plot;
import org.w3c.dom.*;
/**
 * @author  Baldr Team
 */
public class panelTab extends javax.swing.JPanel implements ResDispatcher,Savable{
    private int monNumero;
    private int curview;
    private boolean slabels;
    private DefaultMutableTreeNode fileList;
    private Task analys=null;
    private double[][] vectorsd;
            
    /** Creates new form panelTab */
    public panelTab(int monNum) {
        fileList = new DefaultMutableTreeNode("Documents");
        initComponents();
        monNumero=monNum;
        //jButton10.setVisible(false);
        jButton11.setVisible(false);
        jComboBox1.setSelectedIndex(0);
        jLabel3.setText("sur "+Integer.toString(jComboBox1.getItemCount()));
        // TODO: Comprendre pourquoi les getWidth retournent 0 ici
        //jSplitPane2.setDividerLocation(this.getPreferredSize().width*2/3);
        plot2DPanel1.plotToolBar.remove(3); // On dégage les entrées du menu de la toolbar qui servent à rien
        plot2DPanel1.plotToolBar.remove(4);
        
        plot3DPanel1.plotToolBar.remove(4); // On dégage les entrées du menu de la toolbar qui servent à rien
        plot3DPanel1.plotToolBar.remove(5);
        
        plot2DPanel1.setVisible(true);
        plot3DPanel1.setVisible(false);
        
        jSplitPane3.setDividerSize(0);
        unset3Dview();
    }
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        menuContextuel = new javax.swing.JPopupMenu();
        NouveauDossier = new javax.swing.JMenuItem();
        ajouter = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        supprimer = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        lancer = new javax.swing.JMenuItem();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 =  new javax.swing.JTree(fileList);
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jProgressBar1 = new JProgressBar (0,10000);
        jButton8 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        jSplitPane3 = new javax.swing.JSplitPane();
        plot3DPanel1 = new org.math.plot.Plot3DPanel();
        plot2DPanel1 = new org.math.plot.Plot2DPanel();
        jLabel5 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel4.setVisible(false);
        jPanel5 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jReport = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        NouveauDossier.setText("Nouveau dossier");
        NouveauDossier.setActionCommand("Nouveau_dossier");
        NouveauDossier.setName("Ajouter");
        NouveauDossier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NouveauDossierActionPerformed(evt);
            }
        });

        menuContextuel.add(NouveauDossier);

        ajouter.setText("Ajouter");
        ajouter.setName("Ajouter");
        ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajouterActionPerformed(evt);
            }
        });

        menuContextuel.add(ajouter);

        menuContextuel.add(jSeparator2);

        supprimer.setText("Supprimer");
        supprimer.setName("Supprimer");
        supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprimerActionPerformed(evt);
            }
        });

        menuContextuel.add(supprimer);

        menuContextuel.add(jSeparator1);

        lancer.setText("Lancer l'analyse");
        lancer.setName("Lancer l'analyse");
        lancer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lancerActionPerformed(evt);
            }
        });

        menuContextuel.add(lancer);

        jSplitPane1.setBorder(null);
        jSplitPane1.setDividerSize(7);
        jSplitPane1.setToolTipText("");
        jSplitPane1.setAutoscrolls(true);
        jSplitPane1.setOneTouchExpandable(true);
        jTree1.setCellRenderer(new TreeCellCustomRenderer());
        jTree1.setModel(new DefaultTreeModel(fileList));
        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree1MouseClicked(evt);
            }
        });

        jScrollPane1.setViewportView(jTree1);

        jLabel1.setText("Ajout de fichiers :");

        jButton1.setIcon(new javax.swing.ImageIcon("Images\\add.png"));
        jButton1.setToolTipText("Ajouter un fichier");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon("Images\\delete.png"));
        jButton2.setToolTipText("Supprime le fichier s\u00e9lectionn\u00e9");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon("Images\\arrow_refresh.png"));
        jButton3.setToolTipText("Lancer l'analyse");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jProgressBar1.setRequestFocusEnabled(false);
        jProgressBar1.setString("43%");

        jButton8.setIcon(new javax.swing.ImageIcon("Images\\tab_delete.png"));
        jButton8.setToolTipText("Fermer l'analyse courante");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel8.setText("Progression de l'analyse :");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(28, 28, 28)
                        .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(jLabel8)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 52, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jProgressBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton1)
                    .add(jButton2)
                    .add(jButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 19, Short.MAX_VALUE)
                .add(jLabel8)
                .add(5, 5, 5)
                .add(jProgressBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(22, 22, 22))
        );

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .add(jLabel1)
                .add(99, 99, 99))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
        );
        jSplitPane1.setLeftComponent(jPanel1);

        jSplitPane2.setDividerSize(7);
        jSplitPane2.setOneTouchExpandable(true);
        jSplitPane3.setLeftComponent(plot3DPanel1);

        jSplitPane3.setRightComponent(plot2DPanel1);

        jLabel5.setText("R\u00e9sultats graphiques :");

        jButton11.setText("Vue 3D");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("Afficher \u00e9tiquettes");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(126, 126, 126)
                        .add(jButton11)
                        .add(19, 19, 19)
                        .add(jButton12))
                    .add(jLabel5)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jSplitPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jLabel5)
                .add(12, 12, 12)
                .add(jSplitPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 470, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 24, Short.MAX_VALUE)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton11)
                    .add(jButton12))
                .addContainerGap())
        );
        jSplitPane2.setLeftComponent(jPanel3);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jButton6.setText("<<");
        jButton6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jPanel5.add(jButton6, new java.awt.GridBagConstraints());

        jButton7.setText("<");
        jButton7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jPanel5.add(jButton7, new java.awt.GridBagConstraints());

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setRenderer(new ComboCellCustomRenderer());
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jComboBox1.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jComboBox1MouseWheelMoved(evt);
            }
        });

        jPanel5.add(jComboBox1, new java.awt.GridBagConstraints());

        jLabel3.setText("sur N");
        jPanel5.add(jLabel3, new java.awt.GridBagConstraints());

        jButton5.setText(">");
        jButton5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel5.add(jButton5, new java.awt.GridBagConstraints());

        jButton4.setText(">>");
        jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel5.add(jButton4, new java.awt.GridBagConstraints());

        jLabel6.setText("Notes :");

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane2.setAutoscrolls(true);
        jReport.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jReport.setAutoscrolls(false);
        jScrollPane2.setViewportView(jReport);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable1);

        jLabel4.setText("R\u00e9sultats :");

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                    .add(jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel4Layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(jLabel6)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 148, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(jLabel6)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(75, 75, 75))
        );
        jSplitPane2.setRightComponent(jPanel4);

        jSplitPane1.setRightComponent(jSplitPane2);

        jLabel2.setText("Pr\u00eat");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Statut : ");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel7)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 965, Short.MAX_VALUE))
                    .add(jSplitPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1021, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jSplitPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7)
                    .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void NouveauDossierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NouveauDossierActionPerformed
        DefaultMutableTreeNode nouveauRep = new DefaultMutableTreeNode(new File("Nouveau_Dossier"),true);
        DefaultMutableTreeNode nouveauRep2 = new DefaultMutableTreeNode(new File(""));
        //jTree1.getSelectionPath().getParentPath()
        nouveauRep.add(nouveauRep2);
        DefaultMutableTreeNode lro;
        TreePath ins=jTree1.getSelectionPath(); /*premier fichier selectionn?*/
        
        lro=fileList; /*par def racine*/
        
        if(ins!=null)  /*permet de recuperer le noeud selectionné */
        {
            
            Enumeration files = fileList.breadthFirstEnumeration(); /*Tout l'arbre en largeur*/
            DefaultMutableTreeNode fich;
            while (files.hasMoreElements()) {
                fich=(DefaultMutableTreeNode)files.nextElement();
                if(ins.equals(new TreePath(fich.getPath())) ) { /*Si le selectionn? == le noeud */
                    lro=fich; /*on ajoute l? */
                    break;
                }
            }
        }
    
 //TODO : a modifier pour créer un repertoire vide et pas remplacer le fichier selectionné        
        lro.add(nouveauRep);
        jTree1.updateUI();

    }//GEN-LAST:event_NouveauDossierActionPerformed

    private void jComboBox1MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jComboBox1MouseWheelMoved
// TODO add your handling code here:
        if (evt.getWheelRotation() > 0) {
            itemSuivant();
        }
        else
        {
             itemPrecedent();
        }
    }//GEN-LAST:event_jComboBox1MouseWheelMoved

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        if(slabels){
            hidePlotlabels();
        }else{
            showPlotlabels();
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void lancerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lancerActionPerformed
    jButton3.doClick();
    }//GEN-LAST:event_lancerActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
           String path = jComboBox1.getSelectedItem().toString();
           String name ="";
           if(path.lastIndexOf(File.separator)!=-1){
               name = path.substring(path.lastIndexOf(File.separator)+1);
           }
           plot3Dhlpt(name);
        //   jReport.setText("Fichier "+name);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if(curview == 0){
            set3Dview();
        }else{
            unset3Dview();
        }
    }//GEN-LAST:event_jButton11ActionPerformed
    
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        itemDebut();
    }//GEN-LAST:event_jButton6ActionPerformed
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        itemFin();
    }//GEN-LAST:event_jButton4ActionPerformed
    
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        itemPrecedent();
    }//GEN-LAST:event_jButton7ActionPerformed
    
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        itemSuivant();
    }//GEN-LAST:event_jButton5ActionPerformed
            
    private void supprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprimerActionPerformed
        retirerFichiers();
    }//GEN-LAST:event_supprimerActionPerformed
    
    private void ajouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouterActionPerformed
        ajouterFichiers();
    }//GEN-LAST:event_ajouterActionPerformed
    
    
    private void jTree1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseClicked
        if (evt.getButton()==java.awt.event.MouseEvent.BUTTON2||evt.getButton()==java.awt.event.MouseEvent.BUTTON3) {
            TreePath selPath = jTree1.getPathForLocation(evt.getX(), evt.getY());
            jTree1.setSelectionPath(selPath);
            menuContextuel.show(evt.getComponent(),evt.getX(), evt.getY());
            
        }
    }//GEN-LAST:event_jTree1MouseClicked
    
    public void set3Dview(){
        jButton11.setText("Vue 2D");
        plot2DPanel1.setVisible(false);
        plot3DPanel1.setVisible(true);
        jButton12.setVisible(true);
        curview = 1;
    }
    
    public void unset3Dview(){
        jButton11.setText("Vue 3D");
        plot2DPanel1.setVisible(true);
        plot3DPanel1.setVisible(false);
        jButton12.setVisible(false);
        curview = 0;
    }
    
    private DefaultMutableTreeNode recursDir(File fich){
        //System.out.println(chooser.getSelectedFiles()[i]);
        DefaultMutableTreeNode el= new DefaultMutableTreeNode(fich);
        if(fich.isDirectory()){ /*Si c'est un dossier*/
            for(File ch : fich.listFiles()) {
                if(ch.isDirectory()){/*Ajoute tous les fils*/
                    el.add(recursDir(ch));
                    
                }else{
                    el.add(new DefaultMutableTreeNode(ch));
                }
            }
        }
        
        return el;
    }
    
    private File[] getFileTab() {
        Enumeration files = fileList.depthFirstEnumeration();
        int count=fileList.getLeafCount(); /* Les fichiers ? analyser sont forc?ment des feuilles*/
        // files = fileList.depthFirstEnumeration();
        int i=0;
        DefaultMutableTreeNode fich;
        if(!fileList.isLeaf()){
            File[] fichiers = new File[count];
            
            while (files.hasMoreElements()) {
                fich=(DefaultMutableTreeNode)files.nextElement();
                if(fich.isLeaf()) {
                    fichiers[i++]=(File)fich.getUserObject();
                    
                }
            }
            return fichiers;
            
        }
        return null;
    }
    
    private void ajouterFichiers() {
        JFileChooser chooser = new JFileChooser(); /*boite de dialogue fichiers*/
        DefaultMutableTreeNode lro; /*noeud ou on va add*/
        chooser.setMultiSelectionEnabled(true); /* rend un tab de files */
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); /*Soit des files soit des dirs */
        
        String lastdir = Noyau.opts.readPref("LAST_DIR");
        if(lastdir != null){
            chooser.setCurrentDirectory(new File(lastdir));
        }
        
        TreePath ins=jTree1.getSelectionPath(); /*premier fichier selectionn?*/
        
        lro=fileList; /*par def racine*/
        
        if(ins!=null){
            
            Enumeration files = fileList.breadthFirstEnumeration(); /*Tout l'arbre en largeur*/
            DefaultMutableTreeNode fich;
            while (files.hasMoreElements()) { /*parcours*/
                fich=(DefaultMutableTreeNode)files.nextElement();
                if(ins.equals(new TreePath(fich.getPath())) ) { /*Si le selectionn? == le noeud */
                    lro=fich; /*on ajoute l? */
                    break;
                }
                
            }
            
            if(lro.isLeaf() && !lro.isRoot()) { /*Si le selectionne est un fichier on ajoute dans le dossier parent [sauf racine]*/
                lro=(DefaultMutableTreeNode)lro.getParent();
            }
        }
        
        int res = chooser.showOpenDialog(this);
        
        String curdir = chooser.getCurrentDirectory().toString();
        
        switch(res) {
            case JFileChooser.APPROVE_OPTION:
                //  for(int i=0;i < chooser.getSelectedFiles().length;i++){
                for(File fich : chooser.getSelectedFiles()){
                    lro.add(recursDir(fich)); /*Fonction d'ajout r?cursive de fichiers*/
                }
                jTree1.updateUI(); /*Demande de redessinage du tree*/
                // jTree1.setModel(new DefaultTreeModel(fileList));
                break;
            case JFileChooser.CANCEL_OPTION:
                lastdir=curdir;
                break;
            case JFileChooser.ERROR_OPTION:
                break;
        }
        
        if(lastdir == null || lastdir.compareTo(curdir) != 0){
            Noyau.opts.writePref("LAST_DIR",curdir);
        }
    }
    
    private void retirerFichiers() {
        boolean flag;
        if(jTree1.isSelectionEmpty()){ /*Retire que les fichier* selectionnez*/
            return;
        }
        //     Enumeration files = fileList.breadthFirstEnumeration();
        
        TreePath[] removeList = jTree1.getSelectionPaths();
        // System.out.println("A detruire");
        
        for(TreePath path: removeList) { /*Pour tous les fichiers ? d?truire*/
            /*Possible bug, il faudrait faire l'enum ici*/
            Enumeration files = fileList.breadthFirstEnumeration(); /*Parcours en largeur*/
            
            DefaultMutableTreeNode fich;
            while (files.hasMoreElements()) {
                fich=(DefaultMutableTreeNode)files.nextElement();
                if(path.equals(new TreePath(fich.getPath())) ) { /*On cherche le noeud correspondant*/
                    //    System.out.println(path);
                    fich.removeAllChildren(); /*et on le d?gage, fils d'abord*/
                    
                    if(!fich.isRoot()) { /*Sauf si il est ? la racine*/
                        DefaultMutableTreeNode par=(DefaultMutableTreeNode)fich.getParent();
                        par.remove(fich); /*le noeud*/
                        do {
                            flag=false;
                            if(par.getChildCount()==0 && !par.isRoot()) {
                                flag=true;
                                DefaultMutableTreeNode apar=(DefaultMutableTreeNode)par.getParent();
                                apar.remove(par);
                                par=apar;
                            }
                        }while(flag);
                    }
                    break;
                }
            }
            //    RemovePath(path,fileList);
        }
        //          jTree1.setModel(new DefaultTreeModel(fileList));
        
        jTree1.updateUI();
       /* int[] removelist = jTree1.getSelectionRows();
        int nbr=0;
        
        for(int i=0;i<removelist.length;i++){
            if(removelist[i] == 0)
                return;
            //System.out.println(removelist[i]);
            nbr++;
            fileList.remove(removelist[i] - nbr);
        }
        if(fileList.getDepth() == 0){
            jTree1.setModel(new DefaultTreeModel(null));
        }else{
            jTree1.setModel(new DefaultTreeModel(fileList));
        }*/
    }
    
    private void updateMat(File [] fichs,int nb,double [] val) {
        int j;  int i;
        TableModel mat;
        
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setRowSelectionAllowed(true);
        String[] heading=new String[fichs.length+1];
        for(i=1;i<=fichs.length;i++) {
            heading[i]=fichs[i-1].getName();
        }
        heading[0]="Fichiers";
        mat = new DefaultTableModel(heading,fichs.length){
            public boolean isCellEditable(int i, int j ){return false;}
        };
        double [] val2= val.clone();
        Arrays.sort(val2);
        //jTable1.setDefaultRenderer(String.class,new TableCellCustomRenderer());
        jTable1.setModel(mat);
        TableCellRenderer tr=new TableHeaderCellCustomRenderer();
        TableCellRenderer td=new TableCellCustomRenderer(val2[0],val2[val.length-1]);
        TableColumn tc;
        for(i=0;i<=fichs.length;i++){
            tc=jTable1.getColumnModel().getColumn(i);
            tc.setHeaderRenderer(tr);
            tc.setCellRenderer(td);
            if(i==0){
                //on se passe de aller chercher la font du table header...
                // TODO : Ameliorer ça
                tc.setMinWidth(heading[0].length()*5);
                tc.setCellRenderer(tr);
            }else{
                tc.setCellRenderer(td);
            }
            for(j=0;j<fichs.length;j++){
                if(i==0) {
                    mat.setValueAt(fichs[j].getName(),j,i);
                    
                }else{
                    if(j!=i-1){ //j<i-1 suffit
                        mat.setValueAt(analys.getRes(i-1,j),j,i);}
                    //else{break;}
                }}
        }
    }
    
    private void updateDefilZone(File [] fichs) {
        
        Object[] obj=new Object[fichs.length+1];
        obj[0]="Tout";
        int i=1;
        for(File o:fichs) {
            obj[i]=o;
            i++;
        }
        
        
        ComboBoxModel li=new DefaultComboBoxModel(obj);
        jComboBox1.setModel(li);
        jLabel3.setText("sur "+Integer.toString(jComboBox1.getItemCount()-1));
        
        //   jComboBox1.updateUI();
        
    }
    
    private void plot3Dhlpt(String name){
        Color red = new Color(200,0,0);
        Color blue = new Color(0,0,200);
        
        Plot[] plots = plot3DPanel1.getPlots();

        for(int i=0;i<plots.length;i++){
            plots[i].setColor(blue);
            if(plots[i].getName().compareTo(name) == 0){
                plots[i].setColor(red);
            }
        }
        plot3DPanel1.updateUI();
        
        if(curview == 0){
            set3Dview();
        }
    }
    
    private void showPlotlabels(){
        Color black = new Color(0,0,0);
        Color blue = new Color(0,0,200);
        
        if(analys == null)
            return;
        
        File [] fichs;
        fichs = analys.getFiles();
        double[]labelpos = new double[3];
        
        for(int i=0;i<vectorsd.length;i++){
            labelpos = vectorsd[i].clone();
            labelpos[2]-=0.01;
            plot3DPanel1.addLabel(fichs[i].getName(),black,labelpos);
        }
        
        slabels = true;
        jButton12.setText("Masquer les étiquettes");
    }
    
    private void hidePlotlabels(){
        Plotable[] labels = plot3DPanel1.getPlotables();
        for(int i=0;i<labels.length;i++){
            plot3DPanel1.removePlotable(labels[i]);
        }
        slabels = false;
        jButton12.setText("Afficher les étiquettes");
    }
    
    public void Dispatch3DResult(float[][] vectors){
        Color black = new Color(0,0,0);
        Color blue = new Color(0,0,200);
        vectorsd = new double[vectors.length][vectors[0].length]; 
       
        File [] fichs;
        fichs = analys.getFiles();
        
        for(int i=0;i<vectors.length;i++){
            for(int j=0;j<vectors[i].length;j++){
                vectorsd[i][j] = (double) vectors[i][j];
            }
        }
        // On fait le menage
        plot3DPanel1.removeAllPlots();
        hidePlotlabels();
        
        // On affiche les vecteurs et les labels
        for(int i=0;i<vectorsd.length;i++){
            plot3DPanel1.addScatterPlot(fichs[i].getName(),blue,vectorsd[i]);
        }
        showPlotlabels();
        
        jButton11.setVisible(true);
       
    }
    
    private void updatePlot(double[] val, int nb) {
        
        /*double[][] v2 =new double[1][];
        v2[0]=val;*/
        
        plot2DPanel1.removeAllPlots();
        plot2DPanel1.addHistogramPlot("Histogramme des valeurs",val,nb);
        
    }
    
    public void DispatchResult() {
        // TODO positionner analys ? null quand rajout de fichier
        if(analys!=null) {
            int nb = analys.getNumAnalyse();
            File [] fichs;
            
            fichs=analys.getFiles();
            int  i, j;
            
            double[] val= new double[nb];
            int a=0;
            for(i=0;i<fichs.length;i++){
                for(j=0;j<fichs.length;j++){
                    if(j<i){
                        val[a++]=analys.getRes(i,j);}else{break;}
                }
            }
            
            updateMat(fichs,nb,val);
            updateDefilZone(fichs);
            updatePlot(val,nb);
            jButton3.setEnabled(true);
            
            jPanel4.setVisible(true);
            //TODO : Ouvrir le splitPane à la bonne taille
            jSplitPane2.setDividerLocation(this.getSize().width*1/2);
            
            //    JOptionPane p=new JOptionPane(JOptionPane.INFORMATION_MESSAGE);
            //p.showMessageDialog(this,"Analyse Terminée");
        }
    }
    
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        File[] files = getFileTab();
        if(files!=null && files.length>2){
            //System.out.println(files.length+" fichiers à analyser:");
            
    /*        for(File file: files){
                System.out.println(file.toString());
            }
            try{
                wait(2); //for test
            }catch(Exception e){};*/
            if(analys==null){
                analys=Main.noyau.newGUITask(monNumero,files,this.jLabel2,this.jProgressBar1,this);
            }
            else{
                File[] exfiles = analys.getFiles();
                float [][] exRes= analys.getResults();
                //on peux pas faire repartir un thread donc faut en faire un autre
                analys=Main.noyau.newGUITask(monNumero,files,this.jLabel2,this.jProgressBar1,this);
                analys.setExRes(exfiles,exRes);
                
                //analys.setFiles(files);
            }
            if(analys!=null) {
                jButton3.setEnabled(false);
                analys.start();
                
            }
        }       else if(files!=null && files.length<=2){
           Utils.Errors.Error.noEnoughFiles();
        
        } 
        else{
            Utils.Errors.Error.noFiles();
        }
        
    
    }//GEN-LAST:event_jButton3ActionPerformed

    /*    private void RemovePath(TreePath path,DefaultMutableTreeNode tree)
    {
        if(path.equals(new TreePath(tree.getPath())))
        {
            System.out.println(path);
        tree.removeAllChildren();
     
        tree.remove(tree);
        }else{
           if(!tree.isLeaf())
           {
            tree.
           }
     
        }
      }     */

                public int ExitAndSaveOnglet(){
            int choix = JOptionPane.showConfirmDialog(null,"Souhaitez-vous enregistrer les modifications \n apportées avant de fermer cet onglet ?" );
            if(choix==JOptionPane.NO_OPTION)
            {
                Main.ihm.fermerTab(this,monNumero);
            }
            else if(choix==JOptionPane.OK_OPTION)
            {
                
                if(Main.ihm.sauver()!=null){
                Main.ihm.fermerTab(this,monNumero);
                }
            }
            return 0;
                }
               
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Main.modifie=true;
        retirerFichiers();
    }//GEN-LAST:event_jButton2ActionPerformed
        private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
            if(Main.modifie)
            {
                ExitAndSaveOnglet();
            }
            else
            {
                Main.ihm.fermerTab(this,monNumero);
            }
    }//GEN-LAST:event_jButton8ActionPerformed
            private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                Main.modifie=true;
                ajouterFichiers();
    }//GEN-LAST:event_jButton1ActionPerformed
            
            private void itemSuivant() {
                int suivant = jComboBox1.getSelectedIndex()+1;
                if(suivant<=jComboBox1.getItemCount()-1)
                    jComboBox1.setSelectedIndex(suivant);
            }
            private void itemPrecedent() {
                int prec = jComboBox1.getSelectedIndex()-1;
                if(prec!=-1)
                    jComboBox1.setSelectedIndex(prec);
            }
            private void itemDebut() {
                jComboBox1.setSelectedIndex(0);
            }
            private void itemFin() {
                jComboBox1.setSelectedIndex(jComboBox1.getItemCount()-1);
            }
            
            private StringBuffer recursXmlFile(MutableTreeNode tree) {
                StringBuffer str=new StringBuffer();
                if(tree.isLeaf()) {
                    str.append("<file>").append(tree.toString()).append("</file>\n");
                }else{
                    str.append("<dir name=\"").append(tree.toString()).append("\" >\n");
                    Enumeration ch=tree.children();
                    while(ch.hasMoreElements()) {
                        MutableTreeNode t=(MutableTreeNode)ch.nextElement();
                        str.append(recursXmlFile(t));
                    }
                    str.append("</dir>\n");
                }
                
                return str;
            }
            
            public StringBuffer toXml() {
                StringBuffer str=new StringBuffer();
                str.append("<onglet>\n");
                
                str.append("<filelist>\n").append(recursXmlFile(fileList)).append("</filelist>\n");
                
                str.append("<rapport>").append(jReport.getText()).append("</rapport>\n");
                if(analys!=null) {
                    str.append(analys.toXml());
                }
                str.append("</onglet>\n");
                return str;
            }
            
            private DefaultMutableTreeNode recursDomTree(Node n) {
                int i;
                File f;
                System.out.println(n.getNodeName());
                if(n!=null) {
                    if(n.getNodeName()=="dir") {
                        DefaultMutableTreeNode t;
                        String nom=n.getAttributes().getNamedItem("name").getTextContent().trim();
                        f=new File(nom);
                        if(f.exists()) {
                            t=new DefaultMutableTreeNode(f);
                        }else{
                            t=new DefaultMutableTreeNode(nom);
                        }
                        for(i=0;i<n.getChildNodes().getLength();i++) {
                            DefaultMutableTreeNode u=recursDomTree(n.getChildNodes().item(i));
                            if(u!=null){
                                t.add(u);
                            }
                        }
                        return t;
                    }else if(n.getNodeName()=="file") {
                        
                        f=new File(n.getTextContent().trim());
                        if(f.exists()) {
                            return new DefaultMutableTreeNode(f);
                        }else{
                            return new DefaultMutableTreeNode(n.getTextContent().trim());
                        }
                    }
                }
                return null;
            }
            
            public void fromDom(Node node) {
                int j;
                int i;
                NodeList l=node.getChildNodes();
                for(i=0;i<l.getLength();i++) {
                    if(l.item(i).getNodeName()=="filelist") {
                        for(j=0;j<l.item(i).getChildNodes().getLength();j++){
                            if(l.item(i).getChildNodes().item(j).getNodeName()=="dir" ){
                                fileList=recursDomTree(l.item(i).getChildNodes().item(j));
                                jTree1.setModel(new DefaultTreeModel(fileList));
                                break;
                            }
                        }
                    }else if(l.item(i).getNodeName()=="rapport") {
                        jReport.setText(l.item(i).getTextContent());
                    }else if(l.item(i).getNodeName()!="#text") {
                        File[] files=getFileTab();
                        analys=Main.noyau.newGUITask(monNumero,files,this.jLabel2,this.jProgressBar1,this);
                        analys.fromDom(l.item(i));
                       
                    }
                }
                
                
            }
            
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem NouveauDossier;
    private javax.swing.JMenuItem ajouter;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JTextPane jReport;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTree jTree1;
    private javax.swing.JMenuItem lancer;
    private javax.swing.JPopupMenu menuContextuel;
    private org.math.plot.Plot2DPanel plot2DPanel1;
    private org.math.plot.Plot3DPanel plot3DPanel1;
    private javax.swing.JMenuItem supprimer;
    // End of variables declaration//GEN-END:variables
    
}
