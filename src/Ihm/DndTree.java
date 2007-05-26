/*
 * DndTree.java
 *
 * Created on 23 mai 2007, 20:10
 *
 *taken from http://www.java2s.com/Code/Java/Swing-JFC/TreeDragandDrop.htm
 *adapted to accept file drop from other applications
 *
 */
package Ihm;
import java.beans.*;
import java.io.Serializable;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.Autoscroll;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceContext;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import Ihm.TransferableTreeNode;


/**
 *
 * @author ryco
 */
public class DndTree extends JTree {
    private Insets insets;
    private static DefaultMutableTreeNode noeudDragué;
    private int top = 0, bottom = 0, topRow = 0, bottomRow = 0;
    private panelTab currentPanelTab;
    public DndTree(DefaultMutableTreeNode racine,panelTab PT) {
        super(racine);
        currentPanelTab =PT;
        DragSource dragSource = DragSource.getDefaultDragSource();
        dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY_OR_MOVE,new TreeDragGestureListener());
        DropTarget dropTarget = new DropTarget(this,new TreeDropTargetListener());
    }
    
    private Insets getAutoscrollInsets() {
        return insets;
    }
    private void autoscroll(Point pt) {
        top = Math.abs(getLocation().y) + 10;
        bottom = top + getParent().getHeight() - 20;
        int next;
        if (pt.y < top) {
            next = topRow--;
            bottomRow++;
            scrollRowToVisible(next);
        } else if (pt.y > bottom) {
            next = bottomRow++;
            topRow--;
            scrollRowToVisible(next);
        }
    }
    
    private class TreeDragGestureListener implements DragGestureListener {
        public void dragGestureRecognized(DragGestureEvent dragGestureEvent) {
            /*what to do when drag beggins
             *
             **/
            JTree tree = (JTree) dragGestureEvent.getComponent();
            TreePath path = tree.getSelectionPath();
            if (path == null) {
                // Nothing selected, nothing to drag
                System.out.println("Nothing selected - beep");
                tree.getToolkit().beep();
            } else {
                DefaultMutableTreeNode selection = (DefaultMutableTreeNode) path.getLastPathComponent();
                if (selection.isLeaf()) {
                    noeudDragué =selection;
                    TransferableTreeNode node = new TransferableTreeNode(selection);
                    dragGestureEvent.startDrag(DragSource.DefaultCopyDrop,node, new MyDragSourceListener());
                } else {
                    System.out.println("Not a leaf - beep");
                    tree.getToolkit().beep();
                }
            }
        }
    }
    
    private class TreeDropTargetListener implements DropTargetListener {
        public void dragEnter(DropTargetDragEvent dropTargetDragEvent) {
            // Setup positioning info for auto-scrolling
            top = Math.abs(getLocation().y);
            bottom = top + getParent().getHeight();
            topRow = getClosestRowForLocation(0, top);
            bottomRow = getClosestRowForLocation(0, bottom);
            insets = new Insets(top + 10, 0, bottom - 10, getWidth());
        }
        public void dragExit(DropTargetEvent dropTargetEvent) {
        }
        public void dragOver(DropTargetDragEvent dropTargetDragEvent) {
        }
        public void dropActionChanged(DropTargetDragEvent dropTargetDragEvent) {
        }
        public synchronized void drop(DropTargetDropEvent dropTargetDropEvent) {
            // what to do when dropping
            Point location = dropTargetDropEvent.getLocation();
            TreePath path = getPathForLocation(location.x, location.y);
            DefaultMutableTreeNode node;
            if (path==null ) {
                node=currentPanelTab.getLastSelectedNode();
            } else {
                node = (DefaultMutableTreeNode) path.getLastPathComponent();
            }
            if(node.isLeaf()&&!node.isRoot()&&!((File)node.getUserObject()).isDirectory()) {
                node=(DefaultMutableTreeNode)node.getParent();
                System.out.println("Taking parent of leaf...");
            }
            try {
                Transferable tr = dropTargetDropEvent.getTransferable();
                if ((tr.getTransferDataFlavors()[0]).equals(TransferableTreeNode.DEFAULT_MUTABLE_TREENODE_FLAVOR)) {
                    // when some tree nodes are moved
                    dropTargetDropEvent.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                    Object userObject = tr.getTransferData(TransferableTreeNode.DEFAULT_MUTABLE_TREENODE_FLAVOR);
                    rmvSrcElement();
                    addElement(node, userObject);
                    dropTargetDropEvent.dropComplete(true);
                } else if (tr.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                    // when files are dropped ie from windows explorer
                    dropTargetDropEvent.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                    List ListedeFile=(List)tr.getTransferData(DataFlavor.javaFileListFlavor);
                    Iterator iterator = ListedeFile.iterator();
                    File [] fileTab=new File[ListedeFile.size()];
                    int i=0;
                    while (iterator.hasNext()) {
                        fileTab[i++] = (File) iterator.next();
                    }
                    currentPanelTab.ajouterFichiers(fileTab,node);
                    dropTargetDropEvent.dropComplete(true);
                } else {
                    System.err.println("Rejected");
                    dropTargetDropEvent.rejectDrop();
                }
            } catch (IOException io) {
                io.printStackTrace();
                dropTargetDropEvent.rejectDrop();
            } catch (UnsupportedFlavorException ufe) {
                ufe.printStackTrace();
                dropTargetDropEvent.rejectDrop();
            }
        }
        // the 2 next functions are use to move node in the tree
        private void addElement(DefaultMutableTreeNode parent, Object element) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(element);
            System.out.println("Added: " + node + " to " + parent);
            DefaultTreeModel model = (DefaultTreeModel) (DndTree.this.getModel());
            model.insertNodeInto(node, parent, parent.getChildCount());
        }
        private void rmvSrcElement() {
            DefaultTreeModel model = (DefaultTreeModel) (DndTree.this.getModel());
            model.removeNodeFromParent(noeudDragué);
            System.out.println("MOVE: node removed");
        }
    }
    // listen that is used to modify the mouse cursor when dragging, can also listen for a key to be pressed
    private class MyDragSourceListener implements DragSourceListener {
        public void dragDropEnd(DragSourceDropEvent dragSourceDropEvent) {
        }
        public void dragEnter(DragSourceDragEvent dragSourceDragEvent) {
            DragSourceContext context = dragSourceDragEvent.getDragSourceContext();
            int dropAction = dragSourceDragEvent.getDropAction();
            if ((dropAction & DnDConstants.ACTION_MOVE) != 0) {
                context.setCursor(DragSource.DefaultMoveDrop);
            } else {
                context.setCursor(DragSource.DefaultCopyNoDrop);
            }
        }
        public void dragExit(DragSourceEvent dragSourceEvent) {
        }
        public void dragOver(DragSourceDragEvent dragSourceDragEvent) {
        }
        public void dropActionChanged(DragSourceDragEvent dragSourceDragEvent) {
        }
    }
}


