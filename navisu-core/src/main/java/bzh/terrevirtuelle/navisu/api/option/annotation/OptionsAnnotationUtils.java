package bzh.terrevirtuelle.navisu.api.option.annotation;

import bzh.terrevirtuelle.navisu.api.option.mapping.StringMapper;

import java.lang.reflect.Field;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/02/2014 18:37
 */
public class OptionsAnnotationUtils {

    public static StringMapper<?> getMapperForField(Field field) throws Exception {


        if(field.getDeclaredAnnotations().length > 0) {

            if(field.isAnnotationPresent(IntOption.class)) {
                IntOption annotation = field.getAnnotation(IntOption.class);
                Class<? extends StringMapper> mapper = annotation.mapper();
                return mapper.newInstance();
            }

            else if(field.isAnnotationPresent(DoubleOption.class)) {
                DoubleOption annotation = field.getAnnotation(DoubleOption.class);
                Class<? extends StringMapper> mapper = annotation.mapper();
                return mapper.newInstance();
            }

            else if(field.isAnnotationPresent(Option.class)) {
                Option annotation = field.getAnnotation(Option.class);
                Class<? extends StringMapper> mapper = annotation.mapper();
                return mapper.newInstance();
            }
        }

        return null;
    }

    public static void initField(Object target, Field field) throws Exception  {

        if(field.getDeclaredAnnotations().length > 0) {

            if(field.isAnnotationPresent(IntOption.class)) {
                IntOption annotation = field.getAnnotation(IntOption.class);
                field.setAccessible(true);
                field.setInt(target, annotation.value());
            }

            else if(field.isAnnotationPresent(DoubleOption.class)) {
                DoubleOption annotation = field.getAnnotation(DoubleOption.class);
                field.setAccessible(true);
                field.setDouble(target, annotation.value());
            }

            else if(field.isAnnotationPresent(Option.class)) {
                Option annotation = field.getAnnotation(Option.class);
                field.setAccessible(true);
                field.set(target, annotation.value());
            }
        }
    }

    private OptionsAnnotationUtils() {}
}
