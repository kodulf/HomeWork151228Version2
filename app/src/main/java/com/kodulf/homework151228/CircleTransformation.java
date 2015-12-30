package com.kodulf.homework151228;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;

import com.squareup.picasso.Transformation;

/**
 * Created by XUE on 2015/12/29.
 */
public class CircleTransformation implements Transformation {

    @Override
    public Bitmap transform(Bitmap source) {
        Bitmap  result=Bitmap.createBitmap(source.getWidth(),source.getHeight(),Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));//
        int circleWidth = result.getWidth() / 2;
        int circleHeight = result.getHeight() / 2;
        new Canvas(result).drawCircle(circleWidth,circleHeight,circleHeight>circleWidth?circleWidth:circleHeight,paint);
        source.recycle();
        return result;
    }

    @Override
    public String key() {
        return "circle";
    }
}
