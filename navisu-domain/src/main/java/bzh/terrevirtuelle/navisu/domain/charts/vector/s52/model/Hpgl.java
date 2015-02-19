/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.domain.charts.vector.s52.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Serge Morvan
 * @date 11 juil. 2014
 * NaVisu project
 */
@XmlType(name = "HPGL")
@XmlAccessorType(XmlAccessType.FIELD)
public class Hpgl {

    private String instructions;

    public Hpgl() {
    }

    public Hpgl(String instructions) {
        this.instructions = instructions;
    }

    /**
     * Get the value of instructions
     *
     * @return the value of instructions
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * Set the value of instructions
     *
     * @param instructions new value of instructions
     */
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "Hpgl{" + "instructions=" + instructions + '}';
    }
 
}
