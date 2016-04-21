package ru.petrushin.ya.music.data.net;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import java.io.IOException;
import java.util.List;
import retrofit2.Response;
import ru.petrushin.ya.music.data.exception.NetworkConnectionException;
import ru.petrushin.ya.music.domain.Artist;
import rx.Observable;
import rx.Subscriber;

public class RestClientImpl implements RestClient {

  private final YandexService yandexService;

  public RestClientImpl(YandexService yandexService) {
    this.yandexService = yandexService;
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
