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
 * @date Feb 19, 2016
 *
 */
@XmlRootElement(name = "chapitre")
@XmlAccessorType(XmlAccessType.FIELD)
public class Chapter extends Part {

    @XmlElements({
        @XmlElement(type = SubChapter.class)
    })
    private final List<SubChapter> sChapitre = new ArrayList<>();

    public Chapter() {
    }

    /* Get the value of sChapitreList
     *
     * @return the value of sChapitreList
     */
    public List<SubChapter> getChapitres() {
        return sChapitre;
    }

    
}
