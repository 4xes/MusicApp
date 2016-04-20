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


  public class Cover{
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
}
