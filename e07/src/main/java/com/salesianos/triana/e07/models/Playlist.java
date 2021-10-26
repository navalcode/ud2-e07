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

    @Builder.Default
    @OneToMany(mappedBy="playlist", fetch = FetchType.EAGER)
    private List<AddedTo> addedToList = new ArrayList<>();
}
