package ru.petrushin.ya.music.presentation.view.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import com.thefinestartist.finestwebview.FinestWebView;
import ru.petrushin.ya.music.R;
import ru.petrushin.ya.music.presentation.di.HasComponent;
import ru.petrushin.ya.music.presentation.di.components.ArtistComponent;
import ru.petrushin.ya.music.presentation.di.components.DaggerArtistComponent;
import ru.petrushin.ya.music.presentation.di.modules.ArtistModule;
import ru.petrushin.ya.music.presentation.view.annotation.Layout;
import ru.petrushin.ya.music.presentation.view.fragment.ArtistDetailFragment;
import ru.petrushin.ya.music.presentation.view.fragment.ArtistListFragment;
import ru.petrushin.ya.music.presentation.view.navigation.MainRouter;

@Layout(id = R.layout.activity_main) public class MainActivity extends BaseActivity
    implements MainRouter, HasComponent<ArtistComponent> {

  private ArtistComponent artistComponent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initializeComponent();

    if (savedInstanceState == null) {
      openArtists();
    }
  }

  @Override public void showArtist(long artistId) {
    addBackStack(R.id.content, ArtistDetailFragment.newInstance(artistId));
  }

  @Override public void openArtists() {
    addFragment(R.id.content, new ArtistListFragment());
  }

  @Override public void showSiteArtist(String name, String link) {
    FinestWebView.Builder finestWebView = new FinestWebView.Builder(this);
    if (name != null) {
      finestWebView.titleDefault(name);
    }
    finestWebView.titleColor(
        ContextCompat.getColor(getApplicationContext(), android.R.color.white));
    finestWebView.urlColor(
        ContextCompat.getColor(getApplicationContext(), R.color.transparent_white_hex_99));
    finestWebView.backPressToClose(true);
    finestWebView.iconDefaultColor(
        ContextCompat.getColor(getApplicationContext(), android.R.color.white));
    finestWebView.show(link);
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

  private void initializeComponent() {
    artistComponent = DaggerArtistComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .artistModule(new ArtistModule())
        .build();
  }

  @Override public ArtistComponent getComponent() {
    return artistComponent;
  }
}
