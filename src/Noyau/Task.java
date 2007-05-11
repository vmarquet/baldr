/*
 * Task.java
 *
 * Created on 14 avril 2007, 18:20
 *$Id$
 */

package Noyau;

import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author zeta
 */
public abstract class Task extends Thread{
    private File[] files;
    private float [][] res;
    protected float state=0;
    protected String stateMessage="";
    private int numAnalyse; //nbr de fichier
    protected float meanDist;
    
    /** Creates a new instance of Task */
    public Task() {
        setPriority(Thread.MIN_PRIORITY);
    }
    public Task(File[] fichs) {
        setPriority(Thread.MIN_PRIORITY);
        setFiles(fichs);
    }
    
    public void setFiles(File[] fichs) {
        int i,j;
        files=fichs;
        
        res= new float[fichs.length][];
        for(i=0;i<fichs.length;i++) {
            res[i]=new float[i+1];
            for(j=0;j<i+1;j++) {
                res[i][j]=-1;
                if(i==j) {
                    res[i][j]=0;
                }else{
                    this.numAnalyse++;
                }
            }
        }
        
    }
    
    private static OutputStream makeComp(OutputStream gzos, File file) throws IOException{
        byte[] buf = new byte[1024];
        int len;
        
        FileInputStream fin = new FileInputStream(file);
        BufferedInputStream in = new BufferedInputStream(fin);
        
        while ((len = in.read(buf)) >= 0) {
            gzos.write(buf,0,len);
        }
        in.close();
        
        return gzos;
    }
    
    private static long getGZipSize(File file)
    throws IOException, FileNotFoundException {
        long ret = 0;
        
        ByteArrayOutputStream fos = new ByteArrayOutputStream();
        GZIPOutputStream gzos = new GZIPOutputStream(fos);
        
        makeComp(gzos,file);
        
        gzos.close(); // Complete l'archive et la clot
        
        ret = fos.size();
        fos.close();
        
        return ret;
    }
    
    private static long getGZipSize(File file, File file2)
    throws IOException, FileNotFoundException {
        long ret = 0;
        
        ByteArrayOutputStream fos = new ByteArrayOutputStream();
        GZIPOutputStream gzos = new GZIPOutputStream(fos);
        
        makeComp(gzos,file);
        makeComp(gzos,file2);
        
        gzos.close(); // Complete l'archive et la clot
        
        ret = fos.size();
        fos.close();
        
        return ret;
    }
    
    public void run() {
        int i,j,k, nbr = files.length;
        long ci, cj, cij;
        
        meanDist = k = 0;
        ci = cj = cij = 0L;
        
        System.out.println("# " + files.length + " files");
        
        for(i=0;i<nbr;i++){
            
            System.out.println("# filename : " + files[i].getName());
            try  {
                ci =  getGZipSize(files[i]);
                
                System.out.println("# file size: " + files[i].length());
                System.out.println("# compressed file size: " + ci);
            } catch (Exception e) {
                System.err.println(e);
            }
            
            for(j=0;j<i;j++){
                cj = files[j].length();
                try  {
                    cij = getGZipSize(files[i],files[j]);
                } catch (Exception e) {
                    System.err.println(e);
                }
                setRes(i,j,1F-(float)(ci + cj - cij) / (float) java.lang.Math.max(ci,cj));
                setState(i,j);
                
                System.out.println(" # pair " + files[i].getName()+"<->" + files[j].getName() + " = " + getRes(i,j));
                meanDist += getRes(i,j);
                k++;
            }
        }
        
        meanDist /= (float)k;
        System.out.println("# meanDist = "+meanDist);
        
        stateMessage="Analyse Terminée";
        this.state=1;
        finalState();
        //System.out.println("Finished");
    }
    
    private void setRes(int i,int j,float val) {
        if(res[i].length>j) {
            res[i][j]=val;
        }else{
            res[j][i]=val;
        }
        
    }
    
    private void setRes(File f1,File f2,float val) {
        int r1,r2,i;
        r1=-1;r2=-1;
        i=0;
        for(File fich:files) {
            if(f1==fich){r1=i;}
            if(f2==fich){r2=i;}
            i++;
            if(r2!=-1 && r1!=-1){break;}
        }
        setRes(r1,r2,val);
    }
    
    public float getRes(File f1,File f2) {
        int r1,r2,i;
        r1=-1;r2=-1;
        i=0;
        for(File fich:files) {
            //  System.out.println("files : "+f1.getName()+" "+fich.getName()+" "+files.length);
            if(f1==fich){r1=i;}
            if(f2==fich){r2=i;}
            i++;
            if(r2!=-1 && r1!=-1){break;}
        }
        return getRes(r1,r2);
    }
    
    public float getRes(int i,int j) {
        //System.out.println(i+" "+j +"("+res[i].length+":"+res[j].length+")");
        if(res[i].length>j) {
            return res[i][j];
        }else{
            return res[j][i];
        }
        
    }
    
    private void setState(int i,int j) {
        this.state+=1.0/this.numAnalyse;
        stateMessage="Analyse de "+files[i].getName()+" vs "+files[j].getName();
        printState();
    }
    
    protected abstract void printState();
    protected abstract void finalState();
    
    public float getStateCount() {
        return state;
    }
    
    public String getStateMessage() {
        return stateMessage;
    }
    
    public int getNumAnalyse() {
        return numAnalyse;
    }
    
    
    public File[] getFiles() {
        
        return files;
    }
    
    
    
    
}
