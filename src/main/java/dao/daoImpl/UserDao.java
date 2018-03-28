package dao.daoImpl;

import dao.daoI.Crud;
import dao.daoI.CrudA;
import entities.User;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import static dao.daoI.CrudA.Entity.USER;

/**
 * Created by SELPHA on 26/3/2018.
 */
@RequestScoped
@CrudA(choice = USER)
public class UserDao implements Crud {
    @PersistenceContext(unitName = "libMIS")
    private EntityManager entityManager;
    User user=null;

    public UserDao() {
        user=new User();
    }

    public boolean create(Object o) {
        user= (User) o;
        try {
            entityManager.persist(user);
        }catch (PersistenceException p){
            p.printStackTrace();
            return false;
        }
        return true;
    }

    public Object read(Object o) {
        user= (User) o;
        User u=new User();
        try {
            u=entityManager.find(User.class,user.getIdNumber());
        }catch (PersistenceException p){
            p.printStackTrace();
        }
        return u.toString();
    }

    public boolean update(Object o) {
        user= (User) o;
        User u=new User();
        try {
            u= (User) read(user);
            u.setIdNumber(user.getIdNumber());
            u.setPassword(user.getPassword());
            entityManager.merge(u);
        }catch (PersistenceException p){
            p.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(Object o) {
        user= (User) o;
        try {
            entityManager.remove(user);
        }catch (PersistenceException p){
            p.printStackTrace();
            return false;
        }
       return  true;
    }
    public  boolean login(int idNumber,String password){
        try {
            Query query=entityManager.createNamedQuery("User.login");
            query.setParameter("id",idNumber);
            query.setParameter("pass",password);
        }catch (PersistenceException p){
            p.printStackTrace();
            return false;
        }
        return true;
    }
}
