package ru.petrushin.ya.music.data.cache;

import android.os.Build;
import io.realm.Realm;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import ru.petrushin.ya.data.BuildConfig;
import ru.petrushin.ya.music.data.ArtistRepositoryImpl;
import ru.petrushin.ya.music.data.cashe.ArtistRealmCache;
import ru.petrushin.ya.music.data.cashe.realm.model.ArtistRealm;
import ru.petrushin.ya.music.data.cashe.realm.model.RealmString;
import ru.petrushin.ya.music.domain.Artist;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

// Wait for complete this https://github.com/robolectric/robolectric/issues/2429
// Robolectric, Using Power Mock https://github.com/robolectric/robolectric/wiki/Using-PowerMock
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.M)
@RunWith(RobolectricGradleTestRunner.class)
@PowerMockIgnore({ "org.mockito.*", "org.robolectric.*", "android.*" })
@PrepareForTest({ Realm.class }) public class RealmTest {

  @Rule public PowerMockRule rule = new PowerMockRule();
  Realm mockRealm;
  ArtistRealmCache realmCache;

  @Before public void setup() {
    mockStatic(Realm.class);

    Realm mockRealm = PowerMockito.mock(Realm.class);

    when(Realm.getDefaultInstance()).thenReturn(mockRealm);

    this.mockRealm = mockRealm;
  }

  @Test public void shouldBeAbleToGetDefaultInstance() {
    assertThat(Realm.getDefaultInstance(), is(mockRealm));
  }

  @Test public void shouldBeAbleToMockRealmMethods() {
    when(mockRealm.isAutoRefresh()).thenReturn(true);
    assertThat(mockRealm.isAutoRefresh(), is(true));

    when(mockRealm.isAutoRefresh()).thenReturn(false);
    assertThat(mockRealm.isAutoRefresh(), is(false));
  }

  @Test public void shouldBeAbleToCreateARealmObject() {
    ArtistRealm artist = new ArtistRealm();
    when(mockRealm.createObject(ArtistRealm.class)).thenReturn(artist);

    ArtistRealm output = mockRealm.createObject(ArtistRealm.class);

    assertThat(output, is(artist));
  }

  /**
   * This test verifies the behavior in the {@link ArtistRepositoryImpl} class.
   */
  @Test public void shouldVerifyTransactionWasCreated() {

    ArtistRealm artistRealm = new ArtistRealm();
    when(mockRealm.createObject(ArtistRealm.class)).thenReturn(artistRealm);

    Artist artist = new Artist(0);
    List<String> genres = new ArrayList<>();
    genres.add("pop");
    genres.add("disco");
    artist.setGenres(genres);
    artist.setName("Twenty one pilots");

    realmCache = new ArtistRealmCache(RuntimeEnvironment.application);
    realmCache.putArtist(artist);

    // Verify that the begin transaction was called only once
    verify(mockRealm, times(1)).beginTransaction();

    // Verify that Realm#createObject was called only once
    verify(mockRealm, times(1)).createObject(
        ArtistRealm.class); // Verify that a Dog was in fact created.

    verify(mockRealm, times(2)).createObject(RealmString.class);

    // Verify that Dog#setName() is called only once
    verify(artistRealm, times(1)).setName(Mockito.anyString());
    // Any string will do

    // Verify that the transaction was committed only once
    verify(mockRealm, times(1)).commitTransaction();

    // Verify that the Realm was closed only once.
    verify(mockRealm, times(1)).close();
  }
}
