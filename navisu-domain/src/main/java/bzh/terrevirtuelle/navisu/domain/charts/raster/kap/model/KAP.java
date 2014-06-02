package bzh.terrevirtuelle.navisu.domain.charts.raster.kap.model;

import java.util.List;

/**
 *
 * @author jordan
 */
public interface KAP {
    
    /**
     * Version number of BSB format
     */
    public String getVersion();
    public void setVersion(String version);
    
    /**
     * BSB/ -> NA, NU, RA, DU
     */
    public String getNa();
    public void setNa(String name);
    
    /*** 
     * @return number of charts
     */
    public int getNu();
    public void setNu(int number);
    
    /**
     * @return [width, height] of raster image data in pixels 
     */
    public int[] getRa();
    public void setRa(int[] size);
    
    /**
     * @return Drawing Units in pixels/inch
     */
    public int getDu();
    public void setDu(int drawingUnit);
    
    
    /**
     * KNP/ -> SC, GD, PR, PP, PI, SK, TA, UN, SD, DX, DY
     */
    public int getSC();
    public void setSC(int scale);
    
    /**
     * @return Geodetic datum
     */
    public String getGD();
    public void setGD(String geodeticDatum);
    
    /**
     * @return Projection
     */
    public String getPR();
    public void setPR(String projection);
    
    /**
     * @return Projection parameter
     */
    public double getPP();
    public void setPP(double projectionParameter);
    
    
    public double getPI();
    public void setPI(double pi);
    
    /**
     * @return Skew angle
     */
    public double getSK();
    public void setSK(double skewAngle);
    
    public double getTA();
    public void setTA(double ta);
    
    /**
     * @return Unit
     */
    public String getUN();
    public void setUN(String unit);
    
    /**
     * @return Sounding datum
     */
    public String getSD();
    public void setSD(String sd);
    
    /**
     * @return Distance covered by one pixel in X direction
     */
    public double getDX();
    public void setDX(double dx);
    
    /**
     * @return distance covered by one pixel in Y direction
     */
    public double getDY();
    public void setDY(double dy);
    
    
    /**
     * OST/ Depth of the colormap (bits per pixel).
     */
    public int getOST();
    public void setOST(int offsetStrip);
    
    /**
     * IFM/ Depth of the colormap (bits per pixel).
     */
    public int getIFM();
    public void setIFM(int ifm);
    
    //RGB
    
    //DAY
    
    //DSK
    
    //NGT
    
    //REF
    
    public List<double[]> getPLY();
    public void setPLY(List<double[]> plys);
}
