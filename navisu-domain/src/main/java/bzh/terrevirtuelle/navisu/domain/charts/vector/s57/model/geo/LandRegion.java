package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class LandRegion extends Geo
implements Serializable
{

	public LandRegion(Long id)
	{
		this.id=id;
	}

	public LandRegion(){}

	private String categoryOfLandRegion;

	public String getCategoryOfLandRegion()
	{
		return categoryOfLandRegion;
	}

	public void setCategoryOfLandRegion(String value)
	{
		this.categoryOfLandRegion= value;
	}

	private String natureOfSurface;

	public String getNatureOfSurface()
	{
		return natureOfSurface;
	}

	public void setNatureOfSurface(String value)
	{
		this.natureOfSurface= value;
	}

	private String natureOfSurfaceQualifyingTerms;

	public String getNatureOfSurfaceQualifyingTerms()
	{
		return natureOfSurfaceQualifyingTerms;
	}

	public void setNatureOfSurfaceQualifyingTerms(String value)
	{
		this.natureOfSurfaceQualifyingTerms= value;
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
