package ru.petrushin.ya.music.domain.interactor;

import javax.inject.Inject;
import javax.lang.model.type.NullType;
import ru.petrushin.ya.music.domain.Artist;
import ru.petrushin.ya.music.domain.repository.ArtistRepository;
import rx.Observable;
import rx.Scheduler;

public class ArtistInteractor extends Interactor<Artist, Void>{

  private final long artistId;
  private final ArtistRepository artistRepository;

  @Inject
  public ArtistInteractor(long artistId, ArtistRepository artistRepository, Scheduler jobScheduler, Scheduler uiScheduler) {
    super(jobScheduler, uiScheduler);
    this.artistId = artistId;
    this.artistRepository = artistRepository;
  }

  @Override protected Observable<Artist> buildObservable(Void parameter) {
    return this.artistRepository.artist(artistId);
  }
}
