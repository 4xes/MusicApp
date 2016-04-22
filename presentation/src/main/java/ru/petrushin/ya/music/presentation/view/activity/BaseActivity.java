package ru.petrushin.ya.music.presentation.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import ru.petrushin.ya.music.presentation.MusicApplication;
import ru.petrushin.ya.music.presentation.di.components.ApplicationComponent;
import ru.petrushin.ya.music.presentation.di.modules.ActivityModule;
import ru.petrushin.ya.music.presentation.view.annotation.Layout;
import ru.petrushin.ya.music.presentation.view.fragment.BaseFragment;

/**
 * Base {@link android.app.Activity} class for every Activity in this application.
 */
public abstract class BaseActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Class cls = getClass();
    if (cls.isAnnotationPresent(Layout.class)) {
      Layout layout = (Layout) cls.getAnnotation(Layout.class);
      setContentView(layout.id());
      ButterKnife.bind(this);
    }
  }

  @Override protected void onDestroy() {
    ButterKnife.unbind(this);
    super.onDestroy();
  }

  /**
   * Setting toolbar from fragment
   *
   * @param fragment current fragment
   */
  public void resolveToolbar(BaseFragment fragment) {
    if (fragment.getToolbar() != null) {
      setSupportActionBar(fragment.getToolbar());

      ActionBar actionBar = getSupportActionBar();
      if (actionBar != null) {
        if (fragment.getBack()) {
          actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (fragment.getTitle() != null) {
          actionBar.setTitle(fragment.getTitle());
        }
      }
    }
  }

  /**
   * Setting title of tollbar
   */
  public void resolveTitle(String title) {
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      if (title != null) {
        actionBar.setTitle(title);
      }
    }
  }

  /**
   * Adds a {@link Fragment} to this activity's layout.
   *
   * @param containerViewId The container view to where add the fragment.
   * @param fragment The fragment to be added.
   */
  protected void addFragment(int containerViewId, BaseFragment fragment) {
    FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
    tx.add(containerViewId, fragment, fragment.getFragmentName());
    tx.commit();
  }

  protected void replace(int containerViewId, BaseFragment fragment) {
    FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
    tx.replace(containerViewId, fragment, fragment.getFragmentName());
    tx.commit();
  }

  protected void addBackStack(int containerViewId, BaseFragment fragment) {
    FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
    tx.replace(containerViewId, fragment);
    tx.addToBackStack(fragment.getFragmentName());
    tx.commit();
  }

  /**
   * Get the Main Application component for dependency injection.
   *
   * @return {@link ru.petrushin.ya.music.presentation.di.components.ApplicationComponent}
   */
  protected ApplicationComponent getApplicationComponent() {
    return ((MusicApplication) getApplication()).getApplicationComponent();
  }

  /**
   * Get an Activity module for dependency injection.
   *
   * @return {@link ru.petrushin.ya.music.presentation.di.modules.ActivityModule}
   */
  protected ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }
}
