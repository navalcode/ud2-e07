package com.salesianos.triana.e07.services;

import com.salesianos.triana.e07.models.Song;
import com.salesianos.triana.e07.repositories.SongRepository;
import com.salesianos.triana.e07.services.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class SongService extends BaseService<Song, Long, SongRepository> {
}
