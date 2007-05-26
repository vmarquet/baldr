/*
 * BaldrxFileFilter.java
 *
 * Created on 19 mai 2007, 18:06
 *$Id$
 */

package Ihm.FileFilters;

import java.io.File;
import javax.swing.filechooser.FileFilter;
import Utils.*;

/**
 * Class describing the filefilter which accepts compressed baldr files
 * @author zeta
 */
public class BaldrxFileFilter extends FileFilter{
    
    /** Creates a new instance of BaldrxFileFilter */
    public BaldrxFileFilter() {
    }
  /** Whether the file is acceptable*/
    public boolean accept(File f) {
        if(f.isDirectory()) return true;
        String ext=Extension.getExtension(f);
        if(ext!=null && ext.equalsIgnoreCase(Extension.baldrx) )
        {
        return true;
        }else{
        return false;
        }
//                return f!=null && (f.isDirectory() || Extension.getExtension(f).equalsIgnoreCase(Extension.baldrx));
    }
 /** Description for the filechooser*/
     public String getDescription() {
         return "Fichiers Baldr compressés (."+Extension.baldrx+")";
    }
    
}
