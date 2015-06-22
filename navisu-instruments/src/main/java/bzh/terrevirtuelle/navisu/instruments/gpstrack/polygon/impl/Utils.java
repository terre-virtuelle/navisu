package bzh.terrevirtuelle.navisu.instruments.gpstrack.polygon.impl;

import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;

import java.util.ArrayList;

public class Utils {

	public static Position barycenter(ArrayList<? extends Position> list) {
		double latmax = list.get(0).getLatitude().getDegrees();
		double longmax = list.get(0).getLongitude().getDegrees();
		double latmin = list.get(0).getLatitude().getDegrees();
		double longmin = list.get(0).getLongitude().getDegrees();
		double altmax = list.get(0).getAltitude();
		double latmoy = 0;
		double longmoy = 0;
		Position resu;
		
		for (Position pos : list) {
			if (Math.abs(latmax)<Math.abs(pos.getLatitude().getDegrees())) {latmax = pos.getLatitude().getDegrees();}
			if (Math.abs(latmin)>Math.abs(pos.getLatitude().getDegrees())) {latmin = pos.getLatitude().getDegrees();}
			if (Math.abs(longmax)<Math.abs(pos.getLongitude().getDegrees())) {longmax = pos.getLongitude().getDegrees();}
			if (Math.abs(longmin)>Math.abs(pos.getLongitude().getDegrees())) {longmin = pos.getLongitude().getDegrees();}
			if (altmax<pos.getAltitude()) {altmax = pos.getAltitude();}
		}
		//System.out.println(latmin +" "+ latmax+ " " + latmoy);
		latmoy = (latmin+latmax)/2;
		longmoy = (longmin+longmax)/2;
		if (altmax<=0) {altmax=0;}
		
		resu = new Position(LatLon.fromDegrees(latmoy, longmoy), altmax);
		
		return resu;
	}
	
	public static double computeCourse(Position start, Position end) {
		if (start.getLatitude().getDegrees()==end.getLatitude().getDegrees() && start.getLongitude().getDegrees()==end.getLongitude().getDegrees()) {
			return 500;
			}
		
		double lat1 = start.getLatitude().getRadians();
		double lat2 = end.getLatitude().getRadians();
		double lon1 = start.getLongitude().getRadians();
		double lon2 = end.getLongitude().getRadians();
		double resu = Math.atan2((Math.sin(lon2-lon1))*(Math.cos(lat2)), (Math.cos(lat1))*(Math.sin(lat2))-(Math.sin(lat1))*(Math.cos(lat2))*(Math.cos(lon2-lon1)));
		resu = Math.toDegrees(resu);
		resu = Math.round(resu);
		if (resu<0) {
			resu = resu + 360;
		}
		return resu;
	}
	
	public static Doublon detectTurn(ArrayList<Position> positions, double alpha) {
		ArrayList<Double> headings = new ArrayList<Double>();
		for (int i=0; i<positions.size()-1; i++) {
			//ajout des caps dans la liste headings seulement si les deux points i et i+1 sont bien distincts
			if (Utils.computeCourse(positions.get(i), positions.get(i+1)) != 500) {
				headings.add(Utils.computeCourse(positions.get(i), positions.get(i+1)));
				}
		}
		/*String resu = "";
		for (Double d : headings) {
			resu = resu + d.toString() + "-";
		}
		aisTrackPanel.updateAisPanelStatus(resu);*/
		double h0 = headings.get(0);
		double hf = h0 + alpha;
		if (hf>=360) {
			hf = hf - 360;
			}
		if (hf<0) {
			hf = hf + 360;
		}
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		boolean left = false;
		boolean right = false;
		double h0min = h0-20;
		double h0max = h0+20;
		if (h0min<0) {
			h0min = h0min + 360;
			left = true;
			}
		if (h0max>=360) {
			h0max = h0max - 360;
			right = true;
		}
		
		boolean leftf = false;
		boolean rightf = false;
		double hfmin = hf-20;
		double hfmax = hf+20;
		if (hfmin<0) {
			hfmin = hfmin + 360;
			leftf = true;
			}
		if (hfmax>=360) {
			hfmax = hfmax - 360;
			rightf = true;
		}
		
		for (Double h : headings) {
			if (left || right) {if ((0<h && h<h0max) || (h0min<h && h<360)) {a++;}}
			if (!left && !right) {if (h0min<h && h<h0max) {a++;}}
			if (leftf || rightf) {if ((0<h && h<hfmax) || (hfmin<h && h<360)) {b++;}}
			if (!leftf && !rightf) {if (hfmin<h && h<hfmax) {b++;}}
		}
			
		for (int j=0; j<headings.size()-1; j++) {
			if (Math.abs(headings.get(j)-headings.get(j+1))>40) {d++;}
		}
		
		int taille = headings.size();
		c = taille - a - b;
		double resultat = 0;
		
		//aisTrackPanel.updateAisPanelStatus("a=" + a + " b=" + b + " c=" + c + " d=" + d);
		if (taille>(a+b)) {
			resultat = (Math.round(((a+b)*1000)/taille))/10;
			} 
		else {
			resultat = 100.0;
			}
		
		if ((double)a>=taille*0.1 && (double)b>=taille*0.1 && (double)(a+b)>=taille*0.7 && (double)c<=taille*0.45 && (double)d<=taille*0.4) {
			Doublon doublon = new Doublon (true, resultat);
			return doublon;
			} 
		else {
			Doublon doublon = new Doublon (false, resultat);
			return doublon;
			}
	}
	
	public static Doublon detectStraightLine(ArrayList<Position> positions) {
		ArrayList<Double> headings = new ArrayList<Double>();
		for (int i=0; i<positions.size()-1; i++) {
			//ajout des caps dans la liste headings seulement si les deux points i et i+1 sont bien distincts
			if (Utils.computeCourse(positions.get(i), positions.get(i+1)) != 500) {
				headings.add(Utils.computeCourse(positions.get(i), positions.get(i+1)));
				}
		}
		/*String resu = "";
		for (Double d : headings) {
			resu = resu + d.toString() + "-";
		}
		aisTrackPanel.updateAisPanelStatus(resu);*/
		double h0 = headings.get(0);
		int a = 0;
		int d = 0;
		boolean left = false;
		boolean right = false;
		double h0min = h0-20;
		double h0max = h0+20;
		if (h0min<0) {
			h0min = h0min + 360;
			left = true;
			}
		if (h0max>=360) {
			h0max = h0max - 360;
			right = true;
		}
		
		for (Double h : headings) {
			if (left || right) {if ((0<h && h<h0max) || (h0min<h && h<360)) {a++;}}
			if (!left && !right) {if (h0min<h && h<h0max) {a++;}}
		}
			
		for (int j=0; j<headings.size()-1; j++) {
			if (Math.abs(headings.get(j)-headings.get(j+1))>40) {d++;}
		}
		
		int taille = headings.size();
		double resultat = 0;
		
		//aisTrackPanel.updateAisPanelStatus("a=" + a + " d=" + d);
		resultat = (Math.round((a*1000)/taille))/10;
		
		if (((double)a)>=taille*0.7 && (double)d<=taille*0.4) {
			Doublon doublon = new Doublon (true, resultat);
			return doublon;
			} 
		else {
			Doublon doublon = new Doublon (false, resultat);
			return doublon;
			}
	}
	
	public static Position translate(Position start, Position end, Position pos) {
		return new Position(LatLon.fromDegrees(pos.getLatitude().getDegrees()+end.getLatitude().getDegrees()-start.getLatitude().getDegrees(), pos.getLongitude().getDegrees()+end.getLongitude().getDegrees()-start.getLongitude().getDegrees()), 0);
	}

}
