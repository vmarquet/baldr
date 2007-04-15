/*
 * panelTab.java
 *
 * Created on 30 mars 2007, 19:03
 *$Id$
 */

package Ihm;
import java.io.File;
import java.util.Enumeration;
import javax.swing.JFileChooser;
import javax.swing.TransferHandler;
import javax.swing.tree.*;
import Main.*;
/**
 * @author  Baldr Team
 */
public class panelTab extends javax.swing.JPanel {
    int monNumero;
    DefaultMutableTreeNode fileList;
    /** Creates new form panelTab */
    public panelTab(int monNum) {
        fileList = new DefaultMutableTreeNode("Documents");
        initComponents();
        monNumero=monNum;
        jButton10.setVisible(false);
    }
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        menuContextuel = new javax.swing.JPopupMenu();
        ajouter = new javax.swing.JMenuItem();
        supprimer = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        lancer = new javax.swing.JMenuItem();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 =  new javax.swing.JTree(fileList);
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel4 = new javax.swing.JPanel();
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
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();

        ajouter.setText("Ajouter");
        ajouter.setName("Ajouter");
        ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajouterActionPerformed(evt);
            }
        });

        menuContextuel.add(ajouter);

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
        menuContextuel.add(lancer);

        jSplitPane1.setAutoscrolls(true);
        jTree1.setCellRenderer(new TreeCellCustomRenderer());
        jTree1.setModel(new DefaultTreeModel(fileList));
        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree1MouseClicked(evt);
            }
        });

        jScrollPane1.setViewportView(jTree1);

        jLabel1.setText("Ajout de fichiers :");

        jButton1.setText("Ajouter");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Supprimer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Lancer l'analyse");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jProgressBar1.setRequestFocusEnabled(false);
        jProgressBar1.setString("43%");

        jButton8.setText("Fermer l'onglet");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Masquer cette liste");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel1)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                                .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 82, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 102, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jButton3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jProgressBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .add(jButton8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                        .add(42, 42, 42)
                        .add(jButton9)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 452, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton2)
                    .add(jButton1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jProgressBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton9)
                .addContainerGap())
        );
        jSplitPane1.setLeftComponent(jPanel1);

        jButton6.setText("<<");
        jButton6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton7.setText("<");
        jButton7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("sur N");

        jButton5.setText(">");
        jButton5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton4.setText(">>");
        jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .add(jButton6)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton7)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 71, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton5)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton4)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton6)
                    .add(jButton7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3)
                    .add(jButton5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton4)))
        );

        jLabel6.setText("Rapports :");

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

        jLabel4.setText("L'un ou l'autre :");

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                        .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(39, 39, 39))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                        .add(jLabel4)
                        .add(93, 93, 93))
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                        .addContainerGap())
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jLabel6)
                        .addContainerGap(194, Short.MAX_VALUE))
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jScrollPane4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(jLabel6)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .add(15, 15, 15)
                .add(jLabel4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 163, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(124, 124, 124))
        );
        jSplitPane2.setRightComponent(jPanel4);

        jScrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel2.setIcon(new javax.swing.ImageIcon("images/distMap2.GIF"));
        jScrollPane3.setViewportView(jLabel2);

        jLabel5.setText("R\u00e9sultats graphiques :");

        jButton10.setText("Afficher la liste");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jButton10))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
                    .add(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel5)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jLabel5)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton10)
                .add(11, 11, 11))
        );
        jSplitPane2.setLeftComponent(jPanel3);

        jSplitPane1.setRightComponent(jSplitPane2);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jSplitPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
// TODO add your handling code here:
        jPanel1.setVisible(true);
        jSplitPane1.setLeftComponent(jPanel1);
        jButton10.setVisible(false);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
// TODO add your handling code here:
        jPanel1.setVisible(false);
        jButton10.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void supprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprimerActionPerformed
retirerFichiers();
    }//GEN-LAST:event_supprimerActionPerformed
    
    private void ajouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouterActionPerformed
        ajouterFichiers();
    }//GEN-LAST:event_ajouterActionPerformed
    
    
    private void jTree1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseClicked
// TODO add your handling code here:
        if (evt.getButton()==java.awt.event.MouseEvent.BUTTON2||evt.getButton()==java.awt.event.MouseEvent.BUTTON3) {
            menuContextuel.show(evt.getComponent(),evt.getX(), evt.getY());
            
        }
    }//GEN-LAST:event_jTree1MouseClicked
    
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
        int count=fileList.getLeafCount(); /* Les fichiers � analyser sont forc�ment des feuilles*/
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
        
        TreePath ins=jTree1.getSelectionPath(); /*premier fichier selectionn�*/
        
        lro=fileList; /*par def racine*/
        
        if(ins!=null){
            
            Enumeration files = fileList.breadthFirstEnumeration(); /*Tout l'arbre en largeur*/
            DefaultMutableTreeNode fich;
            while (files.hasMoreElements()) { /*parcours*/
                fich=(DefaultMutableTreeNode)files.nextElement(); 
                if(ins.equals(new TreePath(fich.getPath())) ) { /*Si le selectionn� == le noeud */
                    lro=fich; /*on ajoute l� */
                    break;
                }
                
            }
            
            if(lro.isLeaf() && !lro.isRoot()) { /*Si le selectionn� et un fichier on ajoute dans le dossier parent [sauf racine]*/
                lro=(DefaultMutableTreeNode)lro.getParent();
            }
        }
        
        int res = chooser.showOpenDialog(this);
        switch(res) {
            case JFileChooser.APPROVE_OPTION:
                //  for(int i=0;i < chooser.getSelectedFiles().length;i++){
                for(File fich:chooser.getSelectedFiles()){
                    lro.add(recursDir(fich)); /*Fonction d'ajout r�cursive de fichiers*/
                }
                jTree1.updateUI(); /*Demande de redessinage du tree*/
                // jTree1.setModel(new DefaultTreeModel(fileList));
                break;
            case JFileChooser.CANCEL_OPTION:
                break;
            case JFileChooser.ERROR_OPTION:
                break;
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
        
        for(TreePath path: removeList) { /*Pour tous les fichiers � d�truire*/
            /*Possible bug, il faudrait faire l'enum ici*/
            Enumeration files = fileList.breadthFirstEnumeration(); /*Parcours en largeur*/
            
            DefaultMutableTreeNode fich;
            while (files.hasMoreElements()) { 
                fich=(DefaultMutableTreeNode)files.nextElement();
                if(path.equals(new TreePath(fich.getPath())) ) { /*On cherche le noeud correspondant*/
                    //    System.out.println(path);
                    fich.removeAllChildren(); /*et on le d�gage, fils d'abord*/
                    
                    if(!fich.isRoot()) { /*Sauf si il est � la racine*/
                        DefaultMutableTreeNode par=(DefaultMutableTreeNode)fich.getParent();
                        par.remove(fich); /*le noeud*/
                        do {
                       flag=false;
                         if(par.getChildCount()==0 && !par.isRoot())
                        {
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
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        File[] files = getFileTab();
        if(files!=null){
            System.out.println(files.length+" fichiers � analyser:");
            
            for(File file: files){
                System.out.println(file.toString());
            }
            
            Main.noyau.newTask(monNumero,files);
    }//GEN-LAST:event_jButton3ActionPerformed
        
        else{
            Utils.Errors.Error.noFiles();
        }
        
    }
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
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        retirerFichiers();
    }//GEN-LAST:event_jButton2ActionPerformed
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
            Main.ihm.fermerTab(this,monNumero);
    }//GEN-LAST:event_jButton8ActionPerformed
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                ajouterFichiers();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ajouter;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JTextPane jReport;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTree jTree1;
    private javax.swing.JMenuItem lancer;
    private javax.swing.JPopupMenu menuContextuel;
    private javax.swing.JMenuItem supprimer;
    // End of variables declaration//GEN-END:variables
    
}
