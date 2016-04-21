package ru.petrushin.ya.data.cashe.realm.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ArtistRealm extends RealmObject {

  @PrimaryKey private long id;
  private String name;
  private String[] genres;
  private int tracks;
  private int albums;
  private String link;
  private String description;
  private CoverRealm cover;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String[] getGenres() {
    return genres;
  }

  public void setGenres(String[] genres) {
    this.genres = genres;
  }

  public int getTracks() {
    return tracks;
  }

  public void setTracks(int tracks) {
    this.tracks = tracks;
  }

  public int getAlbums() {
    return albums;
  }

  public void setAlbums(int albums) {
    this.albums = albums;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public CoverRealm getCover() {
    return cover;
  }

  public void setCover(CoverRealm cover) {
    this.cover = cover;
  }
}
