package ru.petrushin.ya.music.data;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import ru.petrushin.ya.music.data.cashe.ArtistCache;
import ru.petrushin.ya.music.data.exception.ArtistNotFoundException;
import ru.petrushin.ya.music.data.net.RestClient;
import ru.petrushin.ya.music.domain.Artist;
import ru.petrushin.ya.music.domain.repository.ArtistRepository;
import rx.Observable;

@Singleton public class ArtistRepositoryImpl implements ArtistRepository {
  private ArtistCache artistCache;
  private RestClient restClient;

  @Inject public ArtistRepositoryImpl(ArtistCache artistCache, RestClient restClient) {
    this.artistCache = artistCache;
    this.restClient = restClient;
  }

  @Override public Observable<List<Artist>> artists() {
    if (!artistCache.isExpired()) {
      return artistCache.getAllArtists();
    }
    return restClient.artists().doOnNext(artists -> artistCache.putArtists(artists));
  }

  @Override public Observable<Artist> artist(long artistId) {
    if (artistCache.isCashed(artistId)) {
      return artistCache.getArtist(artistId);
    } else {
      return Observable.error(new ArtistNotFoundException());
    }
  }
}
