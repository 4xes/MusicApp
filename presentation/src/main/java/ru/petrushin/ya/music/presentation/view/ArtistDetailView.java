package ru.petrushin.ya.music.presentation.view;

import ru.petrushin.ya.music.presentation.view.model.ArtistModel;

public interface ArtistDetailView extends BaseMainView {
  /**
   * Render a {@link ArtistModel} in the UI.
   *
   * @param artistModel that will be shown.
   */
  void viewArtist(ArtistModel artistModel);
}
