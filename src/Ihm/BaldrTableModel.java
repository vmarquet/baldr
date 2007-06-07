/*
 * BaldrTableModel.java
 *
 * Created on 5 juin 2007, 18:06
 *$Id$
 */

package Ihm;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zeta
 */
public class BaldrTableModel extends AbstractTableModel{
    
    /** Creates a new instance of BaldrTableModel */
    private float [][] data;
    private float [][] orderedData;
    private boolean [][] done;
    
    private File[] files;
    private String[] headings;
    private int [] pCol;
    private boolean moy=true;
    
    public BaldrTableModel(File[] _files,float [][] _data) {
        this(_files, _data,true);
    }
    
    public BaldrTableModel(File[] _files,float [][] _data,boolean _moy) {
        int i;
        //pre-calculate headings
        headings=new String[_files.length+1];
        for(i=1;i<=_files.length;i++) {
            headings[i]=_files[i-1].getName();
        }
        headings[0]="Fichiers";
        // setting attributes
        this.files=_files;
        this.data=_data;
        this.orderedData=new float[getFiles().length][getFiles().length];
        this.done=new boolean[getFiles().length][getFiles().length];
        this.moy=_moy;
        reOrder();
    }
    
    /**
     *Reorder the matrix to enhanced user experience
     */
    
    private void reOrder() {
        int i ;
        int j;
        SortedSet<MargEl> nOrder=new  TreeSet<MargEl>();
        //        float[] marg=new float[data.length];
        
        for(i=0;i<getData().length;i++) {
            float sc=(isMoy()?0:10);
            for(j=0;j<getData().length;j++) {
                if(isMoy()){
                    sc+=getData(i,j);
                }else{
                    sc=(sc>getData(i,j)&& i!=j?getData(i,j):sc);
                }
                //       sc+=getData(i,j);
            }
            nOrder.add(new MargEl(i,sc));
        }
        
        //newOrderCol map exOrder with new
        int[] newOrderCol=new int[getFiles().length];
        //   int[] newOrderLin=new int[files.length];
        i=0;
        String [] nheadings=headings.clone();
        String [] nlheadings=headings.clone();
        int p=0;
        for(MargEl e:nOrder) {
            
            p=i/2;
            newOrderCol[i]=e.getRank();
            nheadings[i+1]=headings[e.getRank()+1];
            
            i++;
        }
        headings=nheadings;
        //newOrderLin=newOrderCol;
        pCol=newOrderCol;
        for(i=0;i<getData().length;i++){
            for(j=0;j<getData().length;j++) {
                //setOrderedData(i,j,getData(newOrderCol[i],newOrderCol[j]));
                orderedData[i][j]=getData(newOrderCol[i],newOrderCol[j]);
            }
        }
        
        
        
    }
    
    
    public int getRowCount() {
        return getData().length;
    }
    
    public int getColumnCount() {
        return getFiles().length+1;
    }
    
    public File getColumnFile(int columnIndex) {
        return getFiles()[pCol[columnIndex-1]];
    }
    
    public File getRowFile(int columnIndex) {
        return getFiles()[pCol[columnIndex]];
    }
    
    public String getColumnName(int columnIndex) { //TODO : accord avec tri
        return headings[columnIndex];
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        //TODO : accord avec tri
        if(columnIndex==0)
            return headings[rowIndex+1];
        
        //    return getOrderedData(rowIndex,columnIndex-1);
        return  new TableCell(orderedData[rowIndex][columnIndex-1],done[rowIndex][columnIndex-1]);
    }
    
    private float valRead(float [][] tab,int i,int j){
        if(i==j) return 0;
        if(tab[i].length>j) {
            return tab[i][j];
        }else{
            return tab[j][i];
        }
    }
    
    private float  getData(int i,int j){return valRead(getData(), i,j);}
    
    public void toggleDone(int i,int j) {
        //pCol[columnIndex-1]
        done[i][j-1] ^= true;
        // ! WARNING
        done[j-1][i] ^= true;
        
        
        
    }
    
    public void toggleMethod() {
        int i;
        this.moy ^=true;
       
          headings=new String[files.length+1];
        for(i=1;i<=files.length;i++) {
            headings[i]=files[i-1].getName();
        }
        headings[0]="Fichiers";
 reOrder();
 //fireTableStructureChanged();
    fireTableDataChanged();    
//        fireTableStructureChanged();
    }
    
    private class MargEl implements Comparable {
        private int rank;
        private float value;
        public MargEl(int r,float val) {
            rank=r;
            value=val;
        }
        public int compareTo(Object o) {
            
            
            float v=((MargEl) o).getValue();
            if(v>value)
                return -1;
            //  return (v!=value?1:0);
            return 1; // never equals ( loosing obj else)
            
            
        }
        
        public float getValue() {
            return value;
        }
        
        public int getRank() {
            return rank;
        }
        
        
        
    }

    public float[][] getData() {
        return data;
    }

    public File[] getFiles() {
        return files;
    }

    public boolean isMoy() {
        return moy;
    }

    public String[] getHeadings() {
        return headings;
    }
    
}



