package ru.petrushin.ya.music.domain.interactor;

import javax.inject.Inject;
import javax.inject.Named;
import ru.petrushin.ya.music.domain.Artist;
import ru.petrushin.ya.music.domain.repository.ArtistRepository;
import rx.Observable;
import rx.Scheduler;

public class ArtistInteractor extends Interactor<Artist, Void>{

  private final long artistId;
  private final ArtistRepository artistRepository;

  @Inject public ArtistInteractor(long artistId, ArtistRepository artistRepository,
      @Named(Interactor.JOB) Scheduler jobScheduler, @Named(Interactor.UI) Scheduler uiScheduler) {
    super(jobScheduler, uiScheduler);
    this.artistId = artistId;
    this.artistRepository = artistRepository;
  }

  @Override protected Observable<Artist> buildObservable(Void parameter) {
    return this.artistRepository.artist(artistId);
  }
}
