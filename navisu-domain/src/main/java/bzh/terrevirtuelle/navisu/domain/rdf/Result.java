/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.rdf;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * NaVisu
 *
 * @date 30 oct. 2015
 * @author Serge Morvan
 */
@XmlRootElement
@XmlType(name = "result")
@XmlAccessorType(XmlAccessType.FIELD)
public class Result {

    @XmlElements({
        @XmlElement(name = "binding", type = Binding.class)
    })
    List<Binding> bindings;

    public Result() {
        bindings = new ArrayList<>();
    }

    public Result(List<Binding> bindings) {
        this.bindings = bindings;
    }

    public List<Binding> getBindings() {
        return bindings;
    }

    public void setBindings(List<Binding> bindings) {
        this.bindings = bindings;
    }

    @Override
    public String toString() {
        return "Result{" + "bindings=" + bindings + '}';
    }

}
