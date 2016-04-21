package ru.petrushin.ya.music.presentation.view.activity;

import android.os.Bundle;
import android.view.MenuItem;
import ru.petrushin.ya.music.presentation.di.components.ActivityComponent;
import ru.petrushin.ya.music.presentation.view.model.ArtistModel;
import ru.petrushin.ya.music.presentation.view.navigation.MainRouter;

public class MainActivity extends BaseActivity implements MainRouter {

  private ActivityComponent activityComponent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //mainActivityComponent = DaggerMainActivityComponent.builder()
    //    .domainModule(new DomainModule())
    //    .build();
  }

  @Override public void showArtist(ArtistModel artistModel) {

  }

  @Override public void openArtists() {

  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
}
