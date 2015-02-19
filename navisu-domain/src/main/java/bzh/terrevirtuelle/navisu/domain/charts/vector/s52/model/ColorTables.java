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
@XmlRootElement(name = "color-tables")
@XmlAccessorType(XmlAccessType.FIELD)
public class ColorTables {

    @XmlElements({
        @XmlElement(name = "color-table", type = ColorTable.class)
    })
    List<ColorTable> colorTables;

    public ColorTables() {
        colorTables = new ArrayList<>();
    }

    public void add(ColorTable data) {
        colorTables.add(data);
    }

    public List<ColorTable> getColorTables() {
        return colorTables;
    }

    @Override
    public String toString() {
        return "ColorTables{" + "colorTables=" + colorTables + '}';
    }

}
