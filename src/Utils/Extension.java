/*
 * Extension.java
 *
 * Created on 14 mai 2007, 11:57
 *$Id$
 */

package Utils;

import java.io.File;

/**
 *
 * @author zeta
 */
public class Extension {
    
    public final static String baldr = "baldr";
     public final static String baldrx = "baldrx";
    
    /*
     * Get the extension of a file.
     */  
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
    
}
