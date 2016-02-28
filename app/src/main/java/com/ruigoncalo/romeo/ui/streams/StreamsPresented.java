package com.ruigoncalo.romeo.ui.streams;

import com.ruigoncalo.romeo.ui.viewmodel.StreamViewModel;

import java.util.List;

/**
 * Created by ruigoncalo on 28/02/16.
 */
public interface StreamsPresented {
    void isLoading(boolean loading);
    void showStreamsSuccess(List<StreamViewModel> list);
    void showStreamsError();
    void fileSentSuccess();
    void fileSentError(String error);
}
