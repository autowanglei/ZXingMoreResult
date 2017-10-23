package com.xys.libzxing.zxing.activity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author wanglei
 * @date 2017/10/23 10:42
 * @description
 */
public class ResultPointParcel implements Parcelable {
    private final float x;
    private final float y;

    public ResultPointParcel( Parcel source ) {
        this.x = source.readFloat();
        this.y = source.readFloat();
    }

    public ResultPointParcel( float x, float y ) {
        this.x = x;
        this.y = y;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags ) {
        dest.writeFloat(x);
        dest.writeFloat(y);
    }

    public final float getX() {
        return this.x;
    }

    public final float getY() {
        return this.y;
    }

    public static final Parcelable.Creator<ResultPointParcel> CREATOR = new Creator<ResultPointParcel>() {

        @Override
        public ResultPointParcel[] newArray( int size ) {
            return new ResultPointParcel[size];
        }

        @Override
        public ResultPointParcel createFromParcel( Parcel source ) {
            return new ResultPointParcel(source);
        }
    };

}
