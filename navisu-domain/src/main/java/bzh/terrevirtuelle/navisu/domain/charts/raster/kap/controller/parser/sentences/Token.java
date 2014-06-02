package bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.sentences;

public class Token {

    public final static String UNKNOWN = "UNKNOWN";
    public final static String CRR = "CRR";
    public final static String VER = "VER";
    public final static String BSB = "BSB";
    public final static String KNP = "KNP";
    public final static String KNQ = "KNQ";
    public final static String CED = "CED";
    public final static String NTM = "NTM";
    public final static String OST = "OST";
    public final static String MDF = "MDF";
    public final static String IFM = "IFM";
    public final static String RGB = "RGB";
    public final static String DAY = "DAY";
    public final static String DSK = "DSK";
    public final static String NGT = "NGT";
    public final static String REF = "REF";
    public final static String PLY = "PLY";
    public final static String DTM = "DTM";
    public final static String CPH = "CPH";
    public final static String WPX = "WPX";
    public final static String WPY = "WPY";
    public final static String PWY = "PWX";
    public final static String ERR = "ERR";

    public class SubToken {

        //BSB
        public final static String BSB_NA = "NA";
        public final static String BSB_NU = "NU";
        public final static String BSB_RA = "RA";
        public final static String BSB_DU = "DU";
        //KNP
        public final static String KNP_SC = "SC";
        public final static String KNP_GD = "GD";
        public final static String KNP_PR = "PR";
        public final static String KNP_PP = "PP";
        public final static String KNP_PI = "PI";
        public final static String KNP_SK = "SK";
        public final static String KNP_TA = "TA";
        public final static String KNP_UN = "UN";
        public final static String KNP_SD = "SD";
        public final static String KNP_DX = "DX";
        public final static String KNP_DY = "DY";
        //KNQ
        public final static String KNQ_EC = "EC";
        public final static String KNQ_GD = "GD";
        public final static String KNQ_VC = "VC";
        public final static String KNQ_SC = "SC";
        public final static String KNQ_PC = "PC";
        public final static String KNQ_P1 = "P1";
        public final static String KNQ_P2 = "P2";
        public final static String KNQ_P3 = "P3";
        public final static String KNQ_P4 = "P4";
        public final static String KNQ_GC = "GC";
        public final static String KNQ_RM = "RM";
        //CED
        public final static String CED_SE = "SE";
        public final static String CED_RE = "RE";
        public final static String CED_ED = "ED";
        //NTM
        public final static String NTM_NE = "NE";
        public final static String NTM_ND = "ND";
        //MDF
        public final static String MDF_MN = "MN";
        public final static String MDF_YE = "YE";
        public final static String MDF_DV = "DV";
    }

    public static boolean isWellFormatted(String s) {

        if (s.equalsIgnoreCase(Token.UNKNOWN) || s.equalsIgnoreCase("")) {
            return false;
        }

        return true;
    }

    private Token() {
    }
}
