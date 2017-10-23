package com.xys.libzxing.zxing.activity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.zxing.ResultPoint;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wanglei
 * @date 2017/10/23 10:01
 * @description
 */
public class ResultParcel implements Parcelable {
    private String text;
    private ArrayList<ResultPointParcel> resultPoints = new ArrayList<ResultPointParcel>();
//    private ResultPoint[] resultPoints;

    public ResultParcel( String text, ArrayList<ResultPointParcel> resultPoints ) {
        this.text = text;
        this.resultPoints = resultPoints;
    }

    public ResultParcel( Parcel source ) {
        text = source.readString();
        if (resultPoints != null) {
            source.readList(resultPoints, ResultPoint.class.getClassLoader());
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags ) {
        dest.writeString(text);
        dest.writeList(resultPoints);
    }

    public String getText() {
        return text;
    }

    public ArrayList<ResultPointParcel> getResultPoints() {
        return resultPoints;
    }

    public static final Parcelable.Creator<ResultParcel> CREATOR = new Creator<ResultParcel>() {

        @Override
        public ResultParcel[] newArray( int size ) {
            return new ResultParcel[size];
        }

        @Override
        public ResultParcel createFromParcel( Parcel source ) {
            return new ResultParcel(source);
        }
    };

}
