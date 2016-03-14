package com.ruigoncalo.romeo.ui.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruigoncalo.romeo.R;
import com.ruigoncalo.romeo.imageloader.ImageManager;
import com.ruigoncalo.romeo.ui.viewmodel.SampleViewModel;

/**
 * Created by ruigoncalo on 13/03/16.
 */
public class SampleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
        ViewHolderRenderer<SampleViewModel> {

    private TextView labelSample;
    private ImageView imageSample;

    private ItemClickListener listener;
    private Context context;

    public SampleViewHolder(View itemView, ItemClickListener listener) {
        super(itemView);

        // TODO: check why is butterknife causing problems
        //ButterKnife.bind(this, itemView);

        labelSample = (TextView) itemView.findViewById(R.id.text_label_sample);
        imageSample = (ImageView) itemView.findViewById(R.id.image_sample);

        this.context = itemView.getContext();
        this.listener = listener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void render(SampleViewModel ad, int position) {
        labelSample.setText(ad.getLabel());
        ImageManager.getInstance().load(context, android.R.drawable.ic_media_play, imageSample);
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onItemClick(getAdapterPosition(), v);
        }
    }
}