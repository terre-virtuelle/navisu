package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.Annotation;

public class NrOfFillBitsAnnotation implements Annotation {

    private int nrOfFillBits;

    public NrOfFillBitsAnnotation() {
    }

    public NrOfFillBitsAnnotation(int nrOfFillBits) {
        this.nrOfFillBits = nrOfFillBits;
    }

    public String toString() {
        return "Deviation from standards, more than 5 fill bits used: " + nrOfFillBits;
    }
}
