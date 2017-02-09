package bzh.terrevirtuelle.navisu.app.guiagent.svg;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;

/**
 * SVGLoader is a class for loading SVG file.
 * 
 * <pre> URL url = ...;
 * SVGContent content SVGLoader.load(url);
 * 
 * container.getChildren.add(content);</pre>
 */
public class SVGLoader {
    private SVGLoader() {}
    
    /**
     * Load SVG file and convert it to JavaFX.
     * 
     * @param url The location of SVG file
     * @return a SVGContent object that indicates SVG content
     */
    public static SVGContent load(String url) {
        SVGContent root = null;

        URL tempUrl = null;
        try {
            tempUrl = new URL(url);
        } catch (MalformedURLException ex) {
            tempUrl = SVGLoader.class.getResource(url);
            if (tempUrl == null) {
                try {
                    tempUrl = new File(url).toURI().toURL();
                } catch (final MalformedURLException ex1) {
                    Logger.getLogger(SVGLoader.class.getName()).log(Level.SEVERE, null, ex1);
                    return root;
                }
            }
        }

        SVGContentBuilder builder = new SVGContentBuilder(tempUrl);
        try {
            root = builder.build();
        } catch (IOException | XMLStreamException ex) {
            Logger.getLogger(SVGLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return root;
    }
}
