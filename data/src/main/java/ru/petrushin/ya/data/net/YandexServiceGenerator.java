package ru.petrushin.ya.data.net;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.realm.RealmObject;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.petrushin.ya.data.BuildConfig;

public class YandexServiceGenerator {
  private static String BASE_URL = BuildConfig.YANDEX_MOBILIZATION_URL;

  public static YandexService getService() {
    Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
      @Override public boolean shouldSkipField(FieldAttributes f) {
        return f.getDeclaringClass().equals(RealmObject.class);
      }

      @Override public boolean shouldSkipClass(Class<?> clazz) {
        return false;
      }
    }).create();

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    Retrofit.Builder builder =
        new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.client(httpClient.build()).build();
    return retrofit.create(YandexService.class);
  }
}
