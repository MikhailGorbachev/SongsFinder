package mgorbachev.songs.finder.service;

import java.util.List;
import mgorbachev.songs.finder.entities.Song;
import mgorbachev.songs.finder.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mikhail_Gorbachev on 8/10/2017.
 */
@Service
public class SearchService {

    @Autowired
    private SongRepository songRepository;


    public List<Song> findByAuthorFullName(String authorFullName) {
        return songRepository.findSongsByAuthor(authorFullName);
    }

    public List<Song> findByName(String name) {
        return songRepository.findByName(name);
    }


}
