package com.salesianos.triana.e07.services;

import com.salesianos.triana.e07.models.Artist;
import com.salesianos.triana.e07.repositories.ArtistRepository;
import com.salesianos.triana.e07.services.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ArtistService extends BaseService<Artist,Long, ArtistRepository> {
}
