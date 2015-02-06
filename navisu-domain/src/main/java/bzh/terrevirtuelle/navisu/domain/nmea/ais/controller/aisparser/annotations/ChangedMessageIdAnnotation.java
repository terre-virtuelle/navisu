package nl.esi.metis.aisparser.annotations;


public class ChangedMessageIdAnnotation implements ChangedNMEAAnnotation{
	private String messageMessageId;
	private String lineMessageId;
	
	public ChangedMessageIdAnnotation (String messageMessageId, String lineMessageId)
	{
		this.messageMessageId = messageMessageId;
		this.lineMessageId = lineMessageId;
	}
	
	public String toString()
	{
		return "Deviation from standard: Message Id Changed from " + messageMessageId + " to " + lineMessageId;
	}
}
