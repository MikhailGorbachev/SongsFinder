package mgorbachev.songs.finder;

import java.util.List;
import mgorbachev.songs.finder.entities.Person;
import mgorbachev.songs.finder.entities.Song;
import mgorbachev.songs.finder.repositories.SongRepository;
import mgorbachev.songs.finder.service.SearchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.test.context.junit4.SpringRunner;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Mikhail_Gorbachev on 8/10/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SongsFinderApplication.class)
public class SearchServiceTest {

    @Autowired
    private SearchService searchService;

    @Autowired
    private SongRepository songRepository;


    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Before
    public void before() {
        esTemplate.deleteIndex(Song.class);
        esTemplate.createIndex(Song.class);
        esTemplate.putMapping(Song.class);
        esTemplate.refresh(Song.class);


        esTemplate.deleteIndex(Person.class);
        esTemplate.createIndex(Person.class);
        esTemplate.putMapping(Person.class);
        esTemplate.refresh(Person.class);
    }

    @Test
    public void testFindOne() {
        Song song = new Song("1", "One", newArrayList(new Person("1","Lars Ulrich"), new Person("2", "James Hetfield")),
            newArrayList("Lars Ulrich", "James Hetfield"), newArrayList("Metallica"));
        songRepository.save(song);
        IndexQuery iq = new IndexQuery();
        iq.setId(song.getId());
        iq.setObject(song);
        esTemplate.index(iq);
        esTemplate.refresh(Song.class);

        List<Song> songs = searchService.findByAuthorFullName("James Hetfield");

        assertNotNull(songs);
        assertEquals("Song couldn't be found", 1, songs.size());
        Song foundSong = songs.get(0);
        assertEquals(song.getName(), foundSong.getName());
    }


}
