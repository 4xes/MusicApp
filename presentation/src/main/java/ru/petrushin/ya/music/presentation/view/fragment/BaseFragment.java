package ru.petrushin.ya.music.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import java.util.concurrent.atomic.AtomicInteger;
import ru.petrushin.ya.music.presentation.view.activity.BaseActivity;
import ru.petrushin.ya.music.presentation.view.annotation.Back;
import ru.petrushin.ya.music.presentation.view.annotation.Layout;
import ru.petrushin.ya.music.presentation.view.annotation.Title;
import timber.log.Timber;

public abstract class BaseFragment extends Fragment {
  private static final AtomicInteger lastFragmentId = new AtomicInteger(0);
  private final int fragmentId;

  public BaseFragment() {
    fragmentId = lastFragmentId.incrementAndGet();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(getLayout(), null);
    ButterKnife.bind(this, view);
    ((BaseActivity) getActivity()).resolveToolbar(this);
    return view;
  }

  @Override public void onDestroyView() {
    ButterKnife.unbind(this);
    super.onDestroyView();
  }

  public boolean getBack() {
    return getClass().isAnnotationPresent(Back.class);
  }

  public Toolbar getToolbar() {
    return null;
  }

  @LayoutRes public Integer getLayout() {
    Class cls = getClass();
    if (cls.isAnnotationPresent(Layout.class)) {
      Layout layout = (Layout) cls.getAnnotation(Layout.class);
      return layout.id();
    }
    throw new IllegalArgumentException(
        "You have to override this method or set layout annotation to fragment");
  }

  public String getTitle() {
    Class cls = getClass();
    if (cls.isAnnotationPresent(Title.class)) {
      Title title = (Title) cls.getAnnotation(Title.class);
      return getString(title.title());
    }
    return null;
  }

  public void setTitle(String title) {
    ((BaseActivity) getActivity()).resolveTitle(title);
  }

  public String getFragmentName() {
    Timber.d("fragment_id %d", fragmentId);
    return Long.toString(fragmentId);
  }

}