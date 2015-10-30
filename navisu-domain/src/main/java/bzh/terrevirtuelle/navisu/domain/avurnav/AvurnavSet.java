/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.avurnav;

import bzh.terrevirtuelle.navisu.domain.avurnav.app.App;
import bzh.terrevirtuelle.navisu.domain.avurnav.rdf.Binding;
import bzh.terrevirtuelle.navisu.domain.avurnav.rdf.Result;
import bzh.terrevirtuelle.navisu.domain.avurnav.rdf.Sparql;
import bzh.terrevirtuelle.navisu.util.xml.ImportExportXML;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * NaVisu
 *
 * @date 30 oct. 2015
 * @author Serge Morvan
 */
@XmlRootElement
@XmlType(name = "avurnavSet")
@XmlAccessorType(XmlAccessType.FIELD)
public class AvurnavSet {

    @XmlElements({
        @XmlElement(name = "avurnav", type = Avurnav.class)
    })
    private List<Avurnav> avurnavs;

    public AvurnavSet() {
        avurnavs = new ArrayList<>();
    }

    public AvurnavSet(List<Avurnav> avurnavs) {
        this.avurnavs = avurnavs;
    }

    public AvurnavSet(String srcDir, String filename, String extension) {

        String[] tmp;
        String avurnavName = "";
        Avurnav avurnav = null;
        avurnavs = new ArrayList<>();
        if (extension.contains("rdf")) {
            Sparql sparql = new Sparql();
            try {
                sparql = ImportExportXML.imports(sparql, new File(srcDir + filename + ".rdf"));
            } catch (JAXBException | FileNotFoundException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
            List<Result> results = sparql.getResults().getResultList();
            for (Result r : results) {
                List<Binding> bindings = r.getBindings();
                for (int i = 0; i < 3; i++) {
                    if (bindings.get(0).getName().equals("s")) {
                        tmp = bindings.get(0).getUri().split("#");
                        if (!avurnavName.equals(tmp[1])) {
                            avurnav = new Avurnav();
                            avurnavs.add(avurnav);
                            avurnavName = tmp[1];
                        }
                        if (bindings.get(1).getName().equals("p")) {
                            if (bindings.get(1).getUri() != null) {
                                tmp = bindings.get(1).getUri().split("#");
                            }
                            if (avurnav != null) {
                                switch (tmp[1]) {
                                    case "globalZone":
                                        avurnav.setGlobalZone(bindings.get(2).getLiteral());
                                        break;
                                    case "textDescription":
                                        avurnav.setDescription(bindings.get(2).getLiteral());
                                        break;
                                    case "expirationDate":
                                        avurnav.setExpirationDate(bindings.get(2).getLiteral());
                                        break;
                                    case "avNumber":
                                        avurnav.setId(Integer.parseInt(bindings.get(2).getLiteral()));
                                        break;
                                    case "broadcastTime":
                                        avurnav.setBroadcastTime(bindings.get(2).getLiteral());
                                        break;
                                    default:
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Get the value of avurnavs
     *
     * @return the value of avurnavs
     */
    public List<Avurnav> getAvurnavs() {
        return avurnavs;
    }

    /**
     * Set the value of avurnavs
     *
     * @param avurnavs new value of avurnavs
     */
    public void setAvurnavs(List<Avurnav> avurnavs) {
        this.avurnavs = avurnavs;
    }

    @Override
    public String toString() {
        return "AvurnavSet{" + "avurnavs=" + avurnavs + '}';
    }

    public void print() {
        avurnavs.stream().forEach((a) -> {
            a.print();
        });
    }

}
