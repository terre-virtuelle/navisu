package bzh.terrevirtuelle.navisu.util.patterns.specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author nlecoz <Nicolas Le Coz>
 * @param <T>
 * @since 25 mars 2009
 */
@SuppressWarnings("unchecked")
public abstract class AbstractCompositeSpecification<T> implements Specification<T> {
    protected List<Specification<T>> specifications;

    @Override
    public Specification<T> or(Specification<T> specification) {
        return new OrSpecification<>(this, specification);
    }

    @Override
    public Specification<T> and(Specification<T> specification) {
        return new AndSpecification<>(this, specification);
    }
    
    @Override
    public Specification<T> not() {
        return new NotSpecification<>(this);
    }

    protected AbstractCompositeSpecification(Specification<T>... specifications) {
        this.specifications = new ArrayList<>();
        
        boolean addAll = this.specifications.addAll(Arrays.asList(specifications));
    }
}
