package com.salesianos.triana.e07.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Artist implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

}
