package ru.petrushin.ya.music.presentation.view.navigation;

import ru.petrushin.ya.music.presentation.view.model.ArtistModel;

public interface MainRouter {
  void showArtist(ArtistModel artistModel);

  void openArtists();
}
