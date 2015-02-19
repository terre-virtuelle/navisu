package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class Gridiron extends Geo
implements Serializable
{

	public Gridiron(Long id)
	{
		this.id=id;
	}

	public Gridiron(){}

	private String horizontalAccuracy;

	public String getHorizontalAccuracy()
	{
		return horizontalAccuracy;
	}

	public void setHorizontalAccuracy(String value)
	{
		this.horizontalAccuracy= value;
	}

	private String horizontalLength;

	public String getHorizontalLength()
	{
		return horizontalLength;
	}

	public void setHorizontalLength(String value)
	{
		this.horizontalLength= value;
	}

	private String horizontalWidth;

	public String getHorizontalWidth()
	{
		return horizontalWidth;
	}

	public void setHorizontalWidth(String value)
	{
		this.horizontalWidth= value;
	}

	private String natureOfConstruction;

	public String getNatureOfConstruction()
	{
		return natureOfConstruction;
	}

	public void setNatureOfConstruction(String value)
	{
		this.natureOfConstruction= value;
	}

	private String objectName;

	public String getObjectName()
	{
		return objectName;
	}

	public void setObjectName(String value)
	{
		this.objectName= value;
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

	private String verticalAccuracy;

	public String getVerticalAccuracy()
	{
		return verticalAccuracy;
	}

	public void setVerticalAccuracy(String value)
	{
		this.verticalAccuracy= value;
	}

	private String verticalLength;

	public String getVerticalLength()
	{
		return verticalLength;
	}

	public void setVerticalLength(String value)
	{
		this.verticalLength= value;
	}

	private String waterLevelEffect;

	public String getWaterLevelEffect()
	{
		return waterLevelEffect;
	}

	public void setWaterLevelEffect(String value)
	{
		this.waterLevelEffect= value;
	}

	private String objectNameInNationalLanguage;

	public String getObjectNameInNationalLanguage()
	{
		return objectNameInNationalLanguage;
	}

	public void setObjectNameInNationalLanguage(String value)
	{
		this.objectNameInNationalLanguage= value;
	}


}
