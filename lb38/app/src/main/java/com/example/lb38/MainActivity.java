package com.example.lb38;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.divider.MaterialDividerItemDecoration;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<User> getSampleArrayList(){
        items = new ArrayList<>();
        items.add(new User("Владислав","Привет",R.drawable.work));
        items.add(new User("Мирослав","Hello",R.drawable.baseline_123));
        items.add(new User("Милан","Салют",R.drawable.baseline_10k));
        return items;
    }
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private ArrayList<User> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter = new UserAdapter(this,getSampleArrayList());
        recyclerView.setAdapter(adapter);

        MaterialDividerItemDecoration divider = new MaterialDividerItemDecoration(this,LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(divider);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
    }
    private  ArrayList<User> getNewData(ArrayList<User> oldData){
        ArrayList<User> newData = new ArrayList<>(oldData);
        newData.add(new User("Новенький","^_^",R.drawable.baseline_11mp));
        return newData;
    }
    private void refreshData(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.updateData(getNewData(items));
                swipeRefreshLayout.setRefreshing(false);
            }
        },2000);
    }

}