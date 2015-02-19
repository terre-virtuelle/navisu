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
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Symbols {

    @XmlElements({
        @XmlElement(name = "symbol", type = Symbol.class)
    })
    List<Symbol> symbols;

    public Symbols() {
        symbols = new ArrayList<>();
    }

    public void add(Symbol data) {
        symbols.add(data);
    }

    public List<Symbol> getSymbols() {
        return symbols;
    }

    @Override
    public String toString() {
        return "Symbols{" + "symbols=" + symbols + '}';
    }

}
