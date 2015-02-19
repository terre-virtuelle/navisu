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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Serge Morvan
 * @date 11 juil. 2014 NaVisu project
 */
@XmlRootElement(name = "color-table")
@XmlAccessorType(XmlAccessType.FIELD)
public class ColorTable {

    @XmlAttribute(name = "name")
    private String name;
    @XmlElement(name = "graphics-file")
    private GraphicsFile graphicsFile;

    @XmlElements({
        @XmlElement(name = "color", type = Color.class)
    })
    private List<Color> colors;

    public ColorTable() {
        colors = new ArrayList<>();
    }

    public ColorTable(String name, GraphicsFile graphicsFile, List<Color> colors) {
        colors = new ArrayList<>();
        this.name = name;
        this.graphicsFile = graphicsFile;
        this.colors = colors;
    }

    public void add(Color color) {
        colors.add(color);
    }

    /**
     * Get the value of colors
     *
     * @return the value of colors
     */
    public List<Color> getColors() {
        return colors;
    }

    /**
     * Set the value of colors
     *
     * @param colors new value of colors
     */
    public void setColors(List<Color> colors) {
        this.colors = colors;
    }

    /**
     * Get the value of graphicsFile
     *
     * @return the value of graphicsFile
     */
    public GraphicsFile getGraphicsFile() {
        return graphicsFile;
    }

    /**
     * Set the value of graphicsFile
     *
     * @param graphicsFile new value of graphicsFile
     */
    public void setGraphicsFile(GraphicsFile graphicsFile) {
        this.graphicsFile = graphicsFile;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ColorTable{" + "name=" + name + ", graphicsFile=" + graphicsFile + ", colors=" + colors + '}';
    }

}
