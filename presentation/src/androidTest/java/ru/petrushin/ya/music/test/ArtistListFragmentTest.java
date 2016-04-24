/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.petrushin.ya.music.test;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import ru.petrushin.ya.music.R;
import ru.petrushin.ya.music.presentation.view.activity.MainActivity;
import ru.petrushin.ya.music.presentation.view.fragment.ArtistListFragment;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ArtistListFragmentTest extends ActivityInstrumentationTestCase2<MainActivity> {

  private MainActivity mainActivity;
  private ArtistListFragment artistListFragment;

  public ArtistListFragmentTest() {
    super(MainActivity.class);
  }

  @Override protected void setUp() throws Exception {
    super.setUp();
    this.setActivityIntent(createTargetIntent());
    mainActivity = getActivity();
    artistListFragment = (ArtistListFragment) mainActivity.getSupportFragmentManager()
        .findFragmentById(R.id.container);
  }

  @Override protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testContainsUserListFragment() {
    assertThat(artistListFragment, is(notNullValue()));
  }

  public void testContainsProperTitle() {
    String actualTitle = this.artistListFragment.getToolbar().getTitle().toString();
    assertThat(actualTitle, is("Исполнители"));
  }

  private Intent createTargetIntent() {
    return new Intent(getInstrumentation().getTargetContext(), MainActivity.class);
  }
}
