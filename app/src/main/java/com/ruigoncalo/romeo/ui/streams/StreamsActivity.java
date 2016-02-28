package com.ruigoncalo.romeo.ui.streams;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ruigoncalo.romeo.R;
import com.ruigoncalo.romeo.providers.DataProvider;
import com.ruigoncalo.romeo.ui.recyclerview.ItemClickListener;
import com.ruigoncalo.romeo.ui.recyclerview.StreamsAdapter;
import com.ruigoncalo.romeo.ui.viewmodel.StreamViewModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ruigoncalo on 28/02/16.
 */
public class StreamsActivity extends AppCompatActivity implements StreamsPresented,
        ItemClickListener {

    @Bind(R.id.list_streams) RecyclerView recyclerView;

    private StreamsPresenter presenter;
    private StreamsAdapter adapter;

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
        presenter.onStart(this);
        getStreams();
    }

    @Override
    protected void onStop() {
        presenter.onStop();
        super.onStop();
    }

    private void getStreams(){
        String auth = DataProvider.getInstance().getUserAuth(this).getAccessToken();
        presenter.getStreams(auth);
    }

    private void setupRecyclerView(){
        adapter = new StreamsAdapter(getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position, View view) {
        StreamViewModel streamViewModel = adapter.getItem(position);
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
}
