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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;

public class ArtistDetailFragmentTest extends ActivityInstrumentationTestCase2<MainActivity> {

  private static final int ARTIST_ID = 1080505;

  private MainActivity mainActivity;

  public ArtistDetailFragmentTest() {
    super(MainActivity.class);
  }

  @Override protected void setUp() throws Exception {
    super.setUp();
    this.setActivityIntent(createTargetIntent());
    this.mainActivity = getActivity();
    this.mainActivity.showArtist(ARTIST_ID);
  }

  @Override protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testLoadArtistHappyCaseViews() {
    onView(withId(R.id.retry)).check(matches(not(isDisplayed())));
    onView(withId(R.id.retry_text)).check(matches(not(isDisplayed())));
    onView(withId(R.id.progress)).check(matches(not(isDisplayed())));

    onView(withId(R.id.fabOpenLink)).check(matches(isDisplayed()));
    onView(withId(R.id.genres)).check(matches(isDisplayed()));
    onView(withId(R.id.works)).check(matches(isDisplayed()));
    onView(withId(R.id.description)).check(matches(isDisplayed()));
  }

  public void testLoadArtistHappyCaseData() {
    onView(withId(R.id.genres)).check(matches(withText("pop, dance, electronics")));
    onView(withId(R.id.description)).check(
        matches(withText(startsWith("шведская певица и автор песен."))));
  }

  private Intent createTargetIntent() {
    return new Intent(getInstrumentation().getTargetContext(), MainActivity.class);
  }
}
