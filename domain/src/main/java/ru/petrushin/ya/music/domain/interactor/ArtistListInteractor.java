package ru.petrushin.ya.music.domain.interactor;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import ru.petrushin.ya.music.domain.Artist;
import ru.petrushin.ya.music.domain.repository.ArtistRepository;
import rx.Observable;
import rx.Scheduler;

public class ArtistListInteractor extends Interactor<List<Artist>, Void> {

  private final ArtistRepository artistRepository;

  @Inject public ArtistListInteractor(ArtistRepository artistRepository,
      @Named(Interactor.JOB) Scheduler jobScheduler, @Named(Interactor.UI) Scheduler uiScheduler) {
    super(jobScheduler, uiScheduler);
    this.artistRepository = artistRepository;
  }

  @Override protected Observable<List<Artist>> buildObservable(Void parameter) {
    return this.artistRepository.artists();
  }
}
