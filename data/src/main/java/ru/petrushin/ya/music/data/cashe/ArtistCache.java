package ru.petrushin.ya.music.data.cashe;

import java.util.List;
import ru.petrushin.ya.music.domain.Artist;
import rx.Observable;

public interface ArtistCache {
  /**
   * Gets an {@link rx.Observable} which will emit a {@link Artist}.
   *
   * @param artistId The user id to retrieve data.
   */
  Observable<Artist> getArtist(final long artistId);

  /**
   * Gets an {@link rx.Observable} which will emits a elements of {@link Artist}.
   */
  Observable<List<Artist>> getAllArtists();

  /**
   * Puts elements into the cache.
   *
   * @param artistEntities to insert in the cache.
   */
  void putArtists(List<Artist> artistEntities);

  /**
   * Puts {@link Artist} into cache.
   *
   * @param artistEntity to insert the cache.
   */
  void putArtist(Artist artistEntity);

  /**
   * Checks if an element (Artist) exists in the cache.
   *
   * @param artistId The id used to look for inside the cache.
   * @return true if the element is cached, otherwise false.
   */
  boolean isCashed(final long artistId);

  boolean isExpired();

  /**
   * Clear cache
   */
  void clearArtists();
}
