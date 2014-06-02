package bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.sentences.bsb.impl;

import java.util.List;

import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.sentences.SentenceParser;
import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.sentences.Token;
import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.model.KAP;

/**
 *
 * @author jordan
 */
public class BsbSentenceParserImpl implements SentenceParser {

    /**
     * NA String NU int RA [int, int] DU int
     */
    @Override
    public void parse(KAP kap, List<String> sentences) {

        String sentence = null;
        if (sentences.size() > 0) {
            sentence = sentences.get(0);
        }

        if (sentence != null) {
            String[] splitSentence = sentence.split(",");
            kap.setNa(splitSentence[1]);

            //NA
            String naSentence = splitSentence[0];
            if (naSentence.startsWith(Token.SubToken.BSB_NA)) {
                String[] splitNA = naSentence.split("=");
                if (splitNA.length == 2 && Token.isWellFormatted(splitNA[1])) {
                    kap.setNa(splitNA[1]);
                } else {
                    kap.setNa("");
                }
            }

            //NU
            String nuSentence = splitSentence[1];
            if (nuSentence.startsWith(Token.SubToken.BSB_NU)) {
                String[] splitNU = nuSentence.split("=");
                if (splitNU.length == 2) {
                    try {
                        kap.setNu(Integer.parseInt(splitNU[1]));
                    } catch (NumberFormatException e) {
                        kap.setNu(0);
                    }
                } else {
                    kap.setNu(0);
                }
            }

            //RA
            String raSentence = splitSentence[2];
            if (raSentence.startsWith(Token.SubToken.BSB_RA)) {
                String[] splitRA = raSentence.split("=");
                if (splitRA.length > 3 && Token.isWellFormatted(splitRA[1]) && Token.isWellFormatted(splitSentence[3])) {
                    int[] ra = {Integer.parseInt(splitRA[1]), Integer.parseInt(splitSentence[3])};
                    kap.setRa(ra);
                } else {
                    kap.setRa(new int[]{0, 0});
                }
            }

            //DU
            String duSentence = splitSentence[4];
            if (duSentence.startsWith(Token.SubToken.BSB_DU)) {
                String[] splitDU = duSentence.split("=");
                if (splitDU.length == 2 && Token.isWellFormatted(splitDU[1])) {
                    kap.setDu(Integer.parseInt(splitDU[1]));
                } else {
                    kap.setDu(0);
                }
            }
        }
    }
}
