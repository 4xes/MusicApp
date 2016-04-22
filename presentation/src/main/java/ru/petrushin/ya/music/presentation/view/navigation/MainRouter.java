package ru.petrushin.ya.music.presentation.view.navigation;

/**
 * What Fragment is need to show by Activity
 */
public interface MainRouter {
  /**
   * Show detail screen of artist
   *
   * @param artistId artist id
   */
  void showArtist(long artistId);

  /**
   * Show screen with artists
   */
  void openArtists();
}
