package com.example.labtest1;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button speedI,speedD,color;
    LinearLayout layout;
    int animationSpeed;
    TextView shape;
    int height ;
    int colors;
    int count = 0;
    int startspeed = 3000;
    ObjectAnimator animation;
    boolean click = false;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shape = findViewById(R.id.shape);
        layout = findViewById(R.id.layout);
        speedI = findViewById(R.id.speedI);
        speedD = findViewById(R.id.speedD);
        color = findViewById(R.id.color);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
         height = displayMetrics.heightPixels;
        int layoutWidth = layout.getWidth();
        animationSpeed = (layoutWidth * 1000) / 100;

        speedI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startspeed = startspeed - 300;
            }
        });
        speedD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startspeed = startspeed + 300;
            }
        });

        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rnd = new Random();
                colors = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                shape.setBackgroundColor(colors);
            }
        });

         moveUp();


         layout.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if (click == false) {
                     animation.pause();
                     click = true;

                 }else {
                     animation.resume();
                 }
                 return false;
             }
         });



    }

    public void moveUp(){
        animation = ObjectAnimator.ofFloat(shape, "translationY",0f, (float) height - 500);
        animation.setDuration(startspeed);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                moveDown();
                count++;
                shape.setText(String.valueOf(count));
                Random rnd = new Random();
                colors = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                shape.setBackgroundColor(colors);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animation.start();
    }

    public void moveDown(){
        animation = ObjectAnimator.ofFloat(shape, "translationY",(float) height - 500, 0f);
        animation.setDuration(startspeed);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
              moveUp();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animation.start();
    }
}
