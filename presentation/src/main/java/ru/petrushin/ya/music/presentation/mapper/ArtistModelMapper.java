package ru.petrushin.ya.music.presentation.mapper;

import java.util.List;
import javax.inject.Inject;
import ru.petrushin.ya.music.domain.Artist;
import ru.petrushin.ya.music.presentation.di.PerActivity;
import ru.petrushin.ya.music.presentation.view.model.ArtistModel;

/**
 * Mapper class used to transform {@link Artist} in domain layer
 * to {@link ArtistModel} in the presentation layer
 */

@PerActivity
public class ArtistModelMapper {

  @Inject
  public ArtistModelMapper() {
  }

  /**
   * Transform a {@link Artist} into an {@link ArtistModel}.
   *
   * @param artist Object to be transformed.
   * @return {@link ArtistModel}.
   */
  public ArtistModel transform(Artist artist) {
    if (artist == null) {
      throw new IllegalArgumentException("Cannot transform a null value");
    }
    ArtistModel artistModel = new ArtistModel(artist.getArtistId());
    artistModel.setName(artist.getName());
    if(artist.getGenres() != null){
      StringBuilder builder = new StringBuilder();
      List<String> genres = artist.getGenres();
      for(int i = 0; i < genres.size(); i++){
        builder.append(genres.get(i));
        if(i < genres.size() - 1){
          builder.append(", ");
        }
      }
      artistModel.setGenres(builder.toString());
    }else{
      artistModel.setGenres("");
    }
    artistModel.setTracks(artist.getTracks());
    artistModel.setAlbums(artist.getAlbums());
    if(artist.getCover() != null){
      artistModel.setCoverSmall(artist.getCover().getSmall());
      artistModel.setCoverBig(artist.getCover().getBig());
    }
    artistModel.setLink(artist.getLink());
    artistModel.setDescription(artist.getDescription());


    return artistModel;
  }
}
