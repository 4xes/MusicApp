package ru.petrushin.ya.music.presentation.di.components;

import dagger.Component;
import ru.petrushin.ya.music.presentation.di.PerActivity;
import ru.petrushin.ya.music.presentation.di.modules.ActivityModule;
import ru.petrushin.ya.music.presentation.di.modules.ArtistModule;

@PerActivity @Component(dependencies = ApplicationComponent.class, modules = {
    ActivityModule.class, ArtistModule.class
}) public interface ArtistComponent {

}
