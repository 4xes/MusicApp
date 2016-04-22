package ru.petrushin.ya.music.presentation.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import javax.inject.Inject;
import ru.petrushin.ya.music.R;
import ru.petrushin.ya.music.presentation.view.model.ArtistModel;

public class ArtistsAdapter extends ArrayRecyclerAdapter<ArtistModel, ArtistsAdapter.ArtistViewHolder> {

    private final LayoutInflater layoutInflater;
    private final Context context;

    @Inject public ArtistsAdapter(Activity context) {
        this.layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context.getApplicationContext();

    }

    @Override public long getItemId(int position) {
        return getItem(position).getArtistId();
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = this.layoutInflater.inflate(R.layout.row_artist, parent, false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder holder, int position) {
        final ArtistModel artistModel = getItem(position);
        holder.textViewName.setText(artistModel.getName() != null ? artistModel.getName() : "");
        holder.textViewGenres.setText(
            artistModel.getGenres() != null ? artistModel.getGenres() : "");
        String albums = context.getResources().getQuantityString(R.plurals.n_albums, artistModel.getAlbums(), artistModel.getAlbums());
        String tracks = context.getResources().getQuantityString(R.plurals.n_tracks, artistModel.getTracks(), artistModel.getTracks());
        String worksCount = context.getString(R.string.artist_row_works, albums, tracks);
        holder.textViewWorks.setText(worksCount);

        Glide.with(context)
                .load(artistModel.getCoverSmall())
                .centerCrop()
                .placeholder(R.drawable.loading_spinner)
                .crossFade()
                .into(holder.imageViewAvatar);
    }

    static class ArtistViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.avatar)
        ImageView imageViewAvatar;
        @Bind(R.id.name)
        TextView textViewName;
        @Bind(R.id.genres)
        TextView textViewGenres;
        @Bind(R.id.works)
        TextView textViewWorks;

        public ArtistViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
