package mgorbachev.songs.finder.repositories.impl;

import mgorbachev.songs.finder.entities.Artist;
import mgorbachev.songs.finder.entities.Song;
import mgorbachev.songs.finder.repositories.SearchRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchPhrasePrefixQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.nestedQuery;

/**
 * Created by Mikhail_Gorbachev on 8/10/2017.
 */
public class SongRepositoryImpl implements SearchRepository {
    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Override
    public List<Song> findSongsByAuthor(String author) {
        final QueryBuilder builder = nestedQuery("authors", matchQuery("authors.fullName", author));

        final SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).build();

        return esTemplate.queryForList(searchQuery, Song.class);
    }

    @Override
    public List<Song> findSongsByComposer(String composerName) {
        final QueryBuilder builder = nestedQuery("composers", matchQuery("composers.fullName", composerName));
        final SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).build();

        return esTemplate.queryForList(searchQuery, Song.class);
    }

    @Override
    public List<Song> findSongsByArtist(String artistName) {
        final QueryBuilder builder = nestedQuery("artists", matchQuery("artists.name", artistName));
        final SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).build();

        return esTemplate.queryForList(searchQuery, Song.class);
    }

    @Override
    public List<Song> findSongsByName(String songName) {
        final QueryBuilder builder = matchPhrasePrefixQuery("name", songName);
        final SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).build();

        return esTemplate.queryForList(searchQuery, Song.class);
    }


}
