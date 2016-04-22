package ru.petrushin.ya.music.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import com.bumptech.glide.Glide;
import javax.inject.Inject;
import ru.petrushin.ya.music.R;
import ru.petrushin.ya.music.presentation.di.components.ArtistComponent;
import ru.petrushin.ya.music.presentation.view.ArtistDetailView;
import ru.petrushin.ya.music.presentation.view.adapter.ArtistsAdapter;
import ru.petrushin.ya.music.presentation.view.annotation.Back;
import ru.petrushin.ya.music.presentation.view.annotation.Layout;
import ru.petrushin.ya.music.presentation.view.annotation.Title;
import ru.petrushin.ya.music.presentation.view.model.ArtistModel;
import ru.petrushin.ya.music.presentation.view.presenter.ArtistDetailPresenter;
import ru.petrushin.ya.music.presentation.view.presenter.BasePresenter;

@Back @Title(title = R.string.title_artist) @Layout(id = R.layout.fragment_artist_detail)
public class ArtistDetailFragment extends BaseMainFragment implements ArtistDetailView {
  private static final String EXTRA_ARTIST_ID = "artistId";
  @Inject ArtistDetailPresenter artistPresenter;
  @Inject ArtistsAdapter artistsAdapter;

  @Bind(R.id.toolbar) Toolbar toolbar;

  @Bind(R.id.avatar) ImageView imageAvatar;
  @Bind(R.id.genres) TextView textViewGenres;
  @Bind(R.id.description) TextView textViewDescription;
  @Bind(R.id.works) TextView textViewWorks;

  public ArtistDetailFragment() {
    setRetainInstance(true);
  }

  public static ArtistDetailFragment newInstance(long artistId) {
    ArtistDetailFragment fragment = new ArtistDetailFragment();
    Bundle bundle = new Bundle();
    bundle.putLong(EXTRA_ARTIST_ID, artistId);
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getComponent(ArtistComponent.class).inject(this);
    if (getArguments() != null) {
      long artistId = getArguments().getLong(EXTRA_ARTIST_ID);
      artistPresenter.setArtistId(artistId);
    }
  }

  @Override public Toolbar getToolbar() {
    return toolbar;
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }

  @NonNull @Override protected BasePresenter getPresenter() {
    return artistPresenter;
  }

  @Override public void viewArtist(ArtistModel artistModel) {
    if (artistModel.getName() != null) {
      setTitle(artistModel.getName());
    }
    Glide.with(getContext())
        .load(artistModel.getCoverBig())
        .fitCenter()
        .placeholder(R.drawable.loading_spinner)
        .crossFade()
        .into(imageAvatar);
    textViewGenres.setText(artistModel.getGenres() != null ? artistModel.getGenres() : "");
    textViewDescription.setText(
        artistModel.getDescription() != null ? artistModel.getDescription() : "");
    String albums = getContext().getResources()
        .getQuantityString(R.plurals.n_albums, artistModel.getAlbums(), artistModel.getAlbums());
    String tracks = getContext().getResources()
        .getQuantityString(R.plurals.n_tracks, artistModel.getTracks(), artistModel.getTracks());
    String worksCount = getContext().getString(R.string.artist_detail_works, albums, tracks);
    textViewWorks.setText(worksCount);
  }
}
