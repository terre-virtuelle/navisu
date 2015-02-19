// $ANTLR 2.7.5 (20050128): "S57.g" -> "S57Lexer.java"$

	package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.controller.analyzer;
	import java.util.HashMap;
        import java.util.HashSet;	
	import java.util.Vector;

import java.io.InputStream;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.TokenStreamRecognitionException;
import antlr.CharStreamException;
import antlr.CharStreamIOException;
import antlr.ANTLRException;
import java.io.Reader;
import java.util.Hashtable;
import antlr.CharScanner;
import antlr.InputBuffer;
import antlr.ByteBuffer;
import antlr.CharBuffer;
import antlr.Token;
import antlr.CommonToken;
import antlr.RecognitionException;
import antlr.NoViableAltForCharException;
import antlr.MismatchedCharException;
import antlr.TokenStream;
import antlr.ANTLRHashString;
import antlr.LexerSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.SemanticException;

public class S57Lexer extends antlr.CharScanner implements S57ParserTokenTypes, TokenStream
 {
public S57Lexer(InputStream in) {
	this(new ByteBuffer(in));
}
public S57Lexer(Reader in) {
	this(new CharBuffer(in));
}
public S57Lexer(InputBuffer ib) {
	this(new LexerSharedInputState(ib));
}
public S57Lexer(LexerSharedInputState state) {
	super(state);
	caseSensitiveLiterals = true;
	setCaseSensitive(true);
	literals = new Hashtable();
}

public Token nextToken() throws TokenStreamException {
	Token theRetToken=null;
tryAgain:
	for (;;) {
		Token _token = null;
		int _ttype = Token.INVALID_TYPE;
		setCommitToPath(false);
		resetText();
		try {   // for char stream error handling
			try {   // for lexical error handling
				if (((LA(1) >= '0' && LA(1) <= '9')) && (true) && (true) && (true)) {
					mINT(true);
					theRetToken=_returnToken;
				}
				else if ((LA(1)==' ') && (true) && (true) && (true)) {
					mSPACE(true);
					theRetToken=_returnToken;
				}
				else if ((LA(1)=='\u001e') && (true) && (true) && (true)) {
					mSEP(true);
					theRetToken=_returnToken;
				}
				else if (((LA(1) >= '\u0080' && LA(1) <= '\u009f')) && (true) && (true) && (true)) {
					mZDATA(true);
					theRetToken=_returnToken;
				}
				else if ((_tokenSet_0.member(LA(1))) && (true) && (true) && (true)) {
					mALPHA(true);
					theRetToken=_returnToken;
				}
				else if (((LA(1) >= '\u0000' && LA(1) <= '\u00ff')) && (true) && (true) && (true)) {
					mDATA(true);
					theRetToken=_returnToken;
				}
				else {
					if (LA(1)==EOF_CHAR) {uponEOF(); _returnToken = makeToken(Token.EOF_TYPE);}
				else {consume(); continue tryAgain;}
				}
				
				if ( _returnToken==null ) continue tryAgain; // found SKIP token
				_ttype = _returnToken.getType();
				_ttype = testLiteralsTable(_ttype);
				_returnToken.setType(_ttype);
				return _returnToken;
			}
			catch (RecognitionException e) {
				if ( !getCommitToPath() ) {consume(); continue tryAgain;}
				throw new TokenStreamRecognitionException(e);
			}
		}
		catch (CharStreamException cse) {
			if ( cse instanceof CharStreamIOException ) {
				throw new TokenStreamIOException(((CharStreamIOException)cse).io);
			}
			else {
				throw new TokenStreamException(cse.getMessage());
			}
		}
	}
}

	public final void mINT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = INT;
		int _saveIndex;
		
		{
		int _cnt14=0;
		_loop14:
		do {
			if (((LA(1) >= '0' && LA(1) <= '9'))) {
				matchRange('0','9');
			}
			else {
				if ( _cnt14>=1 ) { break _loop14; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
			}
			
			_cnt14++;
		} while (true);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mD(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = D;
		int _saveIndex;
		
		match('D');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSPACE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = SPACE;
		int _saveIndex;
		
		match(' ');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mFIELD(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = FIELD;
		int _saveIndex;
		
		{
		switch ( LA(1)) {
		case 'E':
		{
			match("EL2D");
			break;
		}
		case 'N':
		{
			match("NATF");
			break;
		}
		default:
			if ((LA(1)=='A') && (LA(2)=='T') && (LA(3)=='T') && (LA(4)=='F')) {
				match("ATTF");
			}
			else if ((LA(1)=='A') && (LA(2)=='T') && (LA(3)=='T') && (LA(4)=='V')) {
				match("ATTV");
			}
			else if ((LA(1)=='C') && (LA(2)=='A') && (LA(3)=='T') && (LA(4)=='D')) {
				match("CATD");
			}
			else if ((LA(1)=='C') && (LA(2)=='A') && (LA(3)=='T') && (LA(4)=='X')) {
				match("CATX");
			}
			else if ((LA(1)=='D') && (LA(2)=='D') && (LA(3)=='D') && (LA(4)=='F')) {
				match("DDDF");
			}
			else if ((LA(1)=='D') && (LA(2)=='D') && (LA(3)=='D') && (LA(4)=='R')) {
				match("DDDR");
			}
			else if ((LA(1)=='D') && (LA(2)=='D') && (LA(3)=='D') && (LA(4)=='I')) {
				match("DDDI");
			}
			else if ((LA(1)=='D') && (LA(2)=='D') && (LA(3)=='S') && (LA(4)=='C')) {
				match("DDSC");
			}
			else if ((LA(1)=='D') && (LA(2)=='D') && (LA(3)=='S') && (LA(4)=='I')) {
				match("DDSI");
			}
			else if ((LA(1)=='D') && (LA(2)=='S') && (LA(3)=='P') && (LA(4)=='M')) {
				match("DSPM");
			}
			else if ((LA(1)=='D') && (LA(2)=='S') && (LA(3)=='P') && (LA(4)=='R')) {
				match("DSPR");
			}
			else if ((LA(1)=='F') && (LA(2)=='F') && (LA(3)=='P') && (LA(4)=='C')) {
				match("FFPC");
			}
			else if ((LA(1)=='F') && (LA(2)=='F') && (LA(3)=='P') && (LA(4)=='T')) {
				match("FFPT");
			}
			else if ((LA(1)=='F') && (LA(2)=='S') && (LA(3)=='P') && (LA(4)=='C')) {
				match("FSPC");
			}
			else if ((LA(1)=='F') && (LA(2)=='S') && (LA(3)=='P') && (LA(4)=='T')) {
				match("FSPT");
			}
			else if ((LA(1)=='V') && (LA(2)=='R') && (LA(3)=='P') && (LA(4)=='C')) {
				match("VRPC");
			}
			else if ((LA(1)=='V') && (LA(2)=='R') && (LA(3)=='P') && (LA(4)=='T')) {
				match("VRPT");
			}
			else if ((LA(1)=='A') && (LA(2)=='R') && (LA(3)=='2')) {
				match("AR2D");
			}
			else if ((LA(1)=='A') && (LA(2)=='R') && (LA(3)=='C')) {
				match("ARCC");
			}
			else if ((LA(1)=='D') && (LA(2)=='D') && (LA(3)=='O')) {
				match("DDOM");
			}
			else if ((LA(1)=='D') && (LA(2)=='D') && (LA(3)=='R')) {
				match("DDRF");
			}
			else if ((LA(1)=='D') && (LA(2)=='S') && (LA(3)=='A')) {
				match("DSAC");
			}
			else if ((LA(1)=='D') && (LA(2)=='S') && (LA(3)=='H')) {
				match("DSHT");
			}
			else if ((LA(1)=='D') && (LA(2)=='S') && (LA(3)=='R')) {
				match("DSRC");
			}
			else if ((LA(1)=='D') && (LA(2)=='S') && (LA(3)=='I')) {
				match("DSID");
			}
			else if ((LA(1)=='D') && (LA(2)=='S') && (LA(3)=='S')) {
				match("DSSI");
			}
			else if ((LA(1)=='S') && (LA(2)=='G') && (LA(3)=='2')) {
				match("SG2D");
			}
			else if ((LA(1)=='S') && (LA(2)=='G') && (LA(3)=='3')) {
				match("SG3D");
			}
			else if ((LA(1)=='S') && (LA(2)=='G') && (LA(3)=='C')) {
				match("SGCC");
			}
			else if ((LA(1)=='V') && (LA(2)=='R') && (LA(3)=='I')) {
				match("VRID");
			}
			else if ((LA(1)=='C') && (LA(2)=='T')) {
				match("CT2D");
			}
			else if ((LA(1)=='F') && (LA(2)=='O')) {
				match("FOID");
			}
			else if ((LA(1)=='F') && (LA(2)=='R')) {
				match("FRID");
			}
		else {
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSEP(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = SEP;
		int _saveIndex;
		
		match('\u001E');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mZDATA(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = ZDATA;
		int _saveIndex;
		
		{
		matchRange('\u0080','\u009F');
		}
		if ( inputState.guessing==0 ) {
			
				char[] octet = text.getBuffer();
				String literal;
				literal = (new Integer(octet[0])).toString();
				text.setLength(_begin); text.append(literal);
			
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mALPHA(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = ALPHA;
		int _saveIndex;
		
		boolean synPredMatched24 = false;
		if (((_tokenSet_1.member(LA(1))) && (_tokenSet_2.member(LA(2))) && (_tokenSet_3.member(LA(3))) && (_tokenSet_4.member(LA(4))))) {
			int _m24 = mark();
			synPredMatched24 = true;
			inputState.guessing++;
			try {
				{
				mFIELD(false);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched24 = false;
			}
			rewind(_m24);
			inputState.guessing--;
		}
		if ( synPredMatched24 ) {
			mFIELD(false);
			if ( inputState.guessing==0 ) {
				_ttype = FIELD;
			}
		}
		else {
			boolean synPredMatched26 = false;
			if (((LA(1)=='D') && (true) && (true) && (true))) {
				int _m26 = mark();
				synPredMatched26 = true;
				inputState.guessing++;
				try {
					{
					mD(false);
					mSPACE(false);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched26 = false;
				}
				rewind(_m26);
				inputState.guessing--;
			}
			if ( synPredMatched26 ) {
				mD(false);
				if ( inputState.guessing==0 ) {
					_ttype = D;
				}
			}
			else if ((_tokenSet_0.member(LA(1))) && (true) && (true) && (true)) {
				{
				int _cnt29=0;
				_loop29:
				do {
					switch ( LA(1)) {
					case 'A':  case 'B':  case 'C':  case 'D':
					case 'E':  case 'F':  case 'G':  case 'H':
					case 'I':  case 'J':  case 'K':  case 'L':
					case 'M':  case 'N':  case 'O':  case 'P':
					case 'Q':  case 'R':  case 'S':  case 'T':
					case 'U':  case 'V':  case 'W':  case 'X':
					case 'Y':  case 'Z':
					{
						{
						matchRange('A','Z');
						}
						break;
					}
					case ' ':
					{
						match(' ');
						break;
					}
					default:
					{
						if ( _cnt29>=1 ) { break _loop29; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
					}
					}
					_cnt29++;
				} while (true);
				}
			}
			else {
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
			}
			}
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		}
		
	public final void mDATA(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DATA;
		int _saveIndex;
		
		{
		matchRange('\u0000','\u00FF');
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 4294967296L, 134217726L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 0L, 4735098L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 0L, 1872082L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 3377699720527872L, 1934106L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 0L, 22291032L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	
	}
