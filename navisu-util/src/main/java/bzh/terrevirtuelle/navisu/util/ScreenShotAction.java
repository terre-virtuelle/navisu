/*
 * Copyright (C) 2012 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration.
 * All Rights Reserved.
 */
package bzh.terrevirtuelle.navisu.util;

import com.jogamp.opengl.util.awt.AWTGLReadBufferUtil;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.RenderingEvent;
import gov.nasa.worldwind.event.RenderingListener;
import gov.nasa.worldwind.util.WWIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.media.opengl.GLAutoDrawable;

/**
 * @author tag
 * @version $Id: ScreenShotAction.java 1171 2013-02-11 21:45:02Z dcollins $
 */
public class ScreenShotAction
        implements RenderingListener {

    WorldWindow wwd;
    private File snapFile;
    private final Stage stage;
    FileChooser fileChooser;
    File file;

    public ScreenShotAction(WorldWindow wwd, Stage stage) {
        this.stage = stage;
        this.wwd = wwd;
        this.fileChooser = new FileChooser();
        this.chooseFile(stage);
    }

    private File chooseFile(Stage stage) {
        File outFile = null;
        Platform.runLater(() -> {
            fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter
                    = new FileChooser.ExtensionFilter("Images files (*.png)", "*.png");
            fileChooser.getExtensionFilters().add(extFilter);
            snapFile = fileChooser.showSaveDialog(stage);
            this.wwd.removeRenderingListener(this); // ensure not to add a duplicate
            this.wwd.addRenderingListener(this);
        });
        return outFile;
    }

    @Override
    public void stageChanged(RenderingEvent event) {
        Platform.runLater(() -> {
            if (event.getStage().equals(RenderingEvent.AFTER_BUFFER_SWAP) && this.snapFile != null) {
                try { 
                    GLAutoDrawable glad = (GLAutoDrawable) event.getSource();
                    AWTGLReadBufferUtil glReadBufferUtil = new AWTGLReadBufferUtil(glad.getGLProfile(), false);
                    BufferedImage image = glReadBufferUtil.readPixelsToBufferedImage(glad.getGL(), true);
                    String suffix = WWIO.getSuffix(this.snapFile.getPath());
                    ImageIO.write(image, suffix, this.snapFile);
                    System.out.printf("Image saved to file %s\n", this.snapFile.getPath());
                } catch (IOException e) {
                    System.out.println("e " + e);
                    e.printStackTrace();
                } finally {
                    this.snapFile = null;
                    this.wwd.removeRenderingListener(this);
                }
            }
        });
    }

}
