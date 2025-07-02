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
import android.widget.ImageView;



import java.util.Random;


public class WelcomeActivity extends AppCompatActivity {
    ImageView mIVEntry;
    private static final int ANIM_TIME = 2000;

    private static final float SCALE_END = 1.15F;
    private static final int[] Imgs={
            R.drawable.welcomimg1,R.drawable.welcomimg2,
            R.drawable.welcomimg3,R.drawable.welcomimg4,
            R.drawable.welcomimg5, R.drawable.welcomimg6,
            R.drawable.welcomimg7,R.drawable.welcomimg8,
            R.drawable.welcomimg9,R.drawable.welcomimg10,
            R.drawable.welcomimg11,R.drawable.welcomimg12,};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //赋值mIVEntry,替代ButterKnife的定义
        mIVEntry = findViewById(R.id.iv_entry);
        //ButterKnife.bind(this);

        Random random = new Random(SystemClock.elapsedRealtime());//SystemClock.elapsedRealtime()
        // 从开机到现在的毫秒数（手机睡眠(sleep)的时间也包括在内）
        mIVEntry.setImageResource(Imgs[random.nextInt(Imgs.length)]);

        mIVEntry.postDelayed(() -> {
            startAnim(); // 1 秒后执行
        }, 1000);
    }


    private void startAnim() {

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mIVEntry, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mIVEntry, "scaleY", 1f, SCALE_END);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIM_TIME).play(animatorX).with(animatorY);
        set.start();

        set.addListener(new AnimatorListenerAdapter()
        {

            @Override
            public void onAnimationEnd(Animator animation)
            {

                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                WelcomeActivity.this.finish();
            }
        });
    }
}