package entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by SELPHA on 26/3/2018.
 */
@Entity
public class BookCategory {
    @Id
    private  int catID;
    private String catName;

    public int getCatID() {
        return catID;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    @Override
    public String toString() {
        return "BookCategory{" +
                "catID=" + catID +
                ", catName='" + catName + '\'' +
                '}';
    }
}
