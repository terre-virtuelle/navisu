package bzh.terrevirtuelle.navisu.util.patterns.specification;

/**
 * @author nlecoz <Nicolas Le Coz>
 * @param <T>
 * @since 25 mars 2009
 */
@SuppressWarnings("unchecked")
public abstract class LeafSpecification<T> extends AbstractCompositeSpecification<T> {
    @Override
    public abstract boolean isSatisfiedBy(T candidate);
}
