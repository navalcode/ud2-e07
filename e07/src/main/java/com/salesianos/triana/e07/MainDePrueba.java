package com.salesianos.triana.e07;

import com.salesianos.triana.e07.models.Artist;
import com.salesianos.triana.e07.models.Playlist;
import com.salesianos.triana.e07.models.Song;
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

        Playlist p1 = Playlist.builder()
                .name("Clasicos del soul")
                .description("Esta lista auna los clasicos de toda la vida")
                .build();

        playlistService.save(p1);

        p1.addSong(s1);
        songService.edit(s1);
        playlistService.edit(p1);

        /*El método addsongToPlaylist de SongService provoca desbordamiento de pila.
         * Posibles mejoras/soluciones:
         *
         * 1. El tratamiento de los atributos extra es defectuoso en su diseño
         * tal vez no debería ser una clase Embeddeable y si una entidad con su respectivos repositorio
         * y servicio.
         *
         * 2. Si el diseño se realiza de otra forma tendría más sentido que el método addSongToPlaylist estuviera
         * en el servicio de AddTo.
         *
         * 3. El verdadero fallo se produce en los helpers.
         * */

      //  songService.addSongToPlaylist(s1,p1,playlistService);

    }

}
