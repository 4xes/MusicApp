package ru.petrushin.ya.music.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.petrushin.ya.music.domain.exception.DefaultErrorBundle;

import static org.mockito.Mockito.verify;

public class DefaultErrorBundleTest {
  private DefaultErrorBundle defaultErrorBundle;

  @Mock private Exception mockException;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);
    defaultErrorBundle = new DefaultErrorBundle(mockException);
  }

  @Test public void testGetErrorMessageInteraction() {
    defaultErrorBundle.getErrorMessage();

    verify(mockException).getMessage();
  }
}