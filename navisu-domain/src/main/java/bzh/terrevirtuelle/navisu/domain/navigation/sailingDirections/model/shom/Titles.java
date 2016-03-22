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
        @XmlElement(type = Title.class, name = "titre")
    })
    private final List<Title> titles = new ArrayList<>();

    public Titles() {
    }

    public List<Title> getTitles() {
        return titles;
    }

    public void add(Title title) {
        titles.add(title);
    }

    public String getStitle() {
        List<String> tmp = new ArrayList<>();
        titles.stream().map((t) -> t.getStitle()).filter((ti) -> (ti != null)).forEach((ti) -> {
            tmp.add(ti);
        });
        return tmp.get(0);
    }

    public String getCountry() {
        List<String> tmp = new ArrayList<>();
        titles.stream().map((t) -> t.getCountry()).filter((ti) -> (ti != null)).forEach((ti) -> {
            tmp.add(ti);
        });
        return tmp.get(0);
    }

    public String getRegion() {
        List<String> tmp = new ArrayList<>();
        titles.stream().map((t) -> t.getRegion()).filter((ti) -> (ti != null)).forEach((ti) -> {
            tmp.add(ti);
        });
        return tmp.get(0);
    }

    @Override
    public String toString() {
        return "Titles{" + "titles=" + titles + '}';
    }

}
