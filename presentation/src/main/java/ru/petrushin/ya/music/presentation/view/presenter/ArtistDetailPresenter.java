package ru.petrushin.ya.music.presentation.view.presenter;

import javax.inject.Inject;
import ru.petrushin.ya.music.BuildConfig;
import ru.petrushin.ya.music.domain.Artist;
import ru.petrushin.ya.music.domain.exception.DefaultErrorBundle;
import ru.petrushin.ya.music.domain.interactor.ArtistInteractor;
import ru.petrushin.ya.music.domain.interactor.Interactor;
import ru.petrushin.ya.music.presentation.exceptions.ErrorMessageFactory;
import ru.petrushin.ya.music.presentation.mapper.ArtistModelMapper;
import ru.petrushin.ya.music.presentation.view.ArtistDetailView;
import ru.petrushin.ya.music.presentation.view.model.ArtistModel;
import rx.Subscriber;

public class ArtistDetailPresenter extends BaseMainPresenter<ArtistDetailView> {

  private final Interactor<Artist, Long> artistInteractor;
  private final ArtistModelMapper artistModelMapper;

  private long artistId;

  @Inject public ArtistDetailPresenter(ArtistInteractor artistListInteractor,
      ArtistModelMapper artistModelMapper) {
    this.artistInteractor = artistListInteractor;
    this.artistModelMapper = artistModelMapper;
  }

  public void setArtistId(long artistId) {
    this.artistId = artistId;
  }

  public void openLink(ArtistModel artistModel) {
    getRouter().showSiteArtist(artistModel.getName(), artistModel.getLink());
  }

  @Override public void onStart() {
    getArtist(artistId);
  }

  @Override public void onStop() {
    artistInteractor.unsubscribe();
  }

  public void getArtist(long artistId) {
    this.artistInteractor.execute(artistId, new ArtistSubscriber());
  }

  private final class ArtistSubscriber extends Subscriber<Artist> {

    @Override public void onStart() {

    }

    @Override public void onCompleted() {
    }

    @Override public void onNext(Artist artist) {
      if (getView() != null) {
        getView().viewArtist(artistModelMapper.transform(artist));
      }
    }

    @Override public void onError(Throwable e) {
      if (BuildConfig.DEBUG) {
        e.printStackTrace();
      }
      if (getView() != null) {
        getView().showError(ErrorMessageFactory.create(getView().context(),
            new DefaultErrorBundle((Exception) e).getException()));
      }
    }
  }
}
