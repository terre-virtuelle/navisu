package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class Compass extends Geo
implements Serializable
{

	public Compass(Long id)
	{
		this.id=id;
	}

	public Compass(){}

	private String compasssize;

	public String getCompasssize()
	{
		return compasssize;
	}

	public void setCompasssize(String value)
	{
		this.compasssize= value;
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

	private String referenceYearForMagneticVariation;

	public String getReferenceYearForMagneticVariation()
	{
		return referenceYearForMagneticVariation;
	}

	public void setReferenceYearForMagneticVariation(String value)
	{
		this.referenceYearForMagneticVariation= value;
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

	private String valueOfAnnualChangeInMagneticvariation;

	public String getValueOfAnnualChangeInMagneticVariation()
	{
		return valueOfAnnualChangeInMagneticvariation;
	}

	public void setValueOfAnnualChangeInMagneticVariation(String value)
	{
		this.valueOfAnnualChangeInMagneticvariation= value;
	}

	private String valueOfMagneticvariation;

	public String getValueOfMagneticVariation()
	{
		return valueOfMagneticvariation;
	}

	public void setValueOfMagneticVariation(String value)
	{
		this.valueOfMagneticvariation= value;
	}


}
