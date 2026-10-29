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

    public void addToSong(Song next) {
        java.util.Objects.requireNonNull(next, "song");
        requireSameMappedId(id.getSong_id(), next.getId());
        if (song != null && song != next) song.getAddedToList().removeIf(item -> item == this);
        song = next;
        if (next.getAddedToList().stream().noneMatch(item -> item == this)) next.getAddedToList().add(this);
    }

    public void removeFromSong(Song owner) {
        java.util.Objects.requireNonNull(owner, "song");
        owner.getAddedToList().removeIf(item -> item == this);
        if (song == owner) song = null;
    }

    public void addToPlaylist(Playlist next) {
        java.util.Objects.requireNonNull(next, "playlist");
        requireSameMappedId(id.getPlaylist_id(), next.getId());
        if (playlist != null && playlist != next) playlist.getAddedToList().removeIf(item -> item == this);
        playlist = next;
        if (next.getAddedToList().stream().noneMatch(item -> item == this)) next.getAddedToList().add(this);
    }

    public void removeFromPlaylist(Playlist owner) {
        java.util.Objects.requireNonNull(owner, "playlist");
        owner.getAddedToList().removeIf(item -> item == this);
        if (playlist == owner) playlist = null;
    }

    public void addSongPlaylist(Song nextSong, Playlist nextPlaylist) {
        java.util.Objects.requireNonNull(nextSong, "song");
        java.util.Objects.requireNonNull(nextPlaylist, "playlist");
        requireSameMappedId(id.getSong_id(), nextSong.getId());
        requireSameMappedId(id.getPlaylist_id(), nextPlaylist.getId());
        addToSong(nextSong);
        addToPlaylist(nextPlaylist);
    }

    public void removeSongPlaylist(Song ownerSong, Playlist ownerPlaylist) {
        java.util.Objects.requireNonNull(ownerSong, "song");
        java.util.Objects.requireNonNull(ownerPlaylist, "playlist");
        removeFromSong(ownerSong);
        removeFromPlaylist(ownerPlaylist);
    }

    private void requireSameMappedId(Long mappedId, Long nextId) {
        if (mappedId != null && !mappedId.equals(nextId)) {
            throw new IllegalStateException("A mapped membership ID cannot be reassigned; remove it and create a new link");
        }
    }
}
