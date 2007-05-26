/*
 * DefaultTask.java
 *
 * Created on 6 mai 2007, 16:09
 *$Id$
 */

package Noyau;

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
        
        System.out.println(this.stateMessage);
    }
    
    protected void finalState()
    {
          System.out.println(this.stateMessage);
  
    }

    protected void cleanUp() {
        
    }
    
    
}
