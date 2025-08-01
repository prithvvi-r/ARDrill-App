package com.example.ardrillapp;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.Color;
import com.google.ar.sceneform.rendering.MaterialFactory;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.rendering.ShapeFactory;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class ARActivity extends AppCompatActivity {
    private ArFragment arFragment;
    private TextView instructionText;
    private String drillName;
    private AnchorNode currentAnchorNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);

        drillName = getIntent().getStringExtra("drill_name");

        initializeViews();
        setupARScene();
    }

    private void initializeViews() {
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);
        instructionText = findViewById(R.id.instructionText);
        instructionText.setText("Tap on ground to place " + drillName + " marker");
    }

    private void setupARScene() {
        arFragment.setOnTapArPlaneListener((HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
            // Remove previous object if exists
            if (currentAnchorNode != null) {
                arFragment.getArSceneView().getScene().removeChild(currentAnchorNode);
                currentAnchorNode.getAnchor().detach();
            }

            // Create anchor at tap location
            Anchor anchor = hitResult.createAnchor();
            currentAnchorNode = new AnchorNode(anchor);
            currentAnchorNode.setParent(arFragment.getArSceneView().getScene());

            // Create 3D object (colored cone for drill marker)
            MaterialFactory.makeOpaqueWithColor(this, new Color(android.graphics.Color.RED))
                    .thenAccept(material -> {
                        // Create a cube shape for the drill marker
                        Renderable cubeRenderable = ShapeFactory.makeCube(
                                new Vector3(0.05f, 0.1f, 0.05f),
                                new Vector3(0f, 0.05f, 0f),
                                material
                        );

                        // Create transformable node
                        TransformableNode drillNode = new TransformableNode(arFragment.getTransformationSystem());
                        drillNode.setParent(currentAnchorNode);
                        drillNode.setRenderable(cubeRenderable);
                        drillNode.select();

                        // Show success message
                        Toast.makeText(ARActivity.this, drillName + " marker placed!", Toast.LENGTH_SHORT).show();
                    })
                    .exceptionally(throwable -> {
                        Toast.makeText(ARActivity.this, "Error creating drill marker", Toast.LENGTH_SHORT).show();
                        return null;
                    });

        });
    }
}