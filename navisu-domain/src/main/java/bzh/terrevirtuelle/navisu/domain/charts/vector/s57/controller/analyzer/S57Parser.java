// $ANTLR 2.7.5 (20050128): "S57.g" -> "S57Parser.java"$
package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.controller.analyzer;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.DataSetGeographicReference;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Feature;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Spatial;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.S57Object;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.S57Model;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;

public class S57Parser extends antlr.LLkParser implements S57ParserTokenTypes {

    private S57Object obj;
    private S57Model data;

    protected S57Parser(TokenBuffer tokenBuf, int k) {
        super(tokenBuf, k);
        tokenNames = _tokenNames;
    }

    public S57Parser(TokenBuffer tokenBuf) {
        this(tokenBuf, 4);
    }

    protected S57Parser(TokenStream lexer, int k) {
        super(lexer, k);
        tokenNames = _tokenNames;
    }

    public S57Parser(TokenStream lexer) {
        this(lexer, 4);
    }

    public S57Parser(ParserSharedInputState state) {
        super(state, 4);
        tokenNames = _tokenNames;
    }

    /**
     * R�cup�re la valeur d'un champs de l'enregistrement en cours.
     *
     * @param fieldStart La position de d�but du champs
     * @param fieldLength La longueur du champs
     */
    public final void getFieldArea(
            int fieldStart, int fieldLength
    ) throws RecognitionException, TokenStreamException {

        {

            String fieldName = LT(-1).getText();
            try {
                Vector<Byte> fieldValue = new Vector<Byte>();
                /*
                 * La variable k permet de g�rer la diff�rence entre le nombre de
                 * caract�re et le nombre de Token
                 */
                int k = 0;
                int start;

                /* Positionne au s�parateur � la fin de l'enregistrement */
                int fieldArea = 0;
                while (LA(++fieldArea) != SEP);

                /*
                 * on se d�cale du nombre d'octet n�cessaire pour aller au d�but du
                 * champ
                 */
                for (start = fieldArea; start + k < fieldArea + fieldStart; start++) {
                    if (LA(start) != ZDATA) {
                        k += (LT(start).getText()).length() - 1;
                    }
                }
                k = 0;

                for (int i = start + 1; i + k < start + fieldLength; i++) {
                    byte[] b;
                    String text = LT(i).getText();
                    /*
                     * Les valeurs de 0x0080 � 0x009f sont transform�es en 0x003F
                     * lorsqu'elles sont converties en String dans les propri�t�
                     * d'un objet Token... Pour �viter �a, leur valeur binaire est
                     * enregistr�e en chaine de caract�re dans le lexer puis
                     * r�cup�r�es et retransform�es en octet dans le parser
                     */
                    if (LA(i) == ZDATA) {
                        b = new byte[1];
                        b[0] = (byte) Integer.parseInt(text);
                    } else {//TODO comprendre pourquoi le AA devient A???
                        b = text.getBytes();
                    }

                    /*
                     * cette boucle permet de traiter les tokens de plus de 1
                     * caract�re (ex: les entiers de plus de 1 chiffre)
                     */
                    for (int j = 0; j < java.lang.reflect.Array.getLength(b); j++) {
                        k++;
                        fieldValue.add(b[j]);
                        if (i + k >= fieldArea + fieldStart + fieldLength) {
                            break;
                        }
                    }
                    k--;
                }
                byte[] fieldByteValues = new byte[fieldValue.size()];
                for (int x = 0; x < fieldValue.size(); x++) {
                    fieldByteValues[x] = (fieldValue.elementAt(x)).byteValue();
                }
                if (obj != null) {
                    obj = obj.setField(fieldName, fieldByteValues);
                }
                //System.out.println(fieldName);
            } catch (Exception e) {
                System.err.println("getFieldArea: " + e);
                e.printStackTrace();
                System.exit(-2);
            }

        }
    }

    /**
     * R�cup�re le nom du champs, la longueur du champs, la position du champs.
     * Fonctionne de mani�re r�cursive jusqu'� l'indicateur de fin
     * d'enregistrement. A la fin de l'enregistrement, enregistre l'objet
     * r�cup�r� dans la liste des objets de l'application.
     *
     * @param recLength La longueur de l'enregistrement
     * @param recStart Le d�but de l'enregistrement
     * @param sizeOfFieldLength La taille du champs "longueur du champs"
     * @param sizeOfFieldPos La taille du champs "position du champs"
     */
    public final void fields(
            int recStart,
            int recLength,
            int sizeOfFieldLength,
            int sizeOfFieldPos
    ) throws RecognitionException, TokenStreamException {

        Token field = null;
        Token fieldLengthAndPos = null;

        try {      // for error handling
            {
                switch (LA(1)) {
                    case FIELD: {
                        field = LT(1);
                        match(FIELD);
                        fieldLengthAndPos = LT(1);
                        match(INT);
                        getFieldArea(Integer.parseInt(
                                (fieldLengthAndPos.getText()).substring(
                                        sizeOfFieldLength,
                                        sizeOfFieldLength + sizeOfFieldPos)),
                                Integer.parseInt(
                                        (fieldLengthAndPos.getText()).substring(
                                                0,
                                                sizeOfFieldLength)));
                        fields(recStart,
                                recLength,
                                sizeOfFieldLength,
                                sizeOfFieldPos);
                        break;
                    }
                    case SEP: {
                        match(SEP);

                        if (obj != null) {
                            if (obj.isFeatureObject()) {
                                //System.out.print((Feature)obj);
                                S57Model.putFeatureObject(new Long(obj.getId()), (Feature) obj);
                            } else if (obj.isSpatialObject()) {
				//System.out.print((Spatial)obj);

                                S57Model.putSpatialObject(new Long(obj.getId()), (Spatial) obj);
                            } else if (obj.isDataSet()) {
                                //System.out.println((DataSetGeographicReference)obj);
                                S57Model.setDataSet((DataSetGeographicReference) obj);
                            }
                        }

                        break;
                    }
                    default: {
                        throw new NoViableAltException(LT(1), getFilename());
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * R�cup�re la taille des champs "longueur du champs" et "position du
     * champs"
     *
     * @param recLength La longueur de l'enregistrement
     * @param recStart Le d�but de l'enregistrement
     */
    public final void entryMap(
            int recStart, int recLength
    ) throws RecognitionException, TokenStreamException {

        Token map = null;

        {
            map = LT(1);
            match(INT);
            fields(recStart, recLength, Integer.parseInt((map.getText()).substring(0, 1)), Integer.parseInt((map.getText()).substring(1, 2)));
        }
    }

    /**
     * Teste si la phrase correspond � un ent�te d'enregistrement et r�cup�re le
     * d�but l'enregistrement le cas �ch�ant
     *
     * @param recLength La longueur de l'enregistrement (si c'en est un)
     */
    public final void leaderID(
            int recLength
    ) throws RecognitionException, TokenStreamException {

        Token recStart = null;

        {
            if ((LA(1) == SPACE) && (LA(2) == D)) {
                match(SPACE);
                match(D);
                match(SPACE);
                match(SPACE);
                match(SPACE);
                match(SPACE);
                match(SPACE);
                recStart = LT(1);
                match(INT);
                match(SPACE);
                match(SPACE);
                match(SPACE);

                obj = new S57Object();

                entryMap(Integer.parseInt(recStart.getText()), recLength);
            } else if ((LA(1) == SPACE) && (_tokenSet_0.member(LA(2)))) {
                match(SPACE);
                matchNot(D);
            } else {
                throw new NoViableAltException(LT(1), getFilename());
            }

        }
    }

    /**
     * Teste si la phrase correspond � un d�but d'enregistrement et r�cup�re la
     * longueur de l'enregistrement le cas �ch�ant
     */
    public final void parse() throws RecognitionException, TokenStreamException {

        Token recLength = null;

        data = new S57Model();
        data.init();
        {
            _loop11:
            do {
                if ((LA(1) == INT) && (LA(2) == SPACE)) {
                    recLength = LT(1);
                    match(INT);
                    leaderID(Integer.parseInt(recLength.getText()));
                } else if ((LA(1) == INT) && (_tokenSet_1.member(LA(2)))) {
                    match(INT);
                    matchNot(SPACE);
                } else if ((_tokenSet_2.member(LA(1)))) {
                    matchNot(INT);
                } else {
                    break _loop11;
                }

            } while (true);
        }

        S57Model.setFeatures(new HashSet<>(S57Model.getFeatureObjects().values()));
        S57Model.linkObjects();

    }

    public static final String[] _tokenNames = {
        "<0>",
        "EOF",
        "<2>",
        "NULL_TREE_LOOKAHEAD",
        "FIELD",
        "INT",
        "SEP",
        "SPACE",
        "D",
        "ZDATA",
        "ALPHA",
        "DATA"
    };

    private static final long[] mk_tokenSet_0() {
        long[] data = {3824L, 0L, 0L, 0L};
        return data;
    }
    public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());

    private static final long[] mk_tokenSet_1() {
        long[] data = {3952L, 0L, 0L, 0L};
        return data;
    }
    public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());

    private static final long[] mk_tokenSet_2() {
        long[] data = {4048L, 0L, 0L, 0L};
        return data;
    }
    public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());

}
