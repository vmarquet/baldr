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
    
    public Object readPref(String name, Object type){
        if(type instanceof Boolean){
            return prefs.getBoolean(name, (Boolean) type);
        }
        if(type instanceof Integer){
            return prefs.getInt(name, (Integer) type);
        }
        if(type instanceof Double){
            return prefs.getDouble(name, (Double) type);
        }
        if(type instanceof Float){
            return prefs.getFloat(name, (Float) type);
        }
        if(type instanceof String){
            return prefs.get(name, (String) type);
        }
       return null;
    }
    
    public String readPref(String name){
        String str = prefs.get(name, "");
        return str;
    }
    public void writePref(String name, Object type){
        if(type instanceof Boolean){
            prefs.putBoolean(name, (Boolean) type);
            return;
        }
        if(type instanceof Integer){
            prefs.putInt(name, (Integer) type);
            return;
        }
        if(type instanceof Double){
            prefs.putDouble(name, (Double) type);
            return;
        }
        if(type instanceof Float){
             prefs.putFloat(name, (Float) type);
             return;
        }
        if(type instanceof String){
            prefs.put(name, (String) type);
            return;
        }
        return;
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
    
    public void flush(){
        try {
            prefs.flush();
        } catch (BackingStoreException ex) {
            ex.printStackTrace();
        }
    }
}
