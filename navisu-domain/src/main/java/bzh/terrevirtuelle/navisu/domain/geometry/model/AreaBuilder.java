/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.geometry.model;

/**
 *
 * @author serge
 * @date Feb 13, 2016
 *
 */
public class AreaBuilder {

    private long id = 0;
    private double latitude = 0.0;
    private double longitude = 0.0;
    private String geometry = "";
    private String name = "";
    private String description = "";

    private AreaBuilder() {
    }

    public static AreaBuilder create() {
        return new AreaBuilder();
    }

    public Area build() {
        return new Area(id, latitude, longitude, geometry, name, description);
    }

    public AreaBuilder id(long id) {
        this.id = id;
        return this;
    }

    public AreaBuilder latitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public AreaBuilder longitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public AreaBuilder geometry(String geometry) {
        this.geometry = geometry;
        return this;
    }

    public AreaBuilder name(String name) {
        this.name = name;
        return this;
    }

    public AreaBuilder description(String description) {
        this.description = description;
        return this;
    }
}
