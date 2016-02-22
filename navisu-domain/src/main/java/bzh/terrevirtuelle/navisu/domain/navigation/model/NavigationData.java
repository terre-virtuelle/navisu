package bzh.terrevirtuelle.navisu.domain.navigation.model;

public interface NavigationData {

    default double getLatitude() {
        return 0.0;
    }

    default double getLongitude() {
        return 0.0;
    }

    default String getGeometry() {
        return "";
    }

    default long getId() {
        return ((long) 0);
    }
}
