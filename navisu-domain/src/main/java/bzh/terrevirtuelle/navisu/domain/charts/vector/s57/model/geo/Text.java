package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class Text extends Geo
implements Serializable
{

	public Text(Long id)
	{
		this.id=id;
	}

	public Text(){}

	private String characterSpacing;

	public String getCharacterSpacing()
	{
		return characterSpacing;
	}

	public void setCharacterSpacing(String value)
	{
		this.characterSpacing= value;
	}

	private String characterSpecification;

	public String getCharacterSpecification()
	{
		return characterSpecification;
	}

	public void setCharacterSpecification(String value)
	{
		this.characterSpecification= value;
	}

	private String colour;

	public String getColour()
	{
		return colour;
	}

	public void setColour(String value)
	{
		this.colour= value;
	}

	private String justificationHorizontal;

	public String getJustificationHorizontal()
	{
		return justificationHorizontal;
	}

	public void setJustificationHorizontal(String value)
	{
		this.justificationHorizontal= value;
	}

	private String justificationVertical;

	public String getJustificationVertical()
	{
		return justificationVertical;
	}

	public void setJustificationVertical(String value)
	{
		this.justificationVertical= value;
	}

	private String pictorialRepresentation;

	public String getPictorialRepresentation()
	{
		return pictorialRepresentation;
	}

	public void setPictorialRepresentation(String value)
	{
		this.pictorialRepresentation= value;
	}

	private String recordIngdate;

	public String getRecordIngdate()
	{
		return recordIngdate;
	}

	public void setRecordIngdate(String value)
	{
		this.recordIngdate= value;
	}

	private String recordingIndication;

	public String getRecordingIndication()
	{
		return recordingIndication;
	}

	public void setRecordingIndication(String value)
	{
		this.recordingIndication= value;
	}

	private String scaleMaximum;

	public String getScaleMaximum()
	{
		return scaleMaximum;
	}

	public void setScaleMaximum(String value)
	{
		this.scaleMaximum= value;
	}

	private String textString;

	public String getTextString()
	{
		return textString;
	}

	public void setTextString(String value)
	{
		this.textString= value;
	}

	private String textStringInNationalLanguage;

	public String getTextStringInNationalLanguage()
	{
		return textStringInNationalLanguage;
	}

	public void setTextStringInNationalLanguage(String value)
	{
		this.textStringInNationalLanguage= value;
	}


}
