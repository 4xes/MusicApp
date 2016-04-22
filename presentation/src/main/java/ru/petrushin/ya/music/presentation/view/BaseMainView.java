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
}
