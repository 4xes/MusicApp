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
package ru.petrushin.ya.music.test.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import junit.framework.TestCase;
import ru.petrushin.ya.music.domain.Artist;
import ru.petrushin.ya.music.domain.Cover;
import ru.petrushin.ya.music.presentation.mapper.ArtistModelMapper;
import ru.petrushin.ya.music.presentation.view.model.ArtistModel;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class UserModelDataMapperTest extends TestCase {

  private static final long FAKE_USER_ID = 99L;
  private static final String FAKE_NAME = "Yo Yo";
  private static final String FAKE_DESCRIPTION = "Famous music artist";
  private static final String FAKE_GENRES1 = "pop";
  private static final String FAKE_GENRES2 = "electronics";
  private static final String FAKE_AVATAR_SMALL = "http://www.yo-yo-music.club/avatar/small";
  private static final String FAKE_AVATAR_BIG = "http://www.yo-yo-music.club/avatar/big";
  private static final String FAKE_LINK = "http://www.yo-yo-music.club";

  private ArtistModelMapper artistModelMapper;

  @Override protected void setUp() throws Exception {
    super.setUp();
    artistModelMapper = new ArtistModelMapper();
  }

  public void testTransformUser() {
    Artist artist = createFakeArtist();
    ArtistModel artistModel = artistModelMapper.transform(artist);

    assertThat(artistModel, is(instanceOf(ArtistModel.class)));
    assertThat(artistModel.getArtistId(), is(FAKE_USER_ID));
    assertThat(artistModel.getGenres(), is(FAKE_GENRES1 + ", " + FAKE_GENRES2));
    assertThat(artistModel.getCoverSmall(), is(FAKE_AVATAR_SMALL));
    assertThat(artistModel.getCoverBig(), is(FAKE_AVATAR_BIG));
    assertThat(artistModel.getLink(), is(FAKE_LINK));
  }

  public void testTransformUserCollection() {
    Artist mockUserOne = mock(Artist.class);
    Artist mockUserTwo = mock(Artist.class);

    List<Artist> userList = new ArrayList<>(5);
    userList.add(mockUserOne);
    userList.add(mockUserTwo);

    Collection<ArtistModel> userModelList = artistModelMapper.transform(userList);

    assertThat(userModelList.toArray()[0], is(instanceOf(ArtistModel.class)));
    assertThat(userModelList.toArray()[1], is(instanceOf(ArtistModel.class)));
    assertThat(userModelList.size(), is(2));
  }

  private Artist createFakeArtist() {
    Artist user = new Artist(FAKE_USER_ID);
    user.setName(FAKE_NAME);
    user.setLink(FAKE_LINK);
    List<String> genres = new ArrayList<>();
    genres.add(FAKE_GENRES1);
    genres.add(FAKE_GENRES2);
    user.setGenres(genres);
    Cover cover = new Cover();
    cover.setSmall(FAKE_AVATAR_SMALL);
    cover.setBig(FAKE_AVATAR_BIG);
    user.setCover(cover);
    user.setDescription(FAKE_DESCRIPTION);

    return user;
  }
}
