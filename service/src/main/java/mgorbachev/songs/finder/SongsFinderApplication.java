package mgorbachev.songs.finder;

import mgorbachev.songs.finder.repositories.SongRepository;
import mgorbachev.songs.finder.service.DataLoaderService;
import mgorbachev.songs.finder.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        dataLoaderService.loadData(this.getClass().getResource("/songDataset.csv").toURI());
    }

    public static void main(String[] args) {
        SpringApplication.run(SongsFinderApplication.class, args);
    }
}
