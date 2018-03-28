package dao.daoI;

import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by SELPHA on 26/3/2018.
 */
@Qualifier

@Retention(RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
public @interface CrudA {
    Entity choice();
    enum Entity{READER,LIBRARIAN,BOOK,BOOKCATEGORY,USER,BOOKBORROW,RESERVATION,RETURNBOOK}
}
