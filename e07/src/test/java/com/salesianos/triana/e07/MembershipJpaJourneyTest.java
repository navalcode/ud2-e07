package com.salesianos.triana.e07;

import com.salesianos.triana.e07.models.*;
import com.salesianos.triana.e07.repositories.*;
import com.salesianos.triana.e07.services.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(properties="spring.datasource.url=jdbc:h2:mem:membership_review;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE")
@Transactional
class MembershipJpaJourneyTest {
    @Autowired AddedToService service; @Autowired PlaylistService playlists; @Autowired SongService songs;
    @Autowired AddedToRepository links; @Autowired EntityManager em;
    Song song;Playlist list;
    @BeforeEach void fixture(){song=songs.save(Song.builder().title("Fictional song").build());list=playlists.save(Playlist.builder().name("Fictional list").build());}
    AddedTo saved(){return links.findAll().stream().filter(x->x.getSong().getId().equals(song.getId())&&x.getPlaylist().getId().equals(list.getId())).findFirst().orElseThrow();}
    @Test void firstPositionIsLocalToEachPlaylist(){Playlist other=playlists.save(Playlist.builder().name("Other list").build());service.addSongToPlaylist(song,other,playlists,songs);service.addSongToPlaylist(song,list,playlists,songs);assertEquals(1,saved().getOrden());assertEquals(1,links.findFirstByPlaylist_IdOrderByOrdenDesc(other.getId()).orElseThrow().getOrden());}
    @Test void duplicateAddPreservesOriginalTimestampAndPosition(){service.addSongToPlaylist(song,list,playlists,songs);em.flush();AddedTo first=saved();var time=first.getDatetime();int order=first.getOrden();service.addSongToPlaylist(song,list,playlists,songs);em.flush();em.clear();AddedTo after=saved();assertEquals(time,after.getDatetime());assertEquals(order,after.getOrden());assertEquals(1,links.findAll().stream().filter(x->x.getSong().getId().equals(song.getId())&&x.getPlaylist().getId().equals(list.getId())).count());}
    @Test void newPositionUsesLargestRemainingPosition(){service.addSongToPlaylist(song,list,playlists,songs);AddedTo first=saved();first.setOrden(7);links.saveAndFlush(first);Song next=songs.save(Song.builder().title("Second song").build());service.addSongToPlaylist(next,list,playlists,songs);em.flush();em.clear();assertEquals(8,links.findAll().stream().filter(x->x.getSong().getId().equals(next.getId())).findFirst().orElseThrow().getOrden());}
    @Test void equalCompositeIdFindsAndRemovesPersistedLink(){service.addSongToPlaylist(song,list,playlists,songs);em.flush();AddedToPK id=new AddedToPK();id.setSong_id(song.getId());id.setPlaylist_id(list.getId());em.clear();AddedTo loaded=service.findById(id).orElseThrow();assertEquals(id,loaded.getId());service.deleteById(id);em.flush();em.clear();assertTrue(links.findById(id).isEmpty());assertNotNull(em.find(Song.class,song.getId()));assertNotNull(em.find(Playlist.class,list.getId()));}
    @Test void unsavedSongFailsBeforeAnyMembershipWrite(){long before=links.count();assertThrows(NullPointerException.class,()->service.addSongToPlaylist(new Song(),list));assertEquals(before,links.count());}
}
