package ru.petrushin.ya.music.domain.interactor;

import javax.inject.Named;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.petrushin.ya.music.domain.repository.ArtistRepository;
import rx.Scheduler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class ArtistInteractorTest {

  private static final long FAKE_ARTIST_ID = 99L;

  private ArtistInteractor artistInteractor;

  @Mock private ArtistRepository mockUserRepository;
  @Mock private @Named("Job") Scheduler mockJobScheduler;
  @Mock private @Named("UI") Scheduler mockUIScheduler;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);
    artistInteractor = new ArtistInteractor(mockUserRepository, mockJobScheduler, mockUIScheduler);
  }

  @Test public void testGetUserDetailsUseCaseObservableHappyCase() {
    artistInteractor.buildObservable(FAKE_ARTIST_ID);

    verify(mockUserRepository).artist(FAKE_ARTIST_ID);
    verifyNoMoreInteractions(mockUserRepository);
    verifyZeroInteractions(mockJobScheduler);
    verifyZeroInteractions(mockUIScheduler);
  }
}
