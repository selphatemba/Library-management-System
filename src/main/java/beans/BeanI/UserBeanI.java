package beans.BeanI;

import entities.User;

import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Created by SELPHA on 27/3/2018.
 */
@Local
public interface UserBeanI {
    boolean create(User u);
    boolean login(User u);
    User find(User u);
    boolean edit(User u);
    boolean remove(User u);
}
