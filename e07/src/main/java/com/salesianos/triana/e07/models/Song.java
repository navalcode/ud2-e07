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
public class Song implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    //Se implementa unidireccionalmente la asociación con Artist
    @ManyToOne
    @JoinColumn(name = "artist_id", foreignKey = @ForeignKey(name = "FK_ARTIST_SONG"))
    private Artist artist;

    private String album;

    private String year;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "song_id"),foreignKey = @ForeignKey(name = "FK_SONG_PLAYLIST"))
    private List<Playlist> playlists= new ArrayList<>();

    @Builder.Default
    @Embedded
    private List<AddedTo> addedTo= new ArrayList<>();

}
