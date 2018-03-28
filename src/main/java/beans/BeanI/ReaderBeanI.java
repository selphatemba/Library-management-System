package beans.BeanI;

import entities.Book;
import entities.BookBorrow;
import entities.Reader;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by SELPHA on 27/3/2018.
 */
@Local
public interface ReaderBeanI {
    boolean create(Reader reader);
    Reader find(Reader reader);
    boolean edit(Reader reader);
    boolean remove(Reader reader);
    List<Object> searchBook(Book b);
    boolean reserveBook(Book b);
    List<BookBorrow> viewborrowedBooks(BookBorrow b);

}
