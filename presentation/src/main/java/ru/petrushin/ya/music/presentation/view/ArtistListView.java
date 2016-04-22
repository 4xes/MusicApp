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

  /**
   * Show a view with a progress bar indicating a loading process.
   */
  void showLoading();

  /**
   * Hide a view with a progress bar indicating a loading process.
   */
  void hideLoading();

  /**
   * Show a retry view in case of an error when retrieving data.
   */
  void showRetry();

  /**
   * Show a retry view in case of an error when retrieving data.
   */
  void hideRetry();

  /**
   * Show a empty view in case of empty retrieving data
   */
  void showEmpty();

  /**
   * Hide a empty view in case of empty retrieving data
   */
  void hideEmpty();

  /**
   * Show a content view in case of empty retrieving data
   */
  void showContent();

  /**
   * Hide a content view in case of empty retrieving data
   */
  void hideContent();

}
