package com.ruigoncalo.romeo.ui.streams;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ruigoncalo.romeo.R;
import com.ruigoncalo.romeo.providers.AudioProvider;
import com.ruigoncalo.romeo.providers.DataProvider;
import com.ruigoncalo.romeo.ui.recyclerview.ItemClickListener;
import com.ruigoncalo.romeo.ui.recyclerview.StreamsAdapter;
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
        ItemClickListener, AudioProvider.AudioCallback {

    @Bind(R.id.list_streams) RecyclerView recyclerView;
    @Bind(R.id.text_selected_stream) TextView textSelectedStream;

    private StreamsPresenter presenter;
    private StreamsAdapter adapter;
    private StreamViewModel selectedStream;

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
        adapter.registerListener(this);
        getStreams();
    }

    @Override
    protected void onStop() {
        AudioProvider.getInstance().unregisterCallback();
        presenter.onStop();
        adapter.unregisterListener();
        adapter.clear();
        super.onStop();
    }

    private void getStreams(){
        String auth = DataProvider.getInstance().getUserAuth(this).getAccessToken();
        presenter.getStreams(auth);
    }

    private void setupRecyclerView(){
        adapter = new StreamsAdapter(getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position, View view) {
        StreamViewModel streamViewModel = adapter.getItem(position);
        textSelectedStream.setText(streamViewModel.getLabel());
        selectedStream = streamViewModel;
    }

    @Override
    public void isLoading(boolean loading) {

    }

    @Override
    public void showStreamsSuccess(List<StreamViewModel> list) {
        adapter.setItemList(list);
    }

    @Override
    public void showStreamsError() {

    }

    @OnClick(R.id.button_send)
    public void onSendButtonClick(){
        if(selectedStream == null){
            Toast.makeText(this, "Choose a stream", Toast.LENGTH_LONG).show();
        } else {

            File file = AudioProvider.getInstance().getLocalAudioFile(this, "bitch.mp3");

            if(file != null) {
                presenter.sendChunkMessage(
                        DataProvider.getInstance().getUserAuth(this).getAccessToken(),
                        selectedStream.getId(),
                        file,
                        39000);
            }
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
}
