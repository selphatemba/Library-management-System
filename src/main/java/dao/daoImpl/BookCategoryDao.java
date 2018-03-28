package dao.daoImpl;

import dao.daoI.Crud;
import dao.daoI.CrudA;
import entities.BookCategory;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import static dao.daoI.CrudA.Entity.BOOKCATEGORY;

/**
 * Created by SELPHA on 26/3/2018.
 */
@RequestScoped
@CrudA(choice = BOOKCATEGORY)
public class BookCategoryDao implements Crud {
    @PersistenceContext(unitName = "libMIS")
    private EntityManager entityManager;
    BookCategory bookCategory=null;

    public BookCategoryDao() {
        bookCategory=new BookCategory();
    }

    public boolean create(Object o) {
        try {
            bookCategory= (BookCategory) o;
            entityManager.persist(bookCategory);
        }catch (PersistenceException pe){
            pe.printStackTrace();
            return false;
        }
       return true;
    }


    public Object read(Object o) {
        BookCategory b=null;
        try {
            bookCategory= (BookCategory) o;
            b=entityManager.find(BookCategory.class,bookCategory.getCatID());
        }catch (PersistenceException pe){
            pe.printStackTrace();
        }
        return b.toString();
    }


    public boolean update(Object o) {
        try {
            bookCategory = (BookCategory) o;
             BookCategory b= (BookCategory) read(bookCategory);
            b.setCatID(bookCategory.getCatID());
            b.setCatName(bookCategory.getCatName());
            entityManager.merge(b);
        }catch (PersistenceException p){
            p.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean delete(Object o) {
        bookCategory= (BookCategory) o;
        try {
            entityManager.remove(bookCategory);
        }catch (PersistenceException p){
            p.printStackTrace();
            return false;
        }
        return true;
    }
}
