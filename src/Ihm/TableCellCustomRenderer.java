/*
 * TableCellCustomRenderer.java
 *
 * Created on 6 mai 2007, 19:35
 *$Id$
 */

package Ihm;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author zeta
 */
public class TableCellCustomRenderer implements TableCellRenderer {
    
    /** Creates a new instance of TableCellCustomRenderer */
    private TableCellRenderer rend;
    public TableCellCustomRenderer() {
        rend=new UIResourceTableCellRenderer();
 
    }
        
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        int i;
       DefaultTableCellRenderer reu=(DefaultTableCellRenderer) rend.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
       StringBuffer str=new StringBuffer();
       String s=value.toString();
       
       for(i=0;i<s.length();i++)
       {
       str.append(s.charAt(i)).append('\n');
       }
       reu.setText(str.toString());
       reu.setToolTipText(s);
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

