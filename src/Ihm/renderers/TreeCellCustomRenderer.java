/*
 * TreeCellCustomRenderer.java
 *
 * Created on 12 avril 2007, 14:40
 *$Id$
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
        JLabel reu;
        if(obj instanceof DefaultMutableTreeNode) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)obj;
            if(node.getUserObject() instanceof File) { //deals with a file
                reu=(JLabel)rend.getTreeCellRendererComponent(tree,obj,selected,expanded,leaf,row,hasFocus);
                File file;
                boolean dir;
                String name;
                file = (File) node.getUserObject();
                dir=file.isDirectory();
                name=file.getName();
                
                if(dir){//if it's a directory, set the icon accordingly
                    if(expanded)
                        reu.setIcon(rend.getOpenIcon());
                    else
                        reu.setIcon(rend.getClosedIcon());
                } else { //if it's not a directory, it must be a file
                    reu.setIcon(rend.getLeafIcon());
                }
                reu.setText(name); // anyway, set the shot name or file/dir
            } else if(node.getUserObject() instanceof String){ //if it is the root, only string type
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
        } else{
            reu=new JLabel("n'est pas un DefaultMutableTreeNode");
        }
        return reu;
    }
}
