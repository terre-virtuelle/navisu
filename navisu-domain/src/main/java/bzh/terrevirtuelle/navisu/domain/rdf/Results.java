/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.rdf;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

/**
 * NaVisu
 *
 * @date 30 oct. 2015
 * @author Serge Morvan
 */
public class Results {

    @XmlElements({
        @XmlElement(name = "result", type = Result.class)
    })
    List<Result> resultList;

    public Results() {
        resultList = new ArrayList<>();
    }

    public Results(List<Result> results) {
        this.resultList = results;
    }

    public List<Result> getResultList() {
        return resultList;
    }

    @Override
    public String toString() {
        return "Results{" + "results=" + resultList + '}';
    }

}
