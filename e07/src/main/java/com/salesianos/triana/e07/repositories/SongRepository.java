package com.salesianos.triana.e07.repositories;

import com.salesianos.triana.e07.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song,Long> {
}
