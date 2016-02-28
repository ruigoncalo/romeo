package com.ruigoncalo.romeo.ui.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruigoncalo.romeo.R;
import com.ruigoncalo.romeo.imageloader.ImageManager;
import com.ruigoncalo.romeo.ui.viewmodel.StreamViewModel;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ruigoncalo on 28/02/16.
 */
public class StreamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
        ViewHolderRenderer<StreamViewModel> {

    @Bind(R.id.text_label_stream) TextView labelStream;
    @Bind(R.id.image_stream) ImageView imageStream;

    private ItemClickListener listener;
    private Context context;

    public StreamViewHolder(View itemView, ItemClickListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.context = itemView.getContext();
        this.listener = listener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void render(StreamViewModel ad, int position) {
        labelStream.setText(ad.getLabel());

        if(ad.getImageUrl() != null) {
            ImageManager.getInstance().load(context, ad.getImageUrl(), imageStream);
        } else {
            ImageManager.getInstance().load(context, R.mipmap.ic_launcher, imageStream);
        }
    }

    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onItemClick(getAdapterPosition(), v);
        }
    }
}
