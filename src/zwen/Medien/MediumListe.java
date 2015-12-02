/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zwen.Medien;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAnyElement;

/**
 *
 * @author Hoeling
 */
public class MediumListe<T> {

    private List<T> items;

    public MediumListe() {
        items = new ArrayList<>();
    }

    public MediumListe(List<T> itemsInternal) {
        items = itemsInternal;
    }
    @XmlAnyElement(lax=true)
    public List<T> getItems(){
    return items;
    }

}
