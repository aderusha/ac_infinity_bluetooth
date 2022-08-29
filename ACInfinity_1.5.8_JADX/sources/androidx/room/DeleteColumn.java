package androidx.room;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Repeatable(Entries.class)
@Retention(RetentionPolicy.CLASS)
public @interface DeleteColumn {

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.CLASS)
    public @interface Entries {
        DeleteColumn[] value();
    }

    String columnName();

    String tableName();
}
