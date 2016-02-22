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
 * @date Feb 20, 2016
 *
 */
@XmlRootElement(name = "titres")
@XmlAccessorType(XmlAccessType.FIELD)
public class Titles {

    @XmlElements({
        @XmlElement(type = MdTitle.class)
    })
    private final List<MdTitle> mdTitles= new ArrayList<>();

    public Titles() {
    }

    public List<MdTitle> getMdTitles() {
        return mdTitles;
    }

    @Override
    public String toString() {
        return "Titles{" + "mdTitles=" + mdTitles + '}';
    }

}
