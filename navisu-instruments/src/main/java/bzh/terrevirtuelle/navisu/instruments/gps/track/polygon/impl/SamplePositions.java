package bzh.terrevirtuelle.navisu.instruments.gps.track.polygon.impl;

import gov.nasa.worldwind.geom.Position;

import java.util.ArrayList;

public class SamplePositions {
	
	public SamplePositions (int mmsi, int zone) {
		this.positions = new ArrayList<Position>();
		this.mmsi = mmsi;
		this.zone = zone;
	}
	
	private ArrayList<Position> positions;
	
	private int mmsi;
	
	public int getMmsi() {
		return mmsi;
	}

	public void setMmsi(int mmsi) {
		this.mmsi = mmsi;
	}

	public int getZone() {
		return zone;
	}

	public void setZone(int zone) {
		this.zone = zone;
	}

	private int zone;

	public ArrayList<Position> getPositions() {
		return positions;
	}

	public void setPositions(ArrayList<Position> positions) {
		this.positions = positions;
	}

}
