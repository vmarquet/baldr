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
    
    protected float[][] errorMatrix = null;
    protected float meanDist;
    public float meanErr;
    protected int dimension;
    protected float[][] vectors = null;
    protected float[] errorVector = null;
    protected boolean[] selection = null;
    
    
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
    
    private void allocateVectors(int dim) {
        dimension = 3;  //!! 3D for display
        vectors = new float[size][dimension]; //allocation
        int i,j;
        Random r = new Random();
        for(i=0;i<size;i++)  //random init
            for(j=0;j<dimension;j++) {
            vectors[i][j] = r.nextFloat()*0.1F; //!!param
            }
    }
    private void shuffle(int[] tab) {
        int n,i,j;
        Random r = new Random();
        for(n=0;n<tab.length;n++) {
            i = r.nextInt(tab.length);
            j = r.nextInt(tab.length);
            tab[i] = tab[i] ^ tab[j];
            tab[j] = tab[i] ^ tab[j];
            tab[i] = tab[i] ^ tab[j];
        }
    }
    
    private float distanceVector(int i, int j) {
        float d = 0;
        int k;
        //System.out.println("#DistanceVector");
        for(k=0;k<dimension;k++) {
            //System.out.println(vectors[i][k]+" "+vectors[j][k]);
            d += (float)Math.pow((double)(vectors[i][k]-vectors[j][k]),2D);
        }
        d = (float)(Math.sqrt(d));// was /(float)dimension; //!! //??
        //System.out.println("d = "+d);
        return d;
    }
    
    public void dist2vect() {
        errorMatrix = new float[size][size];
        
        // allocate "size" vectors of dimension "dim"
        allocateVectors(3); //!! 3D display
        int i,j,k;
        
        //then searches (itertatively) vector coordinates from distance matrix
        // until reaching acceptable error.
        float[] vij = new float[dimension];
        int x,y,tmp;
        //Random r = new Random();
        int randTabI[] = new int[size];
        int randTabJ[] = new int[size];
        float norm, dist, howMuch;
        int iter = 0;
        
        //init randTab i & j
        for(i=0;i<size;i++) {
            randTabI[i] = i;
            randTabJ[i] = i;
        }
        do {
            // move vector coordinate to satisfy distanceMatrix information
            // TODO synchronized vector moves (with tmp vectors)!!
            //to empowerrandom selection of vector i & j
            
            //test
                       /*for(i=0;i<size;i++)
                          System.out.print(randTabI[i]+" ");
                          System.out.println();
                        */
            howMuch = 0;
            shuffle(randTabI);
            for(i=0;i<size;i++) { // sweep all vector pair i & j randomly (better result expected)
                x = randTabI[i];
                shuffle(randTabJ);
                for(j=0;j<size;j++) { //size was i
                    y = randTabJ[j];
                    
                    // restore old formula (without rand selection), almost...
                    x = i;
                    y = j;
                    
                    if (y>x) { //ensure y<x
                        tmp = x;
                        x = y;
                        y = tmp;
                    }
                    if (x==y)
                        continue;
                    //compute vij (vxy in fact...)
                    for(k=0;k<dimension;k++)
                        vij[k] = vectors[y][k]-vectors[x][k]; // xy was ij
                    
                    // normalise vij
                    norm = 0F; // init
                    for(k=0;k<dimension;k++)
                        norm += vij[k]*vij[k];
                    norm = (float)Math.sqrt(norm);
                    if (norm != 0)
                        for(k=0;k<dimension;k++)
                            vij[k] /= norm;
                    
                    //heuristic
                    float threshold = 0.23F; //param!!
                    dist = distanceVector(x,y);
                    if ((getRes(x,y) >= threshold) && (dist >= threshold)) {
                        //System.out.println("continue!!");
                        continue;
                    }
                    //System.out.println("test distanceMatrix["+x+"]["+y+"]"+distanceMatrix[x][y]);
                    // check kind of move : >-< or <->
                    if (dist > getRes(x,y)) // xy was ij
                        howMuch = 0.01F*(float)Math.pow(1F-getRes(x,y),1); // get i & j closer
                    else
                        howMuch = -0.01F; // set i & j apart
                    // modify vector i & j according to distance goal
                    // move vector i & j
                    for(k=0;k<dimension;k++) {
                        vectors[x][k] += howMuch*vij[k]; // xy was ij
                        vectors[y][k] -= howMuch*vij[k]; // xy was ij
                    }
                } // end of j loop
            } // end of i loop
            // compute meanErr & erroMatrix
            float err=0F;
            meanErr = 0;
            for(i=0;i<size;i++)
                for(j=0;j<i;j++) {
                err = Math.abs(getRes(i,j)-distanceVector(i,j));
                meanErr += err;
                errorMatrix[i][j] = err;
                errorMatrix[j][i] = err;
                //System.out.println("err = "+err);
                }
            meanErr /= ((float)(size*(size-1F)/2F)); // normalize meanErr
            //System.out.println("#meanErr = "+meanErr);
        } while((meanErr>0.00001)&&(iter++ < 10000));
        // param!! (2x) //END OF MAIN LOOP
        
        System.out.println("#meanErr = "+meanErr+" (iter="+iter+")");
    }
    
    public void filter(float errorThreshold) { // select points/vectors that have "correct" distance
        int i,j;                    // only call after dist2vect call that sets the errorMatrix values
        float err, maxErr;
        
        errorVector = new float[size];
        int[][] m = new int[size][size];
        for(i=0;i<size;i++) // init m
            for(j=0;j<size;j++)
                m[i][j] = 0;
        selection = new boolean[size];
        
        for(i=0;i<size;i++) {
            errorVector[i] = 0F;
            maxErr = 0F;
            for(j=0;j<size;j++) {
                if (i==j)
                    continue;
                err = errorMatrix[j][i];
                if (err >= errorThreshold)
                    m[i][j]++;
                if (err >= maxErr)
                    maxErr = err;
            }
            errorVector[i] = maxErr;
            if (maxErr <= errorThreshold) // to delete when doing following TODO (see below)
                selection[i] = true;
            else
                selection[i] = false;
        }
        // TODO better selection using greedy algo' to get rid of worst placed vectors
        // use boolean matrix m to wisely select vectors/points
        for(i=0;i<size;i++) {
            //selection[i] = true; // be optimistic assume selection
            for(j=0;j<size;j++) {
                //if (m[i][j]) { // there is an error > threshold on (i, j) pair
                //      selection[i]
                //}
            }
        }
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
            str.append("<file>").append(f.getAbsolutePath()).append("</file>\n");
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
