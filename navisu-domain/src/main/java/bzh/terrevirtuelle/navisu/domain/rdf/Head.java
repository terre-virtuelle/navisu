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
@XmlType(name = "head")
@XmlAccessorType(XmlAccessType.FIELD)
public class Head {

    @XmlElements({
        @XmlElement(name = "variable", type = Variable.class)
    })
    List<Variable> variables;

    public Head() {
        variables = new ArrayList<>();
    }

    public Head(List<Variable> variables) {
        this.variables = variables;
    }

    public void add(Variable variable) {
        variables.add(variable);
    }

    public List<Variable> getVariables() {
        return variables;
    }

    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }

    @Override
    public String toString() {
        return "Head{" + "variables=" + variables + '}';
    }

}
