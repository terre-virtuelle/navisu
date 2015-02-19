/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.charts.vector.s52.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Serge Morvan
 * @date 11 juil. 2014 NaVisu project
 */
@XmlRootElement(name = "chartsymbols")
@XmlAccessorType(XmlAccessType.FIELD)
public class ChartSymbols {

    @XmlElement(name = "color-tables")
    private ColorTables colorTables;
    @XmlElement(name = "line-styles")
    private LineStyles lineStyles;

    private Patterns patterns;

    private Symbols symbols;

    private Lookups lookups;

    public ChartSymbols() {
    }

    public ChartSymbols(Patterns patterns) {
        this.patterns = patterns;
    }

    public ChartSymbols(Symbols symbols) {
        this.symbols = symbols;
    }

    public ChartSymbols(LineStyles lineStyles) {
        this.lineStyles = lineStyles;
    }

    public ChartSymbols(ColorTables colorTables) {
        this.colorTables = colorTables;
    }

    public ChartSymbols(Patterns patterns, Symbols symbols) {
        this.patterns = patterns;
        this.symbols = symbols;
    }

    public ChartSymbols(LineStyles lineStyles, Patterns patterns) {
        this.lineStyles = lineStyles;
        this.patterns = patterns;
    }

    public ChartSymbols(LineStyles lineStyles, Symbols symbols) {
        this.lineStyles = lineStyles;
        this.symbols = symbols;
    }

    public ChartSymbols(LineStyles lineStyles, Patterns patterns, Symbols symbols) {
        this.lineStyles = lineStyles;
        this.patterns = patterns;
        this.symbols = symbols;
    }

    public ChartSymbols(LineStyles lineStyles, Patterns patterns, Symbols symbols, Lookups lookups) {
        this.lineStyles = lineStyles;
        this.patterns = patterns;
        this.symbols = symbols;
        this.lookups = lookups;
    }

    public ChartSymbols(Patterns patterns, Symbols symbols, Lookups lookups) {
        this.patterns = patterns;
        this.symbols = symbols;
        this.lookups = lookups;
    }

    public ChartSymbols(LineStyles lineStyles, Patterns patterns, Lookups lookups) {
        this.lineStyles = lineStyles;
        this.patterns = patterns;
        this.lookups = lookups;
    }

    public ChartSymbols(Symbols symbols, Lookups lookups) {
        this.symbols = symbols;
        this.lookups = lookups;
    }

    public ChartSymbols(Patterns patterns, Lookups lookups) {
        this.patterns = patterns;
        this.lookups = lookups;
    }

    public ChartSymbols(LineStyles lineStyles, Lookups lookups) {
        this.lineStyles = lineStyles;
        this.lookups = lookups;
    }

    public ChartSymbols(ColorTables colorTables, LineStyles lineStyles, Patterns patterns, Symbols symbols, Lookups lookups) {
        this.colorTables = colorTables;
        this.lineStyles = lineStyles;
        this.patterns = patterns;
        this.symbols = symbols;
        this.lookups = lookups;
    }

    public void add(Lookups lookups) {
        this.lookups = lookups;
    }

    public void add(Lookup data) {
        this.lookups.add(data);
    }

    public void add(Patterns patterns) {
        this.patterns = patterns;
    }

    public void add(Pattern pattern) {
        this.patterns.add(pattern);
    }

    public void add(LineStyles data) {
        this.lineStyles = data;
    }

    public void add(LineStyle data) {
        this.lineStyles.add(data);
    }

    public void add(Symbols symbols) {
        this.symbols = symbols;
    }

    public void add(Symbol symbol) {
        this.symbols.add(symbol);
    }

    public void add(ColorTables colorTables) {
        this.colorTables = colorTables;
    }

    public void add(ColorTable colorTable) {
        this.colorTables.add(colorTable);
    }

    /**
     * Get the value of colorTables
     *
     * @return the value of colorTables
     */
    public ColorTables getColorTables() {
        return colorTables;
    }

    /**
     * Set the value of colorTables
     *
     * @param colorTables new value of colorTables
     */
    public void setColorTables(ColorTables colorTables) {
        this.colorTables = colorTables;
    }

    /**
     * Get the value of lineStyles
     *
     * @return the value of lineStyles
     */
    public LineStyles getLineStyles() {
        return lineStyles;
    }

    /**
     * Set the value of lineStyles
     *
     * @param lineStyles new value of lineStyles
     */
    public void setLineStyles(LineStyles lineStyles) {
        this.lineStyles = lineStyles;
    }

    /**
     * Get the value of symbols
     *
     * @return the value of symbols
     */
    public Symbols getSymbols() {
        return symbols;
    }

    /**
     * Set the value of symbols
     *
     * @param symbols new value of symbols
     */
    public void setSymbols(Symbols symbols) {
        this.symbols = symbols;
    }

    /**
     * Get the value of patterns
     *
     * @return the value of patterns
     */
    public Patterns getPatterns() {
        return patterns;
    }

    /**
     * Set the value of patterns
     *
     * @param patterns new value of patterns
     */
    public void setPatterns(Patterns patterns) {
        this.patterns = patterns;
    }

    @Override
    public String toString() {
        return "ChartSymbols{" + "colorTables=" + colorTables + "\n"
                + ", lookups=" + lookups + "\n"
                + ", lineStyles=" + lineStyles + "\n"
                + ", patterns=" + patterns + "\n"
                + ", symbols=" + symbols
                + '}';
    }

}
