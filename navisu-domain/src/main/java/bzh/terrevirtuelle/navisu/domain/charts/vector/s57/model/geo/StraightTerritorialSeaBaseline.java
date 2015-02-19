package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class StraightTerritorialSeaBaseline extends Geo
implements Serializable
{

	public StraightTerritorialSeaBaseline(Long id)
	{
		this.id=id;
	}

	public StraightTerritorialSeaBaseline(){}

	private String nationality;

	public String getNationality()
	{
		return nationality;
	}

	public void setNationality(String value)
	{
		this.nationality= value;
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


}
