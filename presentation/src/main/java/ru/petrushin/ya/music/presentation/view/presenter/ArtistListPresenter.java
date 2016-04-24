package ru.petrushin.ya.music.presentation.view.presenter;

import java.util.List;
import javax.inject.Inject;
import ru.petrushin.ya.music.BuildConfig;
import ru.petrushin.ya.music.domain.Artist;
import ru.petrushin.ya.music.domain.exception.DefaultErrorBundle;
import ru.petrushin.ya.music.domain.interactor.ArtistListInteractor;
import ru.petrushin.ya.music.domain.interactor.Interactor;
import ru.petrushin.ya.music.presentation.exceptions.ErrorMessageFactory;
import ru.petrushin.ya.music.presentation.mapper.ArtistModelMapper;
import ru.petrushin.ya.music.presentation.view.ArtistListView;
import rx.Subscriber;

public class ArtistListPresenter extends BaseMainPresenter<ArtistListView> {

  private final Interactor<List<Artist>, Void> artistListInteractor;
  private final ArtistModelMapper artistModelMapper;

  @Inject public ArtistListPresenter(ArtistListInteractor artistListInteractor,
      ArtistModelMapper artistModelMapper) {
    this.artistListInteractor = artistListInteractor;
    this.artistModelMapper = artistModelMapper;
  }

  @Override public void onStart() {
    getArtistList();
  }

  public void onSelectArtist(long artistId) {
    getRouter().showArtist(artistId);
  }

  @Override public void onStop() {
    artistListInteractor.unsubscribe();
  }

  public void getArtistList() {
    this.artistListInteractor.execute(new ArtistListSubscriber());
  }

  public final class ArtistListSubscriber extends Subscriber<List<Artist>> {

    @Override public void onStart() {
      if (getView() != null) {
        getView().hideContent();
        getView().hideEmpty();
        getView().hideRetry();
        getView().showLoading();
      }
    }

    @Override public void onCompleted() {
    }

    @Override public void onNext(List<Artist> artists) {
      if (getView() != null) {
        getView().hideLoading();
        if (artists.isEmpty()) {
          getView().showEmpty();
        } else {
          getView().showContent();
          getView().renderArtistList(artistModelMapper.transform(artists));
        }
      }
    }

    @Override public void onError(Throwable e) {
      if (BuildConfig.DEBUG) {
        e.printStackTrace();
      }
      if (getView() != null) {
        getView().hideLoading();
        getView().showError(ErrorMessageFactory.create(getView().context(),
            new DefaultErrorBundle((Exception) e).getException()));
        getView().showRetry();
      }
    }
  }
}
