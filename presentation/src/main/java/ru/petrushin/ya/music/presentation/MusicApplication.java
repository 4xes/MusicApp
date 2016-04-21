package ru.petrushin.ya.music.presentation;

import android.app.Application;
import com.squareup.leakcanary.LeakCanary;
import ru.petrushin.ya.music.BuildConfig;
import ru.petrushin.ya.music.presentation.di.components.ApplicationComponent;
import ru.petrushin.ya.music.presentation.di.components.DaggerApplicationComponent;
import ru.petrushin.ya.music.presentation.di.modules.ApplicationModule;
import ru.petrushin.ya.music.presentation.di.modules.DomainModule;

public class MusicApplication extends Application {

  private ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();
    this.initializeInjector();
    this.initializeLeakDetection();
  }

  private void initializeInjector() {
    this.applicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(this))
        .domainModule(new DomainModule())
        .build();
  }

  public ApplicationComponent getApplicationComponent() {
    return this.applicationComponent;
  }

  private void initializeLeakDetection() {
    if (BuildConfig.DEBUG) {
      LeakCanary.install(this);
    }
  }
}
