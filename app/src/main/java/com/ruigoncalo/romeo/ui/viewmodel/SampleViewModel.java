package com.ruigoncalo.romeo.ui.viewmodel;

/**
 * Created by ruigoncalo on 13/03/16.
 */
public class SampleViewModel {

    private final String id;
    private final String label;
    private final String imageUrl;
    private final String sampleUrl;

    public SampleViewModel(String id, String label, String imageUrl, String sampleUrl) {
        this.id = id;
        this.label = label;
        this.imageUrl = imageUrl;
        this.sampleUrl = sampleUrl;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getSampleUrl() {
        return sampleUrl;
    }
}
