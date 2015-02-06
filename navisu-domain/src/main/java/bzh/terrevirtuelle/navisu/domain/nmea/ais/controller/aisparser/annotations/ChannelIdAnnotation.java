package nl.esi.metis.aisparser.annotations;

public class ChannelIdAnnotation implements Annotation{
	private String channelId;
	
	public ChannelIdAnnotation (String channelId)
	{
		this.channelId = channelId;
	}
	
	public String toString()
	{
		if ("1".equals(channelId))
		{
			return "Well known deviation from standard: Use of '1' instead of 'A' as channel identifier.";
		}
		else if ( "2".equals(channelId))
		{
			return "Well known deviation from standard: Use of '2' instead of 'B' as channel identifier.";
		}
		else
		{
			return "Deviation from standard: Use of '" + channelId + "' instead of ('A' or 'B') as channel identifiers.";
		}
	}
}
