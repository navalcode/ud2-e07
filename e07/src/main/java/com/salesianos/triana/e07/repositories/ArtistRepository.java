package com.salesianos.triana.e07.repositories;

import com.salesianos.triana.e07.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist,Long> {
}
