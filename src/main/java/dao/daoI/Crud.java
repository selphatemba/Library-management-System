package dao.daoI;

/**
 * Created by SELPHA on 26/3/2018.
 */
public interface Crud {
    boolean create(Object o);
    Object read(Object o);
    boolean update(Object o);
    boolean delete(Object o);

}
