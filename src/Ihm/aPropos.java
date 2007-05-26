/*
 * aPropos.java
 *
 * Created on 31 mars 2007, 17:15
 *$Id$
 */

package Ihm;
import java.*;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
/**
 * Frame with greetings when starting
 * @author  Baldr Team
 */
public class aPropos extends javax.swing.JFrame implements ActionListener{
    
    /** Creates new form aPropos */
    
    //javax.swing.Timer t;
    Timer scroll;
    String[] about;
    int pos,count;
    
    public aPropos(java.awt.Component win) {

        initComponents();
       
        about = jLabel3.getText().split("<br>");
        jLabel3.setText("");
        pos = count = 0;
        
        setAlwaysOnTop(true);
        setTitle("A propos");
        
        setLocationRelativeTo(win); 
        setVisible(true);
        
        
        TimerTask scrollTask = new TimerTask() {
            public void run() {
                 StringBuffer buf=new StringBuffer();
                 
                 if(pos >= about.length){
                     pos = count = 0;
                 }
                 
                 for(int i=pos;i<(pos+5);i++){
                     if(i < about.length)
                         buf.append("<br>"+about[i]);
                 }
                 jLabel3.setText("<html><center>"+buf+"</center></html>");
                 if(count > 2)
                    pos += 1;

                 count += 1;
            }
        };
        
        //t=new javax.swing.Timer(6000,this);
        scroll = new Timer();
        scroll.scheduleAtFixedRate(scrollTask, 0, 1000);
        
        //t.start();
        
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel1FocusGained(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon("Images/Baldur.jpg"));

        jLabel2.setText("Balder, dieu scandinave de l'innocence");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("<b>Baldr</b> version 0.1 Alpha <br>\n<br>\nLogiciel de lutte contre la fraude <br>\n<br><br>\nProgrammeurs principaux : <br>\nBeno\u00eet Larroque <br>\nC\u00e9dric Tessier <br>\nHubert Wassner <br>\n<br>\nProgrammeurs en charge de l'IHM : <br>\nCorentin <br>\nSigoigne <br>");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 251, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel2)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 99, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        fprop();
    }//GEN-LAST:event_formKeyPressed

    private void fprop()
    {
       //t.stop();
       scroll.cancel();
       setVisible(false);
       this.dispose();
    }
    
    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
fprop();
    }//GEN-LAST:event_formFocusLost

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
fprop();
    }//GEN-LAST:event_formMouseClicked

    private void jPanel1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel1FocusGained
    }//GEN-LAST:event_jPanel1FocusGained
/** Action Listener*/
    public void actionPerformed(ActionEvent e) {
     
        fprop();
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
    
}
