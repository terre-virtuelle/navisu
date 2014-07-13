/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.charts.vector.s52;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Serge Morvan
 * @date 11 juil. 2014 NaVisu project
 */
@XmlRootElement(name = "chartsymbols")
@XmlAccessorType(XmlAccessType.FIELD)
public class ChartSymbols {
    
    private Patterns patterns;
    
    private Symbols symbols;
    
    public ChartSymbols() {
    }
    
    public ChartSymbols(Patterns patterns) {
        this.patterns = patterns;
    }
    
    public ChartSymbols(Symbols symbols) {
        this.symbols = symbols;
    }
    
    public ChartSymbols(Patterns patterns, Symbols symbols) {
        this.patterns = patterns;
        this.symbols = symbols;
    }
    
    public void add(Patterns patterns) {
        this.patterns = patterns;
    }
    
    public void add(Pattern pattern) {
        this.patterns.add(pattern);
    }
    
    public void add(Symbols symbols) {
        this.symbols = symbols;
    }

    public void add(Symbol symbol) {
        this.symbols.add(symbol);
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
        return "ChartSymbols{" + "patterns=" + patterns + ", symbols=" + symbols + '}';
    }
    
}
