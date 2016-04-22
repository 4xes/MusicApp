package ru.petrushin.ya.music.presentation.exceptions;

import android.content.Context;
import ru.petrushin.ya.music.R;
import ru.petrushin.ya.music.data.exception.ArtistNotFoundException;
import ru.petrushin.ya.music.data.exception.NetworkConnectionException;

/**
 * Factory used to create error messages from an Exception as a condition.
 */
public class ErrorMessageFactory {

  private ErrorMessageFactory() {
    //empty
  }

  /**
   * Creates a String representing an error message.
   *
   * @param context Context needed to retrieve string resources.
   * @param exception An exception used as a condition to retrieve the correct error message.
   * @return {@link String} an error message.
   */
  public static String create(Context context, Exception exception) {
    String message = context.getString(R.string.exception_message_undefined);

    if (exception instanceof NetworkConnectionException) {
      message = context.getString(R.string.exception_message_network);
    } else if (exception instanceof ArtistNotFoundException) {
      message = context.getString(R.string.exception_message_artist_not_found);
    }

    return message;
  }
}
