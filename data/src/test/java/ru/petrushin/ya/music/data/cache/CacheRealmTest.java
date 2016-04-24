package ru.petrushin.ya.music.data.cache;

import android.os.Build;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import ru.petrushin.ya.data.BuildConfig;
import ru.petrushin.ya.music.data.ApplicationTestCase;
import ru.petrushin.ya.music.data.cashe.ArtistRealmCache;
import ru.petrushin.ya.music.domain.Artist;
import ru.petrushin.ya.music.domain.Cover;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
/**
 * Очень долго бился почему тесты не работают, но потом зашел в репорт, а там Robolectric does not
 * support API level 23.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class CacheRealmTest extends ApplicationTestCase {

  private static final long FAKE_ARTIST_ID = 99L;
  private static final String FAKE_NAME = "Yo Yo";
  private static final String FAKE_DESCRIPTION = "Famous music artist";
  private static final String FAKE_GENRES1 = "pop";
  private static final String FAKE_GENRES2 = "electronics";
  private static final String FAKE_AVATAR_SMALL = "http://www.yo-yo-music.club/avatar/small";
  private static final String FAKE_AVATAR_BIG = "http://www.yo-yo-music.club/avatar/big";
  private static final String FAKE_LINK = "http://www.yo-yo-music.club";

  private ArtistRealmCache artistRealmCache;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);
    artistRealmCache = new ArtistRealmCache(RuntimeEnvironment.application, "test");
  }

  @Test public void testAddingArtist() {
    Artist artist = createFakeArtist(FAKE_ARTIST_ID);
    artistRealmCache.putArtist(artist);

    assertThat(artistRealmCache.isCashed(FAKE_ARTIST_ID), is(true));

    artistRealmCache.clearArtists();
    assertThat(artistRealmCache.isCashed(FAKE_ARTIST_ID), is(false));
  }

  @Test public void testAddingArtists() {
    given(artistRealmCache.isExpired()).willReturn(true);
    given(artistRealmCache.isCashed(FAKE_ARTIST_ID)).willReturn(false);

    List<Artist> artists = new ArrayList<>();
    artists.add(createFakeArtist(0));
    artists.add(createFakeArtist(1));
    artists.add(createFakeArtist(2));
    artistRealmCache.putArtists(artists);

    assertThat(artistRealmCache.isCashed(0), is(true));
    assertThat(artistRealmCache.isCashed(1), is(true));
    assertThat(artistRealmCache.isCashed(2), is(true));
  }

  private Artist createFakeArtist(long artistId) {
    Artist user = new Artist(artistId);
    user.setName(FAKE_NAME);
    user.setLink(FAKE_LINK);
    List<String> genres = new ArrayList<>();
    genres.add(FAKE_GENRES1);
    genres.add(FAKE_GENRES2);
    user.setGenres(genres);
    Cover cover = new Cover();
    cover.setSmall(FAKE_AVATAR_SMALL);
    cover.setBig(FAKE_AVATAR_BIG);
    user.setCover(cover);
    user.setDescription(FAKE_DESCRIPTION);

    return user;
  }
}
