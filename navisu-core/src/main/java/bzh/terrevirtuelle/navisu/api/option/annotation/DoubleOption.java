package bzh.terrevirtuelle.navisu.api.option.annotation;


import bzh.terrevirtuelle.navisu.api.option.mapping.DoubleMapper;
import bzh.terrevirtuelle.navisu.api.option.mapping.StringMapper;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/02/2014 18:29
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface DoubleOption {

    double value() default 0d;

    Class<? extends StringMapper> mapper() default DoubleMapper.class;
}
