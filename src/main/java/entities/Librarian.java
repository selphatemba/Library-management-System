package entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by SELPHA on 26/3/2018.
 */
@Entity
public class Librarian {
    @Id
    private int idNumber;
    private String fname;
    private String lname;

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "idNumber=" + idNumber +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                '}';
    }
}
