package bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.formatter.impl;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.formatter.KapFormatter;
import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.utils.file.FileUtils;

/**
 *
 * @author jordan
 */
public class KapFormatterImpl implements KapFormatter {

    protected Path filepath;
    
    private final String KAP_COMMENT = "!";
    private final String KAP_MULTILINE = "    ";
    private final String KAP_EXTENSION = "kap";
    
    protected String kapHeader;
    protected String kapHeaderFormatted;
    
    public KapFormatterImpl(Path filepath) {
        
        this.filepath = filepath;
        
        if(FileUtils.getExtension(filepath).toLowerCase().equals(this.KAP_EXTENSION)) {
            
            try (Scanner scanner = new Scanner(this.filepath).useDelimiter("\\x1A\\x00")) {
                this.kapHeader = scanner.next();
                
            } catch (IOException ex) {
                Logger.getLogger(KapFormatterImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    private String format() {
        Scanner scanner = new Scanner(this.kapHeader);
        StringBuilder formattedHeader = new StringBuilder();
        boolean firstTime = true;
        
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            //if it's not a comment
            if(!line.startsWith(this.KAP_COMMENT)) {
                //if the line don't start with four spaces
                if(!line.startsWith(this.KAP_MULTILINE)) {
                    //don't add a \n at the beginning of the formattedHeader
                    if(!firstTime) {
                        formattedHeader.append("\n");
                    }
                    formattedHeader.append(line);
                    firstTime = false;
                } else {
                    line = line.replaceFirst(this.KAP_MULTILINE, ",");
                    formattedHeader.append(line);
                }
            }
        }
        scanner.close();
        return formattedHeader.toString();
    }   

    @Override
    public String getUnformattedKapHeader() {
        return this.kapHeader;
    }


	@Override
	public String getFormattedKapHeader() {
		
		if(this.kapHeaderFormatted == null) {
			this.kapHeaderFormatted = this.format();
		}

		return this.kapHeaderFormatted;
	}
}