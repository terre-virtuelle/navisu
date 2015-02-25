package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.ChangedNMEAAnnotation;


public class ChangedChannelIdAnnotation implements ChangedNMEAAnnotation{
	private String messageChannelId;
	private String lineChannelId;

    public ChangedChannelIdAnnotation() {
    }
	
	public ChangedChannelIdAnnotation (String messageChannelId, String lineChannelId)
	{
		this.messageChannelId = messageChannelId;
		this.lineChannelId = lineChannelId;
	}
	
	public String toString()
	{
		return "Deviation from standard: Channel Id Changed from " + messageChannelId + " to " + lineChannelId;
	}
}
