package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class CartographicSymbol extends Geo
implements Serializable
{

	public CartographicSymbol(Long id)
	{
		this.id=id;
	}

	public CartographicSymbol(){}

	private String orientation;

	public String getOrientation()
	{
		return orientation;
	}

	public void setOrientation(String value)
	{
		this.orientation= value;
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

	private String symbolscalingFactor;

	public String getSymbolScalingFactor()
	{
		return symbolscalingFactor;
	}

	public void setSymbolScalingFactor(String value)
	{
		this.symbolscalingFactor= value;
	}

	private String symbolizationCode;

	public String getSymbolizationCode()
	{
		return symbolizationCode;
	}

	public void setSymbolizationCode(String value)
	{
		this.symbolizationCode= value;
	}


}
