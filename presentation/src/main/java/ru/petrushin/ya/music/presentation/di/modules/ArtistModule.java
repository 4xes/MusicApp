package ru.petrushin.ya.music.presentation.di.modules;

import dagger.Module;
import dagger.Provides;
import java.util.List;
import ru.petrushin.ya.music.domain.Artist;
import ru.petrushin.ya.music.domain.interactor.ArtistListInteractor;
import ru.petrushin.ya.music.domain.interactor.Interactor;
import ru.petrushin.ya.music.presentation.di.PerActivity;

/**
 * Dagger module that provides user related collaborators.
 */
@Module public class ArtistModule {

  public ArtistModule() {
  }

  @Provides @PerActivity Interactor<List<Artist>, Void> provideArtistListRepository(
      ArtistListInteractor artistListInteractor) {
    return artistListInteractor;
  }
}