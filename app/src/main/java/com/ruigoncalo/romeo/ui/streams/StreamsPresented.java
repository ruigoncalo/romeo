package com.ruigoncalo.romeo.ui.streams;

import com.ruigoncalo.romeo.ui.viewmodel.SampleViewModel;
import com.ruigoncalo.romeo.ui.viewmodel.StreamViewModel;

import java.io.File;
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
    void showSamplesSuccess(List<SampleViewModel> list);
    void showSamplesError();
    void onDownloadSuccess(File file);
    void onDownloadError();
}
