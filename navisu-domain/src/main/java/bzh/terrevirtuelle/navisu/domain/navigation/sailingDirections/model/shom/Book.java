/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom;

import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author serge
 * @date Feb 19, 2016
 *
 */
@XmlRootElement(name = "ouvrage")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book{

    @XmlElements({
        @XmlElement(type = Illustration.class)
    })
    private final List<Illustration> illustration = new ArrayList<>();
    @XmlElements({
        @XmlElement(type = Chapter.class)
    })
    private final List<Chapter> chapitre = new ArrayList<>();

    public Book() {
    }

    public List<Chapter> getChapitre() {
        return chapitre;
    }

    @Override
    public String toString() {
        return "Book{" + "illustration=" + illustration + ", chapitre=" + chapitre + '}';
    }

  
}
