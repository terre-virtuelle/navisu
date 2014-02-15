package bzh.terrevirtuelle.navisu.api.option.mapping;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/02/2014 17:42
 */
public class IntMapper implements StringMapper<Integer> {

    @Override
    public String map(Integer value) {
        return value.toString();
    }

    @Override
    public Integer unmap(String value) {
        return Integer.valueOf(value);
    }
}
