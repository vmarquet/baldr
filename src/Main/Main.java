/*
 * Main.java
 *
 * Created on 10 avril 2007, 14:42
 *$Id$
 */

package Main;
import Ihm.windowBalder;
import Noyau.*;
/**
 *
 * @author Baldr Team
 */
public class Main {
    public static Main main;
    public static windowBalder ihm;
    public static Noyau noyau;
    public static final int MAXONGLET=10;
    
    /** Creates a new instance of Main */
    public Main() {
    }
    
    public static void main(String args[]) {
    main=new Main();

    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
        noyau = new Noyau();
        }
    });
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
        ihm = new windowBalder();
        }
    });
    }
    
}
