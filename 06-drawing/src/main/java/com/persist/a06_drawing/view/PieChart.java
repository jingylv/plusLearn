package com.persist.a06_drawing.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.persist.a06_drawing.Utils;

/**
 * @author lvjingyuan
 * @date 2019/2/2
 * @describe 饼图
 */
public class PieChart extends View {
    private static final int RADIUS = (int) Utils.dp2px(150);
    private static final int PULL_OFF_LENGTH = (int) Utils.dp2px(15);
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF rectF = new RectF();
    private int[] angles = {50, 100, 80, 130};
    private int[] colors = {Color.parseColor("#FF6E41"), Color.parseColor("#349CF3"),
            Color.parseColor("#FF0011"), Color.parseColor("#02D0BE")};
    private int pullOffArc = 2;

    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        rectF.set(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int currentAngle = 0;
        for (int i = 0; i < angles.length; i++) {
            paint.setColor(colors[i]);
            canvas.save();
            if (i == pullOffArc) {
                canvas.translate((float) Math.cos(Math.toRadians(currentAngle + angles[i] / 2)) * PULL_OFF_LENGTH,
                        (float) Math.sin(Math.toRadians(currentAngle + angles[i] / 2)) * PULL_OFF_LENGTH);
            }
            canvas.drawArc(rectF, currentAngle, angles[i], true, paint);
            canvas.restore();
            currentAngle += angles[i];
        }
    }
}
