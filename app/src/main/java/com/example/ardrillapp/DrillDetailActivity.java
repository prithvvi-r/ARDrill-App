package com.example.ardrillapp;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DrillDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drill_detail);

        // Get data from intent
        String drillName = getIntent().getStringExtra("drill_name");
        String drillDescription = getIntent().getStringExtra("drill_description");
        String drillTips = getIntent().getStringExtra("drill_tips");
        int drillImage = getIntent().getIntExtra("drill_image", android.R.drawable.ic_menu_camera);

        // Initialize views
        ImageView imageView = findViewById(R.id.drillImage);
        TextView nameView = findViewById(R.id.drillName);
        TextView descriptionView = findViewById(R.id.drillDescription);
        TextView tipsView = findViewById(R.id.drillTips);
        Button btnStartAR = findViewById(R.id.btnStartAR);

        // Set data to views
        imageView.setImageResource(drillImage);
        nameView.setText(drillName);
        descriptionView.setText(drillDescription);
        tipsView.setText(drillTips);

        // Start AR activity
        btnStartAR.setOnClickListener(v -> {
            Intent intent = new Intent(DrillDetailActivity.this, ARActivity.class);
            intent.putExtra("drill_name", drillName);
            startActivity(intent);
        });
    }
}