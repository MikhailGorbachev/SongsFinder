package mgorbachev.songs.finder.repositories.impl;

import java.util.List;
import mgorbachev.songs.finder.entities.Song;
import mgorbachev.songs.finder.repositories.SearchRepository;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.nestedQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/**
 * Created by Mikhail_Gorbachev on 8/10/2017.
 */
public class SongRepositoryImpl implements SearchRepository {
    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Override
    public List<Song> findSongsByAuthor(String author) {

        final QueryBuilder builder = nestedQuery("person", boolQuery().must(termQuery("person.fullname", author)));

        final SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).build();
        final List<Song> songs = esTemplate.queryForList(searchQuery, Song.class);

        return songs;
    }


}
