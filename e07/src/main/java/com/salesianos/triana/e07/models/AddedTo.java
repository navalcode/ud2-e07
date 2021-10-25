package com.salesianos.triana.e07.models;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import java.util.Date;

@Embeddable
public class AddedTo {
    @Id
    private Long id;

    private String datetime;

    private String order;
}
