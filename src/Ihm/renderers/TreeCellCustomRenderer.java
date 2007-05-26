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
 * Renderer for the cell in the Jtree 
 *@see TreeCellCustomRenderer
 * @author Baldr Team
 */
public class TreeCellCustomRenderer implements TreeCellRenderer{
    
    /** Creates a new instance of TreeCellCustomRenderer */
    private DefaultTreeCellRenderer rend;
    
    public TreeCellCustomRenderer() {
        rend=new DefaultTreeCellRenderer();
    }
    
    
    public Component getTreeCellRendererComponent(JTree tree, Object obj,boolean selected,
            boolean expanded, boolean leaf, int row, boolean hasFocus){
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
        JLabel reu;
        if(obj instanceof DefaultMutableTreeNode) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)obj;
            if(node.getUserObject() instanceof File) {
                reu=(JLabel)rend.getTreeCellRendererComponent(tree,obj,selected,expanded,leaf,row,hasFocus);
                
                //deals with a file
                
                File file;
                boolean dir;
                String name;
                file = (File) node.getUserObject();
                dir=file.isDirectory();
                name=file.getName();
                
                if(dir){
                    if(expanded)
                        reu.setIcon(rend.getOpenIcon());
                    else
                        reu.setIcon(rend.getClosedIcon());
                } else {
                    reu.setIcon(rend.getLeafIcon());
                }
                reu.setText(name);
            } else if(node.getUserObject() instanceof String){
                //root is string
                String str =(String)node.getUserObject();
                reu=new JLabel(str);
                if(expanded)
                    reu.setIcon(rend.getOpenIcon());
                else
                    reu.setIcon(rend.getClosedIcon());
            } else {//node type isn't recognised
                reu=new JLabel("type de noeud non reconnu");
            }
            
            //System.out.println(node.getUserObject().getClass().toString());
        } else{
            reu=new JLabel("n'est pas un DefaultMutableTreeNode");
            
        }
        return reu;
        
        
    }
}
