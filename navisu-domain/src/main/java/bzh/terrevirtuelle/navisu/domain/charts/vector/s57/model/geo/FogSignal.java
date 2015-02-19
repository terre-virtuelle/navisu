package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class FogSignal extends Geo
implements Serializable
{

	public FogSignal(Long id)
	{
		this.id=id;
	}

	public FogSignal(){}

	private String categoryOfFogSignal;

	public String getCategoryOfFogSignal()
	{
		return categoryOfFogSignal;
	}

	public void setCategoryOfFogSignal(String value)
	{
		this.categoryOfFogSignal= value;
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

	private String signalFrequency;

	public String getSignalFrequency()
	{
		return signalFrequency;
	}

	public void setSignalFrequency(String value)
	{
		this.signalFrequency= value;
	}

	private String signalGeneration;

	public String getSignalGeneration()
	{
		return signalGeneration;
	}

	public void setSignalGeneration(String value)
	{
		this.signalGeneration= value;
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

	private String signalPeriod;

	public String getSignalPeriod()
	{
		return signalPeriod;
	}

	public void setSignalPeriod(String value)
	{
		this.signalPeriod= value;
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
