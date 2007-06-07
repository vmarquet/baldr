/*
 * Jtask.java
 *
 * Created on 6 mai 2007, 16:22
 *$Id$
 */

package Ihm;
import Noyau.Task;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
/**
 *
 * @author zeta
 */
public class JTask extends Task {
    
    private JProgressBar bar;
    private JLabel statusbar;
    private ResDispatcher recall;
    private conv3d g3d;
    
    /** Creates a new instance of Jtask */
    public JTask() {
        super();
    }
    
    public JTask(JProgressBar bar)
    {
    super();
    setBar(bar);
    }
    
    public JTask(JProgressBar bar, JLabel statusbar)
    {
    super();
    setBar(bar);
    this.statusbar = statusbar;
    }
    public JTask(JProgressBar bar, JLabel statusbar, ResDispatcher recall)
    {
    super();
    setBar(bar);
    this.statusbar = statusbar;
    this.setRecall(recall);
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
        statusbar.setText(stateMessage);
    }

    public JProgressBar getBar() {
        return bar;
    }

    public void setBar(JProgressBar bar) {
        this.bar = bar;
    }

    protected void finalState() {
        this.printState();
       // this.g3d = new conv3d(this.getResults(),this.bar,statusbar,recall);
       // g3d.start(); // lance le calcul de la 3D
        recall.DispatchResult();
    }

    protected void cleanUp(){
        if(g3d != null){
            if(g3d.isAlive())
                //System.out.println("Stopper analyse: 3D On");
                g3d.stopNow();
        }
    }
    
    public void setRecall(ResDispatcher recall) {
        this.recall = recall;
    }
   
}
