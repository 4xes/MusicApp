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

public class ArtistListInteractorTest {

  private ArtistListInteractor artistListInteractor;

  @Mock private ArtistRepository mockUserRepository;
  @Mock private @Named("Job") Scheduler mockJobScheduler;
  @Mock private @Named("UI") Scheduler mockUIScheduler;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);
    artistListInteractor =
        new ArtistListInteractor(mockUserRepository, mockJobScheduler, mockUIScheduler);
  }

  @Test public void testGetUserListUseCaseObservableHappyCase() {
    artistListInteractor.buildObservable(null);

    verify(mockUserRepository).artists();
    verifyNoMoreInteractions(mockUserRepository);
    verifyZeroInteractions(mockJobScheduler);
    verifyZeroInteractions(mockUIScheduler);
  }
}
