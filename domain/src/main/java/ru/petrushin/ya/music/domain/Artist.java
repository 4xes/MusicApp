package ru.petrushin.ya.music.domain;

public class Artist {
  private final long id;

  public Artist(long id) {
    this.id = id;
  }

  private String name;
  private String[] genres;
  private int tracks;
  private int albums;
  private String link;
  private String description;
  private Cover cover;

  public long getArtistId() {
    return id;
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

  public Cover getCover() {
    return cover;
  }

  public void setCover(Cover cover) {
    this.cover = cover;
  }
}
