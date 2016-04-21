package ru.petrushin.ya.music.presentation.di.modules;

import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import ru.petrushin.ya.music.data.ArtistRepositoryImpl;
import ru.petrushin.ya.music.data.cashe.ArtistCache;
import ru.petrushin.ya.music.data.cashe.realm.ArtistRealmCache;
import ru.petrushin.ya.music.domain.interactor.Interactor;
import ru.petrushin.ya.music.domain.repository.ArtistRepository;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module public class DomainModule {

  @Provides @Singleton @Named(Interactor.JOB) public Scheduler provideJobScheduler() {
    return Schedulers.computation();
  }

  @Provides @Singleton @Named(Interactor.UI) public Scheduler provideUIScheduler() {
    return AndroidSchedulers.mainThread();
  }

  @Provides @Singleton ArtistCache provideArtistCache(ArtistRealmCache artistsCache) {
    return artistsCache;
  }

  @Provides @Singleton ArtistRepository provideArtistRepository(
      ArtistRepositoryImpl artistRepository) {
    return artistRepository;
  }
}
