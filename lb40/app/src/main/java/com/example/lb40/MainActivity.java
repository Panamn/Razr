package com.example.lb40;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        PutSharedPreferences();
        GetSharedPreferences();


    }
    private void  PutSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences("UserData", MODE_PRIVATE); //Для сохранения данных
        SharedPreferences.Editor puteditor = preferences.edit();
        puteditor.putString("name", "Иван Иванович");
        puteditor.putInt("age", 25);
        puteditor.putBoolean("isSubscribed", true);
        puteditor.apply();
    }
    private void  GetSharedPreferences() {
        SharedPreferences getSharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        String name = getSharedPreferences.getString("name", "Unknown");
        int age = getSharedPreferences.getInt("age",0);
        boolean isSubscribed = getSharedPreferences.getBoolean("isSubscribed", false);
    }
    private void  DelSharedPreferences() {
        SharedPreferences delSharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor deleditor = delSharedPreferences.edit();
        deleditor.remove("age");
        /*deleditor.apply();*/
        deleditor.clear();
    }
}