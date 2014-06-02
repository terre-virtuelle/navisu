package bzh.terrevirtuelle.navisu.domain.charts.raster.kap.model.impl;

import java.util.List;

import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.model.KAP;

/**
 *
 * @author jordan
 */
public class KAPImpl implements KAP {
    
    //VER/
    protected String version;
    
    //BSB/
    protected String na;
    protected int nu;
    protected int[] ra;
    protected int du;
    
    //KNP/
    protected int sc;
    protected String gd;
    protected String pr;
    protected double pp;
    protected double pi;
    protected double sk;
    protected double ta;
    protected String un;
    protected String sd;
    protected double dx;
    protected double dy;
    
    
    //OST/
    protected int ost;
    
    //IFM/
    protected int ifm;
    
    //PLY/
    protected List<double[]> plys;

    @Override
    public String getVersion() {
        return this.version;
    }

    @Override
    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String getNa() {
        return this.na;
    }

    @Override
    public void setNa(String name) {
        this.na = name;
    }

    @Override
    public int getNu() {
        return this.nu;
    }

    @Override
    public void setNu(int number) {
        this.nu = number;
    }

    @Override
    public int[] getRa() {
        return this.ra;
    }

    @Override
    public void setRa(int[] size) {
       this.ra = size;
    }

    @Override
    public int getDu() {
        return this.du;
    }

    @Override
    public void setDu(int drawingUnit) {
        this.du = drawingUnit;
    }

    @Override
    public int getOST() {
        return this.ost;
    }

    @Override
    public void setOST(int offsetStrip) {
        this.ost = offsetStrip;
    }

    @Override
    public int getIFM() {
        return this.ifm;
    }

    @Override
    public void setIFM(int ifm) {
        this.ifm = ifm;
    }

	@Override
	public int getSC() {
		return this.sc;
	}

	@Override
	public void setSC(int scale) {
		this.sc = scale;
	}

	@Override
	public String getGD() {
		return this.gd;
	}

	@Override
	public void setGD(String geodeticDatum) {
		this.gd = geodeticDatum;
	}

	@Override
	public double getPP() {
		return this.pp;
	}

	@Override
	public void setPP(double projectionParameter) {
		this.pp = projectionParameter;
	}

	@Override
	public double getPI() {
		return this.pi;
	}

	@Override
	public void setPI(double pi) {
		this.pi = pi;
	}

	@Override
	public double getSK() {
		return this.sk;
	}

	@Override
	public void setSK(double skewAngle) {
		this.sk = skewAngle;
	}

	@Override
	public double getTA() {
		return this.ta;
	}

	@Override
	public void setTA(double ta) {
		this.ta = ta;
	}

	@Override
	public String getUN() {
		return this.un;
	}

	@Override
	public void setUN(String unit) {
		this.un = unit;
	}

	@Override
	public String getSD() {
		return this.sd;
	}

	@Override
	public void setSD(String sd) {
		this.sd = sd;
	}

	@Override
	public double getDX() {
		return this.dx;
	}

	@Override
	public void setDX(double dx) {
		this.dx = dx;
	}

	@Override
	public double getDY() {
		return this.dy;
	}

	@Override
	public void setDY(double dy) {
		this.dy = dy;
	}

	@Override
	public String getPR() {
		return this.pr;
	}

	@Override
	public void setPR(String projection) {
		this.pr = projection;
	}

	@Override
	public List<double[]> getPLY() {
		return this.plys;
	}

	@Override
	public void setPLY(List<double[]> plys) {
		this.plys = plys;
	}
}
