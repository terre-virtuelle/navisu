/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.charts.vector.s52.app;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.ColorTable;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.AttribCode;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.Bitmap;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.ChartSymbols;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.Color;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.ColorTables;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.Distance;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.GraphicsFile;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.GraphicsLocation;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.LineStyle;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.LineStyles;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.Lookup;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.Lookups;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.Origin;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.Pattern;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.Patterns;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.Pivot;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.Symbol;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.Symbols;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.Vector;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.controller.xml.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Serge
 */
public class XMLWriterTest {
    
    public XMLWriterTest() {
        Symbols symbols = new Symbols();
        
        Symbol symbol = new Symbol("2035", "ACHARE02", "anchorage",
                new Bitmap("13", "16",
                        new Distance("0", "0"),
                        new Pivot("6", "8"),
                        new Origin("0", "0"),
                        new GraphicsLocation("10", "10")),
                "ACHMGD",
                new Vector("402", "505",
                        new Distance("0", "0"),
                        new Pivot("1267", "1052"),
                        new Origin("1061", "769"),
                        "SPA"),
                "V");
        symbols.add(symbol);
        
        Patterns patterns = new Patterns();
        Pattern pattern = new Pattern("2000", "AIRARE02", "V",
                "S",
                "C",
                new Vector("402", "505",
                        new Distance("0", "0"),
                        new Pivot("1267", "1052"),
                        new Origin("1061", "769")),
                "pattern",
                "ACHMGD",
                "SPA;SW1");
        patterns.add(pattern);
        
        LineStyles lineStyles = new LineStyles();
        LineStyle lineStyle = new LineStyle("3346", "ACHARES51",
                new Vector("3036", "505",
                        new Distance("0", "0"),
                        new Pivot("105", "520"),
                        new Origin("206", "568")),
                "lineStyle",
                "SPA;SW1",
                "ACHMGD");
        lineStyles.add(lineStyle);
        
        Lookups lookups = new Lookups();
        Lookup lookup = new Lookup("2", "32088", "ACHARE",
                "Area",
                "Area 2",
                "Suppressed",
                "plain",
                new AttribCode("0"),
                "SY(ACHAR02);LS",
                "Standard",
                "26220");
        lookups.add(lookup);

        ColorTables colorTables = new ColorTables();
        List<Color> colors = new ArrayList<>();
        Color color = new Color("NODTA", "163", "180", "183");
        colors.add(color);
        color = new Color("CURSR", "235", "125", "183");
        colors.add(color);
        ColorTable colorTable = new ColorTable("DAY_BRIGHT", new GraphicsFile("rastersymbols"), colors);
        colorTable.setColors(colors);
        colorTables.add(colorTable);
        colorTables.add(colorTable);
        
        ChartSymbols chartSymbols = new ChartSymbols();
        chartSymbols.add(colorTables);
        chartSymbols.add(lookups);
        chartSymbols.add(lineStyles);
        chartSymbols.add(patterns);
        chartSymbols.add(symbols);
        
        Writer writer = new Writer(chartSymbols);
        writer.write("out.xml");
    }
    
    public static void main(String[] args) {
        new XMLWriterTest();
    }
}
