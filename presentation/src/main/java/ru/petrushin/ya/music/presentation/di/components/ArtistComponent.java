package ru.petrushin.ya.music.presentation.di.components;

import dagger.Component;
import ru.petrushin.ya.music.presentation.di.PerActivity;
import ru.petrushin.ya.music.presentation.di.modules.ActivityModule;
import ru.petrushin.ya.music.presentation.di.modules.ArtistModule;
import ru.petrushin.ya.music.presentation.view.fragment.ArtistListFragment;

@PerActivity @Component(dependencies = ApplicationComponent.class, modules = {
    ActivityModule.class, ArtistModule.class
}) public interface ArtistComponent extends ActivityComponent {
  void inject(ArtistListFragment artistListFragment);
}
