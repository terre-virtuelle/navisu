package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class ProductionInformation extends Geo
implements Serializable
{

	public ProductionInformation(Long id)
	{
		this.id=id;
	}

	public ProductionInformation(){}

	private String agencyResponsibleforproduction;

	public String getAgencyResponsibleforproduction()
	{
		return agencyResponsibleforproduction;
	}

	public void setAgencyResponsibleforproduction(String value)
	{
		this.agencyResponsibleforproduction= value;
	}

	private String compilationDate;

	public String getCompilationDate()
	{
		return compilationDate;
	}

	public void setCompilationDate(String value)
	{
		this.compilationDate= value;
	}

	private String nationality;

	public String getNationality()
	{
		return nationality;
	}

	public void setNationality(String value)
	{
		this.nationality= value;
	}

	private String noticeToMarinersDate;

	public String getNoticeToMarinersDate()
	{
		return noticeToMarinersDate;
	}

	public void setNoticeToMarinersDate(String value)
	{
		this.noticeToMarinersDate= value;
	}

	private String producingCountry;

	public String getProducingCountry()
	{
		return producingCountry;
	}

	public void setProducingCountry(String value)
	{
		this.producingCountry= value;
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
