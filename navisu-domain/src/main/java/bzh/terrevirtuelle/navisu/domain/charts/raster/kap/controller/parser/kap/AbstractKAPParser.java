package bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.kap;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.formatter.FormatterFactory;
import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.formatter.KapFormatter;

public abstract class AbstractKAPParser implements KapParser {

    protected KapFormatter kapFormatter;
    protected Map<String, List<String>> sentences;

    public AbstractKAPParser() {
        this.init();
    }

    public AbstractKAPParser(Path filepath) {
        this.kapFormatter = FormatterFactory.factory.newKapFormatter(filepath);
        this.init();
    }

    public AbstractKAPParser(File file) {
        this.kapFormatter = FormatterFactory.factory.newKapFormatter(file);
        this.init();
    }

    protected Iterator<String> getKeysFromSentencesIterator() {
        return this.sentences.keySet().iterator();
    }

    /**
     * Fill the Map with key = token and Value = sentence without token
     */
    private void init() {
        this.sentences = new HashMap<>();

        String kapHeader = this.kapFormatter.getFormattedKapHeader();
        Scanner scanner = new Scanner(kapHeader);
        scanner.useDelimiter("\n");
        String currentSentence = null;

        while (scanner.hasNext()) {
            currentSentence = scanner.next();
            String key = currentSentence.substring(0, currentSentence.indexOf("/"));
            String sentenceWithoutToken = currentSentence.substring(currentSentence.indexOf("/") + 1, currentSentence.length());

            if (!this.sentences.containsKey(key)) {
                List<String> values = new ArrayList<>();
                this.sentences.put(key, values);
            }

            List<String> mapList = this.sentences.get(key);
            mapList.add(sentenceWithoutToken);
        }
        scanner.close();
    }
}
