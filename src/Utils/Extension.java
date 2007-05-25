/*
 * Extension.java
 *
 * Created on 14 mai 2007, 11:57
 *$Id$
 */

package Utils;

import java.io.File;

/**
 * Class handling the extensions of the files
 * @author zeta
 */
public class Extension {
    /** Constant for the uncompressed baldr format extension name*/
    public final static String baldr = "baldr";
    /** Constant for the compressed baldr format extension name*/
    public final static String baldrx = "baldrx";
    
    /**
     * Give the extension of a file
     * @param f file whose extension is requested
     * @return String file extension (after the .) or null
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
