/*
 * Task.java
 *
 * Created on 14 avril 2007, 18:20
 *$Id$
 */

package Noyau;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.zip.GZIPOutputStream;
import org.w3c.dom.*;

/**
 *
 * @author zeta
 */
public abstract class Task extends Thread implements Savable{
    private File[] files=null;
    private float [][] res;
    protected int size;
    protected float state=0;
    protected String stateMessage="";
    private int numAnalyse; //nbr de fichier
    public float meanErr;
    protected float meanDist;
    private Map<File,Long> pCalc;
    private boolean stopNow;
    
    /** Creates a new instance of Task */
    public Task() {
        setPriority(Thread.MIN_PRIORITY);
        stopNow=false;
        pCalc=new HashMap<java.io.File,java.lang.Long>();
    }
    public Task(File[] fichs) {
        setPriority(Thread.MIN_PRIORITY);
        setFiles(fichs);
        stopNow=false;
        pCalc=new HashMap<java.io.File,java.lang.Long>();
    }
    
    protected abstract void stopG3d();
    
    public void stopAnalysis(){
        stopMeNow();
        stopG3d();
    }
    
    private void stopMeNow(){
        stopNow=true;
        while(this.isAlive()){};
    }
    
    public void setFiles(File[] fichs) {
        int i,j;
        files=fichs;
        
        size = fichs.length;
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
    
    private long getGZipSize(File file)
    throws IOException, FileNotFoundException {
        long ret = 0;

        if(pCalc.containsKey(file)){
            return ((Long) pCalc.get(file)).longValue();
        }
        OutputStreamSizer fos=new OutputStreamSizer();
        BufferedOutputStream bfos=new BufferedOutputStream(fos);
    
     //   ByteArrayOutputStream fos = new ByteArrayOutputStream();
        GZIPOutputStream gzos = new GZIPOutputStream(fos);
        
        makeComp(gzos,file);
        
        gzos.close(); // Complete l'archive et la clot
    
        ret = fos.getSize();
         // ret = fos.size();
        fos.close();
        fos=null;
        pCalc.put(file,new Long(ret));

        return ret;
    }
    
    private static long getGZipSize(File file, File file2)
    throws IOException, FileNotFoundException {
        long ret = 0;
        
        //ByteArrayOutputStream fos = new ByteArrayOutputStream();
        OutputStreamSizer fos=new OutputStreamSizer();
        BufferedOutputStream bfos=new BufferedOutputStream(fos);
        GZIPOutputStream gzos = new GZIPOutputStream(bfos);
        
        makeComp(gzos,file);
        makeComp(gzos,file2);
        
        gzos.close(); // Complete l'archive et la clot
        
        ret = fos.getSize();
        //System.out.println(ret+"<---taille");
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
                // Annulation ?
                if(stopNow)
                    return;
                try {
                    cj =  getGZipSize(files[j]);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if(getRes(i,j)==-1){
                    try  {
                        cij = getGZipSize(files[i],files[j]);
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                    setRes(i,j,1F-(float)(ci + cj - cij) / (float) java.lang.Math.max(ci,cj));
                }else{
                    System.out.println("cached");
                }
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
    
    public void setExRes(File[] exfiles,float[][] exres) {
        File[] fichs;
        int i,j;
        fichs=files;
        
        Map<File,Integer> aInd=new HashMap<java.io.File,java.lang.Integer>();
        
        for(i=0;i<exfiles.length;i++) {
            aInd.put(exfiles[i],new Integer(i));
        }
        
        
        for(i=0;i<fichs.length;i++) {
            {
                if(aInd.containsKey(fichs[i])) //old file
                {
                    for(j=i;j<fichs.length;j++) { //pas besoin de repasser avant
                        if(aInd.containsKey(fichs[j])) //old file
                        {
                            int ii,jj;
                            ii=aInd.get(fichs[i]);
                            jj=aInd.get(fichs[j]);
                            if(jj>ii) {
                                int t;
                                t=ii;
                                ii=jj;
                                jj=t;
                                
                            }
                            
                            setRes(i,j,exres[ii][jj]);
                        }
                    }
                    
                }
            }
        }
        
        
        
    }
    
    
    public float[][] getResults() {
        return res;
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
    
    public StringBuffer toXml() {
        StringBuffer str=new StringBuffer();
        str.append("<analys>\n");
        
        str.append("<fichs>\n");
        for(File f:files) {
            str.append("<file>").append(SaveAndRestore.escape(f.getAbsolutePath())).append("</file>\n");
        }
        str.append("</fichs>\n");
        str.append("<res len=\"").append(res.length).append("\">\n");
        int i=0;
        for(float [] t : res) {
            str.append("<li len=\"").append(t.length).append("\">\n");
            int j=0;
            for(float f:t) {
                str.append("<l i=\"").append(i).append("\" j=\"").append(j).append("\">").append(f).append("</l>\n");
            //    System.out.println(i+" "+j);
                j++;
            }
              str.append("</li>\n");
          
            i++;
        }
        str.append("</res>\n");
        str.append("</analys>\n");
        
        
        return str;
        
    }
    
    public void fromDom(Node node)
    {
        int k;
        int i,j;
           File [] filelist=null;
           float[][] resmat=null;  
      
            if(node.getNodeName()=="analys")
            {
               NodeList ana=node.getChildNodes();
               for(j=0;j<ana.getLength();j++)
               {
                   if(ana.item(j).getNodeName()=="fichs")
                   {
                       NodeList fichml=ana.item(j).getChildNodes();
                  ArrayList<File> list=new ArrayList<File>();
                   for(k=0;k<fichml.getLength();k++)
                   {
                   if(fichml.item(k).getNodeName()=="file")
                   {
                   File f=new File(fichml.item(k).getTextContent().trim());
                   if(f!=null)
                  list.add(f);
                   }
                   }
                  filelist=new File[1];
                  filelist= (File[]) list.toArray(filelist);
                       
                   }else if(ana.item(j).getNodeName()=="res")
                   {
                   resmat=getMat(Integer.parseInt(ana.item(j).getAttributes().getNamedItem("len").getTextContent()),ana.item(j).getChildNodes()) ;
                   }
               }
                
                
                setExRes(filelist,resmat);
            
            }
        
        
    }

    private float[][] getMat(int i, NodeList l) {
        int j;
        int b;
        int a;
        float[][] mat= new float[i][];
        a=0;
        for(i=0;i<l.getLength();i++)
        {
        if(l.item(i).getNodeName()=="li")
        {
            NodeList m=l.item(i).getChildNodes();
            b=0;
            mat[a]=new float[Integer.parseInt(l.item(i).getAttributes().getNamedItem("len").getTextContent())];
        for(j=0;j<m.getLength();j++)
        {
                
        if(m.item(j).getNodeName()=="l")
        {
            mat[a][b]=Float.parseFloat(m.item(j).getTextContent());
            
        b++;
        }
        }    
        
        a++;
        }
        }
        return mat;
    }
    
}
