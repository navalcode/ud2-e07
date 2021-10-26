package com.salesianos.triana.e07.services;

import com.salesianos.triana.e07.models.AddedTo;
import com.salesianos.triana.e07.models.Playlist;
import com.salesianos.triana.e07.models.Song;
import com.salesianos.triana.e07.repositories.AddedToRepository;
import com.salesianos.triana.e07.services.base.BaseService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class AddedToService extends BaseService<AddedTo,Long, AddedToRepository> {

    public void addSongToPlaylist(Song s, Playlist p, PlaylistService ps, SongService ss) {
        //1. Crear una nueva linea de addedTo
        AddedTo a1 = AddedTo.builder()
                .datetime(LocalDateTime.now())
                .orden(this.findAll().size()+1)
                .build();

    a1.addSongPlaylist(s,p);
        save(a1);
        ps.edit(p);
        ss.edit(s);
        //2.
    }
}
