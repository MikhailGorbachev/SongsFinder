package mgorbachev.songs.finder.service;

import mgorbachev.songs.finder.entities.Album;
import mgorbachev.songs.finder.entities.Artist;
import mgorbachev.songs.finder.entities.Person;
import mgorbachev.songs.finder.entities.Song;
import mgorbachev.songs.finder.repositories.SongRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataLoaderService {
    @Autowired
    private SongRepository songRepository;

    public void loadData(String path) throws IOException {

        Files.lines(Paths.get(path)).skip(1).flatMap(str -> {
                    String[] values = str.split(",");
                    String authors = "";
                    String composers = "";
                    String albums = "";
                    String artists = "";
                    String groups = "";
                    if (values.length == 7 || values.length == 6) {
                        authors = values[2];
                        composers = values[3];
                        albums = values[4];
                        artists = values[5];

                        if (values.length == 7) {
                            groups = values[6];
                        }

                        return Stream.of(new Song(values[1], values[0], parsePersonList(authors), parsePersonList(composers),
                                parseArtists(artists, groups), parseAlbumList(albums)));
                    } else {
                        return Stream.empty();
                    }
                }
        ).forEach(song -> songRepository.save(song));


    }

    private List<Person> parsePersonList(String personStr) {
        return Arrays.stream(personStr.split("\\|"))
                .map(personRaw -> new Person(personRaw))
                .collect(Collectors.toList());
    }

    private List<Album> parseAlbumList(String albumStr) {
        return Arrays.stream(albumStr.split("\\|"))
                .map(albumRaw -> new Album(albumRaw))
                .collect(Collectors.toList());

    }

    private List<Artist> parseArtists(String artists, String groups) {
        List<Artist> singleArtists = Arrays.stream(artists.split("\\|"))
                .map(artistRaw -> new Artist(artistRaw, Artist.ArtistType.SINGLE))
                .collect(Collectors.toList());
        if (StringUtils.isNotBlank(groups)) {
            List<Artist> groupArtists = Arrays.stream(groups.split("\\|"))
                    .map(groupRaw -> new Artist(groupRaw, Artist.ArtistType.GROUP))
                    .collect(Collectors.toList());
            singleArtists.addAll(groupArtists);
        }

        return singleArtists;

    }

}
