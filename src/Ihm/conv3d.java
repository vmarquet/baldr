/*
 * conv3d.java
 *
 * Created on 16 mai 2007, 10:09
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Ihm;

import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author nezetic
 */
public class conv3d extends Thread{
    protected float[][] errorMatrix = null;
    protected float meanDist;
    private float meanErr;
    private float maxErr;
    protected int dimension;
    protected int size;
    protected float[][] vectors = null;
    protected float[] errorVector = null;
    protected boolean[] selection = null;
    
    private float [][] res;
    private JProgressBar bar;
    private JLabel statusbar;
    
    private boolean stopNow;
    
    private ResDispatcher recall;
    
    /** Creates a new instance of conv3d */
    public conv3d(float [][] res, JProgressBar bar,JLabel statusbar, ResDispatcher recall) {
        this.res = res;
        this.bar = bar;
        this.statusbar = statusbar;
        this.recall = recall;
        stopNow=false;
        setPriority(Thread.MIN_PRIORITY);
    }
    
     private void setRes(int i,int j,float val) {
        if(res[i].length>j) {
            res[i][j]=val;
        }else{
            res[j][i]=val;
        }
        
    }
    
    public float getRes(int i,int j) {
        //System.out.println(i+" "+j +"("+res[i].length+":"+res[j].length+")");
        if(res[i].length>j) {
            return res[i][j];
        }else{
            return res[j][i];
        }
        
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
            if(i != j){
                tab[i] = tab[i] ^ tab[j];
                tab[j] = tab[i] ^ tab[j];
                tab[i] = tab[i] ^ tab[j];
            }
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
    
    private void dist2vect() {
        errorMatrix = new float[size][size];
        
        // allocate "size" vectors of dimension "dim"
        allocateVectors(3); //!! 3D display
        int i,j,k,x,y;
        
        //then searches (itertatively) vector coordinates from distance matrix
        // until reaching acceptable error.
        float[] vij = new float[dimension];
        
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
                    
                    if (x==y)
                        continue;
                    
                    if (y>x) { //ensure y<x
                        x = x ^ y;
                        y = x ^ y;
                        x = x ^ y;
                    }
                    
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
                    // TODO Voir pour cette histoire de param
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
            maxErr = 0F;
            
            for(i=0;i<size;i++)
                for(j=0;j<i;j++) {
                err = Math.abs(getRes(i,j)-distanceVector(i,j));
                if(err >= maxErr)
                       maxErr = err;
                meanErr += err;
                errorMatrix[i][j] = err;
                errorMatrix[j][i] = err;
                //System.out.println("err = "+err);
                }
            meanErr /= ((float)(size*(size-1F)/2F)); // normalize meanErr
            updateBar((float)iter/10000F);
            //System.out.println("#meanErr = "+meanErr);
            // Annulation ?
            if(stopNow)
                return;
        } while((meanErr>0.00001)&&(iter++ < 10000));
        // param!! (2x) //END OF MAIN LOOP
        
        System.out.println("#meanErr = "+meanErr+" (iter="+iter+")");
        System.out.println("#maxErr = "+maxErr);
    }

    private void updateBar(float a){
        int m;
        m=bar.getMaximum();
        bar.setValue(Math.round(a*m));
    }
    
    public void run(){
        statusbar.setText(recall.getMsgs().getString("3D_Calc"));
        size = res.length;
        dist2vect();
        //filter(1000.00F); // Run only after dist2vect
        if(!stopNow){
            statusbar.setText(recall.getMsgs().getString("Finished_Analysing"));
            recall.Dispatch3DResult(vectors);
        }
    }

    public void stopNow(){
        stopNow=true;
        while(this.isAlive()){};
    }
    
    public float getMeanErr() {
        return meanErr;
    }
    
    public float getMaxErr() {
        return maxErr;
    }
    
}

