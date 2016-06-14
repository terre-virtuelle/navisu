
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author serge
 * @date Feb 19, 2016
 *
 */
@XmlRootElement(name = "texte")
@XmlAccessorType(XmlAccessType.FIELD)
public class Text {

    @XmlElements({
        @XmlElement(name = "principal", type = Principal.class),
        @XmlElement(name = "secondaire", type = Secondary.class),
        @XmlElement(name = "reference", type = Reference.class),
        @XmlElement(name = "lien", type = Link.class),
        @XmlElement(name = "txt", type = Txt.class)
    })
    private final List<TextPart> textParts = new ArrayList<>();
    private boolean contains = false;

    public Text() {
    }

    public List<TextPart> getTextParts() {
        return textParts;
    }

    @Override
    public String toString() {
        return "Texte{" + "textParts=" + textParts + '}';
    }

    public boolean contains(CharSequence str) {
        textParts.stream().filter((tp) -> (tp.contains(str) == true)).forEach((_item) -> {
            contains = true;
        });
        return contains;
    }

    public String shorten() {
        String tmp = new String();
        tmp = textParts.stream().map((tp) -> tp.shorten()).reduce(tmp, String::concat);
        return tmp;
    }
}
