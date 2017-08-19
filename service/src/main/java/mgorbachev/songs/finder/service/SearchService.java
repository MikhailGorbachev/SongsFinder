package mgorbachev.songs.finder.service;

import mgorbachev.songs.finder.entities.Album;
import mgorbachev.songs.finder.entities.Artist;
import mgorbachev.songs.finder.entities.Song;
import mgorbachev.songs.finder.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mikhail_Gorbachev on 8/10/2017.
 */

@RestController
@RequestMapping("/search")
//@CrossOrigin(origins = "*")
public class SearchService {

    @Autowired
    private SongRepository songRepository;


    /**
     * Find artists that performed song
     * @param songName the name of the song
     * @return list of artists that perfomed current song
     */
    @RequestMapping("/artists/song/{songName}")
    public List<Artist> findArtistsBySong(@PathVariable String songName) {
        List<Song> songs = songRepository.findSongsByName(songName);
        List<Artist> artists = Collections.emptyList();
        if (songs != null) {
            artists = songs.stream()
                    .flatMap(song -> song.getArtists().stream())
                    .distinct()
                    .collect(Collectors.toList());
        }
        return artists;
    }

    /**
     * Find albums that contain the song
     * @param songName the name of the song
     * @return list of albums containing the song
     */
    @RequestMapping("/albums/song/{songName}")
    public List<Album> findAlbumsBySong(@PathVariable String songName) {
        List<Song> songs = songRepository.findSongsByName(songName);
        List<Album> albums = Collections.emptyList();
        if (songs != null) {
            albums = songs.stream()
                    .flatMap(song -> song.getAlbums().stream())
                    .distinct()
                    .collect(Collectors.toList());
        }

        return albums;
    }

    /**
     * Find songs that were written by composer
     * @param composerName the name of composer
     * @return list of the songs
     */
    @RequestMapping("/songs/composer/{composerName}")
    public List<Song> findSongsByComposer(@PathVariable String composerName) {
        return songRepository.findSongsByComposer(composerName);
    }

    /**
     * Find songs with words that were written by author
     * @param authorName the name of the author
     * @return list of the songs
     */
    @RequestMapping("/songs/author/{authorName}")
    public List<Song> findSongsByAuthorName(@PathVariable String authorName) {
        return songRepository.findSongsByAuthor(authorName);
    }

    /**
     * Find songs that were perfomed by group or separate musicians
     * @param artistName the name of group or it could be name of musician
     * @return list of the songs
     */
    @RequestMapping("/songs/artist/{artistName}")
    public List<Song> findSongsByArtistName(@PathVariable String artistName) {
        return songRepository.findSongsByArtist(artistName);
    }

    /**
     * Find songs by starting of the name. There could be specified only part of song name.
     * @param songNamePart beginning of the song name
     * @return list of the songs
     */
    @RequestMapping("/songs/{songNamePart}")
    public List<Song> findSongsByName(@PathVariable String songNamePart) {
        return songRepository.findSongsByName(songNamePart);
    }

    /**
     * Find goups where played specified musician
     * @param artistName name of musician
     * @return list of the artists
     */
    @RequestMapping("/groups/artist/{artistName}")
    public List<Artist> findGroupsByArtistName(@PathVariable String artistName) {
        return songRepository.findSongsByArtist(artistName).stream()
                .flatMap(song -> song.getArtists().stream())
                .filter(artist -> artist.getType() == Artist.ArtistType.GROUP)
                .distinct()
                .collect(Collectors.toList());
    }

}
