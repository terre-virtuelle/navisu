package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class RecommendedTrafficLanePart extends Geo
implements Serializable
{

	public RecommendedTrafficLanePart(Long id)
	{
		this.id=id;
	}

	public RecommendedTrafficLanePart(){}

	private String dateEnd;

	public String getDateEnd()
	{
		return dateEnd;
	}

	public void setDateEnd(String value)
	{
		this.dateEnd= value;
	}

	private String dateStart;

	public String getDateStart()
	{
		return dateStart;
	}

	public void setDateStart(String value)
	{
		this.dateStart= value;
	}

	private String orientation;

	public String getOrientation()
	{
		return orientation;
	}

	public void setOrientation(String value)
	{
		this.orientation= value;
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

	private String status;

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String value)
	{
		this.status= value;
	}


}
