package mgorbachev.songs.finder.entities;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by Mikhail_Gorbachev on 8/10/2017.
 */
@Document(indexName = "persons", type = "person")
public class Person implements Serializable{

    @Id
    private String id;

    private String fullName;

    public Person() {
    }

    public Person(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
