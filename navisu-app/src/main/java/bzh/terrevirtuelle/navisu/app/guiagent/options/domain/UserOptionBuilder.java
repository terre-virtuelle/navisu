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
public class UserOptionBuilder {
private String s57Path = " ";
    private String darkSkyKey = " ";
    private String allCountriesPath = " ";
    private String allCountriesIndexPath = " ";

    public UserOptionBuilder() {
    }

    public static UserOptionBuilder create() {
        return new UserOptionBuilder();
    }

    public UserOption build() {
        return new UserOption(s57Path, darkSkyKey, allCountriesPath, allCountriesIndexPath);
    }
    public UserOptionBuilder s57Path(String s57Path) {
        this.s57Path = s57Path;
        return this;
    }
    public UserOptionBuilder darkSkyKey(String darkSkyKey) {
        this.darkSkyKey = darkSkyKey;
        return this;
    }
    public UserOptionBuilder allCountriesPath(String allCountriesPath) {
        this.allCountriesPath = allCountriesPath;
        return this;
    }
    public UserOptionBuilder allCountriesIndexPath(String allCountriesIndexPath) {
        this.allCountriesIndexPath = allCountriesIndexPath;
        return this;
    }
}
