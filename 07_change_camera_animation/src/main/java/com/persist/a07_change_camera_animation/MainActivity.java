package com.persist.a07_change_camera_animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AnimationSet;

public class MainActivity extends AppCompatActivity {
    private FlipView flipView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flipView = findViewById(R.id.flipView);

        ObjectAnimator bottomAnimator = ObjectAnimator.ofFloat(flipView, "bottomFlip", 45);
        bottomAnimator.setDuration(1500);

        ObjectAnimator flipRotationAnimator = ObjectAnimator.ofFloat(flipView, "flipRotation", 270);
        flipRotationAnimator.setDuration(2000);

        ObjectAnimator topAnimator = ObjectAnimator.ofFloat(flipView, "topFlip", -45);
        topAnimator.setDuration(1500);

        ObjectAnimator lastBottomAnimator = ObjectAnimator.ofFloat(flipView, "bottomFlip", 0);
        lastBottomAnimator.setDuration(1000);

        ObjectAnimator lastFirstAnimator = ObjectAnimator.ofFloat(flipView, "topFlip", 0);
        lastFirstAnimator.setDuration(1000);

        AnimatorSet lastAnimatorSet = new AnimatorSet();
        lastAnimatorSet.playTogether(lastBottomAnimator, lastFirstAnimator);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setStartDelay(2000);
        animatorSet.playSequentially(bottomAnimator, flipRotationAnimator, topAnimator, lastAnimatorSet);
        animatorSet.start();
    }
}
