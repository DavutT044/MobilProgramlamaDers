package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ekran2 extends AppCompatActivity {
    ListView listView;
    ArrayList<String> dataList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ekran2);

        Button btnBack = findViewById(R.id.btnBack);
        listView = findViewById(R.id.lvEkran);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);

        Intent intent = getIntent();
        String veri = intent.getStringExtra("veriAnahtari");

        Intent intent2 = getIntent();
        long time = intent2.getLongExtra("time",0);

        if (veri != null) {
            dataList.add(veri + " " + time);
            adapter.notifyDataSetChanged();
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataList.size() == 3) {
                    System.out.println("Oyun bitti!");
                }else {
                    Intent intent = new Intent(ekran2.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}