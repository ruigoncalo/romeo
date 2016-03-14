package com.ruigoncalo.romeo.ui.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruigoncalo.romeo.R;
import com.ruigoncalo.romeo.ui.viewmodel.SampleViewModel;

/**
 * Created by ruigoncalo on 13/03/16.
 */
public class SamplesAdapter extends BaseAdapter<SampleViewModel, SampleViewHolder> {

    private ItemClickListener itemClickListener;

    public SamplesAdapter(Context context) {
        super(context);
    }

    public void registerListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public void unregisterListener(){
        this.itemClickListener = null;
    }

    @Override
    public SampleViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sample, viewGroup, false);
        return new SampleViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SampleViewModel sampleViewModel = getItem(position);
        ((SampleViewHolder) holder).render(sampleViewModel, position);
    }
}