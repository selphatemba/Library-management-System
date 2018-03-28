package beans.BeanImpl;

import beans.BeanI.UserBeanI;
import dao.daoI.Crud;
import dao.daoI.CrudA;
import dao.daoImpl.UserDao;
import entities.User;

import javax.ejb.Stateless;
import javax.inject.Inject;

import static dao.daoI.CrudA.Entity.USER;

/**
 * Created by SELPHA on 27/3/2018.
 */
@Stateless
public class UserBean  implements UserBeanI{
    @Inject
    @CrudA(choice = USER)
    private Crud crud;
    public boolean create(User u) {
        if (crud.read(u)==null){
            return crud.create(u);
        }
       return true;
    }

    public boolean login(User u) {
        UserDao userDao=new UserDao();
       return userDao.login(u.getIdNumber(),u.getPassword());
    }

    public User find(User u) {
            User k=null;
            k= (User) crud.read(u);
            return  k;
    }

    public boolean edit(User u) {
        return crud.update(u);
    }

    public boolean remove(User u) {
        return crud.delete(u);
    }
}
