package beans.BeanI;

import entities.Book;
import entities.BookBorrow;
import entities.Librarian;

import javax.ejb.Local;
import java.util.List;


/**
 * Created by SELPHA on 27/3/2018.
 */
@Local
public interface LibrarianBeanI {
    boolean create(Librarian librarian);
    Librarian find(Librarian librarian);
    boolean edit(Librarian librarian);
    boolean remove(Librarian librarian);
    boolean issueBook(BookBorrow bookBorrow);
    boolean returnBook(BookBorrow bookBorrow);
    List<BookBorrow> viewReaderHistory(BookBorrow bb);
    List<Book>overdueReport();
}
