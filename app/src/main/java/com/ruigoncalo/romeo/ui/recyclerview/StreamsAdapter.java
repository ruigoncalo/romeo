package com.ruigoncalo.romeo.ui.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruigoncalo.romeo.R;
import com.ruigoncalo.romeo.ui.viewmodel.StreamViewModel;

/**
 * Created by ruigoncalo on 28/02/16.
 */
public class StreamsAdapter extends BaseAdapter<StreamViewModel, StreamViewHolder> {

    private ItemClickListener itemClickListener;

    public StreamsAdapter(Context context) {
        super(context);
    }

    public void registerListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public void unregisterListener(){
        this.itemClickListener = null;
    }

    @Override
    public StreamViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_stream, viewGroup, false);
        return new StreamViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        StreamViewModel streamViewModel = getItem(position);
        ((StreamViewHolder) holder).render(streamViewModel, position);
    }
}
