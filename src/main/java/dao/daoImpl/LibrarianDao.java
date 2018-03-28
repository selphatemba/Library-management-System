package dao.daoImpl;

import dao.daoI.Crud;
import dao.daoI.CrudA;
import entities.Librarian;


import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import static dao.daoI.CrudA.Entity.LIBRARIAN;

/**
 * Created by SELPHA on 26/3/2018.
 */
@RequestScoped
@CrudA(choice = LIBRARIAN)
public class LibrarianDao implements Crud {
    @PersistenceContext(unitName = "libMIS")
    private EntityManager entityManager;
    Librarian librarian=null;

    public LibrarianDao() {
        librarian=new Librarian();
    }

    public boolean create(Object o) {
        librarian= (Librarian) o;
        try {
            entityManager.persist(librarian);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Object read(Object o) {
        librarian= (Librarian) o;
        Librarian lib=null;
        try {
           lib=entityManager.find(Librarian.class,librarian.getIdNumber());
        }catch (PersistenceException p){
            p.printStackTrace();
        }
        return lib.toString();
    }

    public boolean update(Object o) {
        librarian= (Librarian) o;
        Librarian l=null;
        try {
            l= (Librarian) read(librarian);
            l.setIdNumber(librarian.getIdNumber());
            l.setFname(librarian.getFname());
            l.setLname(librarian.getLname());
            entityManager.merge(l);
        }catch (PersistenceException p){
            p.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(Object o) {
        librarian= (Librarian) o;
        try {
            entityManager.remove(librarian);
        }catch (PersistenceException p){
            p.printStackTrace();
            return false;
        }
        return true;
    }

}
