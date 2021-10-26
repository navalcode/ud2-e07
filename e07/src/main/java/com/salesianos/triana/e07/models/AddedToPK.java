package com.salesianos.triana.e07.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class AddedToPK implements Serializable {

    private Long song_id;
    private Long playlist_id;

}