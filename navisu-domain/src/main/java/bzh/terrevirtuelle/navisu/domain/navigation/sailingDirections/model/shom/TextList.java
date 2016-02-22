/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom;

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
 * @date   Feb 19, 2016
 *
 */
@XmlRootElement(name = "liste")
@XmlAccessorType(XmlAccessType.FIELD)
public class TextList {
  
    @XmlElements({
        @XmlElement(name = "texte", type = Text.class)
    })

    private final List<Text> texte = new ArrayList<>();

    public TextList() {
    }

    public List<Text> getListe() {
        return texte;
    }

    @Override
    public String toString() {
        return "Liste{" + "texte=" + texte + '}';
    }

    

}
