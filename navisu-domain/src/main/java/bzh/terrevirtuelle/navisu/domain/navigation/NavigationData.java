package bzh.terrevirtuelle.navisu.domain.navigation;


public interface NavigationData {

    double getLatitude();

    double getLongitude();

    String getGeometry();

    default long getId() {
        return ((long) 0);
    }
}
