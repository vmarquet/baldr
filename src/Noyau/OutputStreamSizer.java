/*
 * OutputStreamSizer.java
 *
 * Created on 26 mai 2007, 10:46
 *$Id$
 */

package Noyau;

import java.io.IOException;
import java.io.OutputStream;


/**
 *Class which mimics a real stream but doesn't actually do anything besides counting
 *
 * @author zeta
 */
public class OutputStreamSizer extends OutputStream{
    private long size;
    /** Creates a new instance of OutputStreamSizer */
    public OutputStreamSizer() {
        size=0;
    }
    /** write character to the counting unit
     @param b byte to write as int
     */
    public void write(int b) throws IOException {
        size++;
    }
/** Return the readed size
 @return size readed from the beginning*/
    public long getSize() {
        return size;
    }
    
}


