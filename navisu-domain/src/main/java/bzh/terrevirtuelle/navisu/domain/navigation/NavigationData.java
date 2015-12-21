package bzh.terrevirtuelle.navisu.domain.navigation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
@XmlAccessorType(XmlAccessType.FIELD)
public interface NavigationData {

    double getLatitude();

    double getLongitude();

    String getGeometry();

    default long getId() {
        return ((long) 0);
    }
}
