/*
 * Savable.java
 *
 * Created on 14 mai 2007, 11:03
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Noyau;

import org.w3c.dom.NodeList;

/**
 *
 * @author zeta
 */
public interface Savable {
    public StringBuffer toXml();
    public void fromDom(NodeList nodes);
    
}
