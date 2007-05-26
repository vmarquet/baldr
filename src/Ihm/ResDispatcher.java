/*
 * ResDispatcher.java
 *
 * Created on 6 mai 2007, 17:25
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 *
 *$Id$
 */

package Ihm;

/**
 * Interface which direct the communication between the analysis thread and the gui
 *
 * @author zeta
 */
public interface ResDispatcher {
    /** Callback for dispalying the results */
    public void DispatchResult();
    /** Callback for displaying the 3D graph 
     * @param vectors 3D vectors to plot
     */
    public void Dispatch3DResult(float[][] vectors);
}
