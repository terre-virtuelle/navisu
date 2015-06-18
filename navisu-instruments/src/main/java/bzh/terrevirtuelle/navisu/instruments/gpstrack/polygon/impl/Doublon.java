package bzh.terrevirtuelle.navisu.instruments.gpstrack.polygon.impl;

public class Doublon {
	
	public Doublon (boolean b, double d) {
		this.result = b;
		this.percent = d;
	}
	
	private boolean result;
	private double percent;

	public boolean getResult() {
		return result;
	}
	
	public void setResult(boolean result) {
		this.result = result;
	}
	
	public double getPercent() {
		return percent;
	}
	
	public void setPercent(double percent) {
		this.percent = percent;
	}
	
}
