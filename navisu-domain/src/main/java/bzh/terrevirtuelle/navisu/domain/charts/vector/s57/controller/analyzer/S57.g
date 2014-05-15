header
{
	package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.controler.analyzer;
	import java.util.HashMap;
        import java.util.HashSet;	
	import java.util.Vector;
	import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.*;
}

class S57Parser extends Parser;
options{k=4;defaultErrorHandler=false;}


{
   private S57Object obj;
   private DataSet data;
}

/**
 * Recupere la valeur d'un champs de l'enregistrement en cours.
 * @param fieldStart
 * 		La position de debut du champs
 * @param fieldLength
 * 		La longueur du champs
 */
getFieldArea[int fieldStart, int fieldLength] : 
(
{
	String fieldName = LT(-1).getText();
	try
	{
		Vector<Byte> fieldValue = new Vector<Byte>();
		/*
		 * La variable k permet de g�rer la diff�rence entre le nombre de
		 * caract�re et le nombre de Token
		 */
		int k = 0;
		int start;

		/* Positionne au separateur a la fin de l'enregistrement */
		int fieldArea = 0;
		while (LA(++fieldArea) != SEP);

		/*
		 * on se decale du nombre d'octet necessaire pour aller au d�but du
		 * champ
		 */
		for (start = fieldArea; start + k < fieldArea + fieldStart; start++)
		{
			if (LA(start) != ZDATA)
			{
				k += (LT(start).getText()).length() - 1;
			}
		}
		k = 0;

		for (int i=start+1;i+k<start+fieldLength;i++)
		{
			byte[] b;
			String text=LT(i).getText();
			/*
			 * Les valeurs de 0x0080 � 0x009f sont transform�es en 0x003F
			 * lorsqu'elles sont converties en String dans les propri�t�
			 * d'un objet Token... Pour �viter �a, leur valeur binaire est
			 * enregistr�e en chaine de caract�re dans le lexer puis
			 * r�cup�r�es et retransform�es en octet dans le parser
			 */
			if (LA(i) == ZDATA)
			{
				b=new byte[1];
				b[0] = (byte)Integer.parseInt(text);
			}
			else
			{//TODO comprendre pourquoi le AA devient A???
				b = text.getBytes();
			}

			/*
			 * cette boucle permet de traiter les tokens de plus de 1
			 * caract�re (ex: les entiers de plus de 1 chiffre)
			 */
			for (int j = 0; j < java.lang.reflect.Array.getLength(b); j++)
			{
				k++;
				fieldValue.add(b[j]);
				if (i + k >= fieldArea + fieldStart + fieldLength)
				{
					break;
				}
			}
			k--;
		}
		byte[] fieldByteValues=new byte[fieldValue.size()];
		for(int x=0;x<fieldValue.size();x++)
		{
			fieldByteValues[x]=(fieldValue.elementAt(x)).byteValue();
		}
		if(obj!=null){
                    obj = obj.setField(fieldName,fieldByteValues);
                }
		//System.out.println(fieldName);
	}
	catch (Exception e)
	{
		System.err.println("getFieldArea: " + e);
		e.printStackTrace();System.exit(-2);
	}
});

/**
 * R�cup�re le nom du champs, la longueur du champs, la position du champs. 
 * Fonctionne de mani�re r�cursive jusqu'� l'indicateur de fin d'enregistrement. 
 * A la fin de l'enregistrement, 
 * enregistre l'objet r�cup�r� dans la liste des objets de l'application.
 * 
 * @param recLength
 * 			La longueur de l'enregistrement
 * @param recStart
 * 			Le d�but de l'enregistrement
 * @param sizeOfFieldLength
 * 			La taille du champs "longueur du champs"
 * @param sizeOfFieldPos
 * 			La taille du champs "position du champs"
 */
fields[int recStart,
	int recLength,
	int sizeOfFieldLength,
	int sizeOfFieldPos] : 
(
		field : FIELD fieldLengthAndPos : INT getFieldArea[Integer.parseInt(
			(fieldLengthAndPos.getText()).substring(
				sizeOfFieldLength,
				sizeOfFieldLength + sizeOfFieldPos)),
		Integer.parseInt(
			(fieldLengthAndPos.getText()).substring(
				0,
				sizeOfFieldLength))] fields[recStart,
		recLength,
		sizeOfFieldLength,
		sizeOfFieldPos] 
		| SEP 
		{
			if(obj!=null)
			{
				if (obj.isFeatureObject())
				{
//System.out.print((Feature)obj);
					DataSet.putFeatureObject(new Long(obj.getId()), (Feature)obj);
				}
				else if (obj.isSpatialObject())
				{
//System.out.print((Spatial)obj);

					DataSet.putSpatialObject(new Long(obj.getId()), (Spatial)obj);
				}
				else if (obj.isDataSet())
				{
//System.out.println((DataSetGeographicReference)obj);
					DataSet.setDataSet((DataSetGeographicReference)obj);
				}
			}
		}
	
)exception catch[Exception e] { System.err.println(e);};

/**
 * R�cup�re la taille des champs "longueur du champs" et "position du champs"
 * @param recLength
 * 			La longueur de l'enregistrement
 * @param recStart
 * 			Le d�but de l'enregistrement
 */
entryMap[int recStart, int recLength] : 
(
	map : INT fields[recStart,recLength,Integer.parseInt((map.getText()).substring(0, 1)),Integer.parseInt((map.getText()).substring(1, 2))]
);

/**
 * Teste si la phrase correspond � un ent�te d'enregistrement
 * et r�cup�re le d�but l'enregistrement le cas �ch�ant
 * @param recLength
 * 			La longueur de l'enregistrement (si c'en est un)
 */
leaderID[int recLength] : 
(
	SPACE D SPACE SPACE SPACE SPACE SPACE recStart : INT SPACE SPACE SPACE 
	{
		obj = new S57Object();
	}
	entryMap[Integer.parseInt(recStart.getText()), recLength] 
	| SPACE ~D
);

/** 
 * Teste si la phrase correspond � un d�but d'enregistrement 
 * et r�cup�re la longueur de l'enregistrement le cas �ch�ant
 */
parse : {data = new DataSet();
            data.init();}
(
	recLength : INT leaderID[Integer.parseInt(recLength.getText())]
	| INT ~SPACE
	| ~INT
)* {
     DataSet.setFeatures(new HashSet<>(DataSet.getFeatureObjects().values()));
     DataSet.linkObjects();
   };

class S57Lexer extends Lexer;
options
{
	filter = true;
	k = 4;
	defaultErrorHandler = false;
	charVocabulary = '\u0000'..'\u00FF';
}

INT : ('0'..'9') +;

protected
D : 'D';
SPACE : ' ';

protected
FIELD : 	
	( "ATTF"
	| "ATTV"
	| "AR2D"
	| "ARCC"
	| "CATD"
	| "CATX"
	| "CT2D"
	| "DDDF"
	| "DDDR"
	| "DDDI"
	| "DDOM"
	| "DDRF"
	| "DDSC"
	| "DDSI"
	| "DSAC"
	| "DSHT"
	| "DSPM"
	| "DSPR"
	| "DSRC"
	| "DSID"
	| "DSSI"
	| "EL2D"
	| "FFPC"
	| "FFPT"
	| "FOID"
	| "FRID"
	| "FSPC"
	| "FSPT"
	| "NATF"
	| "SG2D"
	| "SG3D"
	| "SGCC"
	| "VRID"
	| "VRPC"
	| "VRPT")
;

SEP : '\u001E';


/*
 * On r�cup�re la valeur de l'octet et on la transforme en entier pour
 * l'enregistrer dans une String
 */
ZDATA : ('\u0080'..'\u009F')
{
	char[] octet = text.getBuffer();
	String literal;
	literal = (new Integer(octet[0])).toString();
	$setText(literal);
};

ALPHA : (FIELD)=>FIELD{$setType(FIELD);}
		| (D SPACE)  => D{$setType(D);}
		|(('A'..'Z')|' ')+;
		
DATA : ('\u0000'..'\u00FF');
