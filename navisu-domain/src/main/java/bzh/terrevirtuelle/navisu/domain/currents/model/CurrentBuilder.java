/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.currents.model;

/**
 *
 * @author Serge
 */
public class CurrentBuilder {

    private double latitude;

    private double longitude;

    private double speed;

    private double direction;

    private double depth = 0.0;

    public CurrentBuilder() {
    }

    public static CurrentBuilder create() {
        return new CurrentBuilder();
    }

    public Current build() {
        return new Current(latitude, longitude, speed, direction, depth);
    }

    public CurrentBuilder latitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public CurrentBuilder longitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public CurrentBuilder speed(double speed) {
        this.speed = speed;
        return this;
    }

    public CurrentBuilder direction(double direction) {
        this.direction = direction;
        return this;
    }

    public CurrentBuilder depth(double depth) {
        this.depth = depth;
        return this;
    }
}
