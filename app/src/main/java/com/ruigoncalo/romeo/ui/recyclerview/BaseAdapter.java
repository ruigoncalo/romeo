package com.ruigoncalo.romeo.ui.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruigoncalo on 28/02/16.
 */
public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<T> itemList;

    public BaseAdapter(Context context) {
        super();
        this.context = context;
        itemList = new ArrayList<>();
    }

    public Context getContext() {
        return context;
    }

    public void setItemList(List<T> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    public List<T> getItemList() {
        return itemList;
    }

    public void clear() {
        this.itemList.clear();
    }

    public abstract VH onCreateViewHolder(ViewGroup viewGroup, int viewType);

    public abstract void onBindViewHolder(RecyclerView.ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public T getItem(int position) {
        return itemList.get(position);
    }

    public void addItem(T item) {
        itemList.add(item);
        notifyItemInserted(itemList.size() - 1);
    }
}

