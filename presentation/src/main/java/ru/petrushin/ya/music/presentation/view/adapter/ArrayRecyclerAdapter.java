package ru.petrushin.ya.music.presentation.view.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class ArrayRecyclerAdapter<M, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {

    public static final String TAG = ArrayRecyclerAdapter.class.getSimpleName();

    private List<M> models = Collections.emptyList();

    public ArrayRecyclerAdapter() {
    }

    public void addAll(Collection<? extends M> collection) {
        if (collection != null) {
            this.models.addAll(collection);
            notifyDataSetChanged();
        }
    }

    public void set(Collection<M> collection){
        if(collection == null){
            throw new IllegalArgumentException("The collection cannot be null");
        }
        this.models = (List<M>) collection;
        this.notifyDataSetChanged();
    }

    public void add(M model) {
        this.models.add(model);
        notifyItemInserted(this.models.size());
    }

    public void add(int position, M model) {
        this.models.add(position, model);
        notifyItemInserted(position);
    }

    public void move(int fromPosition, int toPosition) {
        M model = this.models.remove(fromPosition);
        this.models.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    public M remove(int position) {
        M model = this.models.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void clear() {
        this.models.clear();
        notifyDataSetChanged();
    }

    public List<M> getItems() {
        return this.models;
    }

    public M getItem(int position) {
        return this.models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return (this.models != null)? this.models.size(): 0;
    }

}