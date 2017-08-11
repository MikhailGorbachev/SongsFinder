package mgorbachev.songs.finder.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.List;

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

    @Field(type = FieldType.Nested)
    private List<Person> composers;

    @Field(type = FieldType.Nested)
    private List<Artist> artists;

    @Field(type = FieldType.Nested)
    private List<Album> albums;

    public Song() {
    }

    public Song(String id, String name, List<Person> authors, List<Person> composers, List<Artist> artists, List<Album> albums) {
        this.id = id;
        this.name = name;
        this.authors = authors;
        this.composers = composers;
        this.artists = artists;
        this.albums = albums;
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

    public List<Person> getComposers() {
        return composers;
    }

    public void setComposers(List<Person> composers) {
        this.composers = composers;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
