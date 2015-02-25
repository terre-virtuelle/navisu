package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.Sixbit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessage20Unit;

class AISMessage20UnitImpl implements AISMessage20Unit {

    private int offsetNumber;

    /**
     * offsetNumber
     *
     * @return int value of offsetNumber (12 bits)
     */
    public int getOffsetNumber() {
        return offsetNumber;
    }

    private int numberOfSlots;

    /**
     * numberOfSlots
     *
     * @return int value of numberOfSlots (4 bits)
     */
    public int getNumberOfSlots() {
        return numberOfSlots;
    }

    private int timeOut;

    /**
     * timeOut
     *
     * @return int value of timeOut (3 bits)
     */
    public int getTimeOut() {
        return timeOut;
    }

    private int increment;

    /**
     * increment
     *
     * @return int value of increment (11 bits)
     */
    public int getIncrement() {
        return increment;
    }

    public AISMessage20UnitImpl() {
    }

    public AISMessage20UnitImpl(int offset, Sixbit content) {
        assert (content.length() >= offset + 29);
        offsetNumber = content.getIntFromTo(offset, offset + 11);
        numberOfSlots = content.getIntFromTo(offset + 12, offset + 15);
        timeOut = content.getIntFromTo(offset + 16, offset + 18);
        increment = content.getIntFromTo(offset + 19, offset + 29);
    }
}
