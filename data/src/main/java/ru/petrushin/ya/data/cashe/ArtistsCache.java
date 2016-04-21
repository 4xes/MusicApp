package ru.petrushin.ya.data.cashe;

import java.util.List;
import ru.petrushin.ya.music.domain.Artist;
import rx.Observable;

public interface ArtistsCache {
  Observable<Artist> getArtist(final long artistId);

  Observable<List<Artist>> getAllArtists();

  void putArtists(List<Artist> artistEntities);

  /**
   * Puts {@link Artist} into cache.
   *
   * @param artistEntity to insert the cache.
   */
  void putArtist(Artist artistEntity);

  boolean isCashed(final int artistId);

  boolean isExpired();

  /**
   * Clear cache
   */
  void clearArtists();
}
