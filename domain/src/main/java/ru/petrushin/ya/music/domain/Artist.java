package ru.petrushin.ya.music.domain;

import java.util.List;

public class Artist {
  private final long artistId;

  public Artist(long artistId) {
    this.artistId = artistId;
  }

  private String name;
  private List<String> genres;
  private int tracks;
  private int albums;
  private String link;
  private String description;
  private Cover cover;

  public class Cover {
    private String small;
    private String big;

    public String getSmall() {
      return small;
    }

    public void setSmall(String small) {
      this.small = small;
    }

    public String getBig() {
      return big;
    }

    public void setBig(String big) {
      this.big = big;
    }
  }

  public long getArtistId() {
    return artistId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getGenres() {
    return genres;
  }

  public void setGenres(List<String> genres) {
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
