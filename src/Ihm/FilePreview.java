/*
 * FilePreview.java
 *
 * Created on 1 septembre 2007, 15:29
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Ihm;

import Utils.Extension;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.zip.GZIPInputStream;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;
import java.beans.*;
import java.text.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class FilePreview extends JPanel implements PropertyChangeListener{
    private JFileChooser chooser;
    private JLabel nom_desc, taille_desc, date_desc, description_desc, analys_desc, fichiers_desc;
    private JLabel nom, taille, date, description, analys, fichiers;
    
    public static final int FLPREV_SIZE=4;
    
    public FilePreview(JFileChooser chooser){
        super(new GridLayout(0,1));
        
        add(nom_desc = new JLabel("Nom du fichier :"));
        add(nom = new JLabel(""));
        add(taille_desc = new JLabel("Taille du fichier :"));
        add(taille = new JLabel(""));
        add(date_desc = new JLabel("Dernière mod. du fichier :"));
        add(date = new JLabel(""));
        
        add(fichiers_desc = new JLabel("Fichiers :"));
        add(fichiers = new JLabel(""));
        
        add(description_desc = new JLabel("Rapport :"));
        add(description = new JLabel(""));
        add(analys_desc = new JLabel("Analyse :"));
        add(analys = new JLabel(""));
        
        this.chooser = chooser;
        this.chooser.addPropertyChangeListener(this);
        
        this.setVisible(false);
        
        setBorder(new TitledBorder("Aperçu"));
    }
    
    private void getInfos(File file) throws Exception{
        FileInputStream fs=null;
        StringBuffer flprev;
        int fcnt=0;
        
        try{
            DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
            //fabrique.setValidating(true);
            
            DocumentBuilder constructeur = fabrique.newDocumentBuilder();
            
            fs = new FileInputStream(file);
            InputStream st=fs;
            
            if(Extension.getExtension(file).equalsIgnoreCase(Extension.baldrx)) {
                st=new GZIPInputStream(st);
            }
            
            Document document = constructeur.parse(st);
            
            Element racine = document.getDocumentElement();
           
            NodeList tabs = racine.getElementsByTagName("onglet");
            
            for(int i=0; i<tabs.getLength(); i++){
                /* <onglet> */
                Element etab = (Element)tabs.item(i);
                /* <fileList> */
                Element efilelist = (Element)etab.getElementsByTagName("filelist").item(0);
                /* <dir> */
                NodeList dirlist = efilelist.getElementsByTagName("dir");
                for(int j=0; j<dirlist.getLength(); j++){
                    Element edir = (Element)dirlist.item(j);
                    System.out.println("Dir: " + edir.getAttribute("name"));
                    /* <file> */
                    NodeList filelist = edir.getElementsByTagName("file");
                    flprev = new StringBuffer();
                    for(int k=0; k<filelist.getLength(); k++){
                        Element efile = (Element)filelist.item(k);
                        if(k<FLPREV_SIZE){
                            flprev.append((new File(efile.getTextContent())).getName() + " ");
                        }else if(k==FLPREV_SIZE){
                            flprev.append("...");
                        }
                        fcnt++;
                        System.out.println("File: " + efile.getTextContent());
                    }
                    fichiers.setText(fcnt + " " + flprev.toString());
                    System.out.println(flprev.toString());
                }
                /* </dir> */
                /* </fileList> */
                /* <analys> */
                Element eanalys = (Element)etab.getElementsByTagName("analys").item(0);
                if(eanalys != null){
                    analys.setText("Oui");
                    System.out.println("Analyse: Ok");
                }else{
                    analys.setText("Non");
                }
                /* <rapport> */
                Element erapport = (Element)etab.getElementsByTagName("rapport").item(0);
                description.setText(summString(erapport.getTextContent(),25));
                System.out.println(erapport.getTextContent());
                /* </onglet> */
            }
            
        }catch(Exception e){
            throw e;
        }finally{
            try{fs.close();}catch(Exception e){}
            return;
        }
    }
    
    public void propertyChange(PropertyChangeEvent e) {
        String prop = e.getPropertyName();
        
        if(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(prop)){
            
            File file = (File) e.getNewValue();
            
            if(file == null){
                cancel();
                return;
            }
            
            String ext = Extension.getExtension(file);
            
            if(ext == null){
                cancel();
                return;
            }
            
            if(!ext.equalsIgnoreCase(Extension.baldr) && !ext.equalsIgnoreCase(Extension.baldrx)){
                cancel();
                return;
            }
            
            try {
                getInfos(file);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            FileSystemView vueSysteme = FileSystemView.getFileSystemView();
            Locale locale = Locale.getDefault();
            NumberFormat nf = NumberFormat.getInstance(locale);
            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, locale);
            
            
            nom.setText(vueSysteme.getSystemDisplayName(file));
            
            //description.setText(vueSysteme.getSystemTypeDescription(file));
            
            String tailleFile = nf.format(file.length()/1024.0)+" Kb";
            taille.setText(tailleFile);
            
            String dateFile = dateFormat.format(new Date(file.lastModified()));
            date.setText(dateFile);
            
            this.setVisible(true);
        }else{
            cancel();
        }
    }
    
    private void clear(){
        nom.setText("");
        description.setText("");
        taille.setText("");
        date.setText("");
        analys.setText("");
        fichiers.setText("");
    }
    
    private void cancel(){
        this.setVisible(false);
        clear();
    }
    
    public String summString(String s,int longueur){
        if (s.length() <= longueur) return s;
        String debut = s.substring(0,longueur-3);
        return debut+"...";
    }
}