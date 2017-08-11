package mgorbachev.songs.finder;

import mgorbachev.songs.finder.entities.Album;
import mgorbachev.songs.finder.entities.Artist;
import mgorbachev.songs.finder.entities.Person;
import mgorbachev.songs.finder.entities.Song;
import mgorbachev.songs.finder.repositories.SongRepository;
import mgorbachev.songs.finder.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by Mikhail_Gorbachev on 8/10/2017.
 */
@SpringBootApplication
public class SongsFinderApplication implements CommandLineRunner {

    @Autowired
    private SearchService service;

    @Autowired
    private SongRepository repository;


    @Override
    public void run(String... strings) throws Exception {
        populateSong();
    }

    private Song populateSong() {
        Person lars = new Person("1", "Lars Ulrich");
        Person james = new Person("2", "James Hetfield");
        List<Person> personList = newArrayList(lars, james);

        Song song = new Song("1", "One", personList, personList,
                newArrayList(new Artist("Metallica", Artist.ArtistType.GROUP)),
                newArrayList(new Album("And Justice for All", newArrayList("One"))));

        return repository.save(song);
    }

    public static void main(String[] args) {
        SpringApplication.run(SongsFinderApplication.class, args);
    }
}
