package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class RadarTransponderBeacon extends Geo
implements Serializable
{

	public RadarTransponderBeacon(Long id)
	{
		this.id=id;
	}

	public RadarTransponderBeacon(){}

	private String categoryOfRadarTransponderBeacon;

	public String getCategoryOfRadarTransponderBeacon()
	{
		return categoryOfRadarTransponderBeacon;
	}

	public void setCategoryOfRadarTransponderBeacon(String value)
	{
		this.categoryOfRadarTransponderBeacon= value;
	}

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

	private String objectName;

	public String getObjectName()
	{
		return objectName;
	}

	public void setObjectName(String value)
	{
		this.objectName= value;
	}

	private String radarWaveLength;

	public String getRadarWaveLength()
	{
		return radarWaveLength;
	}

	public void setRadarWaveLength(String value)
	{
		this.radarWaveLength= value;
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

	private String sectorLimitOne;

	public String getSectorLimitOne()
	{
		return sectorLimitOne;
	}

	public void setSectorLimitOne(String value)
	{
		this.sectorLimitOne= value;
	}

	private String sectorLimitTwo;

	public String getSectorLimitTwo()
	{
		return sectorLimitTwo;
	}

	public void setSectorLimitTwo(String value)
	{
		this.sectorLimitTwo= value;
	}

	private String signalGroup;

	public String getSignalGroup()
	{
		return signalGroup;
	}

	public void setSignalGroup(String value)
	{
		this.signalGroup= value;
	}

	private String signalsequence;

	public String getSignalSequence()
	{
		return signalsequence;
	}

	public void setSignalSequence(String value)
	{
		this.signalsequence= value;
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

	private String valueOfMaximumRange;

	public String getValueOfMaximumRange()
	{
		return valueOfMaximumRange;
	}

	public void setValueOfMaximumRange(String value)
	{
		this.valueOfMaximumRange= value;
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
