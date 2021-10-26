package com.salesianos.triana.e07.models;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
//@EntityListeners(AuditingEntityListener.class)
public class AddedTo implements Serializable {
    @Builder.Default
    @EmbeddedId
    private AddedToPK id = new AddedToPK();

   // @CreatedDate -->No funciona, se agrega la fecha por medio del servicio.
    private LocalDateTime datetime;

    //se le cambia el nombre order a orden, order es una palabra reservada en sql.
    //provocaba un error de sintaxis ;)
    private int orden;

    @ManyToOne
    @MapsId("song_id")
    @JoinColumn(name="song_id")
    private Song song;


    @ManyToOne
    @MapsId("playlist_id")
    @JoinColumn(name="playlist_id")
    private Playlist playlist;



    //Helpers

    public void addToSong(Song s) {
        song = s;
        s.getAddedToList().add(this);
    }

    public void removeFromSong(Song s) {
        s.getAddedToList().remove(this);
        song = null;
    }

    public void addToPlaylist(Playlist p) {
        playlist = p;
        p.getAddedToList().add(this);
    }

    public void removeFromPlaylist(Playlist p) {
        p.getAddedToList().remove(this);
        playlist = null;
    }

    public void addSongPlaylist(Song s , Playlist p) {
        addToSong(s);
        addToPlaylist(p);
    }

    public void removeSongPlaylist(Song s, Playlist p) {
        removeFromSong(s);
        removeFromPlaylist(p);
    }
}
