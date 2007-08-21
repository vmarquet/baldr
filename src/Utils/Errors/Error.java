/*
 * Error.java
 *
 * Created on 14 avril 2007, 18:42
 *$Id$
 */

package Utils.Errors;
import Main.*;
import javax.swing.JComponent.*;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import java.util.ResourceBundle;

/**
 * Class handling the different type of errors and their diplay to the users
 *
 * @author zeta
 */

//TODO move to IHM
public  class Error {
    static private ResourceBundle msgs=ResourceBundle.getBundle("i18n/Balder");
    
    /** Creates a new instance of Error */
    public  Error() {
        
    }
    
    private static void displayError(String message) {
        JOptionPane.showMessageDialog(null, message ,msgs.getString("ERROR"),JOptionPane.ERROR_MESSAGE );
    }
    
    public static void tropOnglets() {
        displayError(msgs.getString("NO_MORE_TABS"));
        
    }
    
    public static void tropAnalyse() {
        displayError(msgs.getString("TOO_MUCH_ANALYSIS"));
        
    }
    public static void noFiles() {
         displayError(msgs.getString("NO_FILES"));
        
    }
    public static void noEnoughFiles() {
        displayError(msgs.getString("NO_ENOUGH_FILES"));
        
    }
    public static void noEditorDefined() {
        displayError(msgs.getString("NO_EDITOR"));
        
    }
    public static void noComparatorDefined() {
        displayError(msgs.getString("NO_DIFF"));
        
    }
    
    public static void nothingToSave() {
        displayError(msgs.getString("NOTHING_SAVE"));
    }
    
    
    
}
