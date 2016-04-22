package ru.petrushin.ya.music.data.net;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit2.Response;
import ru.petrushin.ya.music.data.exception.NetworkConnectionException;
import ru.petrushin.ya.music.domain.Artist;
import rx.Observable;
import rx.Subscriber;

@Singleton public class ArtistRestClientImpl implements ArtistRestClient {

  private final YandexService yandexService;

  @Inject public ArtistRestClientImpl() {
    this.yandexService = YandexServiceGenerator.getService();
  }

  @RxLogObservable @Override public Observable<List<Artist>> artists() {
    return Observable.create(new Observable.OnSubscribe<List<Artist>>() {
      @Override public void call(Subscriber<? super List<Artist>> subscriber) {
        subscriber.onStart();
        try {
          Response<List<Artist>> response = yandexService.artists().execute();
          if (response.isSuccessful()) {
            subscriber.onNext(response.body());
            subscriber.onCompleted();
          } else {
            subscriber.onError(new NetworkConnectionException());
          }
        } catch (IOException e) {
          subscriber.onError(new NetworkConnectionException(e.getCause()));
        }
      }
    });
  }
}
