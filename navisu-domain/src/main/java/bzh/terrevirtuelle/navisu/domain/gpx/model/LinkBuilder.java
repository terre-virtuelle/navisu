/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.gpx.model;

/**
 *
 * @author Serge
 */
public class LinkBuilder {

    protected String text = "";
    protected String type = "";
    protected String href = "";

    private LinkBuilder() {
    }

    public static LinkBuilder create() {
        return new LinkBuilder();
    }

    public Links build() {
        return new Links(text, type, href);
    }

    public LinkBuilder text(String text) {
        this.text = text;
        return this;
    }

    public LinkBuilder type(String type) {
        this.type = type;
        return this;
    }

    public LinkBuilder href(String href) {
        this.href = href;
        return this;
    }

}
