package beans.BeanI;

import entities.Book;

import javax.ejb.Local;

/**
 * Created by SELPHA on 27/3/2018.
 */
@Local
public interface BookBeanI {
    boolean create(Book b);
    Book find(Book b);
    boolean edit(Book b);
    boolean remove(Book b);
}
