package bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.sentences.knp.impl;

import java.util.List;

import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.sentences.SentenceParser;
import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.sentences.Token;
import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.model.KAP;

public class KnpSentenceParserImpl implements SentenceParser {

    @Override
    public void parse(KAP kap, List<String> sentences) {

        String sentence = null;
        if (sentences.size() > 0) {
            sentence = sentences.get(0);
        }

        if (sentence != null) {
            String[] splitSentence = sentence.split(",");

            for (int i = 0; i < splitSentence.length; i++) {
                String currentSentence = splitSentence[i];

                if (currentSentence.startsWith(Token.SubToken.KNP_SC)) {
                    String[] scSentence = currentSentence.split("=");
                    if (scSentence.length == 2 && Token.isWellFormatted(scSentence[1])) {
                        kap.setSC(Integer.parseInt(scSentence[1]));
                    } else {
                        kap.setSC(0);
                    }
                }

                if (currentSentence.startsWith(Token.SubToken.KNP_GD)) {
                    String[] gdSentence = currentSentence.split("=");
                    if (gdSentence.length == 2) {
                        kap.setGD(gdSentence[1]);
                    } else {
                        kap.setGD("");
                    }
                }

                if (currentSentence.startsWith(Token.SubToken.KNP_PR)) {
                    String[] prSentence = currentSentence.split("=");
                    if (prSentence.length == 2) {
                        kap.setPR(prSentence[1]);
                    } else {
                        kap.setPR("");
                    }
                }

                if (currentSentence.startsWith(Token.SubToken.KNP_PP)) {
                    String[] ppSentence = currentSentence.split("=");
                    if (ppSentence.length == 2 && Token.isWellFormatted(ppSentence[1])) {
                        kap.setPP(Double.parseDouble(ppSentence[1]));
                    } else {
                        kap.setPP(0);
                    }
                }

                if (currentSentence.startsWith(Token.SubToken.KNP_PI)) {
                    String[] piSentence = currentSentence.split("=");
                    if (piSentence.length == 2 && Token.isWellFormatted(piSentence[1])) {
                        kap.setPI(Double.parseDouble(piSentence[1]));
                    } else {
                        kap.setPI(0);
                    }
                }

                if (currentSentence.startsWith(Token.SubToken.KNP_SK)) {
                    String[] skSentence = currentSentence.split("=");
                    if (skSentence.length == 2 && Token.isWellFormatted(skSentence[1])) {
                        kap.setSK(Double.parseDouble(skSentence[1]));
                    } else {
                        kap.setSK(0);
                    }
                }

                if (currentSentence.startsWith(Token.SubToken.KNP_TA)) {
                    String[] taSentence = currentSentence.split("=");
                    if (taSentence.length == 2 && Token.isWellFormatted(taSentence[1])) {
                        kap.setTA(Double.parseDouble(taSentence[1]));
                    } else {
                        kap.setTA(0);
                    }
                }

                if (currentSentence.startsWith(Token.SubToken.KNP_UN)) {
                    String[] unSentence = currentSentence.split("=");
                    if (unSentence.length == 2) {
                        kap.setUN(unSentence[1]);
                    } else {
                        kap.setUN("");
                    }

                }

                if (currentSentence.startsWith(Token.SubToken.KNP_SD)) {
                    String[] sdSentence = currentSentence.split("=");
                    if (sdSentence.length == 2) {
                        kap.setSD(sdSentence[1]);
                    } else {
                        kap.setSD("");
                    }

                }

                if (currentSentence.startsWith(Token.SubToken.KNP_DX)) {
                    String[] dxSentence = currentSentence.split("=");
                    if (dxSentence.length == 2 && Token.isWellFormatted(dxSentence[1])) {
                        kap.setDX(Double.parseDouble(dxSentence[1]));
                    } else {
                        kap.setDX(0);
                    }
                }

                if (currentSentence.startsWith(Token.SubToken.KNP_DY)) {
                    String[] dySentence = currentSentence.split("=");
                    if (dySentence.length == 2 && Token.isWellFormatted(dySentence[1])) {
                        kap.setDY(Double.parseDouble(dySentence[1]));
                    } else {
                        kap.setDY(0);
                    }
                }
            }
        }
    }
}
