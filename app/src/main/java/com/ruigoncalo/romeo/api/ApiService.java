package com.ruigoncalo.romeo.api;

import com.ruigoncalo.romeo.model.Account;
import com.ruigoncalo.romeo.model.Challenge;
import com.ruigoncalo.romeo.model.Stream;
import com.ruigoncalo.romeo.model.Streams;
import com.ruigoncalo.romeo.model.UserAuth;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by ruigoncalo on 20/02/16.
 */
public class ApiService {
    private static ApiService ourInstance = new ApiService();
    private RogerService rogerService;

    public static ApiService getInstance() {
        return ourInstance;
    }

    private ApiService() {
        init();
    }

    private void init() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Endpoints.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        rogerService = retrofit.create(RogerService.class);
    }

    public void refreshToken(String refreshToken, String username, String clientId, Callback<UserAuth> callback){
        Call<UserAuth> call = rogerService.refreshToken(refreshToken, Endpoints.VERSION_NUMBER, "refresh_token", username, clientId);
        call.enqueue(callback);
    }

    public void requestChallenge(String identifier, Callback<Challenge> callback) {
        Call<Challenge> call = rogerService.postChallenge(identifier);
        call.enqueue(callback);
    }

    public void requestAuth(String identifier, String secret, Callback<UserAuth> callback) {
        Call<UserAuth> call = rogerService.postChallengeRespond(identifier, secret);
        call.enqueue(callback);
    }

    public void uploadChunk(String authorization, String streamId, long duration, RequestBody file, Callback<Stream> callback){
        Call<Stream> call = rogerService.postChunk(streamId, authorization, duration, file);
        call.enqueue(callback);
    }

    public void getMyProfile(String authorization, Callback<Account> callback){
        Call<Account> call = rogerService.getMyProfile(authorization);
        call.enqueue(callback);
    }

    public void getStream(String authorization, String streamId, Callback<Stream> callback){
        Call<Stream> call = rogerService.getStream(streamId, authorization);
        call.enqueue(callback);
    }

    public void getStreams(String authorization, Callback<Streams> callback){
        Call<Streams> call = rogerService.getStreams(authorization);
        call.enqueue(callback);
    }
}
