package mgorbachev.songs.finder;

import mgorbachev.songs.finder.entities.Person;
import mgorbachev.songs.finder.entities.Song;
import mgorbachev.songs.finder.repositories.SongRepository;
import mgorbachev.songs.finder.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        Song song = new Song("1", "One", newArrayList(new Person("1","Lars Ulrich"), new Person("2", "James Hetfield")),
            newArrayList("Lars Ulrich", "James Hetfield"), newArrayList("Metallica"));
        repository.save(song);

    }

    public static void main(String[] args) {
        SpringApplication.run(SongsFinderApplication.class, args);
    }
}
