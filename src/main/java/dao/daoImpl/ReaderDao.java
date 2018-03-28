package dao.daoImpl;

import dao.daoI.Crud;
import dao.daoI.CrudA;
import entities.BookBorrow;
import entities.Reader;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;

import static dao.daoI.CrudA.Entity.READER;

/**
 * Created by SELPHA on 26/3/2018.
 */
@RequestScoped
@CrudA(choice = READER)
public class ReaderDao implements Crud{
    @PersistenceContext(unitName = "libMIS")
    private EntityManager entityManager;
    Reader reader=null;

    public ReaderDao() {
        reader=new Reader();
    }


    public boolean create(Object o) {
        reader= (Reader) o;
        try {
            entityManager.persist(reader);
        }catch (PersistenceException p){
            p.printStackTrace();
            return false;
        }
        return true;
    }


    public Object read(Object o) {
        reader= (Reader) o;
        Reader r=null;
        try {
           r=entityManager.find(Reader.class,reader.getIdNumber());
        }catch (PersistenceException p){
            p.printStackTrace();
        }
        return r.toString();
    }

    public boolean update(Object o) {
        reader= (Reader) o;
        Reader r=null;
        try{
            r= (Reader) read(reader);
            r.setIdNumber(reader.getIdNumber());
            r.setEmail(reader.getEmail());
            r.setName(reader.getName());
            entityManager.merge(r);
        }catch (PersistenceException p){
            p.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(Object o) {
        reader= (Reader) o;
        try {
            entityManager.remove(reader);
        }catch (PersistenceException p){
            p.printStackTrace();
            return false;
        }
        return true;
    }

    public List<BookBorrow>readersReport(BookBorrow bookBorrow){
        List<BookBorrow>list=new ArrayList<BookBorrow>();
        try {
            Query query=entityManager.createNamedQuery("Reader.viewReaderReport");
            query.setParameter("reader1",bookBorrow.getReader());
            list= (List<BookBorrow>) query.getSingleResult();
        }catch (Exception p){
            p.printStackTrace();

        }
        return list;
    }

}
