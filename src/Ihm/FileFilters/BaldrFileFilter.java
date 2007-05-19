/*
 * BaldrFileFilter.java
 *
 * Created on 19 mai 2007, 18:01
 *$Id$
 */

package Ihm.FileFilters;

import java.io.File;
import javax.swing.filechooser.FileFilter;
import Utils.*;

/**
 *
 * @author zeta
 */
public class BaldrFileFilter extends FileFilter{
    
    /** Creates a new instance of BaldrFileFilter */
    public BaldrFileFilter() {
    }

    public boolean accept(File f) {
        if(f.isDirectory()) return true;
        String ext=Extension.getExtension(f);
        if(ext!=null && ext.equalsIgnoreCase(Extension.baldr) )
        {
        return true;
        }else{
        return false;
        }
//        return f!=null && (f.isDirectory() || Extension.getExtension(f).equalsIgnoreCase(Extension.baldr));
        
    }

    public String getDescription() {
        return "Fichiers Baldr (."+Extension.baldr+")";
    }
    
}
