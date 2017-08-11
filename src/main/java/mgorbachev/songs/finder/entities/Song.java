package mgorbachev.songs.finder.entities;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Created by Mikhail_Gorbachev on 8/10/2017.
 */
@Document(indexName = "songs", type = "songs")
public class Song implements Serializable {
    @Id
    private String id;

    private String name;

    @Field(type = FieldType.Nested)
    private List<Person> authors;

    private List<String> composers;

    private List<String> artists;

    public Song() {
    }

    public Song(String id, String name, List<Person> authors, List<String> composers,
                List<String> artists) {
        this.id = id;
        this.name = name;
        this.authors = authors;
        this.composers = composers;
        this.artists = artists;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Person> authors) {
        this.authors = authors;
    }

    public List<String> getComposers() {
        return composers;
    }

    public void setComposers(List<String> composers) {
        this.composers = composers;
    }

    public List<String> getArtists() {
        return artists;
    }

    public void setArtists(List<String> artists) {
        this.artists = artists;
    }
}
