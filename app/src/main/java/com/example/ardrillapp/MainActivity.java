package com.example.ardrillapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ardrillapp.Drill;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Spinner drillSpinner;
    private Button btnViewDrill;
    private List<Drill> drillList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupDrillData();
        setupSpinner();
        setupClickListeners();
    }

    private void initializeViews() {
        drillSpinner = findViewById(R.id.drillSpinner);
        btnViewDrill = findViewById(R.id.btnViewDrill);
    }

    private void setupDrillData() {
        drillList = new ArrayList<>();
        drillList.add(new Drill(
                "Basic Drilling",
                "A fundamental drilling technique for beginners. This drill focuses on proper stance and basic movements.",
                "• Keep your feet shoulder-width apart\n• Maintain steady breathing\n• Start slowly and increase speed gradually",
                android.R.drawable.ic_menu_camera // Using system drawable as placeholder
        ));

        drillList.add(new Drill(
                "Advanced Precision",
                "Advanced drilling technique focusing on accuracy and precision movements.",
                "• Focus on target accuracy\n• Use controlled movements\n• Practice regularly for best results",
                android.R.drawable.ic_menu_gallery
        ));

        drillList.add(new Drill(
                "Speed Training",
                "High-intensity drill designed to improve speed and reaction time.",
                "• Warm up properly before starting\n• Maintain form even at high speeds\n• Take breaks to avoid fatigue",
                android.R.drawable.ic_menu_info_details
        ));
    }

    private void setupSpinner() {
        List<String> drillNames = new ArrayList<>();
        for (Drill drill : drillList) {
            drillNames.add(drill.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, drillNames
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        drillSpinner.setAdapter(adapter);
    }

    private void setupClickListeners() {
        btnViewDrill.setOnClickListener(v -> {
            int selectedPosition = drillSpinner.getSelectedItemPosition();
            Drill selectedDrill = drillList.get(selectedPosition);

            Intent intent = new Intent(MainActivity.this, DrillDetailActivity.class);
            intent.putExtra("drill_name", selectedDrill.getName());
            intent.putExtra("drill_description", selectedDrill.getDescription());
            intent.putExtra("drill_tips", selectedDrill.getTips());
            intent.putExtra("drill_image", selectedDrill.getImageResource());
            startActivity(intent);
        });
    }
}