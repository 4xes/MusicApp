package ru.petrushin.ya.music.presentation.view;

import android.content.Context;

/**
 * Interface representing a View that will use to load data.
 */
public interface BaseMainView {

  /**
   * Show an error message
   *
   * @param message A string representing an error.
   */
  void showError(String message);

  /**
   * Get a {@link android.content.Context}.
   */
  Context context();

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
