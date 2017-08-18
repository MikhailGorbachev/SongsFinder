package mgorbachev.songs.finder.entities;

import java.io.Serializable;

/**
 * Created by Mikhail_Gorbachev on 8/10/2017.
 */
public class Artist implements Serializable {

    public enum ArtistType {
        SINGLE, GROUP
    }

    private String name;

    private ArtistType type;

    public Artist(String name, ArtistType type) {
        this.name = name;
        this.type = type;
    }

    public Artist() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArtistType getType() {
        return type;
    }

    public void setType(ArtistType type) {
        this.type = type;
    }
}
