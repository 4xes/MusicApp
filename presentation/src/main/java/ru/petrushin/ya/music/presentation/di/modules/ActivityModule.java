package ru.petrushin.ya.music.presentation.di.modules;

import android.app.Activity;
import dagger.Module;
import dagger.Provides;
import ru.petrushin.ya.music.presentation.di.PerActivity;

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module public class ActivityModule {
  private final Activity activity;

  public ActivityModule(Activity activity) {
    this.activity = activity;
  }

  /**
   * Expose the activity to dependents in the graph.
   */
  @Provides @PerActivity Activity activity() {
    return this.activity;
  }
}
