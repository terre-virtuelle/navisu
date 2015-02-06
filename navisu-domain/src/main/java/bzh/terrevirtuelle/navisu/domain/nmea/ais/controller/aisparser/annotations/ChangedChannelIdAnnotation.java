package nl.esi.metis.aisparser.annotations;


public class ChangedChannelIdAnnotation implements ChangedNMEAAnnotation{
	private String messageChannelId;
	private String lineChannelId;
	
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
