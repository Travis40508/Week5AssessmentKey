package com.example.rodneytressler.week5assessmentkey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    protected RecyclerView recyclerView;

    private DogDatabase dogDatabase;

    private DogAdapter adapter;

    @OnClick(R.id.button_add_dog)
    protected void onAddDogClicked(View view) {
        Intent intent = new Intent(this, AddDogActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        buildDatabase();
    }

    private void buildDatabase() {
        dogDatabase = DogDatabase.getDatabase(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Dog> dogList = dogDatabase.dogDao().getAllDogs();
        adapter = new DogAdapter(dogList, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }
}
