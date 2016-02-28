package com.ruigoncalo.romeo.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruigoncalo on 20/02/16.
 */
public class Account {
    public final static String ID = "id";
    public final static String USERNAME = "username";
    public final static String IDENTIFIER = "identifier";
    public final static String DISPLAY_NAME = "display_name";
    public final static String IMAGE_URL = "image_url";

    public Account(){

    }

    public Account(String id, String username, String identifier, String displayName, String imageUrl){
        this.id = id;
        this.username = username;
        this.identifiers = new ArrayList<>();
        identifiers.add(identifier);
        this.displayName = displayName;
        this.imageUrl = imageUrl;
    }

    private String id;

    private String username;

    private List<String> identifiers;

    @SerializedName("display_name")
    private String displayName;

    @SerializedName("image_url")
    private String imageUrl;


    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getIdentifiers() {
        return identifiers;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isValid(){
        return id != null && !id.isEmpty() &&
                username != null && !username.isEmpty() &&
                displayName != null && !displayName.isEmpty() &&
                imageUrl != null && !imageUrl.isEmpty();
    }

}
