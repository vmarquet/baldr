/*
 * Noyau.java
 *
 * Created on 14 avril 2007, 17:35
 *$Id$
 */

package Noyau;
import Main.*;
import java.io.File;

/**
 *
 * @author zeta
 */
public class Noyau {
    
    Task[] tasks;
    /** Creates a new instance of Noyau */
    public Noyau() {
        tasks=new Task[Main.MAXONGLET];
        int i;
        for(i=0;i<Main.MAXONGLET;i++)
        {
        tasks[i]=null;
        }
    }
   
    
    public Task newTask()
    {
        int i;
        for(i=0;i<Main.MAXONGLET;i++)
        {
            if(tasks[i]==null)
            {
            break;
            }
        }
        
        if(i!=Main.MAXONGLET)
        {
            tasks[i]=new Task();
        return tasks[i];
        }else{
    Utils.Errors.Error.tropAnalyse();
    }
        return null;
    }
    
    public Task newTask(int i)
    {
    if(i<Main.MAXONGLET)
    {
    tasks[i]=new Task();
    return tasks[i];
    }else{
    Utils.Errors.Error.tropAnalyse();
    }
    return null;
    }
    
    public Task newTask(int i,File[] files)
    {
        Task task=newTask(i);
            if(task != null)
            {
            task.setFiles(files);
            }
        
        return task;
    }
    
    
    
}
