package mgorbachev.songs.finder.entities;

import java.io.Serializable;
import java.util.List;

public class Album implements Serializable {

    private String name;

    private List<String> songNames;

    public Album() {
    }

    public Album(String name) {
        this.name = name;
    }

    public Album(String name, List<String> songNames) {
        this.name = name;
        this.songNames = songNames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSongNames() {
        return songNames;
    }

    public void setSongNames(List<String> songNames) {
        this.songNames = songNames;
    }
}
