package com.salesianos.triana.e07.services;

import com.salesianos.triana.e07.models.AddedTo;
import com.salesianos.triana.e07.models.Playlist;
import com.salesianos.triana.e07.models.Song;
import com.salesianos.triana.e07.repositories.SongRepository;
import com.salesianos.triana.e07.services.base.BaseService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SongService extends BaseService<Song, Long, SongRepository> {

public void addSongToPlaylist (Song s, Playlist p,PlaylistService ps){
    //Se agrega la canción a la playlist con el helper de playlist
    p.addSong(s);

    //Se crea un objeto del tipo addedTo con la fecha y hora de la inserción y con el indice
    //de la playlist en el que se guarda la canción(suponiendo que eso es el orden).
    AddedTo a = AddedTo
            .builder()
            .datetime(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()))
            .orden(p.getSongs().indexOf(s)).build();

    //Se agrega al la lista de AddedTo embebida en Song.
     s.getAddedTo().add(a);

     //Se edita la información de los repositorios por medio de los servicios.
     ps.edit(p);
     //Song puede usar el método directamente, lo hereda este servicio de BaseService
     edit(s);

}
}
