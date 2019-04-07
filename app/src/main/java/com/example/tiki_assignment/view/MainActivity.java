package com.example.tiki_assignment.view;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.tiki_assignment.R;
import com.example.tiki_assignment.presenter.MainPresenter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter mMainPresenter;
    private RecyclerView mRcvKeyWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPresenter();

        // load data
        mMainPresenter.loadKeyWordData();
    }

    private void initView() {
        mRcvKeyWord = findViewById(R.id.rcvKeyWord);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRcvKeyWord.setLayoutManager(layoutManager);
    }

    private void initPresenter() {
        mMainPresenter = new MainPresenter(this, getBaseContext());
    }

    @Override
    public void displayKeywordList(@NonNull ArrayList<String> arrayList) {
        if (arrayList.size() == 0 ) {
            mRcvKeyWord.setVisibility(View.GONE);
        } else {
            mRcvKeyWord.setVisibility(View.VISIBLE);
            mRcvKeyWord.setAdapter(new KeyWordAdapter(arrayList, getBaseContext()));
        }
    }
}
