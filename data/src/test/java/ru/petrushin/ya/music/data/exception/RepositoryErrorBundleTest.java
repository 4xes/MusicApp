package ru.petrushin.ya.music.data.exception;

import android.os.Build;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import ru.petrushin.ya.data.BuildConfig;
import ru.petrushin.ya.music.data.ApplicationTestCase;

import static org.mockito.Mockito.verify;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class RepositoryErrorBundleTest extends ApplicationTestCase {

  private RepositoryErrorBundle repositoryErrorBundle;

  @Mock private Exception mockException;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);
    repositoryErrorBundle = new RepositoryErrorBundle(mockException);
  }

  @Test public void testGetErrorMessageInteraction() {
    repositoryErrorBundle.getErrorMessage();

    verify(mockException).getMessage();
  }
}
