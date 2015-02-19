package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class NavigationalSystemOfMarks extends Geo
implements Serializable
{

	public NavigationalSystemOfMarks(Long id)
	{
		this.id=id;
	}

	public NavigationalSystemOfMarks(){}

	private String marksNavigationalSystemof;

	public String getMarksNavigationalSystemof()
	{
		return marksNavigationalSystemof;
	}

	public void setMarksNavigationalSystemof(String value)
	{
		this.marksNavigationalSystemof= value;
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


}
