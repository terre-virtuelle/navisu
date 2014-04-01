/**
 * Original Designers : RAY 
 * Modified : Serge Morvan Enib 09/2009
 *
 *****************************************************************************
 */
package bzh.terrevirtuelle.navisu.nmea.model;

import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Base Station Report
 *
 */
@XmlRootElement(name = "ais8")
@XmlAccessorType(XmlAccessType.FIELD)
public class AIS8
        extends AISMessage {

    private int dac;
    private int fid;
    private float latitude;
    private float longitude;
    private String accuracy;
    private Calendar timestamp;
    private int wspeed;
    private int wgust;
    private int wdir;
    private int wgustdir;
    private int humidity;
    private int stringairtemp;
    private int dewpoint;
    private int pressure;
    private int pressuretend;
    private int visgreater;
    private int visibility;
    private int waterlevel;
    private int leveltrend;
    private int cspeed;
    private int cdir;
    private int cspeed2;
    private int cdir2;
    private int cdepth2;
    private int cspeed3;
    private int cdir3;
    private int cdepth3;
    private int waveheight;
    private int waveperiod;
    private int wavedir;
    private int swellheight;
    private int swellperiod;
    private int seastate;
    private int watertemp;
    private int preciptype;
    private int salinity;
    private int ice;

    public AIS8() {
    }

    public AIS8(int MMSI, String device, int dac, int fid, float latitude, float longitude, String accuracy, Calendar timestamp, int wspeed, int wgust, int wdir, int wgustdir, int humidity, int stringairtemp, int dewpoint, int pressure, int pressuretend, int visgreater, int visibility, int waterlevel, int leveltrend, int cspeed, int cdir, int cspeed2, int cdir2, int cdepth2, int cspeed3, int cdir3, int cdepth3, int waveheight, int waveperiod, int wavedir, int swellperiod, int seastate, int watertemp, int salinity, int ice) {
        super(MMSI, device);
        this.dac = dac;
        this.fid = fid;
        this.latitude = latitude;
        this.longitude = longitude;
        this.accuracy = accuracy;
        this.timestamp = timestamp;
        this.wspeed = wspeed;
        this.wgust = wgust;
        this.wdir = wdir;
        this.wgustdir = wgustdir;
        this.humidity = humidity;
        this.stringairtemp = stringairtemp;
        this.dewpoint = dewpoint;
        this.pressure = pressure;
        this.pressuretend = pressuretend;
        this.visgreater = visgreater;
        this.visibility = visibility;
        this.waterlevel = waterlevel;
        this.leveltrend = leveltrend;
        this.cspeed = cspeed;
        this.cdir = cdir;
        this.cspeed2 = cspeed2;
        this.cdir2 = cdir2;
        this.cdepth2 = cdepth2;
        this.cspeed3 = cspeed3;
        this.cdir3 = cdir3;
        this.cdepth3 = cdepth3;
        this.waveheight = waveheight;
        this.waveperiod = waveperiod;
        this.wavedir = wavedir;
        this.swellperiod = swellperiod;
        this.seastate = seastate;
        this.watertemp = watertemp;
        this.salinity = salinity;
        this.ice = ice; 
    }

    /**
     * Get the value of ice
     *
     * @return the value of ice
     */
    public int getIce() {
        return ice;
    }

    /**
     * Set the value of ice
     *
     * @param ice new value of ice
     */
    public void setIce(int ice) {
        this.ice = ice;
    }


    /**
     * Get the value of salinity
     *
     * @return the value of salinity
     */
    public int getSalinity() {
        return salinity;
    }

    /**
     * Set the value of salinity
     *
     * @param salinity new value of salinity
     */
    public void setSalinity(int salinity) {
        this.salinity = salinity;
    }


    /**
     * Get the value of preciptype
     *
     * @return the value of preciptype
     */
    public int getPreciptype() {
        return preciptype;
    }

    /**
     * Set the value of preciptype
     *
     * @param preciptype new value of preciptype
     */
    public void setPreciptype(int preciptype) {
        this.preciptype = preciptype;
    }


    /**
     * Get the value of watertemp
     *
     * @return the value of watertemp
     */
    public int getWatertemp() {
        return watertemp;
    }

    /**
     * Set the value of watertemp
     *
     * @param watertemp new value of watertemp
     */
    public void setWatertemp(int watertemp) {
        this.watertemp = watertemp;
    }


    /**
     * Get the value of seastate
     *
     * @return the value of seastate
     */
    public int getSeastate() {
        return seastate;
    }

    /**
     * Set the value of seastate
     *
     * @param seastate new value of seastate
     */
    public void setSeastate(int seastate) {
        this.seastate = seastate;
    }


    /**
     * Get the value of swellperiod
     *
     * @return the value of swellperiod
     */
    public int getSwellperiod() {
        return swellperiod;
    }

    /**
     * Set the value of swellperiod
     *
     * @param swellperiod new value of swellperiod
     */
    public void setSwellperiod(int swellperiod) {
        this.swellperiod = swellperiod;
    }


    /**
     * Get the value of swellheight
     *
     * @return the value of swellheight
     */
    public int getSwellheight() {
        return swellheight;
    }

    /**
     * Set the value of swellheight
     *
     * @param swellheight new value of swellheight
     */
    public void setSwellheight(int swellheight) {
        this.swellheight = swellheight;
    }


    /**
     * Get the value of wavedir
     *
     * @return the value of wavedir
     */
    public int getWavedir() {
        return wavedir;
    }

    /**
     * Set the value of wavedir
     *
     * @param wavedir new value of wavedir
     */
    public void setWavedir(int wavedir) {
        this.wavedir = wavedir;
    }


    /**
     * Get the value of waveperiod
     *
     * @return the value of waveperiod
     */
    public int getWaveperiod() {
        return waveperiod;
    }

    /**
     * Set the value of waveperiod
     *
     * @param waveperiod new value of waveperiod
     */
    public void setWaveperiod(int waveperiod) {
        this.waveperiod = waveperiod;
    }


    /**
     * Get the value of waveheight
     *
     * @return the value of waveheight
     */
    public int getWaveheight() {
        return waveheight;
    }

    /**
     * Set the value of waveheight
     *
     * @param waveheight new value of waveheight
     */
    public void setWaveheight(int waveheight) {
        this.waveheight = waveheight;
    }


    /**
     * Get the value of cdepth3
     *
     * @return the value of cdepth3
     */
    public int getCdepth3() {
        return cdepth3;
    }

    /**
     * Set the value of cdepth3
     *
     * @param cdepth3 new value of cdepth3
     */
    public void setCdepth3(int cdepth3) {
        this.cdepth3 = cdepth3;
    }


    /**
     * Get the value of cdir3
     *
     * @return the value of cdir3
     */
    public int getCdir3() {
        return cdir3;
    }

    /**
     * Set the value of cdir3
     *
     * @param cdir3 new value of cdir3
     */
    public void setCdir3(int cdir3) {
        this.cdir3 = cdir3;
    }


    /**
     * Get the value of cspeed3
     *
     * @return the value of cspeed3
     */
    public int getCspeed3() {
        return cspeed3;
    }

    /**
     * Set the value of cspeed3
     *
     * @param cspeed3 new value of cspeed3
     */
    public void setCspeed3(int cspeed3) {
        this.cspeed3 = cspeed3;
    }


    /**
     * Get the value of cdepth2
     *
     * @return the value of cdepth2
     */
    public int getCdepth2() {
        return cdepth2;
    }

    /**
     * Set the value of cdepth2
     *
     * @param cdepth2 new value of cdepth2
     */
    public void setCdepth2(int cdepth2) {
        this.cdepth2 = cdepth2;
    }


    /**
     * Get the value of cdir2
     *
     * @return the value of cdir2
     */
    public int getCdir2() {
        return cdir2;
    }

    /**
     * Set the value of cdir2
     *
     * @param cdir2 new value of cdir2
     */
    public void setCdir2(int cdir2) {
        this.cdir2 = cdir2;
    }


    /**
     * Get the value of cspeed2
     *
     * @return the value of cspeed2
     */
    public int getCspeed2() {
        return cspeed2;
    }

    /**
     * Set the value of cspeed2
     *
     * @param cspeed2 new value of cspeed2
     */
    public void setCspeed2(int cspeed2) {
        this.cspeed2 = cspeed2;
    }


    /**
     * Get the value of cdir
     *
     * @return the value of cdir
     */
    public int getCdir() {
        return cdir;
    }

    /**
     * Set the value of cdir
     *
     * @param cdir new value of cdir
     */
    public void setCdir(int cdir) {
        this.cdir = cdir;
    }


    /**
     * Get the value of cspeed
     *
     * @return the value of cspeed
     */
    public int getCspeed() {
        return cspeed;
    }

    /**
     * Set the value of cspeed
     *
     * @param cspeed new value of cspeed
     */
    public void setCspeed(int cspeed) {
        this.cspeed = cspeed;
    }


    /**
     * Get the value of leveltrend
     *
     * @return the value of leveltrend
     */
    public int getLeveltrend() {
        return leveltrend;
    }

    /**
     * Set the value of leveltrend
     *
     * @param leveltrend new value of leveltrend
     */
    public void setLeveltrend(int leveltrend) {
        this.leveltrend = leveltrend;
    }


    /**
     * Get the value of waterlevel
     *
     * @return the value of waterlevel
     */
    public int getWaterlevel() {
        return waterlevel;
    }

    /**
     * Set the value of waterlevel
     *
     * @param waterlevel new value of waterlevel
     */
    public void setWaterlevel(int waterlevel) {
        this.waterlevel = waterlevel;
    }


    /**
     * Get the value of visibility
     *
     * @return the value of visibility
     */
    public int getVisibility() {
        return visibility;
    }

    /**
     * Set the value of visibility
     *
     * @param visibility new value of visibility
     */
    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }


    /**
     * Get the value of visgreater
     *
     * @return the value of visgreater
     */
    public int getVisgreater() {
        return visgreater;
    }

    /**
     * Set the value of visgreater
     *
     * @param visgreater new value of visgreater
     */
    public void setVisgreater(int visgreater) {
        this.visgreater = visgreater;
    }


    /**
     * Get the value of pressuretend
     *
     * @return the value of pressuretend
     */
    public int getPressuretend() {
        return pressuretend;
    }

    /**
     * Set the value of pressuretend
     *
     * @param pressuretend new value of pressuretend
     */
    public void setPressuretend(int pressuretend) {
        this.pressuretend = pressuretend;
    }


    /**
     * Get the value of pressure
     *
     * @return the value of pressure
     */
    public int getPressure() {
        return pressure;
    }

    /**
     * Set the value of pressure
     *
     * @param pressure new value of pressure
     */
    public void setPressure(int pressure) {
        this.pressure = pressure;
    }


    /**
     * Get the value of dewpoint
     *
     * @return the value of dewpoint
     */
    public int getDewpoint() {
        return dewpoint;
    }

    /**
     * Set the value of dewpoint
     *
     * @param dewpoint new value of dewpoint
     */
    public void setDewpoint(int dewpoint) {
        this.dewpoint = dewpoint;
    }


    /**
     * Get the value of stringairtemp
     *
     * @return the value of stringairtemp
     */
    public int getStringairtemp() {
        return stringairtemp;
    }

    /**
     * Set the value of stringairtemp
     *
     * @param stringairtemp new value of stringairtemp
     */
    public void setStringairtemp(int stringairtemp) {
        this.stringairtemp = stringairtemp;
    }


    /**
     * Get the value of humidity
     *
     * @return the value of humidity
     */
    public int getHumidity() {
        return humidity;
    }

    /**
     * Set the value of humidity
     *
     * @param humidity new value of humidity
     */
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }


    /**
     * Get the value of wgustdir
     *
     * @return the value of wgustdir
     */
    public int getWgustdir() {
        return wgustdir;
    }

    /**
     * Set the value of wgustdir
     *
     * @param wgustdir new value of wgustdir
     */
    public void setWgustdir(int wgustdir) {
        this.wgustdir = wgustdir;
    }


    /**
     * Get the value of wdir
     *
     * @return the value of wdir
     */
    public int getWdir() {
        return wdir;
    }

    /**
     * Set the value of wdir
     *
     * @param wdir new value of wdir
     */
    public void setWdir(int wdir) {
        this.wdir = wdir;
    }


    /**
     * Get the value of wgust
     *
     * @return the value of wgust
     */
    public int getWgust() {
        return wgust;
    }

    /**
     * Set the value of wgust
     *
     * @param wgust new value of wgust
     */
    public void setWgust(int wgust) {
        this.wgust = wgust;
    }


    /**
     * Get the value of wspeed
     *
     * @return the value of wspeed
     */
    public int getWspeed() {
        return wspeed;
    }

    /**
     * Set the value of wspeed
     *
     * @param wspeed new value of wspeed
     */
    public void setWspeed(int wspeed) {
        this.wspeed = wspeed;
    }


    /**
     * Get the value of accuracy
     *
     * @return the value of accuracy
     */
    public String getAccuracy() {
        return accuracy;
    }

    /**
     * Set the value of accuracy
     *
     * @param accuracy new value of accuracy
     */
    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    /**
     * Get the value of timestamp
     *
     * @return the value of timestamp
     */
    public Calendar getTimestamp() {
        return timestamp;
    }

    /**
     * Set the value of timestamp
     *
     * @param timestamp new value of timestamp
     */
    public void setTimestamp(Calendar timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Get the value of longitude
     *
     * @return the value of longitude
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * Set the value of longitude
     *
     * @param longitude new value of longitude
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    /**
     * Get the value of latitude
     *
     * @return the value of latitude
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * Set the value of latitude
     *
     * @param latitude new value of latitude
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     * Get the value of fid
     *
     * @return the value of fid
     */
    public int getFid() {
        return fid;
    }

    /**
     * Set the value of fid
     *
     * @param fid new value of fid
     */
    public void setFid(int fid) {
        this.fid = fid;
    }

    /**
     * Get the value of dac
     *
     * @return the value of dac
     */
    public int getDac() {
        return dac;
    }

    /**
     * Set the value of dac
     *
     * @param dac new value of dac
     */
    public void setDac(int dac) {
        this.dac = dac;
    }

}
/**
 * end AISMessageType4
 */
