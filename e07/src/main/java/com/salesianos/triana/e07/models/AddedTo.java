package com.salesianos.triana.e07.models;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AddedTo implements Serializable {
    @Id
    private Long id;

    private String datetime;

    //se le cambia el nombre order a orden, order es una palabra reservada en sql.
    //provocaba un error de sintaxis ;)
    private int orden;
}
