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
    /**
     * Register a Task in the table
     *@param i anlysis number
     *@param tsk Task to register
     *@return the Task given or null in case of error
     */
    
    public Task registerTask(int i, Task tsk)
    {
     if(i<Main.MAXONGLET) {
            tasks[i]=tsk;
            return tasks[i];
        }else{
            Utils.Errors.Error.tropAnalyse();
        }
        return null;
    }
    
   
    
    
}
