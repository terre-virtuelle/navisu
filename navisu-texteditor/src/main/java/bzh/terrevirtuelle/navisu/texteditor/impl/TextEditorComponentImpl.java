package bzh.terrevirtuelle.navisu.texteditor.impl;

import bzh.terrevirtuelle.navisu.texteditor.TextEditorComponent;
import bzh.terrevirtuelle.navisu.texteditor.TextEditorComponentServices;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.ImageView;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.capcaval.c3.component.ComponentState;

/**
 * @author serge Date: 9/2/2017
 */
public class TextEditorComponentImpl
        implements TextEditorComponent, TextEditorComponentServices,
        ComponentState {

    @Override
    public void componentInitiated() {

    }

    @Override
    public void componentStarted() {

    }

    @Override
    public void componentStopped() {
    }

    @Override
    public List<Image> convertPDFFileToImages(String filename, int width, int height, String extension) {
        List<Image> images = new ArrayList<>();
        try {
            try (PDDocument document = PDDocument.load(new File(filename))) {
                PDFRenderer pdfRenderer = new PDFRenderer(document);
                for (int page = 0; page < document.getNumberOfPages(); ++page) {
                    BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
                    images.add(bim.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(TextEditorComponentImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return images;
    }

    @Override
    public void pageFlipper(List<Image> images) {
    }

}
