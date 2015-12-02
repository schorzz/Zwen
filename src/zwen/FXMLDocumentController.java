/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zwen;

import zwen.Medien.Film;
import zwen.Medien.MediumFile;
import zwen.Medien.Medium;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.FileChooser;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


/**
 *
 * @author Hoeling
 */
public class FXMLDocumentController implements Initializable {
    //----------------Variablen für main-------------------------
    @FXML
    private TextField textfeldHinzu;
    @FXML
    private TextField formularName;
    @FXML
    private TextField formularTyp;

    @FXML
    private TextField formularImdbTitel;
    
    @FXML
    private ChoiceBox choiceboxTyp;

    @FXML
    private ListView<Medium> listviewAnzeige;
    private ObservableList<Medium> listeAlleElem = FXCollections.observableArrayList(new Film("Test", "Film"));
    
    
    private Medium objectInFormular;
    
    //----------------Variablen für optionen----------------------
    @FXML
    private TextField datenbankpfad;

    @FXML
    private void buttonActionHinzufuegen(ActionEvent event) {
        boolean enthalten = false;
        String textfeldinhalt = textfeldHinzu.getText();
        String ausgewaehlertyp = choiceboxTyp.getSelectionModel().getSelectedItem().toString();

        if ((!"".equals(textfeldinhalt)) && (textfeldinhalt != null)) {

            for (Medium elem : listeAlleElem) {
                if ((elem.getTitel().equals(textfeldinhalt)) && (elem.getTyp().equals(ausgewaehlertyp))) {
                    enthalten = true;
                }
            }

            //wenn der Name bereits existiert, dann soll er nicht hinzugefügt werden
            if (enthalten) {

                textfeldHinzu.setText("nein: " + (new Film(textfeldinhalt, ausgewaehlertyp).toString()));

            } else {

                listeAlleElem.add(new Film(textfeldHinzu.getText(), ausgewaehlertyp));
                buttonDataBaseSpeichern();

            }

        }
    }

    @FXML
    private void buttonListeloeschen(ActionEvent event) {
        if (!((-1) == listviewAnzeige.getSelectionModel().getSelectedIndex())) {
            listeAlleElem.remove(listviewAnzeige.getSelectionModel().getSelectedIndex());
        }
        buttonDataBaseSpeichern();

    }
    
    @FXML
    private void selectedlist(){
        if (!((-1) == listviewAnzeige.getSelectionModel().getSelectedIndex())) {
         
            setformular(listviewAnzeige.getSelectionModel().getSelectedItems().get(0));
        
        }
    }

    
    @FXML
    private void buttonZufall(){
        Random zufallsgenerator = new Random();
        
        int zufallsindex = zufallsgenerator.nextInt(listeAlleElem.size());
        Medium item = listeAlleElem.get(zufallsindex);
        
        
        setformular(item);
        System.out.println(item);
        
        
        
        
    
    }
    @FXML
    private void buttonDataBaseSpeichern(){
    
        
        
        try {
            MediumFile.writeFilm(listeAlleElem);
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    @FXML
    private void buttonDataBaseLaden(){
    
        listeAlleElem = FXCollections.observableArrayList(MediumFile.readFilm());
        listviewAnzeige.setItems(listeAlleElem);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //listeAlleElem = FXCollections.observableArrayList(MediumFile.readFilm());
        //Die Liste mit nur einem TestObjekt initialisieren
        //listviewAnzeige.setItems(listeAlleElem);
        buttonDataBaseLaden();

        //In die choicebox "Film" und "Serie" setzten
        choiceboxTyp.setItems(FXCollections.observableArrayList("Film", "Serie"));

        //Es wird das erste element der choicebox als standard ausgewählt
        choiceboxTyp.getSelectionModel().selectFirst();
    }
    public void setformular(Medium elem){
        
        objectInFormular=elem;
        formularName.setText(elem.getTitel());
        formularTyp.setText(elem.getTyp());
        formularImdbTitel.setText(elem.getImdbTitel());
        
    }
    
    @FXML
    public void saveFromFormular(){
    
        objectInFormular.setTitel(formularName.getText());
        objectInFormular.setTyp(formularTyp.getText());
        objectInFormular.setImdbTitel(formularImdbTitel.getText());
        buttonDataBaseSpeichern();
        buttonDataBaseLaden();
           
    }
    
    @FXML
    public void buttonOpen(){
    FileChooser filechooser = new FileChooser();
    File selectedFile = filechooser.showOpenDialog(null);
    
    if(selectedFile != null){
        datenbankpfad.setText(selectedFile.getParent());
        
        //selectedFile.getParent();
    }
    else{
        //datenbankpfad.setText("kein pfad");
    }
    
    }
    
}
