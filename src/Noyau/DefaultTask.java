/*
 * DefaultTask.java
 *
 * Created on 6 mai 2007, 16:09
 *$Id$
 */

package Noyau;

import java.io.File;

/**
 *Basic extension of the Task asbstract classs which will display in console
 * @author zeta
 */
public class DefaultTask extends Task {
    
    /** Creates a new instance of DefaultTask */
    public DefaultTask() {
        super();
    }

    protected void printState() {
        
        System.err.println(this.stateMessage);
    }
    
    protected void finalState()
    {
          System.err.println(this.stateMessage);
          File[] fs=getFiles();
          int i,j;
          i=0;
         System.out.print("Fichiers;");
          for(File f : fs)
         {
          System.out.print(f.getName()+";");
          }
          System.out.println("");
         for(File f : fs)
         {
             j=0;
             System.out.print(f.getName()+";");
         for(File f2 : fs)
         {
         System.out.print(getRes(f,f2)+";");
             
             i++;j++;
         }
         System.out.println("");
         }
          
    }

    protected void cleanUp() {
        
    }
    
    
}
