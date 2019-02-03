package com.persist.a06_drawing;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * @author lvjingyuan
 * @date 2019/2/2
 * @describe 工具类
 */
public class Utils {

    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }
}
