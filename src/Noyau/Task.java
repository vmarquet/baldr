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
public class Task {
    File[] files;
    /** Creates a new instance of Task */
    public Task() {
    }
    
    public void setFiles(File[] fichs)
    {
        files=fichs;
       return;
    }
    
}
