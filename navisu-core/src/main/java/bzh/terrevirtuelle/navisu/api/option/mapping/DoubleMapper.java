package bzh.terrevirtuelle.navisu.api.option.mapping;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/02/2014 17:59
 */
public class DoubleMapper implements StringMapper<Double> {

    @Override
    public String map(Double value) {
        return value.toString();
    }

    @Override
    public Double unmap(String value) {
        return Double.valueOf(value);
    }
}
