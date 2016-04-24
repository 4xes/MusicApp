package ru.petrushin.ya.music.presentation.view;

import java.util.Collection;
import ru.petrushin.ya.music.presentation.view.model.ArtistModel;

public interface ArtistListView extends BaseMainView {
  /**
   * Render a user list in the UI.
   *
   * @param artistModels The collection of {@link ArtistModel} that will be shown.
   */
  void renderArtistList(Collection<ArtistModel> artistModels);

}
