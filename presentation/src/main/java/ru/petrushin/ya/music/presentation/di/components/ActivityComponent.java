package ru.petrushin.ya.music.presentation.di.components;

import android.app.Activity;
import dagger.Component;
import ru.petrushin.ya.music.presentation.di.PerActivity;
import ru.petrushin.ya.music.presentation.di.modules.ActivityModule;

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 *
 * Subtypes of ActivityComponent should be decorated with annotation:
 * {@link ru.petrushin.ya.music.presentation.di.PerActivity}
 */
@PerActivity @Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
  //Exposed to sub-graphs.
  Activity activity();
}
