package ru.petrushin.ya.music.domain.repository;

import java.util.List;
import ru.petrushin.ya.music.domain.Artist;
import rx.Observable;

public interface ArtistRepository {

  Observable<List<Artist>> artists();

  Observable<Artist> artist(final long artistId);

}
