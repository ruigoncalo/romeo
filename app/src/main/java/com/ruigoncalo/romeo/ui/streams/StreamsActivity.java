package com.ruigoncalo.romeo.ui.streams;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ruigoncalo.romeo.R;
import com.ruigoncalo.romeo.providers.AudioProvider;
import com.ruigoncalo.romeo.providers.DataProvider;
import com.ruigoncalo.romeo.ui.recyclerview.ItemClickListener;
import com.ruigoncalo.romeo.ui.recyclerview.SamplesAdapter;
import com.ruigoncalo.romeo.ui.recyclerview.StreamsAdapter;
import com.ruigoncalo.romeo.ui.viewmodel.SampleViewModel;
import com.ruigoncalo.romeo.ui.viewmodel.StreamViewModel;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ruigoncalo on 28/02/16.
 */
public class StreamsActivity extends AppCompatActivity implements StreamsPresented,
        AudioProvider.AudioCallback {

    @Bind(R.id.list_streams) RecyclerView recyclerViewStreams;
    @Bind(R.id.list_samples) RecyclerView recyclerViewSamples;
    @Bind(R.id.text_summary) TextView summaryText;

    private StreamsPresenter presenter;
    private StreamsAdapter streamsAdapter;
    private SamplesAdapter samplesAdapter;
    private StreamViewModel selectedStream;
    private SampleViewModel selectedSample;

    private ItemClickListener streamItemClickListener = new ItemClickListener() {
        @Override
        public void onItemClick(int position, View view) {
            StreamViewModel streamViewModel = streamsAdapter.getItem(position);
            selectedStream = streamViewModel;
            setSummaryText();
        }
    };

    private ItemClickListener sampleItemClickListener = new ItemClickListener() {
        @Override
        public void onItemClick(int position, View view) {
            SampleViewModel sampleViewModel = samplesAdapter.getItem(position);
            selectedSample = sampleViewModel;
            setSummaryText();
            playSample(sampleViewModel.getSampleUrl());
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streams);
        ButterKnife.bind(this);
        setupRecyclerView();

        presenter = new StreamsPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        AudioProvider.getInstance().registerCallback(this);
        presenter.onStart(this);
        streamsAdapter.registerListener(streamItemClickListener);
        samplesAdapter.registerListener(sampleItemClickListener);
        getStreams();
        getSamples();
    }

    @Override
    protected void onStop() {
        AudioProvider.getInstance().unregisterCallback();
        presenter.onStop();
        streamsAdapter.unregisterListener();
        streamsAdapter.clear();
        samplesAdapter.unregisterListener();
        samplesAdapter.clear();
        super.onStop();
    }

    private void getStreams(){
        String auth = DataProvider.getInstance().getUserAuth(this).getAccessToken();
        presenter.getStreams(auth);
    }

    private void getSamples(){
        presenter.getSamples();
    }

    private void setupRecyclerView(){
        streamsAdapter = new StreamsAdapter(getApplicationContext());
        LinearLayoutManager layoutManagerStreams = new LinearLayoutManager(this);
        layoutManagerStreams.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewStreams.setLayoutManager(layoutManagerStreams);
        recyclerViewStreams.setHasFixedSize(true);
        recyclerViewStreams.setAdapter(streamsAdapter);

        samplesAdapter = new SamplesAdapter(getApplicationContext());
        LinearLayoutManager layoutManagerSamples = new LinearLayoutManager(this);
        layoutManagerSamples.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewSamples.setLayoutManager(layoutManagerSamples);
        recyclerViewSamples.setHasFixedSize(true);
        recyclerViewSamples.setAdapter(samplesAdapter);
    }


    @Override
    public void isLoading(boolean loading) {

    }

    @Override
    public void showStreamsSuccess(List<StreamViewModel> list) {
        streamsAdapter.setItemList(list);
    }

    @Override
    public void showStreamsError() {

    }

    @Override
    public void showSamplesSuccess(List<SampleViewModel> list) {
        samplesAdapter.setItemList(list);
    }

    @Override
    public void showSamplesError() {

    }

    @OnClick(R.id.button_send)
    public void onSendButtonClick(){
        if(selectedStream == null || selectedSample == null){
            Toast.makeText(this, "Choose a stream and sample", Toast.LENGTH_LONG).show();
        } else {
            downloadSample();
        }
    }

    @Override
    public void fileSentSuccess() {
        Toast.makeText(this, "Yey", Toast.LENGTH_LONG).show();
    }

    @Override
    public void fileSentError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(String type, Exception exception) {
        Log.d("StreamActivity", "Audio Error: " + type);
    }

    @Override
    public void onDownloadSuccess(File file){
        if(file != null) {
            presenter.sendChunkMessage(
                    DataProvider.getInstance().getUserAuth(this).getAccessToken(),
                    selectedStream.getId(),
                    file,
                    39000);
        }
    }

    @Override
    public void onDownloadError(){

    }


    private void playSample(String url){
        AudioProvider.getInstance().play(this, url);
    }

    private void setSummaryText(){
        String text = "Send " + (selectedSample == null ? "<sample>" : selectedSample.getLabel()) + " to "
                + (selectedStream == null ? "<contact>" : selectedStream.getLabel());
        summaryText.setText(text);
    }

    private void downloadSample(){
        presenter.downloadSample(selectedSample.getSampleUrl());
    }
}
