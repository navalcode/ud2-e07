package com.salesianos.triana.e07.services;

import com.salesianos.triana.e07.models.*;
import com.salesianos.triana.e07.repositories.*;
import com.salesianos.triana.e07.services.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class AddedToService extends BaseService<AddedTo, AddedToPK, AddedToRepository> {
    private final SongRepository songs;
    private final PlaylistRepository playlists;

    public AddedTo addSongToPlaylist(Song song, Playlist playlist) {
        Objects.requireNonNull(song, "song");
        Objects.requireNonNull(playlist, "playlist");
        Objects.requireNonNull(song.getId(), "saved song ID");
        Objects.requireNonNull(playlist.getId(), "saved playlist ID");
        Song managedSong = songs.findById(song.getId()).orElseThrow();
        Playlist managedPlaylist = playlists.findById(playlist.getId()).orElseThrow();
        AddedToPK key = new AddedToPK(song.getId(), playlist.getId());
        return repositorio.findById(key).orElseGet(() -> {
            int position = repositorio.findFirstByPlaylist_IdOrderByOrdenDesc(playlist.getId())
                    .map(link -> Math.addExact(link.getOrden(), 1)).orElse(1);
            AddedTo link = AddedTo.builder().id(key).datetime(LocalDateTime.now()).orden(position).build();
            link.addSongPlaylist(managedSong, managedPlaylist);
            return repositorio.save(link);
        });
    }

    // Retains the original coursework call signature; persistence is owned by this transaction.
    public void addSongToPlaylist(Song song, Playlist playlist, PlaylistService ps, SongService ss) {
        addSongToPlaylist(song, playlist);
    }
}
