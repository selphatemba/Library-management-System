package beans.BeanImpl;

import beans.BeanI.ReaderBeanI;
import dao.daoI.Crud;
import dao.daoI.CrudA;
import dao.daoImpl.BookDao;
import dao.daoImpl.ReaderDao;
import entities.Book;
import entities.BookBorrow;
import entities.Reader;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static dao.daoI.CrudA.Entity.BOOK;

/**
 * Created by SELPHA on 27/3/2018.
 */
@Stateless
public class ReaderBean implements ReaderBeanI{
    @Inject
    @CrudA(choice = BOOK)
    private Crud crud;


    public List<Object> searchBook(Book b) {
        List<Object> book=new ArrayList<Object>();
        BookDao bookDao=new BookDao();
        try {
            book=bookDao.searchBook(b);
        }catch (Exception p){
            p.printStackTrace();
        }
        return book;
    }

    public boolean reserveBook(Book b) {
        if (crud.read(b) != null) {//check that the book isInDb
            if (b.getBookStatus().equals("Available")) {//check that the book is available for reservation
                try {
                    crud.update(b);
                } catch (Exception p) {
                    p.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }

    public List<BookBorrow> viewborrowedBooks(BookBorrow b) {
        ReaderDao readerDao=new ReaderDao();
        List<BookBorrow>list=new ArrayList<BookBorrow>();
        try {
            list=readerDao.readersReport(b);
        }catch (Exception p){
            p.printStackTrace();
        }
        return list;
    }
    public boolean create(Reader reader) {
        if (crud.read(reader)==null){
            return crud.create(reader);
        }
        return true;
    }

    public Reader find(Reader reader) {
        Reader rd=null;
        rd= (Reader) crud.read(reader);
        return  rd;
    }

    public boolean edit(Reader reader) {
        return crud.update(reader);
    }

    public boolean remove(Reader reader) {
        return crud.delete(reader);
    }


}
