package dao.daoImpl;

import dao.daoI.Crud;
import dao.daoI.CrudA;
import entities.Book;
import entities.BookBorrow;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;


import java.util.ArrayList;
import java.util.List;

import static dao.daoI.CrudA.Entity.BOOKBORROW;

/**
 * Created by SELPHA on 27/3/2018.
 */
@RequestScoped
@CrudA(choice = BOOKBORROW)
public class BookBorrowDao implements Crud {
    @PersistenceContext(unitName = "libMIS")
    private EntityManager entityManager;
    BookBorrow bookBorrow=null;

    public BookBorrowDao() {
        bookBorrow=new BookBorrow();
    }

    public boolean create(Object o) {
        bookBorrow= (BookBorrow) o;
        try {
            entityManager.persist(bookBorrow);
        }catch (PersistenceException p){
            p.printStackTrace();
            return false;
        }
        return true;
    }

    public Object read(Object o) {
        return null;
    }

    public boolean update(Object o) {
        return false;
    }

    public boolean delete(Object o) {
        return false;
    }



    double getAmountDue(BookBorrow bookBorrow){
        //Date returndate=bookBorrow.getReturnDate();
       // Date dueDate=bookBorrow.getDueDate();
       // ChronoUnit.DAYS.between(returndate,dueDate);
        return 0;
    }

     public List<Book> OverdueReport(){
        List<Book>overdueBooks=new ArrayList<Book>();
        try {
            Query query=entityManager.createNamedQuery("BookBorrow.findOverdueBooks");
            overdueBooks=query.getResultList();
        }catch (Exception p){
            p.printStackTrace();
        }
        return overdueBooks;
    }
}
