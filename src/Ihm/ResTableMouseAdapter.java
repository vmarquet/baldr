/*
 * ResTableMouseAdapter.java
 *
 * Created on 6 juin 2007, 21:56
 *$Id$
 */

package Ihm;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JTable;
import Noyau.*;

/**
 *
 * @author zeta
 */
public class ResTableMouseAdapter extends MouseAdapter{
    JTable table;
    /** Creates a new instance of ResTableMouseAdapter */
    public ResTableMouseAdapter(JTable tab) {
        super();
        this.table=tab;
    }
    
    public void mouseClicked(MouseEvent e) {
        if(e.isMetaDown()){
            Point pt=new Point(e.getX(), e.getY());
            int row = table.rowAtPoint(pt);
            int col = table.columnAtPoint(pt);
//jLabel2.setText(col+" "+row+"  "+jTable1.getColumnName(col)+" "+jTable1.getValueAt(row,0));
            
        }
        
        if(e.getClickCount()>1  && e.getButton()==e.BUTTON1) {
            Point pt=new Point(e.getX(), e.getY());
            int row = table.rowAtPoint(pt);
            int col = table.columnAtPoint(pt);
            System.out.println(col+" "+row+"  "+table.getColumnName(col)+" "+table.getValueAt(row,0));
            String comparator=Noyau.opts.readPref("COMPARATOR");
            
            if(comparator.length()<1) {
                Utils.Errors.Error.noComparatorDefined();
                return;
            }
            if(!comparator.contains("$1")) {
                comparator=comparator+" $1";
            }
            
            if(!comparator.contains("$2")) {
                comparator=comparator+" $2";
            }
            
            BaldrTableModel tmod=(BaldrTableModel) table.getModel();
            col=table.convertColumnIndexToModel(col);
            System.out.println(tmod.getColumnName(col)+" "+tmod.getValueAt(row,0));
            
            StringBuffer f1=new StringBuffer();
            f1.append('"').append(tmod.getColumnFile(col).getAbsolutePath()).append('"').append(' ');
            
            StringBuffer f2=new StringBuffer();
            f2.append('"').append(tmod.getRowFile(row).getAbsolutePath()).append('"').append(' ');
            
            String exe=comparator.replace("$1",f1.toString()).replace("$2",f2.toString());
            
            System.out.println(exe);
            
            Runtime r=Runtime.getRuntime();
            try {
                r.exec(exe);
            } catch (IOException ex) { //TODO gerer mieux
                ex.printStackTrace();
            }
            
            
        }
        
        
    }
    
}
