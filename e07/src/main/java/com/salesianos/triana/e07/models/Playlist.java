package com.salesianos.triana.e07.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

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

    private String description;

    @Embedded
    @EmbeddedId
    private AddedTo addedTo;
}
