/*
 * TableHeaderCellCustomRenderer.java
 *
 * Created on 6 mai 2007, 19:35
 *$Id$
 */

package Ihm.renderers;



import Ihm.*;
import Ihm.renderers.utils.VTextIcon;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 * Cell Renderer for the Table header and first column
 *@see TableCellRenderer
 * @author zeta
 */
public class TableHeaderCellCustomRenderer implements TableCellRenderer {
    
    /**
     * Creates a new instance of TableHeaderCellCustomRenderer
     */
    private TableCellRenderer rend;
    public TableHeaderCellCustomRenderer() {
        rend=new UIResourceTableCellRenderer();
        
    }
    
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        int i;
        DefaultTableCellRenderer reu=(DefaultTableCellRenderer) rend.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
        StringBuffer str=new StringBuffer();
        String s=value.toString();
        
     /*  for(i=0;i<s.length();i++)
       {
       str.append(s.charAt(i)).append('\n');
       }
       reu.setText(str.toString());
      */
        if(column!=0){
            String s2=s;
            if(s.length()>10) {
                for(i=0;i<3;i++) {
                    str.append(s.charAt(i));
                }
                str.append("...");
                for(i=s.length()-4;i<s.length();i++) {
                    str.append(s.charAt(i));
                }
                s2=str.toString();
            }
            
            
            Icon ic= new VTextIcon(reu,s2,VTextIcon.ROTATE_LEFT);
            reu.setText("");
            reu.setIcon(ic);
        }else{
            reu.setText(s);
            reu.setIcon(null);
        }
        reu.setToolTipText(s);
        if(isSelected && row!=0){
        reu.setBackground(table.getSelectionBackground());
        reu.setForeground(table.getSelectionForeground());
 
        }
        // System.out.println(str);
        return reu;
    }
    
    
}


class UIResourceTableCellRenderer extends DefaultTableCellRenderer  {
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (table != null) {
            JTableHeader header = table.getTableHeader();
            if (header != null) {
                setForeground(header.getForeground());
                setBackground(header.getBackground());
                setFont(header.getFont());
            }
        }
        
        setText((value == null) ? "" : value.toString());
        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        return this;
    }
}

