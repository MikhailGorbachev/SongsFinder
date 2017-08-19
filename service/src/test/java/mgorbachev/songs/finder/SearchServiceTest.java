package mgorbachev.songs.finder;

import mgorbachev.songs.finder.entities.Album;
import mgorbachev.songs.finder.entities.Artist;
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
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
    }

    @Test
    public void testFindByAuthorOne() {
        Song song = populateSong();

        List<Song> songs = searchService.findSongsByAuthorName("James");

        assertNotNull(songs);
        assertEquals(1, songs.size());
        Song foundSong = songs.get(0);
        assertEquals(song.getName(), foundSong.getName());
    }

    @Test
    public void testFindArtistsBySong() {
        Song song = populateSong();

        List<Artist> artists = searchService.findArtistsBySong("one");

        assertNotNull(artists);
        assertEquals(3, artists.size());
        assertEquals(song.getArtists().get(0).getName(), artists.get(0).getName());
    }

    @Test
    public void testFindAlbumsBySong() {
        Song song = populateSong();
        populateSongWithSameAlbum();
        List<Album> albums = searchService.findAlbumsBySong("one");

        assertNotNull(albums);
        assertEquals(1, albums.size());
        assertEquals(song.getAlbums().get(0).getName(), albums.get(0).getName());
    }

    private Song populateSongWithSameAlbum() {
        Person lars = new Person("Lars Ulrich");
        Person james = new Person("James Hetfield");
        List<Person> personList = newArrayList(lars, james);

        Song song = new Song("2", "One", personList, personList,
                newArrayList(new Artist("Cover Metallica", Artist.ArtistType.GROUP)),
                newArrayList(new Album("And Justice for All")));

        return songRepository.save(song);
    }


    @Test
    public void testFindSongsByComposer() {
        Song song = populateSong();

        List<Song> songs = searchService.findSongsByComposer("lars");

        assertNotNull(songs);
        assertEquals(1, songs.size());
        assertEquals(song.getName(), songs.get(0).getName());
    }


    @Test
    public void testFindSongsByArtist() {
        Song song = populateSong();

        List<Song> songs = searchService.findSongsByArtistName("metallica");

        assertNotNull(songs);
        assertEquals(1, songs.size());
        assertEquals(song.getName(), songs.get(0).getName());
    }


    @Test
    public void testFindGroupsByArtistName() {
        populateSong();
        populateAnotherSong();

        List<Artist> groups = searchService.findGroupsByArtistName("lars");

        assertNotNull(groups);
        assertEquals(1, groups.size());
    }

    @Test
    public void testFindSongsByPartialName() {
        Song song = populateSong();

        List<Song> songs = searchService.findSongsByName("on");

        assertNotNull(songs);
        assertEquals(1, songs.size());
        assertEquals(song.getName(), songs.get(0).getName());
    }


    private Song populateSong() {
        Person lars = new Person("Lars Ulrich");
        Person james = new Person("James Hetfield");
        List<Person> personList = newArrayList(lars, james);

        Song song = new Song("1", "One", personList, personList,
                newArrayList(new Artist("Metallica", Artist.ArtistType.GROUP),
                        new Artist(lars.getFullName(), Artist.ArtistType.SINGLE),
                        new Artist(james.getFullName(), Artist.ArtistType.SINGLE)),
                newArrayList(new Album("And Justice for All")));

        return songRepository.save(song);
    }

    private Song populateAnotherSong() {
        Person lars = new Person("Lars Ulrich");
        Person james = new Person("James Hetfield");
        List<Person> personList = newArrayList(lars, james);

        Song song = new Song("2", "Nothing else matters", personList, personList,
                newArrayList(new Artist("Metallica", Artist.ArtistType.GROUP),
                        new Artist(lars.getFullName(), Artist.ArtistType.SINGLE),
                        new Artist(james.getFullName(), Artist.ArtistType.SINGLE)),
                newArrayList(new Album("The best of Metallica")));

        return songRepository.save(song);
    }


}
