package ru.petrushin.ya.music.data.net;

import java.util.List;
import ru.petrushin.ya.music.domain.Artist;
import rx.Observable;

/**
 * For retrieving data from the network
 */
public interface ArtistRestClient {
  /**
   * Retrieves an {@link rx.Observable} which will emit a List of {@link Artist}.
   */
  Observable<List<Artist>> artists();
}
