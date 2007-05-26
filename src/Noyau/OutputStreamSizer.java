/*
 * OutputStreamSizer.java
 *
 * Created on 26 mai 2007, 10:46
 *$Id$
 */

package Noyau;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
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
     @param byte to write
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


