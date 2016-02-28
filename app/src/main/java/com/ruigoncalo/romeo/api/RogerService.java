package com.ruigoncalo.romeo.api;

import com.ruigoncalo.romeo.model.Account;
import com.ruigoncalo.romeo.model.Challenge;
import com.ruigoncalo.romeo.model.Stream;
import com.ruigoncalo.romeo.model.Streams;
import com.ruigoncalo.romeo.model.UserAuth;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ruigoncalo on 20/02/16.
 */
public interface RogerService {

    @FormUrlEncoded
    @POST(Endpoints.OAUTH2_TOKEN)
    Call<UserAuth> refreshToken(@Field("refresh_token") String refreshToke,
                                @Query("api_version") String apiVersion,
                                @Query("grant_type") String grantType,
                                @Query("username") String username,
                                @Query("client_id") String clientId);

    @POST(Endpoints.CHALLENGE)
    Call<Challenge> postChallenge(@Query("identifier") String identifier);

    @POST(Endpoints.CHALLENGE_RESPOND)
    Call<UserAuth> postChallengeRespond(@Query("identifier") String identifier, @Query("secret") String secret);

    @Multipart
    @POST(Endpoints.STREAMS_ID_CHUNKS)
    Call<Stream> postChunk(@Path("stream_id") String streamId,
                           @Query("access_token") String accessToken,
                           @Query("duration") long duration,
                           @Part("audio\"; filename=\"message.3gp\"") RequestBody file);

    @GET(Endpoints.PROFILE_ME)
    Call<Account> getMyProfile(@Query("access_token") String accessToken);

    @GET(Endpoints.STREAMS_ID)
    Call<Stream> getStream(@Path("stream_id") String streamId, @Query("access_token") String accessToken);


    @GET(Endpoints.STREAMS)
    Call<Streams> getStreams(@Query("access_token") String accessToken);
}
