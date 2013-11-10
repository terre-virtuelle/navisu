package bzh.terrevirtuelle.navisu.core.formats.kap.controller.formatter;

import java.io.File;
import java.nio.file.Path;

import bzh.terrevirtuelle.navisu.core.formats.kap.controller.formatter.impl.FormatterFactoryImpl;

/**
 *
 * @author jordanr(Path pathfile);
}
 */
public interface FormatterFactory {

    public static FormatterFactory factory = new FormatterFactoryImpl();
    
    public KapFormatter newKapFormatter(Path pathfile);
    public KapFormatter newKapFormatter(File pathfile);
}
