/*
 * TableCellCustomRenderer.java
 *
 * Created on 12 mai 2007, 16:33
 *$Id$
 */

package Ihm;

import java.awt.Color;
import java.awt.Component;
import javax.swing.CellRendererPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author zeta
 */
public class TableCellCustomRenderer implements TableCellRenderer{
    TableCellRenderer rend;
    double min;
    double max;
    /** Creates a new instance of TableCellCustomRenderer */
    public TableCellCustomRenderer(double min,double max) {
        this.min=min;
        this.max=max;
        rend=new DefaultTableCellRenderer();
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    JLabel reu=(JLabel) rend.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
  
    if(column!=0 && value!=null){
        //TODO : ajouter une pref pour le multiplier
        float v=Float.valueOf(value.toString());
    reu.setText(v*10+"e-1");
    //TODO ajout pref pour couleur 
    if(!isSelected){
   reu.setBackground(Color.getHSBColor((float)(0.37*((v-min)/(max-min))),0.5F,1));} 
   
   }else{
   if(!isSelected){reu.setBackground(Color.WHITE);}
   } 
    if(value!=null){
     reu.setToolTipText(value.toString());
    }else{
    reu.setToolTipText("O par définition");
    }
    return reu;
    }
    
    
    
}
