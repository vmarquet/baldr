/*
 * Save.java
 *
 * Created on 14 mai 2007, 11:00
 *$Id$
 */

package Noyau;

import java.io.File;

/**
 *
 * @author zeta
 */
public class Save {
    
    /** Creates a new instance of Save */
    public Save(Savable obj) {
    }
  
    
    public void write(File f)
    {
    System.out.println(f.getName());
    }
}
