/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Location;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.controller.SailingDirectionsParser;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Book;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Chapter;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Document;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Metadata;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Text;
import bzh.terrevirtuelle.navisu.domain.util.Pair;
import com.vividsolutions.jts.geom.Point;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * NaVisu
 *
 * @date 30 oct. 2015
 * @author Serge Morvan
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SailingDirections
        extends Location
        implements NavigationData {

    @XmlTransient
    protected SailingDirectionsParser sailingDirectionsParser;

    public SailingDirections() {
    }

    public Document getDocument() {
        return sailingDirectionsParser.getDocument();
    }

    public Set<Text> getTextSet() {

        return sailingDirectionsParser.getTextSet();
    }

    public Map<Pair<Double, Double>, String> getPoiMap() {
        return sailingDirectionsParser.getPoiMap();
    }

    public Book getBook() {
        return sailingDirectionsParser.getBook();
    }

    public Metadata getMetadata() {
        return sailingDirectionsParser.getMetadata();
    }

    public List<Chapter> getChapters() {
        return sailingDirectionsParser.getChapters();
    }

    public Point getCentroid() {
        return sailingDirectionsParser.getCentroid();
    }
}
