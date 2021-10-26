package com.salesianos.triana.e07;

import com.salesianos.triana.e07.models.Artist;
import com.salesianos.triana.e07.models.Playlist;
import com.salesianos.triana.e07.models.Song;
import com.salesianos.triana.e07.services.AddedToService;
import com.salesianos.triana.e07.services.ArtistService;
import com.salesianos.triana.e07.services.PlaylistService;
import com.salesianos.triana.e07.services.SongService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class MainDePrueba {
    private final ArtistService artistService;
    private final SongService songService;
    private final PlaylistService playlistService;
    private final AddedToService addedToService;

    @PostConstruct
    public void tests(){
        Artist a1 = Artist.builder()
                .name("Aretha Franklin")
                .build();

        artistService.save(a1);

        Song s1 = Song.builder()
                .title("Natural Woman")
                .artist(a1)
                .album("Lady Soul")
                .year("1968")
                .build();

        songService.save(s1);

        Song s2 = Song.builder()
                .title("I Say a Little Prayer")
                .artist(a1)
                .album("Aretha Now")
                .year("1968")
                .build();

        songService.save(s2);

        Playlist p1 = Playlist.builder()
                .name("Clasicos del soul")
                .description("Esta lista auna los clasicos de toda la vida")
                .build();

        playlistService.save(p1);

        addedToService.addSongToPlaylist(s1,p1,playlistService,songService);
        addedToService.addSongToPlaylist(s2,p1,playlistService,songService);


    }

}
