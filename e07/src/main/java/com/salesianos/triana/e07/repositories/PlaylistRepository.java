package com.salesianos.triana.e07.repositories;

import com.salesianos.triana.e07.models.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist,Long> {
}
