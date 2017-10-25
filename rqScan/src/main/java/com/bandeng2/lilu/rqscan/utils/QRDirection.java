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

    /**
     * 根据前两个点连线角度，确定方向，二维坐标系中，x轴正方向为0度，顺时针增大到x轴负方向为180度，逆时针减小到x轴负方向为-180度
     *
     * @param points
     * @return
     */
    public static int getQRPicDirection( ArrayList<ResultPointParcel> points ) {
        double angle = Math.toDegrees(Math.atan2(points.get(1).getY() - points.get(0).getY(),
                points.get(1).getX() - points.get(0).getX()));
//        float[] x = new float[points.size()];
//        float[] y = new float[points.size()];
//        for (int i = 0; i < points.size(); i++) {
//            x[i] = points.get(i).getX();
//            y[i] = points.get(i).getY();
//        }
//        FinderPatternIndex finderPatternIndex = new FinderPatternIndex();
//        finderPatternIndex.x = QRDirection.getIndex(x, x[0]);
//        finderPatternIndex.y = QRDirection.getIndex(y, y[0]);
//        int direction = getQRPicDirection(finderPatternIndex);
        return changeAngle2Direction(angle);

    }

    private static int changeAngle2Direction( double angle ) {
        int direction = -1;
        if (angle > -135 && angle <= -45) {
            direction = DIRECTION_UP;
        } else if (angle > -45 && angle <= 45) {
            direction = DIRECTION_RIGHT;
        } else if (angle > 45 && angle <= 135) {
            direction = DIRECTION_DOWN;
        } else if ((angle > 135 && angle <= 180) || (angle >= -180 && angle <= -135)) {
            direction = DIRECTION_LEFT;
        }
        return direction;
    }

//    /**
//     * 0 ↑ 1 →  2 ↓ 3 ←
//     */
//    private static int getQRPicDirection( FinderPatternIndex finderPatternIndex ) {
//        int direction = DIRECTION_UP;
//        boolean isXSmall = isSmallvalue(finderPatternIndex.x);
//        boolean isYSmall = isSmallvalue(finderPatternIndex.y);
//        if (isXSmall && !isYSmall) {
//            //x小 y 大
//            direction = DIRECTION_UP;
//        } else if (isXSmall && isYSmall) {
//            //x小 y 小
//            direction = DIRECTION_RIGHT;
//        } else if (!isXSmall && isYSmall) {
//            //x大 y 小
//            direction = DIRECTION_DOWN;
//        } else if (!isXSmall && !isYSmall) {
//            //x大 y 大
//            direction = DIRECTION_LEFT;
//        }
//        return direction;
//    }
//
//    private static boolean isSmallvalue( int value ) {
//        return value <= 1;
//    }
//
//    private static int getIndex( float[] array, float value ) {
//        java.util.Arrays.sort(array);
//        int index = Arrays.binarySearch(array, value);
//        return index;
//    }

}
