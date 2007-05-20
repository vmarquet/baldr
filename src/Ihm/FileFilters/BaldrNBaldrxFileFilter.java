/*
 * BaldrNBaldrxFileFilter.java
 *
 * Created on 20 mai 2007, 17:44
 *$Id$
 */

package Ihm.FileFilters;

import Utils.Extension;
import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author zeta
 */
public class BaldrNBaldrxFileFilter extends FileFilter {
    
    /** Creates a new instance of BaldrNBaldrxFileFilter */
    public BaldrNBaldrxFileFilter() {
    }

    public boolean accept(File f) {
                if(f.isDirectory()) return true;
        String ext=Extension.getExtension(f);
        if(ext!=null && (ext.equalsIgnoreCase(Extension.baldr) || ext.equalsIgnoreCase(Extension.baldrx)))
        {
        return true;
        }else{
        return false;
        }
    }

    public String getDescription() {
          return "Tous les fichiers Baldr (."+Extension.baldr+", ."+Extension.baldrx+")";
    }
    
}
