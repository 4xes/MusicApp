package ru.petrushin.ya.music.presentation.di.modules;

import dagger.Module;
import dagger.Provides;
import java.util.List;
import javax.inject.Named;
import ru.petrushin.ya.music.domain.Artist;
import ru.petrushin.ya.music.domain.interactor.ArtistInteractor;
import ru.petrushin.ya.music.domain.interactor.ArtistListInteractor;
import ru.petrushin.ya.music.domain.interactor.Interactor;
import ru.petrushin.ya.music.domain.repository.ArtistRepository;
import ru.petrushin.ya.music.presentation.di.PerActivity;
import rx.Scheduler;

/**
 * Dagger module that provides user related collaborators.
 */
@Module public class ArtistModule {

  private int userId = -1;

  public ArtistModule() {
  }

  public ArtistModule(int userId) {
    this.userId = userId;
  }

  @Provides @PerActivity @Named("artistList")
  Interactor<List<Artist>, Void> provideGetArtistListUseCase(
      ArtistListInteractor artistListInteractor) {
    return artistListInteractor;
  }

  @Provides @PerActivity @Named("artistDetails") Interactor provideGetArtistDetailsUseCase(
      ArtistRepository artistRepository, @Named("JOB") Scheduler jobScheduler,
      @Named("UI") Scheduler uiScheduler) {
    return new ArtistInteractor(userId, artistRepository, jobScheduler, uiScheduler);
  }
}