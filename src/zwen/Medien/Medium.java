/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zwen.Medien;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hoeling
 */

//@XmlRootElement (name="Medium")
public abstract class Medium {
    
    

    private String titel, imdbtitel, typ;

    public Medium(){
    //throw new IllegalArgumentException("There must be at least 1 Argument");
    }
    
    public Medium(String argtitel, String argtyp) {
        titel = argtitel;
        typ = argtyp;

    }

    public String toString() {
        return titel;
    }
    
    @XmlElement(name="imdbtitel")
    public String getImdbTitel() {
        return imdbtitel;
    }

    public void setImdbTitel(String arg) {
        imdbtitel = arg;
    }

    @XmlElement(name="typ")
    public String getTyp() {
        return typ;
    }

    public void setTyp(String arg) {
        typ = arg;
    }
    
    @XmlElement(name="titel")
    public String getTitel() {
        return titel;
    }

    public void setTitel(String arg) {
        titel = arg;
    }

    public abstract boolean equals(Medium obj);

    public abstract int hashCode();
    
   
}
