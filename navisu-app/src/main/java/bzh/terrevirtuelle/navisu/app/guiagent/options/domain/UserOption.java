/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.app.guiagent.options.domain;

/**
 *
 * @author serge
 * @date Jun 26, 2017
 */
public class UserOption
        implements Option {

    private String s57Path;
    private String darkSkyKey;
    private String allCountriesPath;
    private String allCountriesIndexPath;

    public UserOption() {
    }

    public UserOption(String s57Path, String darkSkyKey, String allCountriesPath, String allCountriesIndexPath) {
        this.s57Path = s57Path;
        this.darkSkyKey = darkSkyKey;
        this.allCountriesPath = allCountriesPath;
        this.allCountriesIndexPath = allCountriesIndexPath;
    }

    /**
     * Get the value of allCountriesIndexPath
     *
     * @return the value of allCountriesIndexPath
     */
    public String getAllCountriesIndexPath() {
        return allCountriesIndexPath;
    }

    /**
     * Set the value of allCountriesIndexPath
     *
     * @param allCountriesIndexPath new value of allCountriesIndexPath
     */
    public void setAllCountriesIndexPath(String allCountriesIndexPath) {
        this.allCountriesIndexPath = allCountriesIndexPath;
    }

    /**
     * Get the value of allCountriesPath
     *
     * @return the value of allCountriesPath
     */
    public String getAllCountriesPath() {
        return allCountriesPath;
    }

    /**
     * Set the value of allCountriesPath
     *
     * @param allCountriesPath new value of allCountriesPath
     */
    public void setAllCountriesPath(String allCountriesPath) {
        this.allCountriesPath = allCountriesPath;
    }

    /**
     * Get the value of darkSkyKey
     *
     * @return the value of darkSkyKey
     */
    public String getDarkSkyKey() {
        return darkSkyKey;
    }

    /**
     * Set the value of darkSkyKey
     *
     * @param darkSkyKey new value of darkSkyKey
     */
    public void setDarkSkyKey(String darkSkyKey) {
        this.darkSkyKey = darkSkyKey;
    }

    /**
     * Get the value of s57Path
     *
     * @return the value of s57Path
     */
    public String getS57Path() {
        return s57Path;
    }

    /**
     * Set the value of s57Path
     *
     * @param s57Path new value of s57Path
     */
    public void setS57Path(String s57Path) {
        this.s57Path = s57Path;
    }

    @Override
    public String toString() {
        return "UserOption{" + "s57Path=" + s57Path + ", darkSkyKey=" + darkSkyKey + ", allCountriesPath=" + allCountriesPath + ", allCountriesIndexPath=" + allCountriesIndexPath + '}';
    }


}
