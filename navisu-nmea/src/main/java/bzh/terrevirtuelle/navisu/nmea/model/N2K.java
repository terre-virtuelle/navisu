/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.nmea.model;

/**
 *
 * @author Serge
 */
public class N2K extends NMEA {

    private int pgn;
    private int sid;

    public N2K() {
    }

    public N2K(int pgn, String source, int sid) {
        super(source);
        this.pgn = pgn;
        this.sid = sid;
    }

    public N2K(int pgn, String source) {
        super(source);
        this.pgn = pgn;
    }

    /**
     * Get the value of sid
     *
     * @return the value of sid
     */
    public int getSid() {
        return sid;
    }

    /**
     * Set the value of sid
     *
     * @param sid new value of sid
     */
    public void setSid(int sid) {
        this.sid = sid;
    }

    /**
     * Get the value of pgn
     *
     * @return the value of pgn
     */
    public int getPgn() {
        return pgn;
    }

    /**
     * Set the value of pgn
     *
     * @param pgn new value of pgn
     */
    public void setPgn(int pgn) {
        this.pgn = pgn;
    }

    public String getSource() {
        return getDevice();
    }

    public void setSource(String source) {
        setDevice(source);
    }
}
