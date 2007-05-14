/*
 * SaveAndRestore.java
 *
 * Created on 14 mai 2007, 11:00
 *$Id$
 */

package Noyau;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author zeta
 */
public class SaveAndRestore {
    public static final int BALDR=1;
    public static final int BALDRX=2;
    private Savable obj ;
    
    /**
     * Creates a new instance of SaveAndRestore
     */
    public SaveAndRestore(Savable object) {
        obj=object;
    }
    
    public void restore(File f,int format) {
        int i;
        
        try {
            FileInputStream file=new FileInputStream(f);
            InputStream st=file;
            if(format==BALDRX) {
                
                st=new GZIPInputStream(st);
                
            }
            //TODO gerer charset
            BufferedReader in=new BufferedReader(new InputStreamReader(st));
            DOMParser parser=new DOMParser();
            
            parser.parse(new InputSource(in));
            Document doc=parser.getDocument();
            for(i=0;i<doc.getChildNodes().getLength();i++){
            obj.fromDom(doc.getChildNodes().item(i));
            }
        } catch (FileNotFoundException ex) {
            //TODO lever d'erreur
            ex.printStackTrace();
        }   catch (IOException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        }
        
    }
    
    
    public void restore(File f) {
        if(Utils.Extension.getExtension(f)==Utils.Extension.baldrx) {
            restore(f,BALDRX);
        }else{
            restore(f,BALDR);
        }
        
        
    }
    
    
    public void save(File f,int format) {
        StringBuffer str;
        
        str=new StringBuffer();
        str.append("<?xml version=\"1.0\" ?>\n");
        str.append(obj.toXml());
        try {
            FileOutputStream file=new FileOutputStream(f);
            OutputStream st=file;
            if(format==BALDRX) {
                
                st=new GZIPOutputStream(st);
                
            }
            //TODO gerer charset
            BufferedWriter out=new BufferedWriter(new OutputStreamWriter(st));
            
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
