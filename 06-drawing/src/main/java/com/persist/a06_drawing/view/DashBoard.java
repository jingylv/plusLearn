package com.persist.a06_drawing.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.persist.a06_drawing.Utils;

/**
 * @author lvjingyuan
 * @date 2019/2/2
 * @describe 仪表盘 View
 */
public class DashBoard extends View {
    private static final float RADIUS = Utils.dp2px(150);
    private static final float LINE = Utils.dp2px(100);
    private static final int ANGLE = 120;
    private Paint paint;
    private Path dash;
    private PathDashPathEffect pathDashPathEffect;

    public DashBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.dp2px(2));

        dash = new Path();
        dash.addRect(0, 0, Utils.dp2px(2), Utils.dp2px(10), Path.Direction.CW);
        Path arc = new Path();
        arc.addArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                90 + ANGLE / 2, 360 - ANGLE);
        PathMeasure pathMeasure = new PathMeasure(arc, false);
        pathDashPathEffect = new PathDashPathEffect(dash,
                (pathMeasure.getLength() - Utils.dp2px(2)) / 20,
                0, PathDashPathEffect.Style.ROTATE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 画圆弧
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                90 + ANGLE / 2, 360 - ANGLE, false, paint);

        // 画刻度
        paint.setPathEffect(pathDashPathEffect);
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                90 + ANGLE / 2, 360 - ANGLE, false, paint);
        paint.setPathEffect(null);

        // 画指示线
        canvas.drawLine(getWidth() / 2, getHeight() / 2,
                (float) (Math.cos(Math.toRadians(getAngleFromMark(5))) * LINE) + getWidth() / 2,
                (float) (Math.sin(Math.toRadians(getAngleFromMark(5))) * LINE) + getHeight() / 2,
                paint);

    }

    /**
     * 获取指向的某个刻度处所对应的角度
     *
     * @param mark 指向的某个刻度
     * @return 角度
     */
    private int getAngleFromMark(int mark) {
        return (int) (90 + (float) ANGLE / 2 + (360 - (float) ANGLE) / 20 * mark);
    }
}
