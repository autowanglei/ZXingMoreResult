package com.bandeng2.lilu.rqscan;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bandeng2.lilu.rqscan.utils.QRDirection;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.activity.ResultParcel;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tv_rsult;
    private EditText et_text;
    private ImageView image;

//    QRCodeMultiReader;
//    DecodeHandler

//    http://blog.csdn.net/cod0904/article/details/52779369

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_rsult = (TextView) findViewById(R.id.tv_result);
        et_text = (EditText) findViewById(R.id.et_text);
        image = (ImageView) findViewById(R.id.iv_image);

        // 这里是我们测试branch和pull request，所以加上这个注释，表示我们修改了代码

//        CaptureActivityHandler;
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.VIBRATE},
                100);

    }

    /**
     * 扫描二维码
     */
    public void scan( View view ) {
        // 开启ZXing库中可以扫描的二维码的Activity
        Intent intent = new Intent(this, CaptureActivity.class);
        // 要有返回结果
        startActivityForResult(intent, 1);
    }

    private String getDirectionStr( int direction ) {
        String directionStr = "未知";
        switch (direction) {
            case QRDirection.DIRECTION_UP:
                directionStr = "上↑";
                break;
            case QRDirection.DIRECTION_RIGHT:
                directionStr = "右→";
                break;
            case QRDirection.DIRECTION_DOWN:
                directionStr = "下↓";
                break;
            case QRDirection.DIRECTION_LEFT:
                directionStr = "左←";
                break;
            default:
                break;
        }
        return directionStr;
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data ) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            ArrayList<ResultParcel> resultParcels = data.getParcelableArrayListExtra(CaptureActivity.RESULT_LIST);
            String s = "";
            for (int i = 0; i < resultParcels.size(); i++) {
                int direction = QRDirection.getQRPicDirection(resultParcels.get(i).getResultPoints());
                s += "第" + i + "个结果：" + resultParcels.get(i).getText() + "\n方向：" + direction + " " + getDirectionStr
                        (direction) + "\n";
            }

//            ArrayList<String> result = data.getStringArrayListExtra("result");
//
////
//            for (int i = 0; i < resultParcels.size(); i++) {
//                s += "第" + i + "个结果：" + result.get(i) + "\n";
//            }

            tv_rsult.setText(s);
        }
    }

    /**
     * 生产二维码
     */
    public void create( View view ) {
        String result = et_text.getText().toString();
        if (!TextUtils.isEmpty(result)) {
            Bitmap bitmap = EncodingUtils.createQRCode(result, 500, 500,
                    BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
            image.setImageBitmap(bitmap);
        } else {
            // 提示
            Toast.makeText(this, "输入不能为空", Toast.LENGTH_LONG).show();

        }

    }

}
