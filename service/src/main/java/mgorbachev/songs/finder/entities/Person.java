package mgorbachev.songs.finder.entities;

import java.io.Serializable;

/**
 * Created by Mikhail_Gorbachev on 8/10/2017.
 */
//@Document(indexName = "persons", type = "person")
public class Person implements Serializable {


    private String fullName;

    public Person() {
    }

    public Person(String fullName) {

        this.fullName = fullName;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
