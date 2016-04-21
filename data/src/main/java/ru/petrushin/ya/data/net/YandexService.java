package ru.petrushin.ya.data.net;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import ru.petrushin.ya.music.domain.Artist;

interface YandexService {
  @GET("/artists.json") Call<List<Artist>> artists();
}
