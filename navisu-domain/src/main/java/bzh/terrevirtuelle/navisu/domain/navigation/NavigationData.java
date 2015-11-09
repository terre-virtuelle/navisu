package bzh.terrevirtuelle.navisu.domain.navigation;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Location;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
@XmlAccessorType(XmlAccessType.FIELD)
public  interface NavigationData {

    default Location getLocation() {
        return (new Location(0.0, 0.0));
    }

    default long getId() {
        return ((long) 0);
    }
}
