package com.persist.a07_change_camera_animation;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * @author lvjingyuan
 * @date 2019/3/28
 * @describe
 */
public class FlipView extends View {
    private static final float PADDING = Utils.dp2px(100);
    private static final float WIDTH = Utils.dp2px(200);

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Camera camera = new Camera();
    private float topFlip = 0;
    private float bottomFlip = 0;
    private float flipRotation = 0;

    public FlipView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        camera.setLocation(0, 0, Utils.getZForCamera());
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.translate(PADDING + WIDTH / 2, PADDING + WIDTH / 2);
        canvas.rotate(-flipRotation);
        camera.save();
        camera.rotateX(topFlip);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.clipRect(-WIDTH, -WIDTH, WIDTH, 0);
        canvas.rotate(flipRotation);
        canvas.translate(-(PADDING + WIDTH / 2), -(PADDING + WIDTH / 2));
        canvas.drawBitmap(Utils.getAvatar(getResources(), (int) WIDTH), PADDING, PADDING, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(PADDING + WIDTH / 2, PADDING + WIDTH / 2);
        canvas.rotate(-flipRotation);
        camera.save();
        camera.rotateX(bottomFlip);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.clipRect(-WIDTH, 0, WIDTH, WIDTH);
        canvas.rotate(flipRotation);
        canvas.translate(-(PADDING + WIDTH / 2), -(PADDING + WIDTH / 2));
        canvas.drawBitmap(Utils.getAvatar(getResources(), (int) WIDTH), PADDING, PADDING, paint);
        canvas.restore();
    }

    public float getTopFlip() {
        return topFlip;
    }

    public void setTopFlip(float topFlip) {
        this.topFlip = topFlip;
        invalidate();
    }

    public float getBottomFlip() {
        return bottomFlip;
    }

    public void setBottomFlip(float bottomFlip) {
        this.bottomFlip = bottomFlip;
        invalidate();
    }

    public float getFlipRotation() {
        return flipRotation;
    }

    public void setFlipRotation(float flipRotation) {
        this.flipRotation = flipRotation;
        invalidate();
    }
}
