/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zwen.Medien;

import zwen.Medien.Medium;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hoeling
 */
@XmlRootElement (name="Film")
public class Film extends Medium {

    public Film(){
    //throw new IllegalArgumentException("There must be at least 1 Argument");
    }
    
    public Film(String titel) {

        this(titel, "Film");
    }

    public Film(String titel, String typ) {
        super(titel, typ);

    }

    @Override
    public boolean equals(Medium other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != getClass()) {
            return false;
        }
        return ((Film) other).getTitel().equals(this.getTitel());
    }

    @Override
    public int hashCode() {

        return (this.getTitel() + this.getTyp()).hashCode();
        //return Objects.hashCode(this.titel,this.typ);
    }

}
