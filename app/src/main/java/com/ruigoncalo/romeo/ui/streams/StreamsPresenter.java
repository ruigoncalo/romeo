package com.ruigoncalo.romeo.ui.streams;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ruigoncalo.romeo.api.ApiService;
import com.ruigoncalo.romeo.model.Account;
import com.ruigoncalo.romeo.model.Stream;
import com.ruigoncalo.romeo.model.Streams;
import com.ruigoncalo.romeo.ui.Presenter;
import com.ruigoncalo.romeo.ui.viewmodel.StreamViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ruigoncalo on 28/02/16.
 */
public class StreamsPresenter extends Presenter<StreamsPresented> {

    @Override
    public void onStart(StreamsPresented presented) {
        super.onStart(presented);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void getStreams(String authorization) {
        if (getPresented() != null) {
            getPresented().isLoading(true);
        }

        ApiService.getInstance().getStreams(authorization, new Callback<Streams>() {
            @Override
            public void onResponse(Call<Streams> call, Response<Streams> response) {
                if (getPresented() != null) {
                    if(response.body() != null) {
                        List<StreamViewModel> list = convert(response.body().getData());
                        getPresented().showStreamsSuccess(list);
                    } else {
                        getPresented().showStreamsError();
                    }
                }
            }

            @Override
            public void onFailure(Call<Streams> call, Throwable t) {
                if (getPresented() != null) {
                    getPresented().showStreamsError();
                }
            }
        });
    }

    @NonNull
    private List<StreamViewModel> convert(@Nullable List<Stream> list) {
        List<StreamViewModel> result = new ArrayList<>();

        if (list != null) {
            for (Stream stream : list) {
                StreamViewModel streamViewModel = createViewModel(stream);
                if (streamViewModel != null) {
                    result.add(streamViewModel);
                }
            }
        }

        return result;
    }

    @Nullable
    private StreamViewModel createViewModel(@NonNull Stream stream) {
        StreamViewModel result = null;
        if (stream.isValid()) {
            String label = getLabel(stream);
            result = new StreamViewModel(label, stream.getImageUrl());
        }

        return result;
    }

    @NonNull
    private String getLabel(Stream stream){
        String result;
        if(stream.getTitle() != null){
            result = stream.getTitle();
        } else {
            result = getDisplayNameShortForm(stream.getOthers());
        }

        return result;
    }

    @NonNull
    private String getDisplayNameShortForm(List<Account> accounts){
        String result = "";
        if(accounts.size() > 0){
            Account first = accounts.get(0);
            if(first.isValid()){
                result = first.getDisplayName();
                if(accounts.size() > 1){
                    result += "+";
                }
            }
        }

        return result;
    }
}
