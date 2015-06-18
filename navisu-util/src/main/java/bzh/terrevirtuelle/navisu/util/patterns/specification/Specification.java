package bzh.terrevirtuelle.navisu.util.patterns.specification;

/**
 * @author nlecoz <Nicolas Le Coz>
 * @since 25 mars 2009
 */
public interface Specification<T> {
    public boolean isSatisfiedBy(T candidate);
    
    public Specification<T> or(Specification<T> specification);
    public Specification<T> and(Specification<T> specification);
    public Specification<T> not();
}
