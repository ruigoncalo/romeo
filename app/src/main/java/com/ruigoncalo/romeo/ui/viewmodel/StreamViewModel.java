package com.ruigoncalo.romeo.ui.viewmodel;

/**
 * Created by ruigoncalo on 28/02/16.
 */
public class StreamViewModel {
    private final String label;
    private final String imageUrl;

    public StreamViewModel(String label, String imageUrl) {
        this.label = label;
        this.imageUrl = imageUrl;
    }


    public String getLabel() {
        return label;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
