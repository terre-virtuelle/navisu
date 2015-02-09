package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser;

public interface HandleVDMLine {
	public void handleVDMLine (VDMLine line);
	public void finished();
}
