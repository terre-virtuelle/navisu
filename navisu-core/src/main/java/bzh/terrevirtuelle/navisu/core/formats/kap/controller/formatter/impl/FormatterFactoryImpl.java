package bzh.terrevirtuelle.navisu.core.formats.kap.controller.formatter.impl;

import java.io.File;
import java.nio.file.Path;

import bzh.terrevirtuelle.navisu.core.formats.kap.controller.formatter.FormatterFactory;
import bzh.terrevirtuelle.navisu.core.formats.kap.controller.formatter.KapFormatter;

/**
 *
 * @author jordan
 */
public class FormatterFactoryImpl implements FormatterFactory {

    @Override
    public KapFormatter newKapFormatter(Path pathfile) {
        return new KapFormatterImpl(pathfile);
    }

    @Override
    public KapFormatter newKapFormatter(File file) {
        return new KapFormatterImpl(file.toPath());
    }
}
