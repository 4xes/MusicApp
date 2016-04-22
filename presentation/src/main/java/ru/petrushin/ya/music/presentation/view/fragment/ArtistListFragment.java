package ru.petrushin.ya.music.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Bind;
import butterknife.OnClick;
import java.util.Collection;
import javax.inject.Inject;
import ru.petrushin.ya.music.R;
import ru.petrushin.ya.music.presentation.di.components.ArtistComponent;
import ru.petrushin.ya.music.presentation.view.ArtistListView;
import ru.petrushin.ya.music.presentation.view.adapter.ArtistsAdapter;
import ru.petrushin.ya.music.presentation.view.adapter.DividerItemDecoration;
import ru.petrushin.ya.music.presentation.view.adapter.RecyclerItemClickListener;
import ru.petrushin.ya.music.presentation.view.annotation.Layout;
import ru.petrushin.ya.music.presentation.view.annotation.Title;
import ru.petrushin.ya.music.presentation.view.model.ArtistModel;
import ru.petrushin.ya.music.presentation.view.presenter.ArtistListPresenter;
import ru.petrushin.ya.music.presentation.view.presenter.BasePresenter;

@Title(title = R.string.title_artists) @Layout(id = R.layout.fragment_artist_list)
public class ArtistListFragment extends BaseMainFragment implements ArtistListView {

  @Inject ArtistListPresenter artistListPresenter;
  @Inject ArtistsAdapter artistsAdapter;

  @Bind(R.id.toolbar) Toolbar toolbar;

  @Bind(R.id.recyclerView) RecyclerView recyclerView;
  @Bind(R.id.progress) ProgressBar progressBar;
  @Bind(R.id.empty) View empty;
  @Bind(R.id.retry) View retry;
  @Bind(R.id.btn_retry) View btnRetry;

  public ArtistListFragment() {
    setRetainInstance(true);
  }

  @OnClick(R.id.btn_retry) public void onBtnRetry() {
    artistListPresenter.getArtistList();
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getComponent(ArtistComponent.class).inject(this);
  }

  @Override public void showLoading() {
    progressBar.setVisibility(View.VISIBLE);
  }

  @Override public void hideLoading() {
    progressBar.setVisibility(View.GONE);
  }

  @Override public void showRetry() {
    retry.setVisibility(View.VISIBLE);
  }

  @Override public void hideRetry() {
    retry.setVisibility(View.GONE);
  }

  @Override public void showEmpty() {
    empty.setVisibility(View.VISIBLE);
  }

  @Override public void hideEmpty() {
    empty.setVisibility(View.GONE);
  }

  @Override public void showContent() {
    recyclerView.setVisibility(View.VISIBLE);
  }

  @Override public void hideContent() {
    recyclerView.setVisibility(View.GONE);
  }

  @Override public Toolbar getToolbar() {
    return toolbar;
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context(),
        (view, position) -> artistListPresenter.onSelectArtist(
            artistsAdapter.getItemId(position))));
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.addItemDecoration(
        new DividerItemDecoration(ContextCompat.getDrawable(getContext(), R.drawable.divider),
            DividerItemDecoration.VERTICAL_LIST));
    recyclerView.setAdapter(artistsAdapter);
  }

  @Override public void onDestroyView() {
    recyclerView.setAdapter(null);
    super.onDestroyView();
  }

  @NonNull @Override protected BasePresenter getPresenter() {
    return artistListPresenter;
  }

  @Override public void renderArtistList(Collection<ArtistModel> artistModels) {
    artistsAdapter.set(artistModels);
  }
}
