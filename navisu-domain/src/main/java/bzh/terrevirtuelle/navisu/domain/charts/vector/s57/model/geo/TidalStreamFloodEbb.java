package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class TidalStreamFloodEbb extends Geo
implements Serializable
{

	public TidalStreamFloodEbb(Long id)
	{
		this.id=id;
	}

	public TidalStreamFloodEbb(){}

	private String currentVelocity;

	public String getCurrentVelocity()
	{
		return currentVelocity;
	}

	public void setCurrentVelocity(String value)
	{
		this.currentVelocity= value;
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

	private String orientation;

	public String getOrientation()
	{
		return orientation;
	}

	public void setOrientation(String value)
	{
		this.orientation= value;
	}

	private String periodicDateEnd;

	public String getPeriodicDateEnd()
	{
		return periodicDateEnd;
	}

	public void setPeriodicDateEnd(String value)
	{
		this.periodicDateEnd= value;
	}

	private String periodicDateStart;

	public String getPeriodicDateStart()
	{
		return periodicDateStart;
	}

	public void setPeriodicDateStart(String value)
	{
		this.periodicDateStart= value;
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
