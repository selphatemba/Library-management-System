package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * Created by SELPHA on 26/3/2018.
 */
@Entity
@NamedQuery(name="User.login", query = "select u from User u where u.idNumber=:id and u.password=:pass")
public class User {
    @Id
    private int idNumber;
    private String password;

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
