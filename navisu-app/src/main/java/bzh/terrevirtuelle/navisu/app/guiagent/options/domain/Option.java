/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.app.guiagent.options.domain;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author serge
 * @date Jul 4, 2017
 */
public class Option {

    private  StringProperty type;

    public Option() {
    }

    public Option(String type) {
        this.type = new SimpleStringProperty(type);
    }

    /**
     * Get the value of type
     *
     * @param value
     */
    public final void setType(String value) {
        type.set(value);
    }

    /**
     * Set the value of type
     *
     * @return 
     */
    public final String getType() {
        return type.get();
    }

    public final StringProperty typeProperty() {
        return type;
    }

    @Override
    public String toString() {
        return "Option{" + "type=" + type + '}';
    }

}
