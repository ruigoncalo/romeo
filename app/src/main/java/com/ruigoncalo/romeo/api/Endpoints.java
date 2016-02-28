package com.ruigoncalo.romeo.api;

/**
 * Created by ruigoncalo on 20/02/16.
 */
public class Endpoints {
    public static final String BASE_URL = "https://api.rogertalk.com";
    public static final String VERSION = "/v10";
    public static final String VERSION_NUMBER = "10";
    public static final String OAUTH2_TOKEN = "/oauth2/token";
    public static final String CHALLENGE = VERSION + "/challenge";
    public static final String CHALLENGE_RESPOND = VERSION + "/challenge/respond";
    public static final String STREAMS = VERSION + "/streams";
    public static final String STREAMS_ID = VERSION + "/streams/{stream_id}";
    public static final String STREAMS_ID_CHUNKS = VERSION + "/streams/{stream_id}/chunks";
    public static final String PROFILE_ME = VERSION + "/profile/me";
}
