package mgorbachev.songs.finder.entities;

import java.io.Serializable;

public class Album implements Serializable {

    private String name;

    public Album() {
    }

    public Album(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Album album = (Album) o;

        return name.equals(album.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
