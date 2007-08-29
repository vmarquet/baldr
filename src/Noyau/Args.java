/*
 * Args.java
 *
 * Created on 29 août 2007, 22:52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Noyau;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author nezetic
 */
public class Args {
    
    /** Creates a new instance of Args */
    public Args() {
    }
    
    static public String[] getArgs(String str){
        
        int i,bp,pa,ma,len=str.length();
        String expath;
        ArrayList<String> args = new ArrayList<String>();
        
        for(i=0,bp=-1,pa=0,ma=0;i<len;i++){
            
            if(str.charAt(i) == '\\' || str.charAt(i) == '/'){
                pa=1;
            }else if(str.charAt(i) == ' ' || ma == 1){
                if(pa == 0){
                    expath = str.substring(bp+1,i+ma);
                    //System.out.println(expath);
                    args.add(expath);
                    bp=i;
                }else{
                    expath = str.substring(bp+1,i+ma);
                    if((new File(expath)).exists()){
                        //System.out.println(expath);
                        args.add(expath);
                        pa=0;
                        bp=i;
                    }
                }
                
            }
            if(i == len - 2){
                ma = 1;
            }
        }
        
        String ex [] = new String [args.size()];
        args.toArray(ex);
        args.clear();
        
        
        return ex;
        
    }
    
}
