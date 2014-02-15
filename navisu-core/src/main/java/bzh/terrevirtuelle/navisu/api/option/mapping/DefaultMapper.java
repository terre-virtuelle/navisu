package bzh.terrevirtuelle.navisu.api.option.mapping;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/02/2014 18:00
 */
public class DefaultMapper implements StringMapper<String> {

    @Override
    public String map(String value) {
        return value;
    }

    @Override
    public String unmap(String value) {
        return value;
    }
}
