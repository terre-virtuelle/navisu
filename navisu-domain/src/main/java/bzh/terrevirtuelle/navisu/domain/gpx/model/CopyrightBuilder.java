/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.domain.gpx.model;

import java.util.GregorianCalendar;
import java.util.logging.Logger;

/**
 *
 * @author Serge
 */
public class CopyrightBuilder {
    protected GregorianCalendar year;
    protected String license;
    protected String author;

    private CopyrightBuilder() {
    }

    public static CopyrightBuilder create() {
        return new CopyrightBuilder();
    }

    public Copyright build() {
        return new Copyright(year, license, author);
    }

    public CopyrightBuilder year(GregorianCalendar year) {
        this.year = year;
        return this;
    }

    public CopyrightBuilder license(String license) {
        this.license = license;
        return this;
    }

    public CopyrightBuilder author(String author) {
        this.author = author;
        return this;
    }
    
}
