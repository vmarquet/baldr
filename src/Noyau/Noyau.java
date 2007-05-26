/*
 * Noyau.java
 *
 * Created on 14 avril 2007, 17:35
 *$Id$
 */

package Noyau;
import Ihm.JTask;
import Ihm.ResDispatcher;
import Main.*;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 * Kernel Main File creates Task and keep them in a tab
 * @author zeta
 */
public class Noyau {
    Task[] tasks;
    public static Prefs opts;
    
    /** Creates a new instance of Noyau */
    public Noyau() {
        opts = new Prefs();
        
        tasks=new Task[Main.MAXONGLET];
        int i;
        for(i=0;i<Main.MAXONGLET;i++) {
            tasks[i]=null;
        }
    }
    
    /** Basic Task creation*/
    public Task newTask() {
        int i;
        for(i=0;i<Main.MAXONGLET;i++) {
            if(tasks[i]==null) {
                break;
            }
        }
        
        if(i!=Main.MAXONGLET) {
            tasks[i]=new DefaultTask();
            return tasks[i];
        }else{
            Utils.Errors.Error.tropAnalyse();
        }
        return null;
    }
    /** Task creation which take the ref number of the analysis
     * @param i ref number of the analysis
     */
    public Task newTask(int i) {
        if(i<Main.MAXONGLET) {
            tasks[i]=new DefaultTask();
            return tasks[i];
        }else{
            Utils.Errors.Error.tropAnalyse();
        }
        return null;
    }
    /** Task creation which take the ref number of the analysis and the file to analyse
     *@param files files to analyse
     * @param i ref number of the analysis
     */
    
    public Task newTask(int i,File[] files) {
        Task task=newTask(i);
        if(task != null) {
            task.setFiles(files);
        }
        
        return task;
    }
    
    
    /** Task creation which take the ref number of the analysis and the file to analyse for the gui
     *
     * @param statusbar statusbar to update 
     * @param bar ProgressBar of to control by the thread
     * @param ret UI which dispatch the results
     * @param i ref number of the analysis
     * 
     */
    
       public Task newGUITask(int i,JLabel statusbar,JProgressBar bar,ResDispatcher ret) {
        if(i<Main.MAXONGLET) {
            tasks[i]=new Ihm.JTask(bar,statusbar);
            //((JTask) tasks[i]).setBar(bar);
            ((JTask) tasks[i]).setRecall(ret);
            return tasks[i];
        }else{
            Utils.Errors.Error.tropAnalyse();
        }
        return null;
    }
        /** Task creation which take the ref number of the analysis and the file to analyse for the gui
     *
     * @param statusbar statusbar to update 
     * @param bar ProgressBar of to control by the thread
     * @param ret UI which dispatch the results
     * @param i ref number of the analysis
     * @param files files to analyse
     */
       
        public Task newGUITask(int i,File[] files,JLabel statusbar,JProgressBar bar,ResDispatcher ret) {
        Task task=newGUITask(i,statusbar,bar,ret);
        if(task != null) {
            task.setFiles(files);
        }
        
        
        return task;
    }
    
    
}
