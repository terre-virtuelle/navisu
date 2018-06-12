package bzh.terrevirtuelle.navisu.cartography.projection.lambert.impl;

/**
 *
 * @author serge from https://github.com/yageek/lambert-java
 *
 * @date 5 Jun, 2018
 */
public enum LambertZone {

    LambertI(0), LambertII(1), LambertIII(2), LambertIV(3), LambertIIExtended(4), Lambert93(5);
    private final int lambertZone;

    private final double[] LAMBERT_N = {0.7604059656, 0.7289686274, 0.6959127966, 0.6712679322, 0.7289686274, 0.7256077650};
    private final double[] LAMBERT_C = {11603796.98, 11745793.39, 11947992.52, 12136281.99, 11745793.39, 11754255.426};
    private final double[] LAMBERT_XS = {600000.0, 600000.0, 600000.0, 234.358, 600000.0, 700000.0};
    private final double[] LAMBERT_YS = {5657616.674, 6199695.768, 6791905.085, 7239161.542, 8199695.768, 12655612.050};

    private LambertZone(int value) {
        lambertZone = value;
    }

    public double n() {
        return LAMBERT_N[lambertZone];
    }

    public double c() {
        return LAMBERT_C[lambertZone];
    }

    public double xs() {
        return LAMBERT_XS[lambertZone];
    }

    public double ys() {
        return LAMBERT_YS[lambertZone];
    }

}
