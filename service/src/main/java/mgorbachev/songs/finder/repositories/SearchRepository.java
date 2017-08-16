package mgorbachev.songs.finder.repositories;

import java.util.List;
import mgorbachev.songs.finder.entities.Song;

/**
 * Created by Mikhail_Gorbachev on 8/10/2017.
 */
public interface SearchRepository {

    List<Song> findSongsByAuthor(String author);


    List<Song> findSongsByComposer(String composerName);

    List<Song> findSongsByArtist(String artistName);

    List<Song> findSongsByName(String songName);

}
