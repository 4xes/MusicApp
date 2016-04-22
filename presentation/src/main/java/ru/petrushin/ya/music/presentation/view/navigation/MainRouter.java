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

  /**
   * Showed webView with site of artist
   *
   * @param name name of artist
   * @param link link artist's web page
   */
  void showSiteArtist(String name, String link);
}
