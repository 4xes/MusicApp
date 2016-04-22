package ru.petrushin.ya.music.presentation.di.modules;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import ru.petrushin.ya.music.data.ArtistRepositoryImpl;
import ru.petrushin.ya.music.data.cashe.ArtistCache;
import ru.petrushin.ya.music.data.cashe.ArtistRealmCache;
import ru.petrushin.ya.music.data.net.ArtistRestClient;
import ru.petrushin.ya.music.data.net.ArtistRestClientImpl;
import ru.petrushin.ya.music.domain.interactor.Interactor;
import ru.petrushin.ya.music.domain.repository.ArtistRepository;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module public class ApplicationModule {
  private final Application application;

  public ApplicationModule(Application application) {
    this.application = application;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return this.application;
  }

  @Provides @Singleton @Named(Interactor.JOB) public Scheduler provideJobScheduler() {
    return Schedulers.computation();
  }

  @Provides @Singleton @Named(Interactor.UI) public Scheduler provideUIScheduler() {
    return AndroidSchedulers.mainThread();
  }

  @Provides @Singleton ArtistCache provideArtistCache(ArtistRealmCache artistsCache) {
    return artistsCache;
  }

  @Provides @Singleton ArtistRestClient provideArtistRestClient(
      ArtistRestClientImpl artistsRestClient) {
    return artistsRestClient;
  }

  @Provides @Singleton ArtistRepository provideArtistRepository(
      ArtistRepositoryImpl artistRepository) {
    return artistRepository;
  }
}
