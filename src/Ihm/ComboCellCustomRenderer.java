/*
 * ComboCellCustomRenderer.java
 *
 * Created on 10 mai 2007, 15:33
 *$Id$
 */

package Ihm;

import java.awt.Component;
import java.io.File;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author zeta
 */
public class ComboCellCustomRenderer implements ListCellRenderer{
    
    /** Creates a new instance of ComboCellCustomRenderer */
ListCellRenderer rend;
    public ComboCellCustomRenderer() {
        rend=new DefaultListCellRenderer();
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel reu=(JLabel) rend.getListCellRendererComponent(list,value,index,isSelected,cellHasFocus);
        String tmp=value.toString();
        if(tmp.lastIndexOf(File.separator)!=-1){
        reu.setText(tmp.substring(tmp.lastIndexOf(File.separator)+1));
        }
        
        return reu;
    }
    
    
}
