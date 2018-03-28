package dao.daoImpl;

import dao.daoI.Crud;
import dao.daoI.CrudA;
import entities.Book;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;

import static dao.daoI.CrudA.Entity.BOOK;

/**
 * Created by SELPHA on 26/3/2018.
 */
@RequestScoped
@CrudA(choice = BOOK)
public class BookDao implements Crud {
    @PersistenceContext(unitName = "libMIS")
    private EntityManager entityManager;
    Book book=null;

    public BookDao() {
        book=new Book();
    }

    public boolean create(Object o) {
        book= (Book) o;
        try {
            entityManager.persist(book);
        }catch (PersistenceException p){
            p.printStackTrace();
            return false;
        }
        return true;
    }

    public Object read(Object o) {
        book= (Book) o;
        Book b=null;
        try {
            b=entityManager.find(Book.class,book.getBookid());
        }catch (PersistenceException p){
            p.printStackTrace();
        }
        return b.toString();
    }

    public boolean update(Object o) {
        book= (Book) o;
        Book b=null;
        try {
            b= (Book) read(book);
            b.setBookid(book.getBookid());
            b.setBookCategory(book.getBookCategory());
            b.setBookStatus(book.getBookStatus());
            b.setBookTitle(book.getBookTitle());
            b.setIsbn(book.getIsbn());
            b.setPageCount(book.getPageCount());
            entityManager.merge(b);
        }catch (PersistenceException p){
            p.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(Object o) {
        book= (Book) o;
        try {
            entityManager.remove(book);
        }catch (PersistenceException p){
            p.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Object> searchBook(Book b){
        List<Object> list=new ArrayList<Object>();
        try {
            Query query=entityManager.createNamedQuery("Book.search");
            query.setParameter("bookid",b.getBookid());
            list=query.getResultList();
        }catch (Exception p){
            p.printStackTrace();
        }
        return list;
    }

    public List<Object>searchAllBooks(){
        List<Object>list=new ArrayList<Object>();
        try {
            String s="select b from Book b";
            list=entityManager.createQuery(s).getResultList();
        }catch (Exception p){
            p.printStackTrace();
        }
        return list;
    }

}
