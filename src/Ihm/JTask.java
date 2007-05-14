/*
 * Jtask.java
 *
 * Created on 6 mai 2007, 16:22
 *$Id$
 */

package Ihm;
import Noyau.Task;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
/**
 *
 * @author zeta
 */
public class JTask extends Task {
    
    private JProgressBar bar;
    private ResDispatcher recall;
    
    /** Creates a new instance of Jtask */
    public JTask() {
        super();
    }
    
    public JTask(JProgressBar bar)
    {
    super();
    setBar(bar);
    }
    
    protected void printState() {
        float a=this.getStateCount();
        int m;
        m=bar.getMaximum();
        try {
            bar.setValue(Math.round(a*m));
            //   bar.updateUI(); //exception leve quand je fait ça
        }catch(Exception e)
        {
        System.out.println("exp");
        }
     
        
    }

    public JProgressBar getBar() {
        return bar;
    }

    public void setBar(JProgressBar bar) {
        this.bar = bar;
    }

    protected void finalState() {
        this.printState();
    recall.DispatchResult();

    }


    public void setRecall(ResDispatcher recall) {
        this.recall = recall;
    }
    
    
   
    
    
}
