package beans.BeanImpl;

import beans.BeanI.BookBeanI;
import dao.daoI.Crud;
import dao.daoI.CrudA;
import entities.Book;

import javax.inject.Inject;

import static dao.daoI.CrudA.Entity.BOOK;

/**
 * Created by SELPHA on 27/3/2018.
 */
public class BookBean implements BookBeanI {
    @Inject
    @CrudA(choice = BOOK)
    private Crud crud;
    public boolean create(Book b) {
        if (crud.read(b)==null){
            return crud.create(b);
        }
        return true;
    }

    public Book find(Book b) {
        Book bk=null;
        bk= (Book) crud.read(b);
        return  bk;
    }

    public boolean edit(Book b) {
        return crud.update(b);
    }

    public boolean remove(Book b) {
        return crud.delete(b);
    }
}
