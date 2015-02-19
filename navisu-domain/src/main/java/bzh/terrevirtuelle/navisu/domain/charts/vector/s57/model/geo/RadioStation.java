package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class RadioStation extends Geo
implements Serializable
{

	public RadioStation(Long id)
	{
		this.id=id;
	}

	public RadioStation(){}

	private String callSign;

	public String getCallSign()
	{
		return callSign;
	}

	public void setCallSign(String value)
	{
		this.callSign= value;
	}

	private String categoryOfRadioStation;

	public String getCategoryOfRadioStation()
	{
		return categoryOfRadioStation;
	}

	public void setCategoryOfRadioStation(String value)
	{
		this.categoryOfRadioStation= value;
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

	private String estimateDrangeOfTransmission;

	public String getEstimateDrangeOfTransmission()
	{
		return estimateDrangeOfTransmission;
	}

	public void setEstimateDrangeOfTransmission(String value)
	{
		this.estimateDrangeOfTransmission= value;
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

	private String signalFrequency;

	public String getSignalFrequency()
	{
		return signalFrequency;
	}

	public void setSignalFrequency(String value)
	{
		this.signalFrequency= value;
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


}
