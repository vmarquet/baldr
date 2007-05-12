/*
 * TableCellCustomRenderer.java
 *
 * Created on 12 mai 2007, 16:33
 *$Id$
 */

package Ihm;

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
    float min;
    float max;
    /** Creates a new instance of TableCellCustomRenderer */
    public TableCellCustomRenderer() {
        rend=new DefaultTableCellRenderer();
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    JLabel reu=(JLabel) rend.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
   if(column!=0 && value!=null){
        //TODO : ajouter une pref pour le multiplier
    reu.setText(Float.valueOf(value.toString())*10+"e-1");} 
    if(value!=null){
    reu.setToolTipText(value.toString());
    }else{
    reu.setToolTipText("O par définition");
    }
    return reu;
    }
    
    
    
}
