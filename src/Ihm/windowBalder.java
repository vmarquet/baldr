/*
 * windowBalder.java
 *
 * Created on 23 mars 2007, 16:11
 *$Id$
 */

package Ihm;
import Ihm.FileFilters.BaldrFileFilter;
import Ihm.FileFilters.BaldrNBaldrxFileFilter;
import Ihm.FileFilters.BaldrxFileFilter;
import Main.*;
import Noyau.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JFileChooser;
import java.awt.*;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 *
 * @author  Baldr Team
 */
public class windowBalder extends javax.swing.JFrame implements Savable {
    // int i=0;
    private HashMap actions;
    static java.awt.event.ActionEvent event;
    panelTab[] listeOnglets=new panelTab[Main.MAXONGLET];
    
    aPropos aProposBaldr;
    java.awt.Image iconBaldr = java.awt.Toolkit.getDefaultToolkit().getImage("Images/baldr.gif");

    public windowBalder() {
        int i;
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
        
        setIconImage(iconBaldr);
        
        for(i=0;i<Main.MAXONGLET;i++) listeOnglets[i]=null;
        
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
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        Preferences = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Baldr");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        fileMenu.setText("Fichier");
        jMenuItem1.setIcon(new javax.swing.ImageIcon("Images/tab_add.png"));
        jMenuItem1.setText("Nouvel onglet");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });

        fileMenu.add(jMenuItem1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon("Images/tab_delete.png"));
        jMenuItem2.setText("Fermer l'onglet courant");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });

        fileMenu.add(jMenuItem2);

        jMenuItem3.setIcon(new javax.swing.ImageIcon("Images/tab_edit.png"));
        jMenuItem3.setText("Renommer l'onglet courant");
        jMenuItem3.setToolTipText("");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });

        fileMenu.add(jMenuItem3);

        fileMenu.add(jSeparator1);

        openMenuItem.setIcon(new javax.swing.ImageIcon("Images/folder_go.png"));
        openMenuItem.setText("Ouvrir");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });

        fileMenu.add(openMenuItem);

        saveMenuItem.setIcon(new javax.swing.ImageIcon("Images/disk.png"));
        saveMenuItem.setText("Enregistrer");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });

        fileMenu.add(saveMenuItem);

        exitMenuItem.setIcon(new javax.swing.ImageIcon("Images/cross.png"));
        exitMenuItem.setText("Quitter");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });

        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setText("Options");
        Preferences.setIcon(new javax.swing.ImageIcon("Images/pref.png"));
        Preferences.setText("Pr\u00e9f\u00e9rences ...");
        Preferences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Prefev(evt);
            }
        });

        editMenu.add(Preferences);

        menuBar.add(editMenu);

        helpMenu.setText("Aide");
        contentsMenuItem.setIcon(new javax.swing.ImageIcon("Images/help.png"));
        contentsMenuItem.setText("Rubriques d'aide");
        contentsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contentsMenuItemActionPerformed(evt);
            }
        });

        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setIcon(new javax.swing.ImageIcon("Images/baldr.gif"));
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

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
for(int i=0;i<Main.MAXONGLET;i++){
        if(listeOnglets[i]!=null)
        {
            if(listeOnglets[i].isShowing())
            {
                int j = i+1;
                String choix = JOptionPane.showInputDialog(this,"Nouveau nom :","Renommer l'onglet n°"+j+" ?",JOptionPane.PLAIN_MESSAGE);
                if(choix!=null && !choix.equals(""))
                {
                    jTabbedPane1.setTitleAt(i,choix);
                }
            }
        }
    }
    }//GEN-LAST:event_jMenuItem3ActionPerformed
    
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

        for(int i=0;i<Main.MAXONGLET;i++)
        {
        if(listeOnglets[i]!=null)
        {
            if(listeOnglets[i].isShowing())
            {
                if(Main.modifie && !listeOnglets[i].isFilelistEmpty())
                {
                    listeOnglets[i].ExitAndSaveOnglet();
                }
                else
                {
                Main.ihm.fermerTab(listeOnglets[i]);
                }
            }
        }
        }

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void contentsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contentsMenuItemActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_contentsMenuItemActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
// TODO add your handling code here:
          if(Main.modifie)
            {
                ExitAndSave();
            }
          else
          {
              System.exit(0);
          }
    }//GEN-LAST:event_formWindowClosing
    /**
     *Funcion which display a filechooser to save the application state
     *@return the file chosen or null
     */
    public File sauver() {
        
        // TODO rendre le filefilter plus propre
        
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileFilter baldrxfifi=new BaldrxFileFilter();
        chooser.setFileFilter(baldrxfifi);
        FileFilter baldrfifi=new BaldrFileFilter();
        chooser.setFileFilter(baldrfifi);
        String lastdir = Noyau.opts.readPref("LAST_DIR");
        if(lastdir != null){
            chooser.setCurrentDirectory(new File(lastdir));
        }
        int res = chooser.showSaveDialog(this);
        
        String curdir = chooser.getCurrentDirectory().toString();
        File ret=null;
        switch(res) {
            case JFileChooser.APPROVE_OPTION:
                SaveAndRestore sav=new SaveAndRestore(this);
                File f=chooser.getSelectedFile();
                if(Utils.Extension.getExtension(f)==null  ) {
                    if(chooser.getFileFilter()==baldrfifi){
                        f=new File(f.getAbsolutePath()+"."+Utils.Extension.baldr);
                    }else if(chooser.getFileFilter()==baldrxfifi){
                        f=new File(f.getAbsolutePath()+"."+Utils.Extension.baldrx);
                    }
                }
                if(chooser.getFileFilter()==baldrxfifi){
                    sav.save(f,SaveAndRestore.BALDRX);
                }else{
                    sav.save(f,SaveAndRestore.BALDR);
                }
                ret=f;
                //TODO New SaveAndRestore
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
        Main.modifie=false;
        return ret;
    } 
    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        Main.modifie=false;
        Main.ihm.sauver();
    }//GEN-LAST:event_saveMenuItemActionPerformed
            private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
                aProposBaldr.setLocationRelativeTo(this);
                aProposBaldr.setVisible(true);
                aProposBaldr.startAbout();
    }//GEN-LAST:event_aboutMenuItemActionPerformed
                private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
                    ajouteOnglet();
    }//GEN-LAST:event_jMenuItem1ActionPerformed
                                            private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
                                                JFileChooser chooser = new JFileChooser();
                                                chooser.setMultiSelectionEnabled(false);
                                                        FileFilter baldrxfifi=new BaldrxFileFilter();
        chooser.setFileFilter(baldrxfifi);
        FileFilter baldrfifi=new BaldrFileFilter();
        chooser.setFileFilter(baldrfifi);
        chooser.setFileFilter(new BaldrNBaldrxFileFilter());
        
                      chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                                                String lastdir = Noyau.opts.readPref("LAST_DIR");
                                                if(lastdir != null){
                                                    chooser.setCurrentDirectory(new File(lastdir));
                                                }
                                                
                                                int res = chooser.showOpenDialog(this);
                                                
                                                String curdir = chooser.getCurrentDirectory().toString();
                                                
                                                switch(res) {
                                                    case JFileChooser.APPROVE_OPTION:
                                                        File f=chooser.getSelectedFile();
                                                        SaveAndRestore sav=new SaveAndRestore(this);
                                                        
                                                        sav.restore(f);
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
    }//GEN-LAST:event_openMenuItemActionPerformed
                                private void Prefev(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Prefev
                                    prefwin test = new prefwin();
                                    test.setVisible(true);
    }//GEN-LAST:event_Prefev
                                        private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
                                            if(Main.modifie)
                                                ExitAndSave();
                                            else
                                                System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed
                                        /**
                                         *Close the Tab 
                                         *@param pt tab to close
                                         */
                                        public void fermerTab(panelTab pt) {
                                            int j=0;
                                            jTabbedPane1.remove(pt);
                                            listeOnglets[pt.getTabNumber()]=null;
                                            for(int i=0;i<Main.MAXONGLET;i++)
                                            {
                                            if(listeOnglets[i]==null)
                                            {
                                                j++;
                                            }
                                            if(j==Main.MAXONGLET)
                                            {
                                                Main.modifie=false;
                                            }
                                        }
                                        }
                                        /**
                                         *Add a tab in the application and give the focus to him
                                         *@see panelTab
                                         */
                                        public panelTab ajouteOnglet(){
                                            int i;
                                            for(i=0;i<Main.MAXONGLET && listeOnglets[i]!=null;i++); //détermine le 1er onglet non utilisé
                                            if (i==Main.MAXONGLET){
                                                Utils.Errors.Error.tropOnglets();
                                                return null;
                                            }else {
                                                int numeroDuTabLibre=i;
                                                int numOnglet=i+1;
                                                final panelTab newtab=new panelTab(numeroDuTabLibre);
                                                listeOnglets[numeroDuTabLibre]=newtab;
                                                jTabbedPane1.addTab("Analyse "+numOnglet ,newtab);
                                                jTabbedPane1.setSelectedComponent(newtab);
                                                return newtab;
                                            }
                                        }
                                        
                                        public StringBuffer toXml() {
                                            StringBuffer str=new StringBuffer();
                                            int i;
                                            str.append("<save>\n");
                                            for(i=0;i<listeOnglets.length;i++) {
                                                if(listeOnglets[i]!=null){
                                                    str.append(listeOnglets[i].toXml());
                                                }
                                                
                                            }
                                            
                                            str.append("</save>\n");
                                            return str;
                                        }
                                        
                                        public void fromDom(Node n) {
                                            int i;
                                            if(n.getNodeName()=="save") {
                                                
                                                for(i=0;i<n.getChildNodes().getLength();i++) {
                                                    if(n.getChildNodes().item(i).getNodeName()!="#text"){
                                                        panelTab tab= ajouteOnglet();
                                                        tab.fromDom(n.getChildNodes().item(i));
                                                        System.out.println(n.getChildNodes().item(i).getNodeName());
                                                    }
                                                }
                                                
                                                
                                            }
                                        }
     /**
     * Called on closing request to ask for saving.
     * and quit
     *
     */                           
            public void ExitAndSave() {
            int choix = JOptionPane.showConfirmDialog(this,"Souhaitez-vous enregistrer les modifications \n apportées avant de fermer cet onglet ?","Baldr",1);
            if(choix==JOptionPane.NO_OPTION)
            {
                System.exit(0);
            }
            else if(choix==JOptionPane.OK_OPTION)
            {
                if(Main.ihm.sauver()!=null)
                    System.exit(0);
            }
        
                }
                                        
    /** Preferences Preference of the appliaction*/                                    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenuItem Preferences;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    // End of variables declaration//GEN-END:variables
    
}
