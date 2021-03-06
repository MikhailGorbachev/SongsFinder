package mgorbachev.songs.finder;

import mgorbachev.songs.finder.entities.Song;
import mgorbachev.songs.finder.repositories.SongRepository;
import mgorbachev.songs.finder.service.DataLoaderService;
import mgorbachev.songs.finder.service.SearchService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.io.FileOutputStream;
import java.io.IOException;

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


    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Override
    public void run(String... strings) throws Exception {
        clear();
       String datasetPath = prepareDataset();
        dataLoaderService.loadData(datasetPath);
    }

    private String prepareDataset() throws IOException {
        String copyPath = "./songDataset.csv";
        FileOutputStream fout = new FileOutputStream(copyPath);
        try {
            IOUtils.copy(this.getClass().getResourceAsStream("/songDataset.csv"), fout);
        } finally {
            fout.close();
        }

        return copyPath;
    }

    private void clear() {
        esTemplate.deleteIndex(Song.class);
        esTemplate.createIndex(Song.class);
        esTemplate.putMapping(Song.class);
        esTemplate.refresh(Song.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SongsFinderApplication.class, args);
    }
}
