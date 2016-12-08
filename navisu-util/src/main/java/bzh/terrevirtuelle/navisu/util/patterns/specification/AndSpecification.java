package bzh.terrevirtuelle.navisu.util.patterns.specification;

/**
 * @author nlecoz <Nicolas Le Coz>
 * @since 25 mars 2009
 */
public class AndSpecification<T> extends AbstractCompositeSpecification<T> {
    
    @Override
    public boolean isSatisfiedBy(final T candidate) {
        boolean result = true;
        
        for (Specification<T> specification : this.specifications) {
            result &= specification.isSatisfiedBy(candidate);
        }
        return result;
    }
    @SuppressWarnings("unchecked")
    public AndSpecification(Specification<T>... specifications) {
        super(specifications);
    }
}
