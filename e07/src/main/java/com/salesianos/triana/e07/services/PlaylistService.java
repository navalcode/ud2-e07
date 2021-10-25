package com.salesianos.triana.e07.services;

import com.salesianos.triana.e07.models.Playlist;
import com.salesianos.triana.e07.repositories.PlaylistRepository;
import com.salesianos.triana.e07.services.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class PlaylistService extends BaseService<Playlist,Long, PlaylistRepository> {
}
