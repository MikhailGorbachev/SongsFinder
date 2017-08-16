package mgorbachev.songs.finder;

import mgorbachev.songs.finder.entities.Album;
import mgorbachev.songs.finder.entities.Artist;
import mgorbachev.songs.finder.entities.Person;
import mgorbachev.songs.finder.entities.Song;
import mgorbachev.songs.finder.repositories.SongRepository;
import mgorbachev.songs.finder.service.DataLoaderService;
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

    @Autowired
    private DataLoaderService dataLoaderService;

    @Override
    public void run(String... strings) throws Exception {
//        populateSong();
//        populateAnotherSong();
        String url = this.getClass().getResource("/songDataset.csv").getPath();
        dataLoaderService.loadData(url);
    }

    private Song populateSong() {
        Person lars = new Person("Lars Ulrich");
        Person james = new Person("James Hetfield");
        List<Person> personList = newArrayList(lars, james);

        Song song = new Song("1", "One", personList, personList,
                newArrayList(new Artist("Metallica", Artist.ArtistType.GROUP)),
                newArrayList(new Album("And Justice for All", newArrayList("One"))));

        return repository.save(song);
    }

    private Song populateAnotherSong() {
        Person lars = new Person("Lars Ulrich");
        Person james = new Person("James Hetfield");
        List<Person> personList = newArrayList(lars, james);

        Song song = new Song("2", "Harvester of sorrow", personList, personList,
                newArrayList(new Artist("Metallica", Artist.ArtistType.GROUP)),
                newArrayList(new Album("And Justice for All", newArrayList("Harvester of sorrow"))));

        return repository.save(song);
    }

    public static void main(String[] args) {
        SpringApplication.run(SongsFinderApplication.class, args);
    }
}
