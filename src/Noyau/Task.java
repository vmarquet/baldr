/*
 * Task.java
 *
 * Created on 14 avril 2007, 18:20
 *$Id$
 */

package Noyau;

import java.io.File;

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
       }else{
       this.numAnalyse++;
       }
       }
       }
       
    }
    
      public void run()
    {
          int i,j;
      for(i=0;i<files.length;i++){
      for(j=0;j<files.length;j++){
      if(i!=j && getRes(i,j)==-1)
      {
       setRes(i,j,analyse(files[i],files[j]));
       setState(i,j);
      }
      
      }
      
      }
      stateMessage="Analyse Terminée";
      this.state=1;
    finalState();
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
          // TODO ecrire la vrai analyse
          
      //faisons la super anlayse :D avec sleep pour etre lent
          try{
          sleep(100);
          }catch(Exception e){};
      return (float)Math.random();
      }
      
      private void setState(int i,int j)
      {
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
    

      

}
