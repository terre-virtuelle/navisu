package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class CompilationScaleOfData extends Geo
implements Serializable
{

	public CompilationScaleOfData(Long id)
	{
		this.id=id;
	}

	public CompilationScaleOfData(){}

	private String compilationScale;

	public String getCompilationScale()
	{
		return compilationScale;
	}

	public void setCompilationScale(String value)
	{
		this.compilationScale= value;
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
