package com.salesianos.triana.e07.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Playlist implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Lob
    private String description;

    @ManyToMany(mappedBy = "playlists" )
    private List<Song> songs = new ArrayList<>();

    //Helpers
    public void addSong(Song s) {
        if (this.getSongs() == null)
            this.setSongs(new ArrayList<>());
        this.getSongs().add(s);

        if (s.getPlaylists() == null)
            s.setPlaylists(new ArrayList<>());
        s.getPlaylists().add(this);
    }

    public void removeSong(Song s) {
        s.getPlaylists().remove(this);
        this.getSongs().remove(s);
    }
}
