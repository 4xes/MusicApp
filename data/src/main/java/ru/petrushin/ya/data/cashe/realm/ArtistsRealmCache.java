package ru.petrushin.ya.data.cashe.realm;

import android.content.Context;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import ru.petrushin.ya.data.cashe.ArtistsCache;
import ru.petrushin.ya.data.cashe.realm.model.ArtistRealm;
import ru.petrushin.ya.data.cashe.realm.model.CoverRealm;
import ru.petrushin.ya.data.cashe.realm.model.ExpiredRealm;
import ru.petrushin.ya.music.domain.Artist;
import ru.petrushin.ya.music.domain.Cover;
import rx.Observable;

@Singleton public class ArtistsRealmCache implements ArtistsCache {

  private static final long EXPIRATION_TIME = 60 * 10 * 1000;
  private static final String ARTISTS_NAME = "artists.realm";
  private static final String EXPIRED_KEY = "artist_expired";
  private final Realm realm;

  @Inject public ArtistsRealmCache(Context context) {
    initConfig(context);
    realm = Realm.getDefaultInstance();
  }

  public void initConfig(Context context) {
    RealmConfiguration config = new RealmConfiguration.Builder(context).name(ARTISTS_NAME)
        .deleteRealmIfMigrationNeeded()
        .build();
    Realm.setDefaultConfiguration(config);
  }

  @Override public Observable<Artist> getArtist(long artistId) {
    return Observable.just(
        mapToObject(realm.where(ArtistRealm.class).equalTo("id", artistId).findFirst()));
  }

  @Override public Observable<List<Artist>> getAllArtists() {
    List<Artist> artists = new ArrayList<>();
    RealmResults<ArtistRealm> result = realm.where(ArtistRealm.class).findAll();
    for (int i = 0; i < result.size(); i++) {
      ArtistRealm artistRealm = result.get(i);
      artists.add(mapToObject(artistRealm));
    }
    return Observable.just(artists);
  }

  @Override public void putArtists(List<Artist> artists) {
    realm.beginTransaction();
    for (Artist artist : artists) {
      mapToRealm(artist);
    }
    ExpiredRealm expiredRealm = realm.createObject(ExpiredRealm.class);
    expiredRealm.setKey(EXPIRED_KEY);
    expiredRealm.setUpdatedTime(System.currentTimeMillis());
    realm.commitTransaction();
  }

  @Override public void putArtist(Artist artist) {
    realm.beginTransaction();
    mapToRealm(artist);
    realm.commitTransaction();
  }

  private ArtistRealm mapToRealm(Artist artist) {
    ArtistRealm artistRealm = realm.createObject(ArtistRealm.class);
    artistRealm.setId(artist.getArtistId());
    artistRealm.setName(artist.getName());
    artistRealm.setDescription(artist.getDescription());
    artistRealm.setLink(artist.getLink());
    artistRealm.setAlbums(artist.getAlbums());
    artistRealm.setTracks(artist.getTracks());
    if (artist.getCover() != null) {
      CoverRealm coverRealm = realm.createObject(CoverRealm.class);
      coverRealm.setSmall(artist.getCover().getSmall());
      coverRealm.setBig(artist.getCover().getBig());
      artistRealm.setCover(coverRealm);
    }
    return artistRealm;
  }

  private Artist mapToObject(ArtistRealm artistRealm) {
    Artist artist = new Artist(artistRealm.getId());
    artist.setName(artistRealm.getName());

    artist.setDescription(artistRealm.getDescription());
    artist.setLink(artistRealm.getLink());
    artist.setAlbums(artistRealm.getAlbums());
    artist.setTracks(artistRealm.getTracks());

    if (artistRealm.getCover() != null) {
      Cover cover = new Cover();
      cover.setSmall(artistRealm.getCover().getSmall());
      cover.setBig(artistRealm.getCover().getBig());
      artist.setCover(cover);
    }
    return artist;
  }

  @Override public boolean isCashed(int artistId) {
    ArtistRealm artistRealm = realm.where(ArtistRealm.class).equalTo("id", artistId).findFirst();
    return artistRealm != null;
  }

  @Override public boolean isExpired() {
    ExpiredRealm expiredRealm =
        realm.where(ExpiredRealm.class).equalTo("key", EXPIRED_KEY).findFirst();
    return expiredRealm == null
        || (expiredRealm.getUpdatedTime() + EXPIRATION_TIME) < System.currentTimeMillis();
  }

  @Override public void clearArtists() {

  }
}
