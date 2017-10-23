package com.bandeng2.lilu.rqscan.utils;

import com.xys.libzxing.zxing.activity.ResultPointParcel;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author wanglei
 * @date 2017/10/18 10:54
 * @description
 */
public class QRDirection {

    public final static int DIRECTION_UP = 0;
    public final static int DIRECTION_RIGHT = 1;
    public final static int DIRECTION_DOWN = 2;
    public final static int DIRECTION_LEFT = 3;

    public static int getQRPicDirection( ArrayList<ResultPointParcel> points ) {
        float[] x = new float[points.size()];
        float[] y = new float[points.size()];
        for (int i = 0; i < points.size(); i++) {
            x[i] = points.get(i).getX();
            y[i] = points.get(i).getY();
        }
        FinderPatternIndex finderPatternIndex = new FinderPatternIndex();
        finderPatternIndex.x = QRDirection.getIndex(x, x[0]);
        finderPatternIndex.y = QRDirection.getIndex(y, y[0]);
        int direction = getQRPicDirection(finderPatternIndex);
        return direction;

    }

    /**
     * 0 ↑ 1 →  2 ↓ 3 ←
     */
    private static int getQRPicDirection( FinderPatternIndex finderPatternIndex ) {
        int direction = DIRECTION_UP;
        boolean isXSmall = isSmallvalue(finderPatternIndex.x);
        boolean isYSmall = isSmallvalue(finderPatternIndex.y);
        if (isXSmall && !isYSmall) {
            //x小 y 大
            direction = DIRECTION_UP;
        } else if (isXSmall && isYSmall) {
            //x小 y 小
            direction = DIRECTION_RIGHT;
        } else if (!isXSmall && isYSmall) {
            //x大 y 小
            direction = DIRECTION_DOWN;
        } else if (!isXSmall && !isYSmall) {
            //x大 y 大
            direction = DIRECTION_LEFT;
        }
        return direction;
    }

    private static boolean isSmallvalue( int value ) {
        return value <= 1;
    }

    private static int getIndex( float[] array, float value ) {
        java.util.Arrays.sort(array);
        int index = Arrays.binarySearch(array, value);
        return index;
    }

}
