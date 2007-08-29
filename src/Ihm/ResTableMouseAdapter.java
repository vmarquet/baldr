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
import java.io.File;

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
            BaldrTableModel tmod=(BaldrTableModel) table.getModel();
            col=table.convertColumnIndexToModel(col);
            tmod.toggleDone(row,col);
            table.repaint();
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
            f1.append(tmod.getColumnFile(col).getAbsolutePath());
            
            StringBuffer f2=new StringBuffer();
            f2.append(tmod.getRowFile(row).getAbsolutePath());
            
            String[] ex;
          
            if ((System.getProperty("os.name").toUpperCase().indexOf("MAC") != -1) && (comparator.replace("$1","").replace("$2","").trim().endsWith(".app"))) {
                comparator = "open -a " + comparator;
            }    
          
            comparator=comparator.replace("$1",f1.toString()).replace("$2",f2.toString());
            
            ex = Args.getArgs(comparator);
            
            System.out.print("Start: "); for(String s : ex){
                System.out.print("<" + s + "> ");
            }System.out.println();
            
            Runtime r=Runtime.getRuntime();
            try {
                r.exec(ex,(String[])null,File.listRoots()[0]);
            } catch (IOException exp) { //TODO gerer mieux
                exp.printStackTrace();
            }
            
        }
        
        
    }
    
}
