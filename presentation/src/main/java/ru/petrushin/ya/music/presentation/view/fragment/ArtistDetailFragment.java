package ru.petrushin.ya.music.presentation.view.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import javax.inject.Inject;
import ru.petrushin.ya.music.R;
import ru.petrushin.ya.music.presentation.di.components.ArtistComponent;
import ru.petrushin.ya.music.presentation.view.ArtistDetailView;
import ru.petrushin.ya.music.presentation.view.adapter.ArtistsAdapter;
import ru.petrushin.ya.music.presentation.view.annotation.Back;
import ru.petrushin.ya.music.presentation.view.annotation.Layout;
import ru.petrushin.ya.music.presentation.view.model.ArtistModel;
import ru.petrushin.ya.music.presentation.view.presenter.ArtistDetailPresenter;
import ru.petrushin.ya.music.presentation.view.presenter.BasePresenter;

@Back @Layout(id = R.layout.fragment_artist_detail)
public class ArtistDetailFragment extends BaseMainFragment implements ArtistDetailView {
  private static final String EXTRA_ARTIST_ID = "artistId";
  @Inject ArtistDetailPresenter artistPresenter;
  @Inject ArtistsAdapter artistsAdapter;

  @Bind(R.id.toolbar_layout) CollapsingToolbarLayout collapsingToolbarLayout;
  @Bind(R.id.toolbar) Toolbar toolbar;

  @Bind(R.id.avatar) ImageView imageAvatar;
  @Bind(R.id.genres) TextView textViewGenres;
  @Bind(R.id.description) TextView textViewDescription;
  @Bind(R.id.works) TextView textViewWorks;
  @Bind(R.id.fabOpenLink) FloatingActionButton fabOpenLink;

  private ArtistModel artistModel;

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

  @OnClick(R.id.fabOpenLink) public void onClickFabOpenLink() {
    artistPresenter.openLink(artistModel);
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getComponent(ArtistComponent.class).inject(this);
    if (getArguments() != null) {
      long artistId = getArguments().getLong(EXTRA_ARTIST_ID);
      artistPresenter.setArtistId(artistId);
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = super.onCreateView(inflater, container, savedInstanceState);

    //change system bar color
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      getActivity().getWindow()
          .setStatusBarColor(
              ContextCompat.getColor(getContext(), R.color.transparent_black_percent_15));
    }
    return view;
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
    this.artistModel = artistModel;
    if (artistModel.getName() != null) {
      collapsingToolbarLayout.setTitle(artistModel.getName());
    }
    Glide.with(getContext())
        .load(artistModel.getCoverBig()).centerCrop()
        .placeholder(R.drawable.loading_spinner)
        .crossFade()
        //if we change size of view don't need load image again
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(imageAvatar);
    if (artistModel.getLink() != null) {
      fabOpenLink.setVisibility(View.VISIBLE);
    }
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
