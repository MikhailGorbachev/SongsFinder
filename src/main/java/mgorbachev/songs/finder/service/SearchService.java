package mgorbachev.songs.finder.service;

import mgorbachev.songs.finder.entities.Album;
import mgorbachev.songs.finder.entities.Artist;
import mgorbachev.songs.finder.entities.Song;
import mgorbachev.songs.finder.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * Created by Mikhail_Gorbachev on 8/10/2017.
 */

@RestController
@RequestMapping("/search")
public class SearchService {

    @Autowired
    private SongRepository songRepository;


    //Design methods
    @RequestMapping("/artists/song/{songId}")
    public List<Artist> findArtistsBySong(@PathVariable String songId) {
        Song song = songRepository.findById(songId);
        List<Artist> artists = Collections.emptyList();
        if (song != null && !CollectionUtils.isEmpty(song.getArtists())) {
            artists = song.getArtists();
        }
        return artists;
    }


    public List<Artist> findGroupsByArtistName(String artistName) {
        //TODO TBD
        return null;
    }

    @RequestMapping("/albums/song/{songId}")
    public List<Album> findAlbumsBySong(@PathVariable String songId) {
        Song song = songRepository.findById(songId);
        List<Album> albums = Collections.emptyList();
        if (song != null && !CollectionUtils.isEmpty(song.getAlbums())) {
            albums = song.getAlbums();
        }
        return albums;
    }


    @RequestMapping("/songs/composer/{composerName}")
    public List<Song> findSongsByComposer(@PathVariable String composerName) {
        return songRepository.findSongsByComposer(composerName);
    }

    @RequestMapping("/songs/author/{authorName}")
    public List<Song> findSongsByAuthorName(@PathVariable String authorName) {
        return songRepository.findSongsByAuthor(authorName);
    }

    @RequestMapping("/songs/artist/{artistName}")
    public List<Song> findSongsByArtistName(@PathVariable String artistName) {
        return songRepository.findSongsByArtist(artistName);
    }

}
