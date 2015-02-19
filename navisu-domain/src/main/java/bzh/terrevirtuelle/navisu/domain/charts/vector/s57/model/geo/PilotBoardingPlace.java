package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class PilotBoardingPlace extends Geo
implements Serializable
{

	public PilotBoardingPlace(Long id)
	{
		this.id=id;
	}

	public PilotBoardingPlace(){}

	private String categoryOfPilotBoardingPlace;

	public String getCategoryOfPilotBoardingPlace()
	{
		return categoryOfPilotBoardingPlace;
	}

	public void setCategoryOfPilotBoardingPlace(String value)
	{
		this.categoryOfPilotBoardingPlace= value;
	}

	private String communicationchannel;

	public String getCommunicationChannel()
	{
		return communicationchannel;
	}

	public void setCommunicationChannel(String value)
	{
		this.communicationchannel= value;
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

	private String pilotDistrict;

	public String getPilotDistrict()
	{
		return pilotDistrict;
	}

	public void setPilotDistrict(String value)
	{
		this.pilotDistrict= value;
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

	private String objectNameInNationalLanguage;

	public String getObjectNameInNationalLanguage()
	{
		return objectNameInNationalLanguage;
	}

	public void setObjectNameInNationalLanguage(String value)
	{
		this.objectNameInNationalLanguage= value;
	}

	private String pilotDistrictInNationalLanguage;

	public String getPilotDistrictInNationalLanguage()
	{
		return pilotDistrictInNationalLanguage;
	}

	public void setPilotDistrictInNationalLanguage(String value)
	{
		this.pilotDistrictInNationalLanguage= value;
	}


}
