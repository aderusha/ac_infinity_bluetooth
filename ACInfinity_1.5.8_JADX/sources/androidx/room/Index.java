package androidx.room;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({})
@Retention(RetentionPolicy.CLASS)
public @interface Index {

    public enum Order {
        ASC,
        DESC
    }

    String name() default "";

    Order[] orders() default {};

    boolean unique() default false;

    String[] value();
}
