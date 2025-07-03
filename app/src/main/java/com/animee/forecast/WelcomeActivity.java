package com.animee.forecast;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;



import java.util.Random;


public class WelcomeActivity extends AppCompatActivity {
    ImageView mIVEntry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mIVEntry = findViewById(R.id.iv_entry);
        startAnim();

    }

    private void startAnim() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.scale);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                WelcomeActivity.this.finish();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mIVEntry.startAnimation(anim);
    }
}