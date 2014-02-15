package bzh.terrevirtuelle.navisu.api.option.usage;

import bzh.terrevirtuelle.navisu.api.option.annotation.DoubleOption;
import bzh.terrevirtuelle.navisu.api.option.annotation.IntOption;
import bzh.terrevirtuelle.navisu.api.option.annotation.Option;
import bzh.terrevirtuelle.navisu.api.option.mapping.IntMapper;

import java.util.List;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/02/2014 18:11
 */
public class TestModel {

    @IntOption(18)
    private Integer intValue;

    @Option("Hello World")
    private String stringValue;

    @DoubleOption(3.14)
    private Double doubleValue;

    public TestModel() {}

    public TestModel(Integer intValue, String stringValue, Double doubleValue) {
        this.intValue = intValue;
        this.stringValue = stringValue;
        this.doubleValue = doubleValue;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }
}
