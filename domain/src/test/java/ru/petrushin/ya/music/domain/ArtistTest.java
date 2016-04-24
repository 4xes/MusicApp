package ru.petrushin.ya.music.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArtistTest {

  private static final long FAKE_ARTIST_ID = 99L;

  private Artist artist;

  @Before public void setUp() {
    artist = new Artist(FAKE_ARTIST_ID);
  }

  @Test public void testUserConstructorHappyCase() {
    long artistId = artist.getArtistId();

    assertThat(artistId, is(FAKE_ARTIST_ID));
  }
}
