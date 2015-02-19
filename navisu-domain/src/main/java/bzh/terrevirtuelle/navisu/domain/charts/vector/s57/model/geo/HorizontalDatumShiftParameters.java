package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class HorizontalDatumShiftParameters extends Geo
implements Serializable
{

	public HorizontalDatumShiftParameters(Long id)
	{
		this.id=id;
	}

	public HorizontalDatumShiftParameters(){}

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

	private String shiftParameters;

	public String getShiftParameters()
	{
		return shiftParameters;
	}

	public void setShiftParameters(String value)
	{
		this.shiftParameters= value;
	}


}
