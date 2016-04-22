package ru.petrushin.ya.music.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import ru.petrushin.ya.music.presentation.di.HasComponent;
import ru.petrushin.ya.music.presentation.view.BaseMainView;
import ru.petrushin.ya.music.presentation.view.presenter.BasePresenter;
import timber.log.Timber;

public abstract class BaseMainFragment extends BaseFragment implements BaseMainView {

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    //noinspection unchecked
    getPresenter().setRouter(getActivity());
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    //noinspection unchecked
    getPresenter().setView(this);
  }

  @Override public void onStart() {
    super.onStart();
    getPresenter().onStart();
  }

  @Override public void onStop() {
    super.onStop();
    getPresenter().onStop();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    //noinspection unchecked
    getPresenter().setRouter(null);
  }

  /**
   * Gets a component for dependency injection by its type.
   */
  @SuppressWarnings("unchecked") public <C> C getComponent(Class<C> componentType) {
    return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
  }

  @Override public Context context() {
    return getActivity().getApplicationContext();
  }

  @NonNull protected abstract BasePresenter getPresenter();

  @Override public void showError(String message) {
    if (getView() != null) {
      Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    } else {
      Timber.d(message);
    }
  }
}
