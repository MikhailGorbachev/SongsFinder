package mgorbachev.songs.finder.repositories;

import mgorbachev.songs.finder.entities.Song;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by Mikhail_Gorbachev on 8/10/2017.
 */
public interface SongRepository extends ElasticsearchRepository<Song, String>, SearchRepository {

    Song save(Song song);

    Song findById(String id);

}
