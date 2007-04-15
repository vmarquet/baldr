/*
 * Task.java
 *
 * Created on 14 avril 2007, 18:20
 *$Id$
 */

package Noyau;

import java.io.File;
import Main.*;

/**
 *
 * @author zeta
 */
public class Task extends Thread{
    File[] files;
    float [][] res;
    float state;
    String stateMessage;
    int numAnalyse; //nbr de fichier
    
    /** Creates a new instance of Task */
    public Task() {
        setPriority(Thread.MIN_PRIORITY);
    }
     public Task(File[] fichs) {
      setPriority(Thread.MIN_PRIORITY);
         setFiles(fichs);
    }
    
    public void setFiles(File[] fichs)
    {
        int i,j;
       files=fichs;
       
       res= new float[fichs.length][];
       for(i=0;i<fichs.length;i++)
       {
       res[i]=new float[i+1];
       for(j=0;j<i+1;j++)
       {
       res[i][j]=-1;
       if(i==j)
       {
       res[i][j]=0;
       }
       this.numAnalyse++;
       }
       }
       
    }
    
      public void run()
    {
      for(File fich1:files){
      for(File fich2:files){
      if(fich1!=fich2 && getRes(fich1,fich2)==-1)
      {
       setRes(fich1,fich2,analyse(fich1,fich2));
       setState(fich1,fich2);
      }
      
      }
      
      }
      stateMessage="Analyse Terminée";
        //System.out.println("Finished");
    }
    
      private void setRes(int i,int j,float val)
      {
      if(res[i].length>j)
      {
      res[i][j]=val;
      }else{
      res[j][i]=val;
      }
      
      }
      
      private void setRes(File f1,File f2,float val)
      {
      int r1,r2,i;
      r1=-1;r2=-1;
      i=0;
      for(File fich:files)
      {
      if(f1==fich){r1=i;}    
      if(f2==fich){r2=i;}
      i++;
         if(r2!=-1 && r1!=-1){break;}
   }
      setRes(r1,r2,val);
      }
      
      private float getRes(File f1,File f2)
      {
      int r1,r2,i;
      r1=-1;r2=-1;
      i=0;
      for(File fich:files)
      {
        //  System.out.println("files : "+f1.getName()+" "+fich.getName()+" "+files.length);
      if(f1==fich){r1=i;}    
      if(f2==fich){r2=i;}
      i++;
      if(r2!=-1 && r1!=-1){break;}
      }
      return getRes(r1,r2);
      }
      
      private float getRes(int i,int j)
      {
          //System.out.println(i+" "+j +"("+res[i].length+":"+res[j].length+")");
      if(res[i].length>j)
      {
      return res[i][j];
      }else{
      return res[j][i];
      }
      
      }
      
      private float analyse(File fich1,File fich2)
      {
      //faisons la super anlayse :D 
      return (float)Math.random();
      }
      
      private void setState(File f1,File f2)
      {
        this.state+=1.0/this.numAnalyse;
        stateMessage="Analyse de "+f1.getName()+" vs "+f2.getName();
          //System.out.println(stateMessage);
          yield();
      }

    public float getStateCount() {
        return state;
    }

    public String getStateMessage() {
        return stateMessage;
    }
    

      

}
