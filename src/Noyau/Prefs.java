/*
 * Prefs.java
 *
 * Created on 11 mai 2007, 14:46
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Noyau;

import java.util.prefs.*;
/**
 *
 * @author nezetic
 */
public class Prefs {
    private Preferences prefs;
    
    /** Creates a new instance of Prefs */
    public Prefs() {
        //prefs = Preferences.userRoot();
        prefs = Preferences.userNodeForPackage(this.getClass());
    }
    
    public String readPref(String name){
       String str = prefs.get(name, "");
       return str;
    }
    
    public void writePref(String name, String str){
       prefs.put(name,str);
    }
    
    public boolean exist(String name){
        String[] list=null;
        try {
            list = prefs.keys();
        } catch (BackingStoreException ex) {
            ex.printStackTrace();
            return false;
        }
        for(String str:list){
            if(str.compareTo(name) == 0){
                return true;
            }
        }
        return false;
    }
}
