package com.salesianos.triana.e07.repositories;

import com.salesianos.triana.e07.models.AddedTo;
import com.salesianos.triana.e07.models.AddedToPK;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddedToRepository extends JpaRepository<AddedTo,AddedToPK> {
    Optional<AddedTo> findFirstByPlaylist_IdOrderByOrdenDesc(Long playlistId);
}
