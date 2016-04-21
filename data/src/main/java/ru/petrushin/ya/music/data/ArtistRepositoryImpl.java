package ru.petrushin.ya.music.data;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import ru.petrushin.ya.music.data.cashe.ArtistsCache;
import ru.petrushin.ya.music.data.net.RestClient;
import ru.petrushin.ya.music.domain.Artist;
import ru.petrushin.ya.music.domain.repository.ArtistRepository;
import rx.Observable;

@Singleton public class ArtistRepositoryImpl implements ArtistRepository {
  private ArtistsCache artistsCache;
  private RestClient restClient;

  @Inject
  public ArtistRepositoryImpl(ArtistsCache artistsCache, RestClient restClient) {
    this.artistsCache = artistsCache;
    this.restClient = restClient;
  }

  @Override public Observable<List<Artist>> artists() {
    if (!artistsCache.isExpired()) {
      return artistsCache.getAllArtists();
    }
    return restClient.artists().doOnNext(artists -> artistsCache.putArtists(artists));
  }

  @Override public Observable<Artist> artist(long artistId) {
    return artistsCache.getArtist(artistId);
  }
}
