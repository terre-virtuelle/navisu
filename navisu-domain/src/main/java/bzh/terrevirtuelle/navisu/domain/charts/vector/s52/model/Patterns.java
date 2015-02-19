/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.charts.vector.s52.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Serge Morvan
 * @date 13 juil. 2014 NaVisu project
 */
@XmlRootElement(name = "patterns")
@XmlAccessorType(XmlAccessType.FIELD)
public class Patterns {

    @XmlElements({
        @XmlElement(name = "pattern", type = Pattern.class)
    })
    List<Pattern> patterns;

    public Patterns() {
        patterns = new ArrayList<>();
    }

    public void add(Pattern data) {
        patterns.add(data);
    }

    public List<Pattern> getPatterns() {
        return patterns;
    }

    @Override
    public String toString() {
        return "Patterns{" + "patterns=" + patterns + '}';
    } 
}
