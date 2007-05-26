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
/**
 *
 * @author zeta
 */
public  class Error {
    
    /** Creates a new instance of Error */
    public  Error() {
    }
    
    public static void displayError(String message)
    {
    JOptionPane.showMessageDialog(null, message ,"Erreur",JOptionPane.ERROR_MESSAGE );
    }
    
    public static void tropOnglets()
    {
        displayError( "Désolé mais vous ne pouvez pas créer plus d'onglets."  );

    }
    
    public static void tropAnalyse()
    {
        displayError( "Désolé mais vous ne pouvez pas effectuer plus d'analyse." );

    }
    public static void noFiles()
    {
        displayError( "Aucun fichiers à analyser." );

    }
        public static void noEnoughFiles()
    {
        displayError( "Pas assez de fichiers à analyser (3 minimum)." );

    }
          public static void noEditorDefined()
    {
        displayError( "L'éditeur préféré n'est pas défini" );

    }
        public static void nothingToSave()
        {
            displayError( "Rien à enregistrer !" );
        }
    
    
    
 }
