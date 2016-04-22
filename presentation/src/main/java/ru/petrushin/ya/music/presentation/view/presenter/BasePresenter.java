package ru.petrushin.ya.music.presentation.view.presenter;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<View, Router> {
  private WeakReference<View> view;
  private Router router;

  public abstract void onStart();

  public abstract void onStop();

  public View getView() {
    return view.get();
  }

  public void setView(View view) {
    this.view = new WeakReference<>(view);
  }

  public Router getRouter() {
    return router;
  }

  public void setRouter(Router router) {
    this.router = router;
  }
}
