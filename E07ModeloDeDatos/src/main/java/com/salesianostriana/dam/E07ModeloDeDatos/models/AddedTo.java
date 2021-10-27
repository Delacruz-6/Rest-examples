package com.salesianostriana.dam.E07ModeloDeDatos.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddedTo implements Serializable {


    @Builder.Default
    @EmbeddedId
    private AddedToPK id = new AddedToPK();

    @ManyToOne
    @MapsId("playlist_id")
    @JoinColumn(name="playlist_id")
    private Playlist playlist;

    @ManyToOne
    @MapsId("song_id")
    @JoinColumn(name="song_id")
    private Song song;

    @OrderBy
    private LocalDate date;

    @JoinColumn(name="orden")
    private String order;


    /*
        HELPERS
     */

    public void addToSongs(Song a) {
        song = a;
        a.getAddedTos().add(this);
    }

    public void removeFromSong(Song a) {
        a.getAddedTos().remove(this);
        song = null;
    }

    public void addToPlaylist(Playlist a) {
        playlist = a;
        a.getAddedTos().add(this);
    }

    public void removeFromPlaylist(Playlist a) {
        a.getAddedTos().remove(this);
        playlist = null;
    }

    public void addPlaylistSong(Playlist playlist, Song song) {
        addToSongs(song);
        addToPlaylist(playlist);
    }

    public void removePlaylistSong(Playlist playlist, Song song) {
        removeFromSong(song);
        removeFromPlaylist(playlist);
    }



}