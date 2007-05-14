/*
 * SaveAndRestore.java
 *
 * Created on 14 mai 2007, 11:00
 *$Id$
 */

package Noyau;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.zip.GZIPOutputStream;

/**
 *
 * @author zeta
 */
public class SaveAndRestore {
    public static final int BALDR=1;
    public static final int BALDRX=2;
    
    
    StringBuffer str;
    /**
     * Creates a new instance of SaveAndRestore
     */
    public SaveAndRestore(Savable obj) {
        str=new StringBuffer();
        str.append("<?xml version=\"1.0\" ?>\n");
        str.append(obj.toXml());
        
        
        
    }
    
    
    public void write(File f,int format) {
        try {
            FileOutputStream file=new FileOutputStream(f);
            OutputStream st=file;
            if(format==BALDRX) {
                
                st=new GZIPOutputStream(st);
                
            }
            //TODO gerer charset
            OutputStreamWriter out=new OutputStreamWriter(st);
            
            out.write(str.toString());
            out.close();
            st.close();
        } catch (FileNotFoundException ex) {
            //TODO lever d'erreur
            ex.printStackTrace();
        }   catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
      
    }
}
