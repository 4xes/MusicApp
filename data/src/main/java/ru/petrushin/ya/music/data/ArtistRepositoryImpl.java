package ru.petrushin.ya.music.data;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import ru.petrushin.ya.music.data.cashe.ArtistCache;
import ru.petrushin.ya.music.data.exception.ArtistNotFoundException;
import ru.petrushin.ya.music.data.net.ArtistRestClient;
import ru.petrushin.ya.music.domain.Artist;
import ru.petrushin.ya.music.domain.repository.ArtistRepository;
import rx.Observable;

@Singleton public class ArtistRepositoryImpl implements ArtistRepository {

  private ArtistCache artistCache;
  private ArtistRestClient artistRestClient;

  @Inject public ArtistRepositoryImpl(ArtistCache artistCache, ArtistRestClient artistRestClient) {
    this.artistCache = artistCache;
    this.artistRestClient = artistRestClient;
  }

  @Override public Observable<List<Artist>> artists() {
    if (!artistCache.isExpired()) {
      return artistCache.getAllArtists();
    }
    return artistRestClient.artists().doOnNext(artists -> {
        artistCache.clearArtists();
        artistCache.putArtists(artists);
    });
  }

  @Override public Observable<Artist> artist(long artistId) {
    if (artistCache.isCashed(artistId)) {
      return artistCache.getArtist(artistId);
    } else {
      return Observable.error(new ArtistNotFoundException());
    }
  }
}
