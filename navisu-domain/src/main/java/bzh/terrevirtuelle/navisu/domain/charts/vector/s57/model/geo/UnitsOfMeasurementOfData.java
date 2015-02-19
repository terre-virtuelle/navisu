package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class UnitsOfMeasurementOfData extends Geo
implements Serializable
{

	public UnitsOfMeasurementOfData(Long id)
	{
		this.id=id;
	}

	public UnitsOfMeasurementOfData(){}

	private String depthUnits;

	public String getDepthUnits()
	{
		return depthUnits;
	}

	public void setDepthUnits(String value)
	{
		this.depthUnits= value;
	}

	private String heightLengthUnits;

	public String getHeightLengthUnits()
	{
		return heightLengthUnits;
	}

	public void setHeightLengthUnits(String value)
	{
		this.heightLengthUnits= value;
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


}
