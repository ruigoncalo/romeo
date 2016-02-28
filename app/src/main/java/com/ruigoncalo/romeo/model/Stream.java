package com.ruigoncalo.romeo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ruigoncalo on 20/02/16.
 */
public class Stream {

    private String id;
    private long created;
    private List<Chunk> chunks;
    @SerializedName("image_url")
    private String imageUrl;
    private String title;
    private long joined;
    @SerializedName("last_played_from")
    private long lastPlayedFrom;
    @SerializedName("last_interaction")
    private long lastInteraction;
    @SerializedName("total_duration")
    private long totalDuration;
    private boolean visible;
    @SerializedName("others_listened")
    private long others_listened;
    private List<Account> others;


    public String getId() {
        return id;
    }

    public long getCreated() {
        return created;
    }

    public List<Chunk> getChunks() {
        return chunks;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public long getJoined() {
        return joined;
    }

    public long getLastPlayedFrom() {
        return lastPlayedFrom;
    }

    public long getLastInteraction() {
        return lastInteraction;
    }

    public long getTotalDuration() {
        return totalDuration;
    }

    public boolean isVisible() {
        return visible;
    }

    public long getOthers_listened() {
        return others_listened;
    }

    public List<Account> getOthers() {
        return others;
    }

    public boolean isValid(){
        return chunks != null && others != null;
    }
}
