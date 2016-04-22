package ru.petrushin.ya.music.presentation.view.model;

public class ArtistModel {
  private final long artistId;
  private String name;
  private String genres;
  private int tracks;
  private int albums;
  private String link;
  private String description;
  private String coverSmall;
  private String coverBig;

  public ArtistModel(long artistId) {
    this.artistId = artistId;
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

  public String getGenres() {
    return genres;
  }

  public void setGenres(String genres) {
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

  public String getCoverSmall() {
    return coverSmall;
  }

  public void setCoverSmall(String coverSmall) {
    this.coverSmall = coverSmall;
  }

  public String getCoverBig() {
    return coverBig;
  }

  public void setCoverBig(String coverBig) {
    this.coverBig = coverBig;
  }

  @Override public String toString() {
    return "ArtistModel{" +
        "artistId=" + artistId +
        ", name='" + name + '\'' +
        ", genres='" + genres + '\'' +
        ", tracks=" + tracks +
        ", albums=" + albums +
        ", link='" + link + '\'' +
        ", description='" + description + '\'' +
        ", coverSmall='" + coverSmall + '\'' +
        ", coverBig='" + coverBig + '\'' +
        '}';
  }
}
