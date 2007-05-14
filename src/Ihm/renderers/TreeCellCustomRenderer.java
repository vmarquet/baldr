/*
 * TreeCellCustomRenderer.java
 *
 * Created on 12 avril 2007, 14:40
 *$Id: TreeCellCustomRenderer.java 100 2007-05-06 22:24:31Z zeta $
 */

package Ihm.renderers;

import javax.swing.tree.*;
import javax.swing.*;
import java.awt.Component;
import java.io.File;
import javax.swing.tree.DefaultTreeCellRenderer;
/**
 *
 * @author Baldr Team
 */
public class TreeCellCustomRenderer implements TreeCellRenderer{
    
    /** Creates a new instance of TreeCellCustomRenderer */
     private DefaultTreeCellRenderer rend;
   
    public TreeCellCustomRenderer() {
          rend=new DefaultTreeCellRenderer();
    }
    
    
 public Component getTreeCellRendererComponent(JTree tree, Object obj,
 boolean selected, boolean expanded, boolean leaf,
 int row, boolean hasFocus){
     /*
JLabel toto ;
 DefaultMutableTreeNode node = (DefaultMutableTreeNode)obj;
  
 if(node==tree.getModel().getRoot()){       
 
toto = new JLabel((String)node.getUserObject());
 
 }else{
      File file = (File) node.getUserObject();

 toto = new JLabel(file.getName());
 
 }
 
 return toto;*/
     
            JLabel reu=(JLabel)rend.getTreeCellRendererComponent(tree,obj,selected,expanded,leaf,row,hasFocus);
      DefaultMutableTreeNode node = (DefaultMutableTreeNode)obj;
  
 if(node!=tree.getModel().getRoot())       
 {
   
        File file = (File) node.getUserObject();
        reu.setText(file.getName());
   
  
 }
     
 return reu;
 
 
 }
}
