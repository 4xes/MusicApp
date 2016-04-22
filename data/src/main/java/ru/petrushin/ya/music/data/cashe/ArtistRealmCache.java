package ru.petrushin.ya.music.data.cashe;

import android.content.Context;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import ru.petrushin.ya.music.data.cashe.realm.model.ArtistRealm;
import ru.petrushin.ya.music.data.cashe.realm.model.CoverRealm;
import ru.petrushin.ya.music.data.cashe.realm.model.ExpiredRealm;
import ru.petrushin.ya.music.data.cashe.realm.model.RealmString;
import ru.petrushin.ya.music.domain.Artist;
import ru.petrushin.ya.music.domain.Cover;
import rx.Observable;

@Singleton public class ArtistRealmCache implements ArtistCache {

  private static final long EXPIRATION_TIME = 120 * 10 * 1000;
  private static final String ARTISTS_NAME = "artists.realm";
  private static final String EXPIRED_KEY = "artist_expired";

  @Inject public ArtistRealmCache(Context context) {
    initConfig(context);
  }

  public void initConfig(Context context) {
    RealmConfiguration config = new RealmConfiguration.Builder(context).name(ARTISTS_NAME)
        .deleteRealmIfMigrationNeeded()
        .build();
    Realm.setDefaultConfiguration(config);
  }

  @Override public Observable<Artist> getArtist(long artistId) {
    Realm realm = Realm.getDefaultInstance();
    Artist artist = mapToObject(realm.where(ArtistRealm.class).equalTo("id", artistId).findFirst());
    realm.close();
    return Observable.just(artist);
  }

  @Override public Observable<List<Artist>> getAllArtists() {
    Realm realm = Realm.getDefaultInstance();
    List<Artist> artists = new ArrayList<>();
    RealmResults<ArtistRealm> result = realm.where(ArtistRealm.class).findAll();
    for (int i = 0; i < result.size(); i++) {
      ArtistRealm artistRealm = result.get(i);
      artists.add(mapToObject(artistRealm));
    }
    realm.close();
    return Observable.just(artists);
  }

  @Override public void putArtists(List<Artist> artists) {
    Realm realm = Realm.getDefaultInstance();
    realm.beginTransaction();
    for (Artist artist : artists) {
      mapToRealm(realm, artist);

    }
    ExpiredRealm expiredRealm = realm.createObject(ExpiredRealm.class);
    expiredRealm.setKey(EXPIRED_KEY);
    expiredRealm.setUpdatedTime(System.currentTimeMillis());
    realm.commitTransaction();
    realm.close();
  }

  private void putArtist(Realm realm, Artist artist) {
    realm.beginTransaction();
    mapToRealm(realm, artist);
    realm.commitTransaction();
  }

  @Override public void putArtist(Artist artist) {
    Realm realm = Realm.getDefaultInstance();
    putArtist(realm, artist);
    realm.close();
  }

  /**
   * Transform {@link Artist} into a Collection of {@link ArtistRealm}.
   *
   * @param artist Object to be transformed.
   * @return {@link ArtistRealm}
   */
  private ArtistRealm mapToRealm(Realm realm, Artist artist) {
    ArtistRealm artistRealm = realm.createObject(ArtistRealm.class);
    artistRealm.setId(artist.getArtistId());
    artistRealm.setName(artist.getName());
    artistRealm.setDescription(artist.getDescription());
    artistRealm.setLink(artist.getLink());
    artistRealm.setAlbums(artist.getAlbums());
    artistRealm.setTracks(artist.getTracks());

    if (artist.getGenres() != null && artist.getGenres().size() > 0) {
      RealmList<RealmString> genresRealm = new RealmList<>();
      for (String genre : artist.getGenres()) {
        RealmString genreRealm = realm.createObject(RealmString.class);
        genreRealm.setValue(genre);
        genresRealm.add(genreRealm);
      }
      artistRealm.setGenres(genresRealm);
    }


    if (artist.getCover() != null) {
      CoverRealm coverRealm = realm.createObject(CoverRealm.class);
      coverRealm.setSmall(artist.getCover().getSmall());
      coverRealm.setBig(artist.getCover().getBig());
      artistRealm.setCover(coverRealm);
    }
    return artistRealm;
  }

  /**
   * Transform a {@link ArtistRealm} into a Collection of {@link Artist}.
   *
   * @param artistRealm Object to be transformed.
   * @return {@link Artist}
   */
  private Artist mapToObject(ArtistRealm artistRealm) {
    Artist artist = new Artist(artistRealm.getId());
    artist.setName(artistRealm.getName());
    artist.setDescription(artistRealm.getDescription());
    RealmList<RealmString> genresRealm = artistRealm.getGenres();

    if (genresRealm != null && genresRealm.size() > 0) {
      List<String> genres = new ArrayList<>();
      for (RealmString genreRealm : genresRealm) {
        genres.add(genreRealm.getValue());
      }
      artist.setGenres(genres);
    }

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

  /**
   * If artist saved by id
   *
   * @param artistId used to look for inside the cache.
   * @return true if the element is cached, otherwise false.
   */
  @Override public boolean isCashed(long artistId) {
    Realm realm = Realm.getDefaultInstance();
    ArtistRealm artistRealm = realm.where(ArtistRealm.class).equalTo("id", artistId).findFirst();
    boolean isCached = artistRealm != null;
    realm.close();
    return isCached;
  }

  /**
   * Check if cache is expired
   * @return true if the cache is expired, otherwise false.
   */
  @Override public boolean isExpired() {
    Realm realm = Realm.getDefaultInstance();
    ExpiredRealm expiredRealm =
        realm.where(ExpiredRealm.class).equalTo("key", EXPIRED_KEY).findFirst();
    boolean isExpired = expiredRealm == null
        || (expiredRealm.getUpdatedTime() + EXPIRATION_TIME) < System.currentTimeMillis();
    realm.close();
    return isExpired;
  }

  /**
   * Evict {@link ArtistRealm} and remove last record time when it was saved {@link ExpiredRealm}
   */
  @Override public void clearArtists() {
    Realm realm = Realm.getDefaultInstance();
    ExpiredRealm expiredRealm =
        realm.where(ExpiredRealm.class).equalTo("key", EXPIRED_KEY).findFirst();
    realm.beginTransaction();
    if (expiredRealm != null) {

      expiredRealm.removeFromRealm();
    }
    realm.clear(ArtistRealm.class);
    realm.commitTransaction();
    realm.close();

  }
}
