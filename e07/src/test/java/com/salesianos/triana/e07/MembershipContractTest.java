package com.salesianos.triana.e07;

import com.salesianos.triana.e07.models.*;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
class MembershipContractTest {
    AddedToPK key(){AddedToPK key=new AddedToPK();key.setSong_id(1000L);key.setPlaylist_id(2000L);return key;}
    @Test void compositeKeyHasValueEqualityAndHashing(){assertEquals(key(),key());assertEquals(key().hashCode(),key().hashCode());assertEquals(1,new HashSet<>(List.of(key(),key())).size());}
    @Test void repeatedAttachAndTransferKeepBothSidesConsistent(){AddedTo link=new AddedTo();Song old=new Song(),next=new Song();Playlist first=new Playlist(),second=new Playlist();link.addSongPlaylist(old,first);link.addSongPlaylist(next,second);link.addSongPlaylist(next,second);assertTrue(old.getAddedToList().isEmpty());assertTrue(first.getAddedToList().isEmpty());assertEquals(1,next.getAddedToList().size());assertEquals(1,second.getAddedToList().size());assertSame(next,link.getSong());assertSame(second,link.getPlaylist());}
    @Test void wrongOwnerRemovalKeepsActualLinks(){AddedTo link=new AddedTo();Song song=new Song();Playlist list=new Playlist();link.addSongPlaylist(song,list);link.removeSongPlaylist(new Song(),new Playlist());assertSame(song,link.getSong());assertSame(list,link.getPlaylist());}
    @Test void nullPlaylistDoesNotPartiallyAttachSong(){AddedTo link=new AddedTo();Song song=new Song();assertThrows(NullPointerException.class,()->link.addSongPlaylist(song,null));assertNull(link.getSong());assertTrue(song.getAddedToList().isEmpty());}
    @Test void mappedCompositeIdentityCannotMoveToAnotherSong(){Song first=new Song(),next=new Song();first.setId(1000L);next.setId(1001L);Playlist list=new Playlist();list.setId(2000L);AddedTo link=new AddedTo();link.setId(key());link.addSongPlaylist(first,list);assertThrows(IllegalStateException.class,()->link.addSongPlaylist(next,list));assertSame(first,link.getSong());assertTrue(next.getAddedToList().isEmpty());assertEquals(1,first.getAddedToList().size());}
}
