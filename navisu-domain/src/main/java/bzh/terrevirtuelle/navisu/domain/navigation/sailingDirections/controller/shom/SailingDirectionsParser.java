/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.controller.shom;

import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Book;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Chapter;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Document;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Text;
import bzh.terrevirtuelle.navisu.domain.util.Pair;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author serge
 * @date Mar 21, 2016
 *
 */
public abstract class SailingDirectionsParser {

    protected Document document;
    protected Set<Text> textSet;
    protected Map<Pair<Double, Double>, String> poiMap;
    protected Book book;
    protected List<Chapter> chapitres;

    public SailingDirectionsParser(String filename) {
        document = new Document();
        textSet = new HashSet<>();
        poiMap = new HashMap<>();
        readData(filename);
    }

    public Document getDocument() {
        return document;
    }

    public Set<Text> getTextSet() {
        textSet = parseText();
        return textSet;
    }

    public Map<Pair<Double, Double>, String> getPoiMap() {
        textSet = parseText();
        return parsePoi();
    }

    public Book getBook() {
        return book;
    }

    public List<Chapter> getChapters() {
        if (book != null) {
            return book.getChapitre();
        }
        return null;
    }

    protected abstract void readData(String filename);

    protected abstract Set<Text> parseText();

    protected abstract Map<Pair<Double, Double>, String> parsePoi();
}
