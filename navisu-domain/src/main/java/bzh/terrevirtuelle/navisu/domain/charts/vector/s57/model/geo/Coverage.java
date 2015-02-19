package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class Coverage extends Geo
implements Serializable
{

	public Coverage(Long id)
	{
		this.id=id;
	}

	public Coverage(){}

	private String categoryOfcoverage;

	public String getCategoryOfCoverage()
	{
		return categoryOfcoverage;
	}

	public void setCategoryOfCoverage(String value)
	{
		this.categoryOfcoverage= value;
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
