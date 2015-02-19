package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class AccuracyOfData extends Geo
implements Serializable
{

	public AccuracyOfData(Long id)
	{
		this.id=id;
	}

	public AccuracyOfData(){}

	private String horizontalAccuracy;

	public String getHorizontalAccuracy()
	{
		return horizontalAccuracy;
	}

	public void setHorizontalAccuracy(String value)
	{
		this.horizontalAccuracy= value;
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

	private String soundingAccuracy;

	public String getSoundingAccuracy()
	{
		return soundingAccuracy;
	}

	public void setSoundingAccuracy(String value)
	{
		this.soundingAccuracy= value;
	}

	private String verticalAccuracy;

	public String getVerticalAccuracy()
	{
		return verticalAccuracy;
	}

	public void setVerticalAccuracy(String value)
	{
		this.verticalAccuracy= value;
	}


}
