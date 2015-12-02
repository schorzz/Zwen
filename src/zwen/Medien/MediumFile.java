/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zwen.Medien;

import zwen.Medien.Medium;
import java.io.File;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Collection;
import javafx.collections.ObservableList;
import static javafx.scene.input.KeyCode.T;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import sun.security.jca.GetInstance;

/**
 *
 * @author Hoeling
 */
public class MediumFile{
    
    private static String pfad = "db.xml";
    
    //private static final MediumFile handler= new MediumFile();
   //private static final File pfad=new File(".");
//    private static MediumFile instanz = null;
//    
//    
//    protected MediumFile(){
//        
//    }
//    
//    public static MediumFile getInstance(){
//    if(instanz==null) instanz= new MediumFile();
//    return instanz;
//    }
    
    public static Collection readFilm(){
        
        MediumListe ml=new MediumListe();
        
        try {
            
        
        
        
        JAXBContext jaxbContext = JAXBContext.newInstance(MediumListe.class, Film.class, Medium.class);
        
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        
        StreamSource xml = new StreamSource(pfad);
        ml= (MediumListe) unmarshaller.unmarshal(xml, MediumListe.class).getValue();
        
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        
    return ml.getItems();
    }
    
    public static void writeFilm(Collection sammlung)throws Exception{
        
        ArrayList sammlung2 = new ArrayList<Medium>();
        
        //sammlung.removeIf((Medium)e -> e.getType.equals("Film"));
        
        JAXBContext jaxbContext = JAXBContext.newInstance(MediumListe.class, Film.class);
        

        //Marshall
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        QName qName = new QName("Filme");
        MediumListe ml =new MediumListe(new ArrayList(sammlung));
        JAXBElement<MediumListe> jaxbElement = new JAXBElement<MediumListe>(qName,MediumListe.class,ml);
        marshaller.marshal(jaxbElement,new File(pfad));
        
        }
    private static void marshal(){}
        
//        ArrayList<Medium> arrayliste = new ArrayList<Medium>(sammlung);
//        arrayliste.add(new Film("teeeeeest"));
//        System.out.println(arrayliste);
//        
//        try {
//            File file = new File(pfad);
//            
//            JAXBContext jaxbContext = JAXBContext.newInstance(Medium.class);
//            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//            
//            
//            
//            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            jaxbMarshaller.marshal(arrayliste, file);
//            
//            
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
        
    
    
    
//    public static void main(String[] args) {
//        
//       Film film1 = new Film("Testfilm");
//        film1.setImdbTitel("DER Testfilm!!");
//        //System.out.println(pfad.listFiles());
//        
//        try {
//            File file = new File("test.xml");
//            
//            JAXBContext jaxbContext = JAXBContext.newInstance(Film.class);
//            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            jaxbMarshaller.marshal(film1, file);
//            
//        } catch (JAXBException e) {
//        e.printStackTrace();
//        }
//        
//    }
}
