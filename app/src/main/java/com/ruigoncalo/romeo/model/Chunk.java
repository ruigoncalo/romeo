package com.ruigoncalo.romeo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ruigoncalo on 20/02/16.
 */
public class Chunk {

    private int id;
    private long start;
    private long end;
    @SerializedName("sender_id")
    private long senderId;
    @SerializedName("audio_url")
    private String audioUrl;
    private int duration;


    public int getId() {
        return id;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    public long getSenderId() {
        return senderId;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public int getDuration() {
        return duration;
    }
}
