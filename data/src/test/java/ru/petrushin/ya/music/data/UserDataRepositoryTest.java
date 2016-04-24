package ru.petrushin.ya.music.data;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.petrushin.ya.music.data.cashe.ArtistRealmCache;
import ru.petrushin.ya.music.data.cashe.realm.model.ArtistRealm;
import ru.petrushin.ya.music.data.net.ArtistRestClient;
import ru.petrushin.ya.music.domain.Artist;
import rx.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class UserDataRepositoryTest extends ApplicationTestCase {

  private static final long FAKE_ARTIST_ID = 1080505L;
  @Rule public ExpectedException expectedException = ExpectedException.none();
  private ArtistRepositoryImpl userDataRepository;
  @Mock private ArtistRealmCache mockRealmCache;
  @Mock private ArtistRestClient mockRestClient;
  @Mock private Artist mockArtist;
  @Mock private ArtistRealm mockArtistRealm;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);
    userDataRepository = new ArtistRepositoryImpl(mockRealmCache, mockRestClient);
  }

  @Test public void testGetArtistsHappyCase() {
    List<Artist> artistsList = new ArrayList<>();
    artistsList.add(new Artist(FAKE_ARTIST_ID));
    given(mockRealmCache.getAllArtists()).willReturn(Observable.just(artistsList));

    userDataRepository.artists();

    verify(mockRealmCache).isExpired();
    verify(mockRealmCache).getAllArtists();
  }

  @Test public void testGetArtistHappyCase() {
    Artist userEntity = new Artist(FAKE_ARTIST_ID);
    given(mockRealmCache.getArtist(FAKE_ARTIST_ID)).willReturn(Observable.just(userEntity));
    userDataRepository.artist(FAKE_ARTIST_ID);

    verify(mockRealmCache).getArtist(FAKE_ARTIST_ID);
  }
}
